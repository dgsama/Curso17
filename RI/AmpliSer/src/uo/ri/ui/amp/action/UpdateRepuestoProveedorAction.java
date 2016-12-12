package uo.ri.ui.amp.action;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class UpdateRepuestoProveedorAction implements Action{

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long idProveedor = Console.readLong("Id del proveedor");
		Long idRepuesto = Console.readLong("Id del repuesto");
		Double precio = Console.readDouble("Precio del repuesto");

		AdminService service = ServiceFactory.getAdminService();
		try {
			service.updateRepuestoProveedor(idProveedor, idRepuesto, precio);
			Console.println("Se ha modificado el repuesto del proveedor");

		} catch (Exception e) {
			Console.println("Fallo al modificar el repuesto del proveedor.");
		}

	}
}
