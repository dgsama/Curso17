package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.business.conf.ServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class FacturarReparacionesAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();
		
		// pedir las averias a incluir en la factura
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while ( masAverias() );

		//v2 //Map<String,Object> factura = new CashServiceImpl().createInvoiceFor(idsAveria);
		
		mostrarFactura(ServicesFactory.getCashService().createInvoiceFor(idsAveria));
		
	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

	private void mostrarFactura(Map<String,Object> factura) {
		
		Console.printf("Factura nº: %d\n", (long)factura.get("id"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", (Date)factura.get("fecha"));
		Console.printf("\tTotal: %.2f €\n", (double)factura.get("total"));
		Console.printf("\tIva: %.1f %% \n", (double)factura.get("iva"));
		Console.printf("\tTotal con IVA: %.2f €\n", (double)factura.get("totalConIva"));
	}
}
