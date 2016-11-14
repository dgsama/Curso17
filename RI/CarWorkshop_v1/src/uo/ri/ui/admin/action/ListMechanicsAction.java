package uo.ri.ui.admin.action;

import java.util.Map;

import uo.ri.business.admin.ListMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Console.println("\nListado de mecánicos\n");

		Map<String, Object> info = new ListMechanic().execute();
		int i = 0;
		while (!info.containsValue(i)) {
			Console.printf(info.get(i).toString());
			i++;
		}

	}
}
