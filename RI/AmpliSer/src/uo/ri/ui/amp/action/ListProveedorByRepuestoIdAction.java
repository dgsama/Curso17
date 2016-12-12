package uo.ri.ui.amp.action;

import java.util.ArrayList;
import java.util.Map;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class ListProveedorByRepuestoIdAction implements Action{
	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del repuesto");

		// Procesar

		AdminService service = ServiceFactory.getAdminService();
		ArrayList<Map<String, Object>> lista;
		try {
			lista = service.findProveedoresByIdRepuesto(id);

			if (lista.size() == 0)
				System.out
						.println("El repuesto no existe o no hay ningun proveedor que lo suministre");
			else {

				for (int i = 0; i < lista.size(); i++) {
					System.out.println(lista.get(i));
				}
			}
		} catch (Exception e) {
			Console.println("Error al buscar los proveedores.");
		}
	}
}
