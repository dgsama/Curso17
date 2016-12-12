package uo.ri.business.impl.Ampliacion.GestionRepuestos;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class UpdateRepuesto {
	private Long id;
	private double precio;
	
	
	
	public UpdateRepuesto(Long id, double precio) {
		super();
		this.id = id;
		this.precio = precio;
	}

	public void execute() {

		// Procesar
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			RepuestosGateway g = PersistenceFactory.getRepuestosGateway();
			g.setConnection(c);
			g.actualizarRepuesto(id, precio);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}
}
