package uo.ri.ui.amp.action;

import java.rmi.NotBoundException;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.ServiceFactory;

public class RecibirPedidoAction implements Action{


	@Override
	public void execute() throws BusinessException, NotBoundException {
		// Pedir datos
		Long id = Console.readLong("Id del pedido");

		// Procesar
		AdminService service = ServiceFactory.getAdminService();
		try {
			service.recibirPedido(id);
			// Mostrar resultado
			Console.println("Pedido recibido.");
		} catch (RuntimeException e) {
			Console.println("Error al recibir pedido.");
		} catch (Exception e) {
			Console.println("Pedido ya recibido.");
		}

	}
}
