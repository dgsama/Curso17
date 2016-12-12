package uo.ri.ui.amp.action;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;
import uo.ri.conf.ServiceFactory;

public class AddRepuestoAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		String descripcion = Console.readString("Nombre");
		Double precio = Console.readDouble("Precio");
		Integer unidades = Console.readInt("Unidades en stock");
		Integer stockMinimo = Console.readInt("Stock minimo permitido");
		

		// Procesar

		AdminService service = ServiceFactory.getAdminService();
		try {
			service.newRepuesto(descripcion, precio, unidades, stockMinimo);
			Console.println("Nuevo repuesto insertado");
		} catch (Exception e) {
			Console.println("Fallo al incluir nuevo repuesto.");
		}
	}
}
