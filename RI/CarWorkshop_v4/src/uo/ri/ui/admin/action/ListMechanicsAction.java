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
		Console.println("\nListado de mecánicos\n");

		List<Map<String, Object>> info = ServicesFactory.getAdminService().findAllMechanics();
		mostrarInfo(info);

	}

	private void mostrarInfo(List<Map<String, Object>> info) {
		for (Map<String, Object> m : info) {
			Console.printf("ID: %s Nombre: %s Apellidos: %s\n", m.get("id"), m.get("nombre"), m.get("apellidos"));
		}
	}
}