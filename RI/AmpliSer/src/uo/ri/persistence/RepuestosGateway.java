package uo.ri.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

public interface RepuestosGateway {
	
	public void setConnection(Connection c);

	public Map<String, Object> findById(Long id);

	public ArrayList<Map<String, Object>> findAll();

	public void update(Long id, String nombre, Double precio);

	public void save(String nombre, Double precio, Integer unidades, Integer minStock);

	public void delete(Long id);
}
