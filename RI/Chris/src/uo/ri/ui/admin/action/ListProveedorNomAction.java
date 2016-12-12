package uo.ri.ui.admin.action;

import java.util.Map;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListProveedorNomAction implements Action{

	@Override
	public void execute() throws Exception {
		// PEDIR DATOS
		String nom = Console.readString("\nNombre del proveedor");
		
		Map<String,Object> proveedor = ServicesFactory.getAdminService()
				.findByNom(nom);	
		
		if(proveedor.get("id")!=null){
			Console.println("\n id \t código \t nombre");
			Console.print( (long)proveedor.get("id") + "\t" + (String)proveedor
				.get("cod") + "\t\t" + (String)proveedor.get("nombre") + "\n");
		} else { Console.println("No se ha encontrado al proveedor con esos " +
				"datos");}
	}
			
	
}
