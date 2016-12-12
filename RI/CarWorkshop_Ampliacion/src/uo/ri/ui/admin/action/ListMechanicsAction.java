package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Console.println("\nListado de repuestos\n");

		List<Map<String, Object>> info = ServicesFactory.getAdminService().listaRepuestos();
		mostrarInfo(info);

	}

	private void mostrarInfo(List<Map<String, Object>> info) {
		for (Map<String, Object> m : info) {
			Console.printf(m.get("id") + " " + m.get("codigo") + " " + m.get("descripcion") + " " + m.get("existencias")
					+ " " + m.get("min") + " " + m.get("max"));
		}
	}
}
