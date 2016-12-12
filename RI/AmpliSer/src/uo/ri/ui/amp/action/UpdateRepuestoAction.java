package uo.ri.ui.amp.action;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class UpdateRepuestoAction implements Action{
	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del repuesto");
		String descripcion = Console.readString("Nombre");
		Double precio = Console.readDouble("Precio");

		// Procesar

		AdminService service = ServiceFactory.getAdminService();
		try {
			service.updateRepuesto(id, descripcion, precio);
			// Mostrar resultado
			Console.println("Repuesto actualizado");
		} catch (RuntimeException e) {
			Console.println("El repuesto no existe.");
		} catch (Exception e) {
			Console.println("Fallo al actualizar repuesto.");
		}

	}
}
