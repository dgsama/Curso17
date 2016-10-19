package comprobaciones;

public class ComprobacionEdad implements Comprobacion {

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
			if (Integer.parseInt(texto) < 18) {
				valido = false;
			}
		}

		return valido;
	}

}