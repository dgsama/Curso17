package uo.ri.ui.amp.action;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class UpdateProveedorAction implements Action{

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del proveedor");
		String nombre = Console.readString("Nombre");

		// Procesar

		AdminService service = ServiceFactory.getAdminService();
		try {
			service.updateProveedor(id, nombre);

			Console.println("Proveedor actualizado");
		} catch (RuntimeException e) {
			Console.println("El proveedor no existe o tiene datos pendientes (Pedidos sin cerrar o ofertas disponibles).");
		} catch (Exception e) {
			Console.println("Fallo al actualizar proveedor.");
		}

	}
}
