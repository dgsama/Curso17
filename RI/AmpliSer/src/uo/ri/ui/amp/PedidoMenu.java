package uo.ri.ui.amp;

import alb.util.menu.BaseMenu;
import uo.ri.ui.amp.action.ListPedidosProveedorAction;
import uo.ri.ui.amp.action.RecibirPedidoAction;

public class PedidoMenu extends BaseMenu{
	public PedidoMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gestion de pedidos", null },

				{ "Recibir Pedido", RecibirPedidoAction.class },
				{ "Listar pedidos de un proveedor",
						ListPedidosProveedorAction.class }, };
	}
}
