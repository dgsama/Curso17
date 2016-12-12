package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class UpdateRepuestoAction implements Action {
	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del repuesto");
		double precio = Console.readDouble("Nuevo precio");

		ServicesFactory.getAdminService().actualizarRepuesto(id, precio);

		// Mostrar resultado
		Console.println("Repuesto actualizado");

	}
}