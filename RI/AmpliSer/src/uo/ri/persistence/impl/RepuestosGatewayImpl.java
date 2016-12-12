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
import uo.ri.persistence.RepuestosGateway;

public class RepuestosGatewayImpl implements RepuestosGateway {

	Connection c;
	
	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public Map<String, Object> findById(Long id) {
		Map<String, Object> repuesto = null;

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_REPUESTO_BY_ID"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				repuesto = new HashMap<String, Object>();
				repuesto.put("ID", rs.getLong(1));
				repuesto.put("Nombre", rs.getString(2));
				repuesto.put("Precio", rs.getDouble(3));
				repuesto.put("Unidades", rs.getInt(4));
				repuesto.put("MinStock", rs.getInt(5));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return repuesto;
	}

	@Override
	public ArrayList<Map<String, Object>> findAll() {
		ArrayList<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Map<String, Object> map =null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_FIND_ALL_REPUESTOS"));

			rs = pst.executeQuery();
			while (rs.next()) {
				map = new HashMap<String, Object>();
				map.put("ID", rs.getLong(1));
				map.put("Nombre", rs.getString(2));
				map.put("Precio", rs.getDouble(3));
				map.put("Unidades", rs.getInt(4));
				map.put("MinStock", rs.getInt(5));
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
	public void update(Long id, String nombre, Double precio) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		if (findById(id) == null)
			throw new RuntimeException("El repuesto no existe.");
		else {

			try {
				pst = c.prepareStatement(Conf.get("SQL_UPDATE_REPUESTO"));
				pst.setString(1, nombre);
				pst.setDouble(2, precio);
				pst.setLong(3, id);

				pst.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(rs, pst);
			}
		}

	}

	@Override
	public void save(String nombre, Double precio, Integer unidades, Integer minStock) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int id = 0;
		int codigo = 0;
		try {
			pst2 = c.prepareStatement(Conf.get("SQL_FIND_MAYOR_ID"));

			rs = pst2.executeQuery();
			while(rs.next()){
				id =  rs.getInt(1);
			}
			
			pst3 = c.prepareStatement(Conf.get("SQL_FIND_MAYOR_CODIGO"));

			rs2 = pst3.executeQuery();
			while(rs.next()){
				codigo =  rs2.getInt(1);
			}
			
			pst = c.prepareStatement(Conf.get("SQL_ADD_REPUESTO"));
			pst.setInt(1, (id+1));
			pst.setInt(2, (codigo + 10));
			pst.setString(2, nombre);
			pst.setDouble(3, precio);
			pst.setInt(4, unidades);
			pst.setInt(5, minStock);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}


	}

	@Override
	public void delete(Long id) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		if (findById(id) == null)
			throw new RuntimeException("Repuesto no existente.");
		else {

			try {

				pst = c.prepareStatement(Conf.get("SQL_DELETE_REPUESTO"));
				pst.setLong(1, id);

				pst.executeUpdate();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(rs, pst);
			}
		}
	}
}
