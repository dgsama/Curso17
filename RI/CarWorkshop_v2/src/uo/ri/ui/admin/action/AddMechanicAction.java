package uo.ri.ui.admin.action;

import java.util.HashMap;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		
		Map<String, Object> info = new HashMap<String, Object>();
		info .put("nombre", nombre);
		info.put("apellidos", apellidos);

		// Procesar
		new AddMechanic(info).execute();
	}

}
