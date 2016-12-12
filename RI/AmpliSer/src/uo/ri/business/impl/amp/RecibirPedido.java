package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.PedidosGateway;

public class RecibirPedido {

	Long id;

	public RecibirPedido(Long id) {
		this.id = id;
	}

	public void execute() throws Exception {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			PedidosGateway gat = PersistenceFactory.getPedidoGateway();
			gat.setConnection(c);
			
			if(gat.statusById(id))
				gat.recibirPedido(id);
			else
				throw new Exception();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
