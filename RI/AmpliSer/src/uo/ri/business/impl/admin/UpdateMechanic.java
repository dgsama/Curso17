package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.MecanicosGatewayImpl;

public class UpdateMechanic {

	
	
	private long id;
	private String nombre;
	private String apellidos;
	
	public UpdateMechanic(long id, String nombre, String apellidos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public void execute(){
		
		// Procesar
				Connection c = null;
				try {
					c = Jdbc.getConnection();
					MecanicosGateway gate = PersistenceFactory.getMecanicosGateway();
					gate.setConnection(c);
					gate.update(id, nombre, apellidos);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				finally {
					Jdbc.close(c);
				}
	}

	
	

}
