package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface AdminService {

	void newMechanic(String nombre, String apellidos);
	
	void deleteMechanic(Long id);
	
	void updateMechanic(Long id, String nombre, String apellidos);
	
	List<Map<String,Object>> findAllMechanics();

	void newProveedor(String código, String nombre);

	void deleteProveedor(Long idProveedor);

	List<Map<String,Object>> findAllProveedores();

	void updateProveedor(Map<String,Object> proveedorDatos);
	
	Map<String,Object> findByCod(String cod);
	
	Map<String,Object> findByNom(String nom);
	
	List<Map<String,Object>> findPedidos(String cod);

	void recibirPedido(String cod);
}
