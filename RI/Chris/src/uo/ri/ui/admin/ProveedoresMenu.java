package uo.ri.ui.admin;

import uo.ri.ui.admin.action.ListProveedorAction;
import uo.ri.ui.admin.action.AddProveedorAction;
import uo.ri.ui.admin.action.DeleteProveedorAction;
import uo.ri.ui.admin.action.ListProveedorCodAction;
import uo.ri.ui.admin.action.ListProveedorNomAction;
import uo.ri.ui.admin.action.UpdateProveedorAction;
import alb.util.menu.BaseMenu;

public class ProveedoresMenu extends BaseMenu{

	public ProveedoresMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de proveedores", null},
			
			{ "Añadir proveedor", 				AddProveedorAction.class }, 
			{ "Modificar datos de proveedor", 	UpdateProveedorAction.class }, 
			{ "Eliminar proveedor", 			DeleteProveedorAction.class }, 
			{ "Listar proveedores",				ListProveedorAction.class },
			{ "Buscar proveedor por código",	ListProveedorCodAction.class},
			{ "Buscar proveedor por nombre",	ListProveedorNomAction.class},
		};
	}
}
