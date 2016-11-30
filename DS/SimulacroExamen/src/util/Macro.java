package util;

import java.util.ArrayList;
import java.util.List;

import actions.Action;

public class Macro {

	private String nombre;
	private List<Action> acciones;

	public Macro(String nombre) {
		this.nombre = nombre;
		acciones = new ArrayList<Action>();
	}

	public String getNombre() {
		return nombre;
	}

	public List<Action> getAcciones() {
		return acciones;
	}

}