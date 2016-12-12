package uo.ri.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

public interface RepuestosGateway {
	
	public void setConnection(Connection c);

	public void a�adirRepuesto(String descripcion, double precio, int min_cantidad, int max_cantidad, int existencias);
	
	public void actualizarRepuesto(Long id, double precio);
	
	public void borrarRepuesto(long id);

	public ArrayList<Map<String, Object>> listaRepuestos();


}