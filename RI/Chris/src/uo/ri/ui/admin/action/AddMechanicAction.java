package uo.ri.ui.admin.action;


import uo.ri.business.conf.ServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		// Procesar
		//v2//new AdminServiceImpl().newMechanic(nombre, apellidos);
		ServicesFactory.getAdminService().newMechanic(nombre,apellidos);
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
