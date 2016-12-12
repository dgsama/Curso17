package uo.ri.business.impl.amp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ProveedoresGateway;

public class AddRepuestosProveedor {

	private final ArrayList<Map<String, Object>> lista;
	private final Long idProveedor;

	public AddRepuestosProveedor(ArrayList<Map<String, Object>> lista, Long idProveedor) {
		this.lista = lista;
		this.idProveedor = idProveedor;
	}

	public void execute() throws Exception {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			ProveedoresGateway gat = PersistenceFactory.getProveedorGateway();
			gat.setConnection(c);
			gat.nuevoRepuesto(lista, idProveedor);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}
}
