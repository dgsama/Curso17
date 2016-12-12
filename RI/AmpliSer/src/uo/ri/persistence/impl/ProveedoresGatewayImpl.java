package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.ProveedoresGateway;

public class ProveedoresGatewayImpl implements ProveedoresGateway {

	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Map<String, Object> findById(Long idProveedor) {
		Map<String, Object> proveedor = new HashMap<String, Object>();

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_FIND_PROVEEDOR_BY_ID"));
			pst.setLong(1, idProveedor);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				proveedor.put("ID", rs.getLong(1));
				proveedor.put("Nombre", rs.getString(2));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return proveedor;
	}

	@Override
	public ArrayList<Map<String, Object>> findAll() {
		ArrayList<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_FIND_ALL_PROVEEDORES"));

			rs = pst.executeQuery();
			while (rs.next()) {
				
				map.put("ID", rs.getLong(1));
				map.put("Nombre", rs.getString(2));
				lista.add(map);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return lista;

	}

	@Override
	public void update(Long idProveedor, String nombre) throws Exception {
		PreparedStatement pst = null;

		if (findById(idProveedor) == null)
			throw new Exception();
		else {

			try {
				pst = c.prepareStatement(Conf.get("SQL_UPDATE_PROVEEDOR"));
				pst.setString(1, nombre);
				pst.setLong(2, idProveedor);

				pst.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(pst);
			}
		}
	}

	@Override
	public void save(String nombre) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs = null;
		int id = 0;
		try {
			pst2 = c.prepareStatement(Conf.get("SQL_FIND_MAYOR_IDP"));
			rs = pst2.executeQuery();
			
			while(rs.next()){
				id = rs.getInt(1) +1;
			}
			
			pst = c.prepareStatement(Conf.get("SQL_ADD_PROVEEDOR"));
			pst2.setInt(1, id);
			pst.setString(2, nombre);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
		
	}

	@Override
	public void delete(Long idProveedor) throws Exception {
		PreparedStatement pst = null;

		if (findById(idProveedor) == null)
			throw new Exception();
		else {

			try {

				pst = c.prepareStatement(Conf.get("SQL_DELETE_PROVEEDOR"));
				pst.setLong(1, idProveedor);

				pst.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(pst);
			}

		}
	}

	@Override
	public ArrayList<Map<String, Object>> findPedidosByID(Long idProveedor) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();

		if (findById(idProveedor) == null)
			throw new Exception();
		else {

			try {
				pst = c.prepareStatement(Conf.get("SQL_FIND_PEDIDOS_PROVEEDOR"));
				pst.setLong(1, idProveedor);
				rs = pst.executeQuery();
				while (rs.next()) {
					
					map.put("ID Pedido", rs.getLong(1));
					map.put("ID Proveedor", rs.getLong(2));
					map.put("Cantidad", rs.getInt(3));
					map.put("ID Repuesto", rs.getLong(4));
					map.put("Status", rs.getString(5));
					map.put("Fecha", rs.getDate(6));
					lista.add(map);
				}

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(rs, pst);
			}
		}

		return lista;
	}

	@Override
	public ArrayList<Map<String, Object>> findRepuestosProveedor(Long id) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();

		if (findById(id) == null)
			throw new Exception();
		else {

			try {
				pst = c.prepareStatement(Conf
						.get("SQL_FIND_REPUESTOS_PROVEEDOR"));
				pst.setLong(1, id);
				rs = pst.executeQuery();
				while (rs.next()) {
					
					map.put("ID Proveedor", rs.getLong(1));
					map.put("ID Repuesto", rs.getLong(2));
					map.put("Precio", rs.getDouble(3));
					lista.add(map);
				}

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(rs, pst);
			}
		}

		return lista;

	}

	@Override
	public void nuevoRepuesto(ArrayList<Map<String, Object>> lista, Long idProveedor) throws Exception {
		PreparedStatement pst = null;
		Double precio;
		Long idRepuesto;

		if (findById(idProveedor) == null)
			throw new Exception();
		else {

			try {
				pst = c.prepareStatement(Conf
						.get("SQL_ADD_REPUESTOS_PROVEEDOR"));
				pst.setLong(1, idProveedor);

				for (int i = 0; i < lista.size(); i++) {
					idRepuesto = (Long) lista.get(i).get("IDRepuesto");
					pst.setLong(2, idRepuesto);
					precio = (Double) lista.get(i).get("Precio");
					pst.setDouble(3, precio);
					pst.executeUpdate();
				}

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(pst);
			}
		}
	}

	@Override
	public Map<String, Object> findByName(String nombre) {
		Map<String, Object> proveedor = new HashMap<String, Object>();

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_FIND_PROVEEDOR_BY_NAME"));
			pst.setString(1, nombre);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				proveedor.put("ID", rs.getLong(1));
				proveedor.put("Nombre", rs.getString(2));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return proveedor;
	}

	@Override
	public ArrayList<Map<String, Object>> findProveedoresByIdRepuesto(Long id) throws Exception {
		ArrayList<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf
					.get("SQL_FIND_PROVEEDORES_BY_ID_REPUESTO"));
			pst.setLong(1, id);

			rs = pst.executeQuery();
			while (rs.next()) {
				
				map.put("ID_Proveedor", rs.getLong(1));
				map.put("Nombre Proveedor", rs.getString(2));
				map.put("Precio Unitario", rs.getDouble(3));
				lista.add(map);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return lista;

	}

	@Override
	public void deleteRepuestoProveedor(Long idRepuesto, Long idProveedor) throws Exception {
		PreparedStatement pst = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_DELETE_REPUESTO_PROVEEDOR"));
			pst.setLong(1, idProveedor);
			pst.setLong(2, idRepuesto);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public boolean findSuministro(Long idRepuesto, Long idProveedor) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			pst = c.prepareStatement(Conf.get("SQL_FIND_SUMINISTRO"));
			pst.setLong(1, idProveedor);
			pst.setLong(2, idRepuesto);
			rs = pst.executeQuery();

			while (rs.next()) {
				map.put("ID_Proveedor", rs.getLong(1));
				map.put("ID_Repuesto", rs.getLong(2));
				map.put("Precio Unitario", rs.getDouble(3));
			}

			return !map.isEmpty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void updateRepuestoProveedor(Long idRepuesto, Long idProveedor, Double precio) throws Exception {
		PreparedStatement pst = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_UPDATE_REPUESTO_PROVEEDOR"));
			pst.setDouble(1, precio);
			pst.setLong(2, idProveedor);
			pst.setLong(3, idRepuesto);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

}
