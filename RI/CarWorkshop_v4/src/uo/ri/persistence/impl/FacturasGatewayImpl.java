package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway{

	Connection c;
	@Override
	public void setConnection(Connection con) {
		c = con;
	}

	@Override
	public Map<String, Object> findByid(Long l) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_UPDATE_"));
			ps.setLong(1, l);
			ps.executeQuery();
		}catch(Exception e){
			
		}finally{
			Jdbc.close(ps);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_RECUPERAR_FACTURAS"));
			ps.executeQuery();
		}catch(Exception e){
			
		}finally{
			Jdbc.close(ps);
		}
		return null;
	}

	@Override
	public Long save(Map<String, Object> map) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long num = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_ULTIMO_NUMERO_FACTURA"));
			rs = ps.executeQuery();
			while(rs.next()){
				num = rs.getLong("numero");
			}
			ps = c.prepareStatement(Conf.get("SQL_INSERTAR_FACTURA"));
			ps.executeUpdate();
			
		}catch(Exception e){
			
		}finally{
			Jdbc.close(ps);
		}
		return num;
	}

	@Override
	public void delete(Long l) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_"));
			ps.setLong(1, l);
			ps.executeUpdate();
		}catch(Exception e){
			
		}finally{
			Jdbc.close(ps);
		}
		
	}

	@Override
	public void update(Map<String, Object> map) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));
			ps.executeUpdate();
			ps = c.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_AVERIA"));
			ps.executeUpdate();
		}catch(Exception e){
			
		}finally{
			Jdbc.close(ps);
		}
		
	}

	@Override
	public Map<String, Object> findByNumber(Long l) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_RECUPERAR_CLAVE_GENERADA"));
			ps.setLong(1, l);
			ps.executeQuery();
		}catch(Exception e){
			
		}finally{
			Jdbc.close(ps);
		}
		return null;
	}

	@Override
	public Long getLastInvoiceNumber() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long num = null;
		try{
			ps = c.prepareStatement(Conf.get("SQL_ULTIMO_NUMERO_FACTURA"));
			rs = ps.executeQuery();
			while(rs.next()){
				num = rs.getLong("numero");
			}
		}catch(Exception e){
			
		}finally{
			Jdbc.close(ps);
		}
		return num;
	}

}
