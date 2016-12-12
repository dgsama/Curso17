package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class UpdateRepuestoProveedor {

	private final Long idRepuesto;
	private final Long idProveedor;
	private final Double precio;

	public UpdateRepuestoProveedor(Long idRepuesto, Long idProveedor,
			Double precio) {
		this.idRepuesto = idRepuesto;
		this.idProveedor = idProveedor;
		this.precio = precio;
	}

	public void execute() throws Exception {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);

			if (gat.findSuministro(idRepuesto, idProveedor))
				gat.updateRepuestoProveedor(idRepuesto, idProveedor, precio);
			else
				throw new Exception();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
