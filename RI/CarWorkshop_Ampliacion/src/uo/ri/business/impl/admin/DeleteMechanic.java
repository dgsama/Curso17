package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class DeleteMechanic {


	private Long idMecanico;

	public DeleteMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			MecanicosGateway gate = PersistenceFactory.getMecanicosGateway();
			gate.setConnection(c);
			gate.deleteMechanic(idMecanico);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}
}
