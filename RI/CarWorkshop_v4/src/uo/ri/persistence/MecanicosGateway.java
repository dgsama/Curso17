package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {
	
	public void setConnection(Connection con);
	public Map<String,Object> findByid(Long l);
	public List<Map<String,Object>> findAll();
	public void update(Long l, String nombre, String apellidos);
	public void save(String a, String b);
	public void delete(Long l);
}
