package uo.ri.business.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;

public class AddMechanic {
	private static String SQL = "insert into TMecanicos(nombre, apellidos) values (?, ?)";

	private String nombre;
	private String apellidos;

	public AddMechanic(Map<String, Object> info) {
		super();
		this.nombre = (String) info.get("nombre");
		this.apellidos = (String) info.get("apellidos");
	}

	public void execute() {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
