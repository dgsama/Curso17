package actions;

import java.util.List;

import util.Macro;

public class EjecutarMacro {

	private StringBuilder texto;
	private String nombre;
	private List<Macro> macros;

	public EjecutarMacro(StringBuilder texto, String nombre, List<Macro> macros) {
		this.texto = texto;
		this.nombre = nombre;
		this.macros = macros;
	}

	public void execute() {
		Macro mac = null;
		for (Macro m : this.macros) {
			if (m.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				mac = m;
				for (Action act : mac.getAcciones()) {
					act.execute();
				}
				break;
			}
		}
	}

}