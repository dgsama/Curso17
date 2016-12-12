package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class DeleteRepuestoAction implements Action {
	@Override
	public void execute() throws BusinessException {
		Long id = Console.readLong("Id del repuesto");

		ServicesFactory.getAdminService().borrarRepuesto(id);

		Console.println("Se ha eliminado el repuesto");
	}
}
