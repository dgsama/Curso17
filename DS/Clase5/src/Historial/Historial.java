package Historial;

import java.util.Stack;

import cambios.Cambio;

public class Historial {

	private static Historial instance = null;
	private Stack<Cambio> stackUndo;
	private Stack<Cambio> stackRedo;

	private Historial() {
		stackRedo = new Stack<Cambio>();
		stackUndo = new Stack<Cambio>();
	}

	public void addCambio(Cambio c) {
		stackUndo.push(c);
		stackRedo.clear();
	}

	public static Historial getInstance() {
		if (instance == null) {
			instance = new Historial();
		}
		return instance;
	}

	public void rehacer() {
		if (!stackRedo.isEmpty()) {
			Cambio c = stackRedo.pop();
			c.rehacer();
			stackUndo.push(c);
		} else {
			System.out.println("No hay cambios que rehacer");
		}

	}

	public void deshacer() {
		if (!stackUndo.isEmpty()) {
			Cambio c = stackUndo.pop();
			c.deshacer();
			stackRedo.push(c);
		} else {
			System.out.println("No hay cambios que deshacer");
		}
	}
}
