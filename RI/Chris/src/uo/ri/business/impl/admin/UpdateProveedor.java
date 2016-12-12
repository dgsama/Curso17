package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;
import alb.util.jdbc.Jdbc;

public class UpdateProveedor {
	private Map<String,Object> proveedor = new HashMap<String,Object>();
	
	public UpdateProveedor(Map<String,Object> proveedorDatos) {
			this.proveedor=proveedorDatos;
	}

	public void execute(){
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			
			ProveedoresGateway pGate = PersistenceFactory.getProveedorGateway();
			
			pGate.setConnection(c);
			pGate.update(this.proveedor);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}
}
