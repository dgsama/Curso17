package comprobaciones.composite;

import comprobaciones.Comprobacion;

public class ComprobacionAlguna implements Comprobacion {

	private Comprobacion[] comprobaciones;

	public ComprobacionAlguna(Comprobacion... comprobaciones) {
		this.comprobaciones = comprobaciones;
	}

	@Override
	public boolean comprobar(String texto) {
		boolean valido = false;

		for (Comprobacion c : comprobaciones) {
			valido = c.comprobar(texto);
			if (valido == true) {
				break;
			}
		}

		return valido;
	}
}
