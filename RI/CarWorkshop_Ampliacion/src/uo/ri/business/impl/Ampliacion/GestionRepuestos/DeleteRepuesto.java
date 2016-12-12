package uo.ri.business.impl.Ampliacion.GestionRepuestos;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class DeleteRepuesto {
	private Long id;

	public DeleteRepuesto(Long id) {
		this.id = id;
	}

	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			RepuestosGateway g = PersistenceFactory.getRepuestosGateway();
			g.setConnection(c);
			g.borrarRepuesto(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}
}
