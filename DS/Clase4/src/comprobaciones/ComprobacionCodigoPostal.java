package comprobaciones;

public class ComprobacionCodigoPostal implements Comprobacion {

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
			if (texto.length() != 5) {
				valido = false;
			}
		}

		return valido;
	}

}
