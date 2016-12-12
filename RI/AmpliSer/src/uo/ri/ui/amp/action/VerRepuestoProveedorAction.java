package uo.ri.ui.amp.action;

import java.util.ArrayList;
import java.util.Map;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class VerRepuestoProveedorAction implements Action{

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del proveedor");

		AdminService service = ServiceFactory.getAdminService();
		ArrayList<Map<String, Object>> lista;
		try {
			lista = service.findRepuestosProveedor(id);

			for (int i = 0; i < lista.size(); i++) {
				System.out.println(lista.get(i));
			}
		} catch (Exception e) {
			Console.println("Fallo al buscar los repuestos del proveedor.");
		}

	}
}
