package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class FacturarReparacionesAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();

		// pedir las averias a incluir en la factura
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while (masAverias());

		Map<String, Object> infoFactura;

		infoFactura = ServicesFactory.getCashService().facturarReparaciones(idsAveria);

		mostrarFactura(infoFactura);

	}

	private void mostrarFactura(Map<String, Object> infoFactura) {

		Console.printf("Factura nº: %d\n", infoFactura.get("numeroFactura"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", infoFactura.get("fechaFactura"));
		Console.printf("\tTotal: %.2f €\n", infoFactura.get("totalFactura"));
		Console.printf("\tIva: %.1f %% \n", infoFactura.get("iva"));
		Console.printf("\tTotal con IVA: %.2f €\n", infoFactura.get("importe"));
	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}