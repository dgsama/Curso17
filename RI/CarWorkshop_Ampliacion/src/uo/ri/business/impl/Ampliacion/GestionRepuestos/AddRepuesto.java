package uo.ri.business.impl.Ampliacion.GestionRepuestos;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.RepuestosGateway;

public class AddRepuesto {

	private String descripcion;
	private double precio;
	private int min_cantidad;
	private int max_cantidad;
	private int existencias;

	public AddRepuesto(String descripcion, double precio, int min_cantidad, int max_cantidad, int existencias)
			throws BusinessException {
		super();
		this.descripcion = descripcion;
		this.precio = precio;
		this.min_cantidad = min_cantidad;
		this.max_cantidad = max_cantidad;
		setExistencias(existencias);
	}

	private void setExistencias(int e) throws BusinessException {
		if (e < min_cantidad || e > max_cantidad) {
			throw new BusinessException("No puede haber repuestos con stock superior al numero de existencias maximas");
		} else {
			existencias = e;
		}

	}

	public void execute() throws RuntimeException {

		Connection c = null;

		try {
			c = Jdbc.getConnection();

			RepuestosGateway g = PersistenceFactory.getRepuestosGateway();
			g.setConnection(c);
			g.añadirRepuesto(descripcion, precio, min_cantidad, max_cantidad, existencias);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

	}

}
