package uo.ri.ui.admin.action;

import java.util.Map;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListProveedorAction implements Action{

	@Override
	public void execute() throws Exception {
		Console.println("\nListado de proveedores\n");  
		
		Console.println( "id \t código \t nombre" );
		
		for (Map<String, Object> prov : ServicesFactory.getAdminService().findAllProveedores()){
			Console.println( String.valueOf(prov.get("id") + "\t" + prov.get("cod")) + "\t\t" + 
					(String)prov.get("nombre") );
		}
	}

}