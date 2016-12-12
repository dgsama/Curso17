package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class DeleteProveedor {

	long idProveedor;

	public DeleteProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public void execute() throws Exception {
		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);
			gat.delete(idProveedor);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
