package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;

public class InvoiceFinder {

	Long l;
	
	public InvoiceFinder(Long l) {
		this.l = l;
	}

	public Map<String, Object> execute() {
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			FacturasGateway gate = PersistenceFactory.getFacturasGateway();
			gate.setConnection(c);
			return gate.findByNumber(l);
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
		
	}

}
