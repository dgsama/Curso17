package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class UpdateRespuesto {

	long id;
	String nombre;
	Double precio;

	public UpdateRespuesto(long id, String nombre, Double precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();

			RepuestosGateway gat = PersistenceFactory.getRepuestosGateway();
			gat.setConnection(c);
			gat.update(id, nombre, precio);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
