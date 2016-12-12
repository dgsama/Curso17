package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.PedidosGateway;
import alb.util.jdbc.Jdbc;

public class FindPedidoById {

	List<Map<String, Object>> lista;
	Long id;

	public FindPedidoById(Long id) {
		this.id = id;
	}

	public List<Map<String, Object>> execute() {

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			PedidosGateway gat = PersistenceFactory.getPedidoGateway();
			gat.setConnection(c);
			lista = gat.findById(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return lista;
	}
}
