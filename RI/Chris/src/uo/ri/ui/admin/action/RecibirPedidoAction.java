package uo.ri.ui.admin.action;

import uo.ri.business.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class RecibirPedidoAction implements Action{

	@Override
	public void execute() throws Exception {

		new ListPedidoCodAction().execute();
		
		// PEDIR DATOS
		String cod = Console.readString("Código de pedido");
		
		ServicesFactory.getAdminService().recibirPedido(cod);
	}

}
