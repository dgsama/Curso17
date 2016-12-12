package uo.ri.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

public interface PedidosGateway {

	ArrayList<Map<String, Object>> findPedidosByIDProveedor(long id);

	void setConnection(Connection c);

}
