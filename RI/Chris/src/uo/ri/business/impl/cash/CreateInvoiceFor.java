package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.common.BusinessException;
import uo.ri.persistence.FacturasGateway;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import alb.util.properties.Settings;

public class CreateInvoiceFor {

	private List<Long> idsAveria;
	private Connection connection;
	private FacturasGateway fGate; 
	
	public CreateInvoiceFor(List<Long> averias){
		if (averias != null) 
			this.idsAveria = averias;
	}
	
	public Map<String, Object> execute() throws BusinessException{
		try {
			Map <String,Object> factura = new HashMap<String,Object>();
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);
			
			this.fGate = PersistenceFactory.getFacturaGateway();
			
			verificarAveriasTerminadas(idsAveria);

			long numeroFactura = generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			double totalFactura = calcularImportesAverias(idsAveria);
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva/100);
			importe = Round.twoCents(importe);
			
			long idFactura = crearFactura(numeroFactura, fechaFactura, iva, importe);
			vincularAveriasConFactura(idFactura, idsAveria);
			cambiarEstadoAverias(idsAveria, "FACTURADA");
			 
			connection.commit();
			
			factura.put("id",numeroFactura);
			factura.put("fecha", fechaFactura);
			factura.put("total", totalFactura);
			factura.put("iva", iva);
			factura.put("totalConIva", importe);
			
			return factura;
			
		}
		catch (SQLException e) {
			try { connection.rollback(); } catch (SQLException ex) {};
			throw new RuntimeException(e);
		}
		catch (BusinessException e) {
			try { connection.rollback(); } catch (SQLException ex) {};
			throw e;
		}
		finally {
			Jdbc.close(connection);
		}
	}
	
	@SuppressWarnings("resource")
	private void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Settings.get("SQL_VERIFICAR_ESTADO_AVERIA"));
			
			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);
				
				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("No existe la averia " + idAveria);
				}
				
				String status = rs.getString(1); 
				if (! "TERMINADA".equalsIgnoreCase(status) ) {
					throw new BusinessException("No está terminada la avería " + idAveria);
				}
				
				rs.close();
			}
		} finally {
			Jdbc.close(rs, pst);
		}

	}
	
	private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException {
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(Settings.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));
			
			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException {
		
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(Settings.get("SQL_VINCULAR_AVERIA_FACTURA"));

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idFactura);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}
	
	private long crearFactura(long numeroFactura, Date fechaFactura,
			double iva, double totalConIva) throws SQLException {
		
		Map<String,Object> facturaDatos = new HashMap<String, Object>();
		facturaDatos.put("id",numeroFactura);
		facturaDatos.put("fecha",fechaFactura);
		facturaDatos.put("iva",iva);
		facturaDatos.put("totalIva",totalConIva);

		//this.fGate.save(facturaDatos);
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(Settings.get("SQL_INSERTAR_FACTURA"));
			pst.setLong(1, numeroFactura);
			pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
			pst.setDouble(3, iva);
			pst.setDouble(4, totalConIva);
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();

			return getGeneratedKey(numeroFactura); // Id de la nueva factura generada
			
		} finally {
			Jdbc.close(pst);
		}
	}
	
	private long getGeneratedKey(long numeroFactura) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Settings.get("SQL_RECUPERAR_CLAVE_GENERADA"));
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);
			
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	
	private Long generarNuevoNumeroFactura() throws SQLException {
		Long num = fGate.getLastInvoiceNumber();
		
		return (num!=1L)? num+1:1L;
	}
	
	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
	}
	
	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connection.prepareStatement(Settings.get("SQL_IMPORTE_REPUESTOS"));
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}
			
			return rs.getDouble(1);
			
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}
	
	private double consultaImporteManoObra(Long idAveria) throws BusinessException, SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connection.prepareStatement(Settings.get("SQL_IMPORTE_MANO_OBRA"));
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}
			
			return rs.getDouble(1);
			
		} catch (BusinessException e) {
			throw e;
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}
	private void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException {
			PreparedStatement pst = null;
			
			try {
				pst = connection.prepareStatement(Settings.get("SQL_UPDATE_IMPORTE_AVERIA"));
				pst.setDouble(1, totalAveria);
				pst.setLong(2, idAveria);
				pst.executeUpdate();
			}	
			finally {
				Jdbc.close(pst);
			}
	}
		
	
	protected double calcularImportesAverias(List<Long> idsAveria)
			throws BusinessException, SQLException {
		
		double totalFactura = 0.0;
		for(Long idAveria : idsAveria) {
			double importeManoObra = consultaImporteManoObra(idAveria);
			double importeRepuestos = consultaImporteRepuestos(idAveria);
			double totalAveria = importeManoObra + importeRepuestos;
			
			actualizarImporteAveria(idAveria, totalAveria);
			
			totalFactura += totalAveria; 
		}
		return totalFactura;
	}
	
	
	
}
