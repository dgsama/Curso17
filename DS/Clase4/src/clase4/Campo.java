package clase4;

import java.io.*;

public abstract class Campo {
	private String etiqueta;
	private String texto;

	public Campo(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public void pideDato() {
		BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));

		boolean valido;
		do {
			valido = true;
			try {
				System.out.print(etiqueta + ": ");
				texto = consola.readLine();

				valido = comprobar();
			} catch (IOException ex) {
				System.out.println(ex);
			}
		} while (!valido);
	}

	public String getString() {
		return texto;
	}
	
	public abstract boolean comprobar();
}
