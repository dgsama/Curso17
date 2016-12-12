package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class FindProveedorByName {

	Map<String, Object> map;
	String nombre;

	public FindProveedorByName(String nombre) {
		this.nombre = nombre;
	}

	public Map<String, Object> execute() throws RuntimeException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);
			map = gat.findByName(nombre);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return map;
	}

}
