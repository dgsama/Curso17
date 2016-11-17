package uo.ri.business.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;

public class ListMechanic {

	private static String SQL = "select id, nombre, apellidos from TMecanicos";

	public Map<String, Object> execute() {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> info;
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);

			rs = pst.executeQuery();
			int i = 0;
			info = new HashMap<String, Object>();
			while (rs.next()) {
				info.put("i", ("\t" + rs.getLong(1) + " " + rs.getString(2)
						+ " " + rs.getString(3) + " " + rs.getString(4) + "\n"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return info;
	}
}