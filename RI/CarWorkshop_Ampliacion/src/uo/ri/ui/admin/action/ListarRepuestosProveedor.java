package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class ListarRepuestosProveedor implements Action {

	@Override
	public void execute() throws Exception {

		long id = Console.readLong("Id del proveedor");

		Console.println("\nListado de repuestos\n");

		List<Map<String, Object>> info = ServicesFactory.getAdminService().encontrarPedidosProveedor(id);
		mostrarInfo(info);

	}

	private void mostrarInfo(List<Map<String, Object>> info) {
		for (Map<String, Object> m : info) {
			Console.printf(m.get("codigo") + " " + m.get("fecha creacion").toString() + " "
					+ m.get("fecha recepcion").toString() + " " + m.get("estado"));
		}
	}

}