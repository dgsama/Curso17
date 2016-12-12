package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class AddRepuestoAction implements Action {

	public void execute() throws BusinessException {

		// Pedir datos
		String descripcion = Console.readString("Descripcion");
		double precio = Console.readDouble("Precio");
		int existencias = Console.readInt("Existencias");
		int existencias_minimas = Console.readInt("Existencias Minimas");
		int existencias_maximas = Console.readInt("Existencias Maximas");

		// Procesar
		ServicesFactory.getAdminService().añadirRepuesto(descripcion, precio, existencias_minimas, existencias_maximas,
				existencias);
		;

		// Mostrar resultado
		Console.println("Nuevo repuesto añadido");
	}

}
