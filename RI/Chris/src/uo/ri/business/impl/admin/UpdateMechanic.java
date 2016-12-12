package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class UpdateMechanic {
	
	private Map<String,Object> mechanic = new HashMap<String,Object>();
	
	public UpdateMechanic(Long id, String nombre, String apellidos) {
			mechanic.put("id",id);
			mechanic.put("nombre",nombre);
			mechanic.put("apellidos",apellidos);
	}

	public void execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			MecanicosGateway mGate = PersistenceFactory.getMecanicoGateway();
			
			mGate.setConnection(c);
			mGate.update(this.mechanic);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
