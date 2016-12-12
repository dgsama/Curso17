package uo.ri.ui.admin.action;

import uo.ri.business.conf.ServicesFactory;
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
		
		//v2//new AdminServiceImpl().updateMechanic(id,nombre,apellidos);
		ServicesFactory.getAdminService().updateMechanic(id,nombre,apellidos);

		
		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
