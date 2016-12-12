package uo.ri.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

public interface ProveedoresGateway {
	public void setConnection(Connection c);

	public Map<String, Object> findById(Long idProveedor);

	public ArrayList<Map<String, Object>> findAll();

	public void update(Long idProveedor, String nombre) throws Exception;

	public void save(String nombre);

	public void delete(Long idProveedor) throws Exception;

	public ArrayList<Map<String, Object>> findPedidosByID(Long idProveedor)
			throws Exception;

	public ArrayList<Map<String, Object>> findRepuestosProveedor(Long id)
			throws Exception;

	public void nuevoRepuesto(ArrayList<Map<String, Object>> lista,
			Long idProveedor) throws Exception;

	public Map<String, Object> findByName(String nombre);

	public ArrayList<Map<String, Object>> findProveedoresByIdRepuesto(Long id)
			throws Exception;

	public void deleteRepuestoProveedor(Long idRepuesto, Long idProveedor)
			throws Exception;

	public boolean findSuministro(Long idRepuesto, Long idProveedor)
			throws Exception;

	public void updateRepuestoProveedor(Long idRepuesto, Long idProveedor,
			Double precio) throws Exception;
}
