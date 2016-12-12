package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ProveedoresGateway {

	void setConnection(Connection connection) throws SQLException;
		
	List<Map<String,Object>> findAll();
	
	Long save(String cod, String nombre);
	
	long delete(Long idProveedor);
	
	long update(Map<String, Object> proveedor);
	
	Map<String,Object> findByCod(String cod);
	
	Map<String,Object> findByNom(String nom);

	List<Map<String, Object>> findPedidos(String cod);

	void recibirPedido(String cod);}
