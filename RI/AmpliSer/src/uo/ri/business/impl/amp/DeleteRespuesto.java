package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class DeleteRespuesto {

	long id;

	public DeleteRespuesto(long id) {
		this.id = id;
	}

	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();

			RepuestosGateway gat = PersistenceFactory.getRepuestosGateway();
			gat.setConnection(c);
			gat.delete(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
