package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FacturasGateway {

	void setConnection(Connection connection) throws SQLException;
	
	Long getLastInvoiceNumber();
	
	Map<String,Object> findById(Long idFactura);
	
	List<Map<String,Object>> findAll();
	
	Long save(Map<String,Object> factura);
	
	void delete(Long idFactura);
	
	void update(Map<String, Object> factura);
	
	Map<String,Object> findByNumber(Long idFactura);
}
