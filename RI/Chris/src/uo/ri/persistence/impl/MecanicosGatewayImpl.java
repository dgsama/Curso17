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

import uo.ri.persistence.MecanicosGateway;

public class MecanicosGatewayImpl implements MecanicosGateway{

	Connection c;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	@Override
	public void setConnection(Connection connection) throws SQLException {
		this.c = connection;		
	}

	@Override
	public Map<String, Object> findById(Long idMecanico) {
		Map<String,Object> mechanic = new HashMap<String, Object>();
		
		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_MECHANIC"));
			
			pst.setLong(1,idMecanico);
			rs = pst.executeQuery();
			
			mechanic.put("id",rs.getLong(1));
			mechanic.put("nombre",rs.getString(2)); 
			mechanic.put("apellidos",rs.getString(3));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		return mechanic;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		List<Map<String,Object>> mechanics = new ArrayList<Map<String,Object>>();
		Map<String,Object> mecánico = new HashMap<String,Object>();

		try {
			pst = c.prepareStatement(Settings.get("SQL_FIND_ALL_MECHANICS"));
			rs = pst.executeQuery();
			while(rs.next()) {
				mecánico = new HashMap<String,Object>();
					mecánico.put("id",rs.getLong(1));
					mecánico.put("nombre",rs.getString(2)); 
					mecánico.put("apellidos",rs.getString(3));
				mechanics.add(mecánico);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		return mechanics;
	}

	@Override
	public void delete(Long idMecanico) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_DELETE_MECHANIC"));
			pst.setLong(1, idMecanico);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		finally{
			Jdbc.close(pst);
		}
	}

	@Override
	public void update(Map<String, Object> mecanico) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_UPDATE_MECHANIC"));
			pst.setString(1, (String)mecanico.get("nombre"));
			pst.setString(2, (String)mecanico.get("apellidos"));
			pst.setLong(3, (Long)mecanico.get("id"));
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
	}

	@Override
	public Map<String, Object> findByNumber(Long idMecanico) {
		Map<String,Object> mecánico = new HashMap<String,Object>();

		try {
			pst = c.prepareStatement(Settings.get("SQL_SELECT_MECHANIC"));
			pst.setLong(1,idMecanico);
			
			rs = pst.executeQuery();
			
			mecánico.put("id",rs.getLong(1));
			mecánico.put("nombre",rs.getString(2)); 
			mecánico.put("apellidos",rs.getString(3));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		return mecánico;
	}

	@Override
	public Long save(String s1, String s2) {
		try {
			pst = c.prepareStatement(Settings.get("SQL_INSERT_MECHANIC"));
			pst.setString(1, s1);
			pst.setString(2, s2);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(pst);
		}
		
		return 1L;
	}

}
