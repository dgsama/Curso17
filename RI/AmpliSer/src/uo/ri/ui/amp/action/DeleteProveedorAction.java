package uo.ri.ui.amp.action;


import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;
import uo.ri.common.BusinessException;

public class DeleteProveedorAction implements Action{

	@Override
	public void execute() throws BusinessException {
		Long idProveedor = Console.readLong("Id de proveedor");

		AdminService service = ServiceFactory.getAdminService();
		try {
			service.deleteProveedor(idProveedor);

			Console.println("Se ha eliminado el proveedor");
		} catch (RuntimeException e) {
			Console.println("El proveedor no existe o tiene datos pendientes (Pedidos sin cerrar o ofertas disponibles).");
		} catch (Exception e) {
			Console.println("Fallo al eliminar proveedor.");
		}

	}

}
