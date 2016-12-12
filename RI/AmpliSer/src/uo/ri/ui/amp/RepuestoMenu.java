package uo.ri.ui.amp;

import alb.util.menu.BaseMenu;
import uo.ri.ui.amp.action.AddRepuestoAction;
import uo.ri.ui.amp.action.DeleteRepuestoAction;
import uo.ri.ui.amp.action.ListRepuestoAction;
import uo.ri.ui.amp.action.UpdateRepuestoAction;

public class RepuestoMenu extends BaseMenu{
	public RepuestoMenu() {
		menuOptions = new Object[][] { { "Administrador > GestiÃ³n de repuestos", null },

		{ "AÃ±adir repuesto", AddRepuestoAction.class }, { "Modificar detalles de repuesto", UpdateRepuestoAction.class },
				{ "Eliminar repuesto", DeleteRepuestoAction.class }, { "Listado de repuestos", ListRepuestoAction.class }, };
	}
}
