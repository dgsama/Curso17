package comprobaciones;

public class ComprobacionSueldo implements Comprobacion {

	@Override
	public boolean comprobar(String texto) {
		boolean valido = true;
		for (char ch : texto.toCharArray()) {
			if (!Character.isDigit(ch)) {
				valido = false;
				break;
			}
		}
		if (valido == true) {
			int sueldo = Integer.parseInt(texto);
			if (sueldo < 800 || sueldo > 1200) {
				valido = false;
			}
		}

		return valido;
	}

}