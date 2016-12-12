package uo.ri.ui.amp.action;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class DeleteRepuestoAction implements Action {
	@Override
	public void execute() throws BusinessException {
		Long idRepuesto = Console.readLong("Id de repuesto");

		AdminService service = ServiceFactory.getAdminService();
		try {
			service.deleteRepuesto(idRepuesto);
			Console.println("Se ha eliminado el repuesto");
		} catch (RuntimeException e) {
			Console.println("El repuesto no existe.");
		} catch (Exception e) {
			Console.println("Fallo al eliminar repuesto.");
		}

	}
}
