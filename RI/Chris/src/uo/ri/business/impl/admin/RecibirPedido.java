package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;
import alb.util.jdbc.Jdbc;

public class RecibirPedido {
	private String cod;
	
	public RecibirPedido(String cod) {
			this.cod=cod;
	}

	public void execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			ProveedoresGateway pGate = PersistenceFactory.getProveedorGateway();
			
			pGate.setConnection(c);
			pGate.recibirPedido(this.cod);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
