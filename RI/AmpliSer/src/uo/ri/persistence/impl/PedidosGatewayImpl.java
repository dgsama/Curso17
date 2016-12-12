package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.PedidosGateway;

public class PedidosGatewayImpl implements PedidosGateway{

	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public void recibirPedido(Long id) {
		PreparedStatement pst = null;
		PreparedStatement pstRep = null;
		ResultSet rs = null;
		PreparedStatement actualizarStock = null;
		PreparedStatement datosRepuesto = null;

		java.util.Date fechaRecepcion = new java.util.Date();
	
			try {

				pst = c.prepareStatement(Conf.get("SQL_RECIBIR_PEDIDO"));
				pst.setDate(1, new java.sql.Date(fechaRecepcion.getYear(),
						fechaRecepcion.getMonth(), fechaRecepcion.getDay()));
				pst.setLong(2, id);

				pstRep = c.prepareStatement(Conf.get("SQL_REPUESTOS_PEDIDO"));
				pstRep.setLong(1, id);

				rs = pstRep.executeQuery();
				List<Long> repuestos;
				while (rs.next()) {
					repuestos = new ArrayList<Long>();
					repuestos.add(rs.getLong(1));
				}

				List<Map<String, Object>> pedido = findById(id);

				for (int i = 0; i < pedido.size(); i++) {
					Long idRepuesto = (Long) pedido.get(i).get("IDRepuesto");

					datosRepuesto = c.prepareStatement(Conf
							.get("SQL_DATOS_REPUESTO"));
					datosRepuesto.setLong(1, idRepuesto);

					rs = datosRepuesto.executeQuery();
					Map<String, Object> repuesto = new HashMap<String, Object>();
					while (rs.next()) {
						repuesto.put("Unidades", rs.getInt(1));
						repuesto.put("Precio", rs.getDouble(2));
					}

					int cantidad = (Integer) repuesto.get("Unidades") + (Integer) pedido.get(i).get("Cantidad");

					actualizarStock = c.prepareStatement(Conf.get("SQL_ACTUALIZA_STOCK"));
					actualizarStock.setDouble(
							1,
							nuevoPrecio((Integer) repuesto.get("Unidades"),
									(Integer) pedido.get(i).get("Cantidad"),
									(Double) repuesto.get("Precio"),
									(Double) pedido.get(i).get("Precio")));
					actualizarStock.setInt(2, cantidad);
					actualizarStock.setLong(3,
							(Long) pedido.get(i).get("IDRepuesto"));

					actualizarStock.executeUpdate();
					pst.executeUpdate();
				}

			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				Jdbc.close(pst);
			}
	}
	
	private Double nuevoPrecio(int unidadesExistentes, int unidadesNuevas,
			Double precioViejo, Double precioNuevo) {
		Double precio;
		if (precioNuevo == precioViejo)
			return precioViejo;
		else {
			precio = (precioViejo * unidadesExistentes + precioNuevo
					* unidadesNuevas)
					/ (unidadesExistentes + unidadesNuevas);

			return precio;
		}
	}

	@Override
	public List<Map<String, Object>> findById(Long l) {
		Map<String, Object> pedido = new HashMap<String, Object>();
		List<Map<String, Object>> pedidos = new ArrayList<Map<String, Object>>();

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_DATOS_PEDIDO"));
			pst.setLong(1, l);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				pedido.put("IDRepuesto", rs.getLong(1));
				pedido.put("Cantidad", rs.getInt(2));
				pedido.put("Precio", rs.getDouble(3));
				pedidos.add(pedido);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return pedidos;
	}

	@Override
	public Long findRepuestoPedido(Long l) {
		Long idRepuesto = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_REPUESTO_PEDIDO"));
			pst.setLong(1, l);
			rs = pst.executeQuery();
			while (rs.next()) {
				idRepuesto = rs.getLong(1);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return idRepuesto;
	}

	@Override
	public boolean statusById(Long l) {
		String pedido = null;

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = c.prepareStatement(Conf.get("SQL_STATUS_PEDIDO"));
			pst.setLong(1, l);
			rs = pst.executeQuery();
			while (rs.next()) {
				pedido = rs.getString(1);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		if (pedido.equalsIgnoreCase("RECIBIDO"))
			return false;
		else
			return true;

	}

}
