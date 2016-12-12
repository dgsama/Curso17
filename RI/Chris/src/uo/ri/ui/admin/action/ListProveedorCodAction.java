package uo.ri.ui.admin.action;

import java.util.Map;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListProveedorCodAction implements Action{

	@Override
	public void execute() throws Exception {
				
		// PEDIR DATOS
		String cod = Console.readString("\nCódigo del proveedor");
		
		Map<String,Object> proveedor = ServicesFactory.getAdminService()
				.findByCod(cod);	
		
		// MOSTRAR DATOS
		if(proveedor.get("id")!=null){
			Console.println("\nid \t código \t nombre");
			Console.print( (long)proveedor.get("id") + "\t" + (String)proveedor
				.get("cod") + "\t\t" + (String)proveedor.get("nombre") + "\n");
		} else { Console.println("No se ha encontrado al proveedor con esos " +
				"datos");}
	}

}
