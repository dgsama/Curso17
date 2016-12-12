package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.RepuestosGateway;

public class RepuestosGatewayImpl implements RepuestosGateway {

	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;

	}

	@Override
	public void añadirRepuesto(String descripcion, double precio, int min_cantidad, int max_cantidad, int existencias) {
		PreparedStatement ps = null;

		try {
			ps = c.prepareStatement(Conf.get("SQL_AÑADIR_REPUESTO"));
			long id = obtenerIdSiguiente();
			long codigo = obtenerCodigoSiguiente();
			ps.setLong(1, id);
			ps.setLong(2, codigo);
			ps.setString(3, descripcion);
			ps.setDouble(4, precio);
			ps.setInt(5, existencias);
			ps.setInt(6, min_cantidad);
			ps.setInt(7, max_cantidad);
			ps.executeUpdate();
		} catch (Exception e) {

		} finally {
			Jdbc.close(ps);
		}

	}

	private long obtenerCodigoSiguiente() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long l = 0;
		try {
			ps = c.prepareStatement(Conf.get("SQL_ULTIMO_CODIGO"));
			rs = ps.executeQuery();
			rs.next();

			l = rs.getLong(1) + 1;

		} catch (Exception e) {

		} finally {
			Jdbc.close(ps);
		}
		return l;
	}

	private long obtenerIdSiguiente() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		long l = 0;
		try {
			ps = c.prepareStatement(Conf.get("SQL_ULTIMA_ID"));
			rs = ps.executeQuery();
			rs.next();

			l = rs.getLong(1) + 1;

		} catch (Exception e) {

		} finally {
			Jdbc.close(ps);
		}
		return l;
	}

	@Override
	public void actualizarRepuesto(Long id, double precio) {
		PreparedStatement ps = null;

		try {
			ps = c.prepareStatement(Conf.get("SQL_ACTUALIZAR_REPUESTO"));
			ps.setLong(1, id);
			ps.setDouble(2, precio);
			ps.executeUpdate();

		} catch (Exception e) {

		} finally {
			Jdbc.close(ps);
		}

	}

	@Override
	public void borrarRepuesto(long id) {
		PreparedStatement pst = null;

		try {

			pst = c.prepareStatement(Conf.get("SQL_BORRAR_REPUESTO"));
			pst.setLong(1, id);

			pst.executeUpdate();

		} catch (Exception e) {

		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public ArrayList<Map<String, Object>> listaRepuestos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Map<String, Object>> res = new ArrayList<>();
		try {
			ps = c.prepareStatement(Conf.get("SQL_ENCONTRAR_TODOS_REPUESTOS"));

			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> aux = new HashMap<>();
				aux.put("id", rs.getLong(1));
				aux.put("codigo", rs.getLong(2));
				aux.put("descripcion", rs.getString(3));
				aux.put("precio", rs.getDouble(4));
				aux.put("existencias", rs.getInt(5));
				aux.put("min", rs.getInt(6));
				aux.put("max", rs.getInt(7));
				res.add(aux);

			}

		} catch (Exception e) {

		} finally {
			Jdbc.close(ps);
		}
		return null;
	}

}
