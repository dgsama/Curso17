package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class AddMechanic {
	
	private String nombre, apellidos;
	
	public AddMechanic(String nom, String ape){
		this.nombre = nom;
		this.apellidos = ape;
	}
	
	public void execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			MecanicosGateway mGate= PersistenceFactory.getMecanicoGateway();
			
			mGate.setConnection(c);
			mGate.save(nombre, apellidos);
			
			//pst = c.prepareStatement(Settings.get("SQL_INSERT_MECHANIC"));
			//pst.setString(1, nombre);
			//pst.setString(2, apellidos);
			
			//pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
