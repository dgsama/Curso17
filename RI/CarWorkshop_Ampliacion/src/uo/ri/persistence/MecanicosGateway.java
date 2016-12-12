package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {
	
	public void setConnection(Connection con);
	public Map<String,Object> findMechanicByid(Long l);
	public List<Map<String,Object>> findAllMechanics();
	public void updateMechanic(Long l, String nombre, String apellidos);
	public void addMechanic(String a, String b);
	public void deleteMechanic(Long l);
}
