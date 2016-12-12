package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.AddProveedor;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.DeleteProveedor;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.FindAllProveedores;
import uo.ri.business.impl.admin.FindPedidos;
import uo.ri.business.impl.admin.FindProveedorCod;
import uo.ri.business.impl.admin.FindProveedorNom;
import uo.ri.business.impl.admin.RecibirPedido;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.business.impl.admin.UpdateProveedor;

public class AdminServiceImpl implements AdminService{


@Override
public void newMechanic(String nombre, String apellidos) {
	new AddMechanic(nombre,apellidos).execute();
}

@Override
public void deleteMechanic(Long id) {
	new DeleteMechanic(id).execute();
}

@Override
public void updateMechanic(Long id, String nombre, String apellidos) {
	new UpdateMechanic(id,nombre,apellidos).execute();
}

@Override
public List<Map<String, Object>> findAllMechanics() {
	return new FindAllMechanics().execute();
}

@Override
public void newProveedor(String código, String nombre) {
	new AddProveedor(código, nombre).execute();
}

@Override
public void deleteProveedor(Long idProveedor) {
	new DeleteProveedor(idProveedor).execute();
}

@Override
public List<Map<String, Object>> findAllProveedores() {
	return new FindAllProveedores().execute();
}

@Override
public void updateProveedor(Map<String,Object> proveedorDatos) {
	new UpdateProveedor(proveedorDatos).execute();
}

@Override
public Map<String, Object> findByCod(String cod) {
	return new FindProveedorCod(cod).execute();
}

@Override
public Map<String, Object> findByNom(String nom) {
	return new FindProveedorNom(nom).execute();
}

@Override
public List<Map<String, Object>> findPedidos(String cod) {
	return new FindPedidos(cod).execute();
}

@Override
public void recibirPedido(String cod) {
	new RecibirPedido(cod).execute();
}

}
