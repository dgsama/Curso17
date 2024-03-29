package uo.ri.business.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;

public class UpdateMechanic {

	private Long id;
	private String nombre;
	private String apellidos;
	private static String SQL = "update TMecanicos "
			+ "set nombre = ?, apellidos = ? " + "where id = ?";

	public UpdateMechanic(Map<String, Object> info) {
		this.id = (Long) info.get("id");
		this.nombre = (String) info.get("nombre");
		this.apellidos = (String) info.get("apellidos");
	}

	public void execute() {

		// Procesar
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
