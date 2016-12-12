package uo.ri.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.Ampliacion.ListarPedidosProoveedor;
import uo.ri.business.impl.Ampliacion.GestionRepuestos.AddRepuesto;
import uo.ri.business.impl.Ampliacion.GestionRepuestos.DeleteRepuesto;
import uo.ri.business.impl.Ampliacion.GestionRepuestos.ListarRepuestos;
import uo.ri.business.impl.Ampliacion.GestionRepuestos.UpdateRepuesto;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.ListMechanic;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService {

	@Override
	public void newMechanic(String nombre, String apellidos) {
		new AddMechanic(nombre, apellidos).execute();

	}

	@Override
	public void deleteMechanic(Long id) {
		new DeleteMechanic(id).execute();

	}

	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) {
		new UpdateMechanic(id, nombre, apellidos).execute();

	}

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		return new ListMechanic().execute();
	}

	@Override
	public void añadirRepuesto(String descripcion, double precio, int min_cantidad, int max_cantidad, int existencias) {
		try {
			new AddRepuesto(descripcion, precio, min_cantidad, max_cantidad, existencias).execute();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actualizarRepuesto(Long id, double precio) {
		new UpdateRepuesto(id, precio).execute();
	}

	@Override
	public void borrarRepuesto(long id) {
		new DeleteRepuesto(id).execute();

	}

	@Override
	public ArrayList<Map<String, Object>> listaRepuestos() {
		return (ArrayList<Map<String, Object>>) new ListarRepuestos().execute();
	}

	@Override
	public ArrayList<Map<String, Object>> encontrarPedidosProveedor(Long id) throws Exception {
		return new ListarPedidosProoveedor(id).execute();
	}

}
