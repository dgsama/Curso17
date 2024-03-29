package formulario;

import java.io.*;

import comprobaciones.Comprobacion;

public class Campo{
	private String etiqueta;
	private String texto;
	private Comprobacion[] comprobaciones;

	public Campo(String etiqueta, Comprobacion... comprobaciones) {
		this.etiqueta = etiqueta;
		this.comprobaciones = comprobaciones;
	}

	public void pideDato() {
		BufferedReader consola = new BufferedReader(new InputStreamReader(
				System.in));

		boolean valido;
		do {
			valido = true;
			try {
				System.out.print(etiqueta + ": ");
				texto = consola.readLine();

				for (Comprobacion c : comprobaciones) {
					valido = c.comprobar(texto);
					if (valido == false) {
						break;
					}
				}

			} catch (IOException ex) {
				System.out.println(ex);
			}
		} while (!valido);
	}

	public String getString() {
		return texto;
	}
}
