package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface PedidosGateway {
	
	public void setConnection(Connection c);

	public void recibirPedido(Long id);

	public List<Map<String, Object>> findById(Long l);

	public Long findRepuestoPedido(Long l);

	boolean statusById(Long l);

}
