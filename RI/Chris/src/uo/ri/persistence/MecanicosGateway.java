package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {

	void setConnection(Connection connection) throws SQLException;
		
	Map<String,Object> findById(Long idMecanico);
	
	List<Map<String,Object>> findAll();
	
	Long save(String s1,String s2);
	
	void delete(Long idFactura);
	
	void update(Map<String, Object> mecanico);
	
	Map<String,Object> findByNumber(Long idMecanico);
	
}
