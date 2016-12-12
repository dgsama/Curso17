package uo.ri.ui.amp.action;


import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class DeleteRepuestoProveedorAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long idProveedor = Console.readLong("Id del proveedor");
		Long idRepuesto = Console.readLong("Id del repuesto");

		AdminService service = ServiceFactory.getAdminService();
		try {
			service.deleteRepuestoProveedor(idProveedor, idRepuesto);
			Console.println("Se ha eliminado el repuesto del proveedor");

		} catch (Exception e) {
			Console.println("Fallo al eliminar el repuesto del proveedor.");
		}

	}
}
