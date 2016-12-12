package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.FacturasGateway;

public class SettleInvoice {
	
	Long l;
	Map<Long, Double> map;

	public SettleInvoice(Long l, Map<Long, Double> map) {
		this.l = l;
		this.map = map;
	}

	public void execute() {
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			FacturasGateway gate = PersistenceFactory.getFacturasGateway();
			gate.setConnection(c);
			//Metodo que uso aqui?????
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(c);
		}
		
		
	}

}
