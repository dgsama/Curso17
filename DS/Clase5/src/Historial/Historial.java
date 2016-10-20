package Historial;

import java.util.Stack;

public class Historial {

	private Stack<Cambio> stackUndo;
	private Stack<Cambio> stackRedo;

	public Historial() {
		stackRedo = new Stack<Cambio>();
		stackUndo = new Stack<Cambio>();
	}

	public void nuevoCambio(Cambio c) {
		stackUndo.push(c);
		stackUndo.clear();
	}

	public void undoHecho() {
		stackRedo.push(stackUndo.pop());
	}

	public void uredoHecho() {
		stackUndo.push(stackRedo.pop());
	}
}
