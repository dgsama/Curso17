package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.FacturasGateway;

public class PaymentMeansForInvoiceFinder {
	
	Long l;

	public PaymentMeansForInvoiceFinder(Long l) {
		this.l = l;
	}

	public List<Map<String, Object>> execute() {
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			FacturasGateway gate = PersistenceFactory.getFacturasGateway();
			gate.setConnection(c);
			return gate.findAll();//Metodo que uso aqui??????
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
	}

}
