package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class ListMechanic {

	public List<Map<String, Object>> execute() {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			MecanicosGateway gate = PersistenceFactory.getMecanicosGateway();
			gate.setConnection(c);
			return gate.findAllMechanics();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}
}
