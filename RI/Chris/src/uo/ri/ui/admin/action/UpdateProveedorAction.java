package uo.ri.ui.admin.action;

import java.util.HashMap;
import java.util.Map;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class UpdateProveedorAction implements Action{

	@Override
	public void execute() throws Exception {
		new ListProveedorAction().execute();
		
		// Pedir datos
		Long id = Console.readLong("\nId del proveedor"); 
		String nombre = "";
		String cod = "";

		do{
			nombre = Console.readString("Nuevo nombre del proveedor (máximo 50 "
					+ "caracteres)"); 
		}while(nombre.equals("") || nombre.length()>50);
		
		do{
			cod = Console.readString("Nuevo código del proveedor (máximo 6 " +
						"caracteres)");	
		}while(cod.equals("") || cod.length()>6);
		
		Map<String,Object> proveedor = new HashMap<String,Object>();
		
		proveedor.put("id",id);
		proveedor.put("nombre",nombre);
		proveedor.put("cod",cod);
		
		ServicesFactory.getAdminService().updateProveedor(proveedor);
	
		
		// Mostrar resultado
		Console.println("Proveedor actualizado");
	}
	
}
