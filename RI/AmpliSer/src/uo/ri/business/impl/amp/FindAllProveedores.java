package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class FindAllProveedores {

	ArrayList<Map<String, Object>> lista;

	public FindAllProveedores() {

	}

	public ArrayList<Map<String, Object>> execute() {

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);
			lista = gat.findAll();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return lista;
	}

}
