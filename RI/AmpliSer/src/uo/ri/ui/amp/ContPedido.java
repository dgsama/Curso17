package uo.ri.ui.amp;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;

public class ContPedido {
	private List<Map<String, Object>> list;

	public ContPedido(List<Map<String, Object>> list) {
		this.list = list;
	}

	public boolean preguntar() {
		Console.println("Contenido del pedido: ");
		System.out.println(list);
		String respuesta;

		do {
			respuesta = Console.readString("¿Es correcto? [Indique S o N]");
		} while (!respuesta.equalsIgnoreCase("S")
				&& !respuesta.equalsIgnoreCase("N"));

		if (respuesta.equalsIgnoreCase("S")) {
			return true;
		} else
			return false;

	}
}
