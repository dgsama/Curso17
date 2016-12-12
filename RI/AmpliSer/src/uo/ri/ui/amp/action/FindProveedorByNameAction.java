package uo.ri.ui.amp.action;

import java.util.ArrayList;
import java.util.Map;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class FindProveedorByNameAction implements Action{

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		String nombreBuscado = Console.readString("Nombre del proveedor");

		// Procesar

		AdminService service = ServiceFactory.getAdminService();
		Map<String, Object> lista;
		try {
			lista = service.findProveedorByName(nombreBuscado);
			Long codigo = (Long) lista.get("ID");
			String nombre = (String) lista.get("Nombre");

			System.out.println("ID\tNombre");
			System.out.println(codigo + "\t" + nombre);
			System.out.println();
			System.out.println("Repuestos suministrados:");
			ArrayList<Map<String, Object>> listaRep = service
					.findRepuestosProveedor(codigo);

			for (int i = 0; i < listaRep.size(); i++) {
				System.out.println(listaRep.get(i));
			}
		} catch (Exception e) {
			System.out.println("El proveedor que esta buscado no existe");
		}

	}
}
