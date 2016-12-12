package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.PedidosGateway;

public class PedidoGatewayImpl implements PedidosGateway {

	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public ArrayList<Map<String, Object>> findPedidosByIDProveedor(long id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();

		try {
			pst = c.prepareStatement(Conf.get("SQL_ENCONTRAR_PEDIDOS_PROVEEDOR"));
			pst.setLong(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {

				map.put("codigo", rs.getLong(1));
				map.put("fecha creacion", rs.getDate(2));
				map.put("fecha recepcion", rs.getDate(3));
				map.put("estado", rs.getString(4));
				lista.add(map);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return lista;
	}
}
