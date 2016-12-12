package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;
import alb.util.jdbc.Jdbc;

public class DeleteProveedor {
	private long idProveedor;
	
	public DeleteProveedor(long idProv){
		this.idProveedor = idProv;
	}
	
	public void execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			ProveedoresGateway mGate = PersistenceFactory.getProveedorGateway();
			
			mGate.setConnection(c);
			mGate.delete(idProveedor);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
