package uo.ri.ui.admin.action;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteProveedorAction implements Action{

	@Override
	public void execute() throws Exception {
		new ListProveedorAction().execute();
		
		Long idProveedor = Console.readLong("Id de proveedor a eliminar"); 
		
		ServicesFactory.getAdminService().deleteProveedor(idProveedor);

		Console.println("Se ha eliminado el proveedor");		
	}

	
}
