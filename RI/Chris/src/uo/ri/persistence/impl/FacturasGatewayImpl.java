package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import alb.util.properties.Settings;

import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway{

	Connection c;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	@Override
	public void setConnection(Connection connection) {
		this.c = connection;
	}

	@Override
	public Long getLastInvoiceNumber() {
		try {
			pst = this.c.prepareStatement(Settings.get("SQL_ULTIMO_NUMERO_FACTURA"));
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, el siguiente
			} else {  // todavía no hay ninguna
				return 1L;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Map<String, Object> findById(Long idFactura) {
		Map<String,Object> factura = new HashMap<String, Object>();
		
		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_INVOICE"));
			
			pst.setLong(1,idFactura);
			rs = pst.executeQuery();
			
			factura.put("numero",rs.getLong(1));
			factura.put("fecha",rs.getDate(2));
			factura.put("iva",rs.getDouble(3));
			factura.put("importe",rs.getDouble(3));
			factura.put("status",rs.getString(3));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(rs, pst);
		}
		return factura;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<Map<String,Object>> facturas = new ArrayList<Map<String, Object>>();
		Map<String,Object> factura;// = new HashMap<String, Object>();
		
		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_ALL_INVOICES"));
			
			rs = pst.executeQuery();
			while(rs.next()){
				factura = new HashMap<String,Object>();
				factura.put("numero",rs.getLong(1));
				factura.put("fecha",rs.getDate(2));
				factura.put("iva",rs.getDouble(3));
				factura.put("importe",rs.getDouble(3));
				factura.put("status",rs.getString(3));
				facturas.add(factura);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(rs, pst);
		}
		return facturas;
	}

	@Override
	public Long save(Map<String, Object> factura) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_INSERTAR_FACTURA"));
			pst.setLong(1, (Long)factura.get("numero"));
			pst.setDate(2,(Date)factura.get("fecha"));
			pst.setDouble(3,(Double)factura.get("iva"));
			pst.setDouble(4,(Double)factura.get("importe"));
			pst.setString(5,(String)factura.get("status"));
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		
		return 1L;
	}

	@Override
	public void delete(Long idFactura) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_DELETE_FACTURA"));
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		finally{
			Jdbc.close(pst);
		}
	}

	@Override
	public void update(Map<String, Object> factura) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_UPDATE_FACTURA"));
			pst.setString(1, (String)factura.get("status"));
			pst.setLong(2, (Long)factura.get("id"));
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}		
	}

	@Override
	public Map<String, Object> findByNumber(Long numFactura) {
		Map<String,Object> factura = new HashMap<String, Object>();
		
		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_INVOICE_NUM"));
			
			pst.setLong(1,numFactura);
			rs = pst.executeQuery();
			
			factura.put("numero",rs.getLong(1));
			factura.put("fecha",rs.getDate(2));
			factura.put("iva",rs.getDouble(3));
			factura.put("importe",rs.getDouble(3));
			factura.put("status",rs.getString(3));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(rs, pst);
		}
		return factura;
	}

}
