package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import uo.ri.business.admin.ListMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Console.println("\nListado de mecánicos\n");

		List<Map<String, Object>> info = new ListMechanic().execute();
		mostrarInfo(info);

	}

	private void mostrarInfo(List<Map<String, Object>> info) {
		for (Map<String, Object> m : info) {
			Console.printf("ID: %s Nombre: %s Apellidos: %s\n", m.get("id"),
					m.get("nombre"), m.get("apellidos"));
		}
	}
}
