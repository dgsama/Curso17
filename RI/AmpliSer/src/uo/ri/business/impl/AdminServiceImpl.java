package uo.ri.business.impl;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.business.impl.amp.AddProveedor;
import uo.ri.business.impl.amp.DeleteProveedor;
import uo.ri.business.impl.amp.AddRespuesto;
import uo.ri.business.impl.amp.AddRepuestosProveedor;
import uo.ri.business.impl.amp.DeleteRespuesto;
import uo.ri.business.impl.amp.DeleteRepuestoProveedor;
import uo.ri.business.impl.amp.FindAllProveedores;
import uo.ri.business.impl.amp.FindAllRespuestos;
import uo.ri.business.impl.amp.FindPedidoById;
import uo.ri.business.impl.amp.FindPedidosProveedor;
import uo.ri.business.impl.amp.FindProveedorByID;
import uo.ri.business.impl.amp.FindProveedorByName;
import uo.ri.business.impl.amp.FindProveedoresByIdRepuesto;
import uo.ri.business.impl.amp.FindRepuestosProveedor;
import uo.ri.business.impl.amp.RecibirPedido;
import uo.ri.business.impl.amp.UpdateProveedor;
import uo.ri.business.impl.amp.UpdateRepuestoProveedor;
import uo.ri.business.impl.amp.UpdateRespuesto;
import uo.ri.ui.amp.ContPedido;
import uo.ri.business.impl.amp.FindPedidoById;
import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.ListMechanics;
import uo.ri.business.impl.admin.UpdateMechanic;

public class AdminServiceImpl implements AdminService {

	@Override
	public void newMechanic(String a, String b) {
		AddMechanic am = new AddMechanic(a, b);
		am.execute();
		
	}

	@Override
	public void deleteMechanic(long l) {
		DeleteMechanic dm = new DeleteMechanic(l);
		dm.execute();
		
	}

	@Override
	public void updateMechanic(long l, String a, String b) {
		UpdateMechanic um = new UpdateMechanic(l, a, b);
		um.execute();
		
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		ListMechanics list = new ListMechanics();
		List<Map<String, Object>> res = null;
		res = list.execute();
		return null;
	}

	@Override
	public void newProveedor(String nombre) {
		AddProveedor add = new AddProveedor(nombre);
		add.execute();
	}

	@Override
	public void newRepuesto(String descripcion, Double precio, Integer unidades, Integer stockMinimo) {
		AddRespuesto add = new AddRespuesto(descripcion, precio, unidades,
				stockMinimo);

		add.execute();
	}

	@Override
	public void addRepuestosProveedor(ArrayList<Map<String, Object>> lista, Long idProveedor) throws Exception {
		AddRepuestosProveedor add = new AddRepuestosProveedor(lista,
				idProveedor);

		add.execute();
		
	}

	@Override
	public void deleteProveedor(Long idProveedor) throws Exception {
		DeleteProveedor del = new DeleteProveedor(idProveedor);

		del.execute();
	}

	@Override
	public void deleteRepuesto(Long id) {
		DeleteRespuesto del = new DeleteRespuesto(id);

		del.execute();
	}

	@Override
	public void deleteRepuestoProveedor(Long idProveedor, Long idRepuesto) throws Exception {
		DeleteRepuestoProveedor del = new DeleteRepuestoProveedor(idRepuesto,idProveedor);
		del.execute();
	}

	@Override
	public Map<String, Object> findProveedorByID(Long id) {
		FindProveedorByID find = new FindProveedorByID(id);

		Map<String, Object> map = find.execute();
		return map;
	}

	@Override
	public ArrayList<Map<String, Object>> findRepuestosProveedor(Long id) throws Exception {
		FindRepuestosProveedor find = new FindRepuestosProveedor(id);

		ArrayList<Map<String, Object>> lista = find.execute();
		return lista;
	}

	@Override
	public Map<String, Object> findProveedorByName(String nombreBuscado) {
		FindProveedorByName find = new FindProveedorByName(nombreBuscado);

		Map<String, Object> map = find.execute();
		return map;
	}

	@Override
	public ArrayList<Map<String, Object>> findPedidosProveedor(Long id) throws Exception {
		FindPedidosProveedor find = new FindPedidosProveedor(id);

		ArrayList<Map<String, Object>> lista = find.execute();
		return lista;
	}

	@Override
	public ArrayList<Map<String, Object>> findProveedoresByIdRepuesto(Long id) throws Exception {
		FindProveedoresByIdRepuesto find = new FindProveedoresByIdRepuesto(id);

		ArrayList<Map<String, Object>> lista = find.execute();
		return lista;
	}

	@Override
	public ArrayList<Map<String, Object>> findAllProveedores() {
		FindAllProveedores find = new FindAllProveedores();

		ArrayList<Map<String, Object>> lista = find.execute();
		return lista;
	}

	@Override
	public ArrayList<Map<String, Object>> findAllRepuestos() {
		FindAllRespuestos find = new FindAllRespuestos();

		ArrayList<Map<String, Object>> lista = find.execute();
		return lista;
	}

	@Override
	public void recibirPedido(Long id) throws Exception {
		RecibirPedido ped = null;

		List<Map<String, Object>> list = findPedidoById(id);

		if (list.size() == 0)
			throw new NotBoundException();
		else {
			ContPedido cp = new ContPedido(list);
			boolean respuesta = cp.preguntar();

			if (respuesta) {
				ped = new RecibirPedido(id);
				ped.execute();
			}
		}
	}
	

	@Override
	public void updateProveedor(Long id, String nombre) throws Exception {
		UpdateProveedor update = new UpdateProveedor(id, nombre);

		update.execute();

	}

	@Override
	public void updateRepuesto(Long id, String nombre, Double precio) {
		UpdateRespuesto update = new UpdateRespuesto(id, nombre, precio);

		update.execute();

	}

	@Override
	public void updateRepuestoProveedor(Long idProveedor, Long idRepuesto, Double precio) throws Exception {
		UpdateRepuestoProveedor del = new UpdateRepuestoProveedor(idRepuesto,
				idProveedor, precio);
		del.execute();
	}

	@Override
	public List<Map<String, Object>> findPedidoById(Long id) throws Exception {
			FindPedidoById find = new FindPedidoById(id);

			List<Map<String, Object>> lista = find.execute();
			return lista;
	}
	
	

}
