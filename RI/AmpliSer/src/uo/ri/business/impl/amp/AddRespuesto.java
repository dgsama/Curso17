package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class AddRespuesto {

	String nombre;
	Double precio;
	Integer stockMinimo;
	Integer unidades;

	public AddRespuesto(String nombre, Double precio, Integer unidades, Integer stockMinimo) {
		this.nombre = nombre;
		this.precio = precio;
		this.unidades = unidades;
		this.stockMinimo = stockMinimo;
	}

	public void execute() throws RuntimeException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			RepuestosGateway gat = PersistenceFactory.getRepuestosGateway();
			gat.setConnection(c);
			gat.save(nombre, precio, unidades, stockMinimo);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
