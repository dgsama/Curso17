package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import alb.util.properties.Settings;

import uo.ri.persistence.ProveedoresGateway;

public class ProveedoresGatewayImpl implements ProveedoresGateway{

	Connection c;
	PreparedStatement pst;
	ResultSet rs;
	
	@Override
	public void setConnection(Connection connection) throws SQLException {
		this.c = connection;
	}

	@Override
	/**
	 * Devuelve el identificador, código y nombre de todos los proveedores 
	 * existentes en la base de datos.
	 */
	public List<Map<String, Object>> findAll() {
		List<Map<String,Object>> proveedores = new ArrayList<Map<String,Object>>();
		Map<String,Object> proveedor = new HashMap<String,Object>();

		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_ALL_SUPPLIERS"));
			rs = pst.executeQuery();	
			while(rs.next()) {
				proveedor = new HashMap<String,Object>();
					proveedor.put("id", rs.getLong(1));
					proveedor.put("cod",rs.getString(2));
					proveedor.put("nombre",rs.getString(3)); 
				proveedores.add(proveedor);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		return proveedores;
	}

	@Override
	/**
	 * Inserta en la base de datos un nuevo proveedor.
	 * @param cod código con el que el cliente identifica al proveedor
	 * @param nombre denominación del proveedor
	 */
	public Long save(String cod, String nombre) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_INSERT_SUPPLIER"));
			pst.setString(1, cod);
			pst.setString(2, nombre);
			int resultado = pst.executeUpdate();
			return (long) resultado;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}		
}

	@Override
	/**
	 * Elimina de la base los datos relativos al proveedor con id idProveedor
	 * @param idProveedor id del proveedor a buscar y eliminar
	 */
	public long delete(Long idProveedor) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_DELETE_SUPPLIER"));
			pst.setLong(1, idProveedor);
			
			return (long) pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		finally{
			Jdbc.close(pst);
		}
	}

	@Override
	/**
	 * Actualiza el nombre y/o código de un proveedor.
	 * No realiza comprobaciones de integridad referencial.
	 */
	public long update(Map<String, Object> proveedor) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_UPDATE_SUPPLIER"));
			pst.setString(1, (String)proveedor.get("nombre"));
			pst.setString(2, (String)proveedor.get("cod"));
			pst.setLong(3, (long)proveedor.get("id"));
			
			return (long) pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}		
	}

	@Override
	/**
	 * Devuelve la información existente en la base del proveedor de código cod
	 * @param cod código del proveedor a buscar
	 */
	public Map<String, Object> findByCod(String cod) {
		Map<String,Object> proveedor = new HashMap<String,Object>();

		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_SUPPLIER_COD"));
			pst.setString(1,cod);
			
			rs = pst.executeQuery();
			
			if(rs.next()){
				proveedor.put("id",rs.getLong(1));
				proveedor.put("cod",rs.getString(2));
				proveedor.put("nombre",rs.getString(3)); 
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		return proveedor;
	}

	@Override
	/**
	 * Devuelve la información existente en la base del proveedor de nombre nom
	 * @param nom denominación del proveedor a buscar
	 */
	public Map<String, Object> findByNom(String nom) {
		Map<String,Object> proveedor = new HashMap<String,Object>();

		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_SUPPLIER_NOM"));
			pst.setString(1,nom);
			
			rs = pst.executeQuery();
			
			if(rs.next()){
				proveedor.put("id",rs.getLong(1));
				proveedor.put("cod",rs.getString(2));
				proveedor.put("nombre",rs.getString(3)); 
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		return proveedor;
	}

	@Override
	/**
	 * Devuelve todos los datos de los pedidos relacionados con el proveedor, 
	 * ordenados descendientemente por fecha de creación.
	 * Primero devuelve los PENDIENTES.
	 */
	public List<Map<String, Object>> findPedidos(String cod) {
		List<Map<String,Object>> pedidos = new ArrayList<Map<String,Object>>();
		Map<String,Object> pedido = new HashMap<String,Object>();

		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_PEDIDOS_PENDIENTES_FECHA_DESC"));
			pst.setString(1, cod);
			rs = pst.executeQuery();	

			while(rs.next()) {
				pedido = new HashMap<String,Object>();
					pedido.put("id", rs.getLong("id"));
					pedido.put("cod",rs.getString("cod"));
					pedido.put("creado",rs.getDate("fecha_creacion"));
					pedido.put("recibido", rs.getDate("fecha_recepcion"));
					pedido.put("estado", rs.getString("estado"));
					pedido.put("cod_proveedor", rs.getString("cod_proveedor"));
				pedidos.add(pedido);
			}
			
			pst = c.prepareStatement(Settings.get("SQL_FIND_PEDIDOS_NOPENDIENTES_FECHA_DESC"));
			pst.setString(1, cod);
			rs = pst.executeQuery();	
			while(rs.next()) {
				pedido = new HashMap<String,Object>();
					pedido.put("id", rs.getLong("id"));
					pedido.put("cod",rs.getString("cod"));
					pedido.put("creado",rs.getDate("fecha_creacion"));
					pedido.put("recibido", rs.getDate("fecha_recepcion"));
					pedido.put("estado", rs.getString("estado"));
					pedido.put("cod_proveedor", rs.getString("cod_proveedor"));
				pedidos.add(pedido);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		return pedidos;
	}

}
