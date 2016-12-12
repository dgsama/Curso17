package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;
import alb.util.jdbc.Jdbc;

public class AddProveedor {

private String nombre, código;
	
	public AddProveedor(String cod, String nom){
		this.nombre = nom;
		this.código = cod;
	}
	
	public void execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			ProveedoresGateway pGate= PersistenceFactory.getProveedorGateway();
			
			pGate.setConnection(c);
			pGate.save(código,nombre);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
