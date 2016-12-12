package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class FindAllRespuestos {

	ArrayList<Map<String, Object>> lista;

	public FindAllRespuestos() {

	}

	public ArrayList<Map<String, Object>> execute() {

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			RepuestosGateway gat = PersistenceFactory.getRepuestosGateway();
			gat.setConnection(c);
			lista = gat.findAll();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return lista;
	}

}
