package uo.ri.business.impl.Ampliacion.GestionRepuestos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class ListarRepuestos {

	public ListarRepuestos() {
		// TODO Auto-generated constructor stub
	}

	public List<Map<String, Object>> execute() {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			RepuestosGateway g = PersistenceFactory.getRepuestosGateway();
			g.setConnection(c);
			return g.listaRepuestos();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}
}