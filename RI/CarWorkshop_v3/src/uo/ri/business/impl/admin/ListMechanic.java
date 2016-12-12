package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;

public class ListMechanic {

	private static String SQL = "select id, nombre, apellidos from TMecanicos";

	public List<Map<String, Object>> execute() {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> mecanico;
		List<Map<String, Object>> info = new ArrayList<Map<String, Object>>();
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			while (rs.next()) {
				mecanico = new HashMap<String, Object>();
				mecanico.put("id", rs.getString(1));
				mecanico.put("nombre", rs.getString(2));
				mecanico.put("apellidos", rs.getString(3));
				info.add(mecanico);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return info;
	}
}
