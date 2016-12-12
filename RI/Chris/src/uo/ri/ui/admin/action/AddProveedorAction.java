package uo.ri.ui.admin.action;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AddProveedorAction implements Action{

	@Override
	public void execute() throws Exception {

		// Pedir datos
		String nombre = "";
		String código = ""; 
		
		do{
			nombre = Console.readString("Nombre del proveedor (máximo 50 " +
					"caracteres)"); 
		}while(nombre.equals("") || nombre.length()>50);
		
		do{
			código = Console.readString("Código del proveedor (máximo 6 " +
						"caracteres)");	
		}while(código.equals("") || código.length()>6);
				
		
		ServicesFactory.getAdminService().newProveedor(código,nombre);
				
		// Mostrar resultado
		Console.println("Proceso finalizado");
	}

}
