package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface FacturasGateway {
	
	public void setConnection(Connection con);
	public Map<String,Object> findByid(Long l);
	public List<Map<String,Object>> findAll();
	public Long save(Map<String, Object> map);
	public void delete(Long l);
	public void update(Map<String, Object> map);
	public Map<String,Object> findByNumber(Long l);
	public Long getLastInvoiceNumber();

}
