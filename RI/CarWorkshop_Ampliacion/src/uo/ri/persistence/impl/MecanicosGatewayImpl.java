package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.MecanicosGateway;

public class MecanicosGatewayImpl implements MecanicosGateway{

	
	Connection c;
	@Override
	public void setConnection(Connection con) {
		c = con;
		
	}

	@Override
	public Map<String, Object> findMechanicByid(Long l) {
		return null;
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_LIST_MECHANICS"));
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Console.printf("\t%d %s %s\n",  
					rs.getLong(1)
					,  rs.getString(2) 
					,  rs.getString(3)
				);
			}
			
		}catch(Exception e){
			
		}
		finally{
			Jdbc.close(ps);
		}
		return null;
	}

	@Override
	public void updateMechanic(Long l, String nombre, String apellidos) {
		PreparedStatement ps = null;
		
		try{
			ps = c.prepareStatement(Conf.get("SQL_UPDATE_MECHANIC"));
			ps.setLong(1, l);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.executeUpdate();
			
		}catch(Exception e){
			
		}
		finally{
			Jdbc.close(ps);
		}
		
		
	}

	@Override
	public void addMechanic(String a, String b) {
		PreparedStatement ps = null;
		
		try{
			ps = c.prepareStatement(Conf.get("SQL_ADD_MECHANIC"));
			ps.setString(1, a);
			ps.setString(2, b);
			ps.executeUpdate();
		}catch(Exception e){
			
		}
		finally{
			Jdbc.close(ps);
		}
		
	}

	@Override
	public void deleteMechanic(Long l) {
		
		PreparedStatement pst = null;
		
		try{
			
			pst = c.prepareStatement(Conf.get("SQL_DELETE_MECHANIC"));
			pst.setLong(1, l);
		
			pst.executeUpdate();
		
		}catch(Exception e){
		
		}
		finally{
			Jdbc.close(pst);
		}
	}
}