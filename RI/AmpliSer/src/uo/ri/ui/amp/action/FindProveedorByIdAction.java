package uo.ri.ui.amp.action;

import java.util.ArrayList;
import java.util.Map;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class FindProveedorByIdAction implements Action{
	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del proveedor");

		// Procesar

		AdminService service = ServiceFactory.getAdminService();
		Map<String, Object> lista;
		try {
			lista = service.findProveedorByID(id);
			Long codigo = (Long) lista.get("ID");
			String nombre = (String) lista.get("Nombre");

			System.out.println("ID\tNombre");
			System.out.println(codigo + "\t" + nombre);
			System.out.println();
			System.out.println("Repuestos suministrados:");
			ArrayList<Map<String, Object>> listaRep = service
					.findRepuestosProveedor(id);

			for (int i = 0; i < listaRep.size(); i++) {
				System.out.println(listaRep.get(i));
			}
		} catch (Exception e) {
			System.out.println("El proveedor con id: " + id + " no existe");
		}

	}
}
