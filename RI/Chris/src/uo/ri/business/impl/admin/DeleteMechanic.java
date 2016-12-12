package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class DeleteMechanic {
	private long idMecanico;
	
	public DeleteMechanic(long idMec){
		this.idMecanico = idMec;
	}
	
	public void execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			MecanicosGateway mGate = PersistenceFactory.getMecanicoGateway();
			
			mGate.setConnection(c);
			mGate.delete(idMecanico);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
