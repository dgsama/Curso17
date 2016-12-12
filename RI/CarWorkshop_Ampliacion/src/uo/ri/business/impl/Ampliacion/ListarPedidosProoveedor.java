package uo.ri.business.impl.Ampliacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.PedidosGateway;

public class ListarPedidosProoveedor {

	private ArrayList<Map<String, Object>> list;
	long id;

	public ListarPedidosProoveedor(Long id) {
		this.id = id;
	}

	public ArrayList<Map<String, Object>> execute() throws Exception {

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			PedidosGateway g = PersistenceFactory.getPedidosGateway();
			g.setConnection(c);
			list = g.findPedidosByIDProveedor(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return list;
	}

}
