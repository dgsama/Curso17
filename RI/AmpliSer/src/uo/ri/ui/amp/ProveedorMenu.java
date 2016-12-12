package uo.ri.ui.amp;

import alb.util.menu.BaseMenu;
import uo.ri.ui.amp.action.AddProveedorAction;
import uo.ri.ui.amp.action.AddRepuestoProveedorAction;
import uo.ri.ui.amp.action.DeleteProveedorAction;
import uo.ri.ui.amp.action.DeleteRepuestoProveedorAction;
import uo.ri.ui.amp.action.FindProveedorByIdAction;
import uo.ri.ui.amp.action.FindProveedorByNameAction;
import uo.ri.ui.amp.action.ListProveedorAction;
import uo.ri.ui.amp.action.ListProveedorByRepuestoIdAction;
import uo.ri.ui.amp.action.UpdateProveedorAction;
import uo.ri.ui.amp.action.UpdateRepuestoProveedorAction;
import uo.ri.ui.amp.action.VerRepuestoProveedorAction;

public class ProveedorMenu extends BaseMenu{
	public ProveedorMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > GestiÃ³n de proveedores", null },

				{ "AÃ±adir proveedor", AddProveedorAction.class },
				{ "Modificar datos de proveedor", UpdateProveedorAction.class },
				{ "Eliminar proveedor", DeleteProveedorAction.class },
				{ "Listar todos los proveedores", ListProveedorAction.class },
				{ "Buscar proveedor por codigo", FindProveedorByIdAction.class },
				{ "Buscar proveedor por nombre",
						FindProveedorByNameAction.class },
				{ "Ver repuestos suministrados por un proveedor",
						VerRepuestoProveedorAction.class },
				{ "AÃ±adir repuestos suministrados por un proveedor",
						AddRepuestoProveedorAction.class },
				{ "Eliminar repuestos suministrados por un proveedor",
						DeleteRepuestoProveedorAction.class },
				{ "Modificar repuestos suministrados por un proveedor",
						UpdateRepuestoProveedorAction.class },
				{ "Listar proveedores que venden un determinado repuesto",
						ListProveedorByRepuestoIdAction.class }, };
	}
}
