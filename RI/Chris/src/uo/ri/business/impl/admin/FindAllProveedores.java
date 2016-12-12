package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.business.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;
import alb.util.jdbc.Jdbc;

public class FindAllProveedores {
	
public List<Map<String,Object>> execute() {
	Connection c = null;

	try {
		c = Jdbc.getConnection();
		
		ProveedoresGateway pGate = PersistenceFactory.getProveedorGateway();
		
		pGate.setConnection(c);
		
		return pGate.findAll();
		
		} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	finally {
		Jdbc.close(c);
	}
}
}
