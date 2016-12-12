package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class FindPedidosProveedor {

	ArrayList<Map<String, Object>> lista;
	Long id;

	public FindPedidosProveedor(Long id) {
		this.id = id;
	}

	public ArrayList<Map<String, Object>> execute() throws Exception {

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);
			lista = gat.findPedidosByID(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return lista;
	}

}
