package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PedidosGateway {
	
	void setConnection(Connection connection) throws SQLException;
		
	List<Map<String,Object>> findAll();
	
	Long save(long cod, String nombre);
	
	void delete(Long codPedido);
	
	long update(Map<String, Object> pedido);
	
	Map<String,Object> findByCod(long codPedido);
}
