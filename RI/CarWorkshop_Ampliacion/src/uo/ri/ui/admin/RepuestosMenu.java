package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.business.impl.Ampliacion.GestionRepuestos.ListarRepuestos;
import uo.ri.ui.admin.action.AddRepuestoAction;
import uo.ri.ui.admin.action.DeleteRepuestoAction;
import uo.ri.ui.admin.action.UpdateRepuestoAction;

public class RepuestosMenu extends BaseMenu {

	public RepuestosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de repuestos", null},
			
			{ "Añadir repuesto", 				AddRepuestoAction.class }, 
			{ "Modificar datos de repuesto", 	UpdateRepuestoAction.class }, 
			{ "Eliminar repuesto", 				DeleteRepuestoAction.class }, 
			{ "Listar repuestos", 				ListarRepuestos.class },
		};
	}

}
