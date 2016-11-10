package uo.ri.ui.cash.action;

import java.sql.*;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class ReparacionesNoFacturadasUnClienteAction implements Action {

	/**
	 * Proceso:
	 * 
	 * - Pide el DNI del cliente
	 * 
	 * - Muestra en pantalla todas sus averias no facturadas (status <>
	 * 'FACTURADA'). De cada avería muestra su id, fecha, status, importe y
	 * descripción
	 */

	private static String consulta = "SELECT a.id, a.fecha, a.status,a.importe, a.descripcion "
			+ "FROM taverias a,tvehiculos v,tclientes c " + "WHERE a.status<>'FACTURADA' " + "AND a.vehiculo_id = v.id "
			+ "AND v.cliente_id = c.id " + "AND c.dni = ?";

	private Connection conn = null;
	private PreparedStatement prST = null;
	private ResultSet rs = null;

	@Override
	public void execute() throws BusinessException, SQLException {
		Console.println("\nListado de averías no facturadas de un cliente\n");

		String dni = Console.readString("Dni");

		conn = Jdbc.getConnection();
		prST = conn.prepareStatement(consulta);
		prST.setString(1, dni.toString());
		rs = prST.executeQuery();
		while (rs.next()) {
			Console.print("ID de la factura: " + rs.getInt(1) + " -- Fecha : " + rs.getDate(2).toString()
					+ " -- Status: " + rs.getString(3) + " -- Importe: " + rs.getDouble(4) + " -- Descripcion: "
					+ rs.getString(5) + ".\n");
		}

		Jdbc.close(rs, prST, conn);

	}

}