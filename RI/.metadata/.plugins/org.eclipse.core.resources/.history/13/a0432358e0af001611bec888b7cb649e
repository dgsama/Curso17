package uo.ri.ui.admin.action;

import java.util.HashMap;
import java.util.Map;

import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del mecánico");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		Map<String, Object> info = new HashMap<String, Object>();
		info.put("id", id);
		info.put("nombre", nombre);
		info.put("apellidos", apellidos);

		new UpdateMechanic(info).execute();
		;

	}

}
