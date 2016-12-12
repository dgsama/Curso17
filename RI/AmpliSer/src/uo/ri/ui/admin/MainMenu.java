package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.ui.amp.PedidoMenu;
import uo.ri.ui.amp.ProveedorMenu;
import uo.ri.ui.amp.RepuestoMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gestión de mecánicos", 			MecanicosMenu.class }, 
			{ "Gestión de repuestos", 			RepuestosMenu.class },
			{ "Gestión de tipos de vehículo", 	TiposVehiculoMenu.class },
			{ "GestiÃ³n de repuestos", RepuestoMenu.class },
			{ "GestiÃ³n de proveedores", ProveedorMenu.class }, 
			{ "GestiÃ³n de pedidos", PedidoMenu.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
