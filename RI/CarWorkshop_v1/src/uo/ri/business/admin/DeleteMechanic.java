package uo.ri.business.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;

public class DeleteMechanic {

	private static String SQL = "delete from TMecanicos where id = ?";

	private Long idMecanico;

	public DeleteMechanic(Map<String, Object> info) {
		this.idMecanico = (Long) info.get("idMecanico");
	}

	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMecanico);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

		Console.println("Se ha eliminado el mecánico");
	}
}
