package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class FindProveedorByID {

	Map<String, Object> map;
	Long id;

	public FindProveedorByID(Long id) {
		this.id = id;
	}

	public Map<String, Object> execute() {

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);
			map = gat.findById(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return map;
	}

}
