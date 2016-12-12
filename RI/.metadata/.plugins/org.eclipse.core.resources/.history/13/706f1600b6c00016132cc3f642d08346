package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

public class UpdateMechanic {

	private Long id;
	private String nombre;
	private String apellidos;
	private static String SQL = "update TMecanicos " + "set nombre = ?, apellidos = ? " + "where id = ?";

	public UpdateMechanic(Long id, String nombre, String apellidos) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() {

		// Procesar
		Connection c = null;
		try {
			c = Jdbc.getConnection();
			MecanicosGateway gate = PersistenceFactory.getMecanicosGateway();
			gate.setConnection(c);
			gate.updateMechanic(id, nombre, apellidos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
