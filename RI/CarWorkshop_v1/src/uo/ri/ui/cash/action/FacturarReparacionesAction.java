package uo.ri.ui.cash.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import uo.ri.business.impl.cash.FacturarReparaciones;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import alb.util.menu.Action;

public class FacturarReparacionesAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();

		// pedir las averias a incluir en la factura
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while (masAverias());

		Map<String, Object> infoFactura = new FacturarReparaciones()
				.execute(idsAveria);

		mostrarFactura(infoFactura);

	}

	private void mostrarFactura(Map<String, Object> infoFactura) {

		Console.printf("Factura nº: %d\n", infoFactura.get("numeroFactura"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n",
				infoFactura.get("fechaFactura"));
		Console.printf("\tTotal: %.2f €\n", infoFactura.get("totalFactura"));
		Console.printf("\tIva: %.1f %% \n", infoFactura.get("iva"));
		Console.printf("\tTotal con IVA: %.2f €\n", infoFactura.get("importe"));
	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ")
				.equalsIgnoreCase("s");
	}

}
