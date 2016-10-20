package comprobaciones.composite;

import comprobaciones.Comprobacion;

public class ComprobarTodas implements Comprobacion {

	private Comprobacion[] comprobaciones;

	public ComprobarTodas(Comprobacion... comprobaciones) {
		this.comprobaciones = comprobaciones;
	}

	@Override
	public boolean comprobar(String texto) {
		boolean valido = true;

		for (Comprobacion c : comprobaciones) {
			valido = c.comprobar(texto);
			if (valido == false) {
				break;
			}
		}

		return valido;
	}

}
