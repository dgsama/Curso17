package uo.ri.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AdminService {

	void newMechanic(String a, String b);
	void deleteMechanic (long l);
	void updateMechanic(long l, String a, String b);
	List<Map<String, Object>> findAllMechanics();
	void newProveedor(String nombre);
	void newRepuesto(String descripcion, Double precio, Integer unidades, Integer stockMinimo);
	void addRepuestosProveedor(ArrayList<Map<String, Object>> lista, Long idProveedor) throws Exception;
	void deleteProveedor(Long idProveedor) throws Exception;
	void deleteRepuesto(Long idRepuesto);
	void deleteRepuestoProveedor(Long idProveedor, Long idRepuesto) throws Exception;
	Map<String, Object> findProveedorByID(Long id);
	ArrayList<Map<String, Object>> findRepuestosProveedor(Long id) throws Exception;
	Map<String, Object> findProveedorByName(String nombreBuscado);
	ArrayList<Map<String, Object>> findPedidosProveedor(Long id) throws Exception;
	ArrayList<Map<String, Object>> findProveedoresByIdRepuesto(Long id) throws Exception;
	ArrayList<Map<String, Object>> findAllProveedores();
	ArrayList<Map<String, Object>> findAllRepuestos();
	void recibirPedido(Long id) throws Exception;
	void updateProveedor(Long id, String nombre) throws Exception;
	void updateRepuesto(Long id, String descripcion, Double precio);
	void updateRepuestoProveedor(Long idProveedor, Long idRepuesto, Double precio) throws Exception;
	List<Map<String, Object>> findPedidoById(Long id) throws Exception;
	
}
