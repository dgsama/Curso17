package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.ui.admin.action.ListarRepuestosProveedor;

public class PedidosMenu extends BaseMenu {

	public PedidosMenu() {
		menuOptions = new Object[][] { { "Listar pedidos de un proveedor", ListarRepuestosProveedor.class }, };
	}
}
