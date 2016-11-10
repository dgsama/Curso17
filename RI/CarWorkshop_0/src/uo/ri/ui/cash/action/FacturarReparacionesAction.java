package uo.ri.ui.cash.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class FacturarReparacionesAction implements Action {

	private static final String precio_repuestos = "SELECT SUM(s.cantidad * r.precio)"
			+ " FROM TSustituciones s, TRepuestos r " + "WHERE s.repuesto_id = r.id "
			+ "AND s.intervencion_averia_id = ?";

	private static final String precio_obra = "SELECT SUM(i.minutos * tv.precioHora / 60) "
			+ "FROM TAverias a, TIntervenciones i, TVehiculos v, TTiposVehiculo tv " + "WHERE i.averia_id = a.id "
			+ "AND a.vehiculo_id = v.id " + "AND v.tipo_id = tv.id " + "AND a.id = ? " + "AND a.status = 'TERMINADA'";

	private static final String precio_averia = "UPDATE TAverias SET importe = ? WHERE id = ?";

	private static final String id_ultima_factura = "SELECT MAX(numero) FROM TFacturas";

	private static final String crear_factura = "INSERT INTO TFacturas(numero, fecha, iva, importe, status) VALUES(?, ?, ?, ?, ?)";

	private static final String link_averia_factura = "UPDATE TAverias SET factura_id = ? WHERE id = ?";

	private static final String actualizar_estado = "UPDATE TAverias SET status = ? WHERE id = ?";

	private static final String clave = "SELECT id FROM TFacturas WHERE numero = ?";

	private Connection connection;
	private ArrayList<Long> averias;
	private PreparedStatement prST = null;
	private ResultSet rs = null;

	/**
	 * Proceso:
	 * 
	 * - Pide los ids de las averías a incluir en la factura. El usuario, antes,
	 * habrá listado las averias no facturadas del cliente y tendrá en pantalla,
	 * a la vista, los ids de las averías. - Verifica que las averías se pueden
	 * facturar (status 'TERMINADA') - Genera un nuevo número de factura (el
	 * último registrado + 1) - Calcula el importe de cada una de las averias y
	 * lo acumula al total de la factura - Calcula lo que corresponde por IVA
	 * (cuidado con la fecha) - Calcula el total (importe + IVA) y lo redondea a
	 * dos decimales (Round.twoCents( x )) - Establece como fecha de factura la
	 * del instante - Registra la factura en BDD - Vincula las averias con la
	 * factura generada - Pone el status de las averias a 'FACTURADA' -
	 * Finalmente, muestra el detalle de la factura generada en pantalla: nº de
	 * factura, fecha, importe, importe de IVA, importe total, status
	 * 
	 * @throws SQLException
	 * 
	 */

	@Override
	public void execute() throws BusinessException, SQLException {

		averias = new ArrayList<Long>();

		while (masAverias()) {
			Long id = Console.readLong("ID de averia");
			averias.add(id);
		}

		connection = Jdbc.getConnection();
		connection.setAutoCommit(false);

		long id_facutra = numeroFactura();
		Date fechaFactura = DateUtil.today();
		double totalFactura = calcularImportesAverias();
		double iva = porcentajeIva(totalFactura, fechaFactura);
		double importe = totalFactura * (1 + iva / 100);
		importe = Round.twoCents(importe);
		long idFactura = crearFactura(id_facutra, fechaFactura, iva, importe);
		vincularAveriasConFactura(idFactura);
		actualizarEstado("FACTURADA");
		show(id_facutra, fechaFactura, totalFactura, iva, importe);

		connection.commit();

		Jdbc.close(connection);

	}

	private boolean masAverias() {
		String aux = Console.readString("¿Añadir más averias? (s/n) ");

		if (aux.equals("s")) {
			return true;
		}
		return false;
	}

	private Long numeroFactura() throws SQLException {

		try {
			prST = connection.prepareStatement(id_ultima_factura);
			rs = prST.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1;
			} else {
				return 1L;
			}
		} finally {
			Jdbc.close(rs, prST);
		}
	}

	private double importeRepuestos(Long idAveria) throws SQLException {

		prST = connection.prepareStatement(precio_repuestos);
		prST.setLong(1, idAveria);

		rs = prST.executeQuery();
		if (rs.next() == false) {
			return 0.0;
		}

		Jdbc.close(rs, prST);
		return rs.getDouble(1);

	}

	private double importeManoObra(Long idAveria) throws BusinessException, SQLException {

		prST = connection.prepareStatement(precio_obra);
		prST.setLong(1, idAveria);

		rs = prST.executeQuery();
		if (rs.next() == false) {
			throw new BusinessException("La averia no existe o no se puede facturar");
		}

		Jdbc.close(rs, prST);
		return rs.getDouble(1);

	}

	private double calcularImportesAverias() throws BusinessException, SQLException {

		double totalFactura = 0.0;
		for (Long idAveria : averias) {
			double totalAveria = importeManoObra(idAveria) + importeRepuestos(idAveria);

			// Actualizar Importe

			prST = connection.prepareStatement(precio_averia);
			prST.setDouble(1, totalAveria);
			prST.setLong(2, idAveria);
			prST.executeUpdate();

			Jdbc.close(prST);

			totalFactura += totalAveria;
		}
		return totalFactura;
	}

	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
	}

	private long crearFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
			throws SQLException {

		prST = connection.prepareStatement(crear_factura);
		prST.setLong(1, numeroFactura);
		prST.setDate(2, new java.sql.Date(fechaFactura.getTime()));
		prST.setDouble(3, iva);
		prST.setDouble(4, totalConIva);
		prST.setString(5, "SIN_ABONAR");

		prST.executeUpdate();
		Jdbc.close(prST);

		prST = connection.prepareStatement(clave);
		prST.setLong(1, numeroFactura);

		rs = prST.executeQuery();
		rs.next();

		Jdbc.close(rs, prST);

		return rs.getLong(1);

	}

	private void vincularAveriasConFactura(long idFactura) throws SQLException {

		prST = connection.prepareStatement(link_averia_factura);

		for (Long idAveria : averias) {
			prST.setLong(1, idFactura);
			prST.setLong(2, idAveria);

			prST.executeUpdate();
		}

		Jdbc.close(prST);

	}

	private void actualizarEstado(String status) throws SQLException {

		prST = connection.prepareStatement(actualizar_estado);

		for (Long idAveria : averias) {
			prST.setString(1, status);
			prST.setLong(2, idAveria);

			prST.executeUpdate();
		}

		Jdbc.close(prST);

	}

	private void show(long numeroFactura, Date fechaFactura, double totalFactura, double iva, double totalConIva) {

		Console.print("Factura nº: " + numeroFactura + "\n");
		Console.print("\tFecha: " + fechaFactura.toString() + "\n");
		Console.print("\tTotal:" + totalFactura + "€\n");
		Console.print("\tIva: " + iva + " \n");
		Console.print("\tTotal con IVA: " + totalConIva + " €\n");
	}

}