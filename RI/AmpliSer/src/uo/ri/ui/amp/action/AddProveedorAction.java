package uo.ri.ui.amp.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class AddProveedorAction implements Action{

	@Override
	public void execute() throws BusinessException {
		
		String nombre = Console.readString("Nombre");
		
		AdminService service = ServiceFactory.getAdminService();
		try {
			service.newProveedor(nombre);
			Console.println("Nuevo proveedor insertado");
		} catch (Exception e) {
			Console.println("Fallo al incluir nuevo proveedor.");
		}
	}
}