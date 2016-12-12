package uo.ri.ui.admin;

import uo.ri.ui.admin.action.ListPedidosAction;
import uo.ri.ui.admin.action.RecibirPedidoAction;
import alb.util.menu.BaseMenu;

public class PedidosMenu extends BaseMenu{
	
	public PedidosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de pedidos", null},
			
			{ "Listar pedidos de un proveedor", ListPedidosAction.class},
			{ "Recibir pedido", RecibirPedidoAction.class},
		};
	}
}
