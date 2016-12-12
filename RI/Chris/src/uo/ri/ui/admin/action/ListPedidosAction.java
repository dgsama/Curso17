package uo.ri.ui.admin.action;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListPedidosAction implements Action{

	@Override
	public void execute() throws Exception {
		// PEDIR DATOS
		String cod = Console.readString("Código del proveedor");
		
		Console.println( "\nid \t código \t pedido \t recibido \t estado " +
				 "\t proveedor" );
		
		// MOSTRAR DATOS
		List<Map<String,Object>> pedidos = ServicesFactory.getAdminService().
				findPedidos(cod);
		
		if(pedidos.size()==0)
			Console.println("\nNo se ha encontrado pedidos de ese proveedor");
		else
		for(Map<String,Object> pedido : pedidos) 
			Console.println( String.valueOf(pedido.get("id")) + "\t" +
					String.valueOf(pedido.get("cod") + "\t\t" + (Date)pedido.get(
					"creado")) + "\t" + (Date)pedido.get("recibido") + 
					"\t" + (String)pedido.get("estado") + "\t" +  
					(String)pedido.get("cod_proveedor"));
	}

}
