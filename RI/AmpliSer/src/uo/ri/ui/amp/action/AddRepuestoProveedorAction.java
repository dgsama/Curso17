package uo.ri.ui.amp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class AddRepuestoProveedorAction implements Action{

	@Override
	public void execute() throws uo.ri.common.BusinessException {

		// Pedir datos
		Long idProveedor = Console.readLong("Id del proveedor");

		AdminService service = ServiceFactory.getAdminService();
		ArrayList<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		String respuesta;

		do {
			map = new HashMap<String, Object>();
			Long idRepuesto = Console.readLong("Id del repuesto");
			Double precio = Console.readDouble("Precio del repuesto");
			map.put("IDRepuesto", idRepuesto);
			map.put("Precio", precio);
			lista.add(map);
			respuesta = Console
					.readString("Â¿Quiere incluir mas repuestos? [Indique S o N]");
		} while (respuesta.equalsIgnoreCase("S"));

		if (respuesta.equalsIgnoreCase("N")) {
			try {
				service.addRepuestosProveedor(lista, idProveedor);
				System.out.println("Repuesto incluido en el proveedor");
			} catch (Exception e) {
				Console.println("Fallo al insertar repuesto en el proveedor.");
			}
		} else
			Console.println("Error. No se ha pulsado S o N.");

	}

}
