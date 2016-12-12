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

public class DeleteMechanic {
	
	private long idMechanic;
	

	public DeleteMechanic(long idMechanic) {
		this.idMechanic = idMechanic;
	}
	
	public void execute(){
		
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			MecanicosGateway gate = PersistenceFactory.getMecanicosGateway();
			gate.setConnection(c);
			gate.delete(idMechanic);
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
