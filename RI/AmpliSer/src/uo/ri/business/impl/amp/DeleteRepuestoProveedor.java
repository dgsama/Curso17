package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class DeleteRepuestoProveedor {

	private final Long idRepuesto;
	private final Long idProveedor;

	public DeleteRepuestoProveedor(Long idRepuesto, Long idProveedor) {
		this.idRepuesto = idRepuesto;
		this.idProveedor = idProveedor;
	}

	public void execute() throws Exception {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);

			if (gat.findSuministro(idRepuesto, idProveedor))
				gat.deleteRepuestoProveedor(idRepuesto, idProveedor);
			else
				throw new Exception();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
