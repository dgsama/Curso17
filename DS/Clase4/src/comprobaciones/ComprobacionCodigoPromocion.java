package comprobaciones;

public class ComprobacionCodigoPromocion implements Comprobacion {

	@Override
	public boolean comprobar(String texto) {
		boolean valido = false;

		if (texto.length() == 3) {
			valido = true;

			for (char ch : texto.toCharArray()) {
				if (!Character.isDigit(ch)) {
					valido = false;
					break;
				}
			}

		}

		return valido;
	}

}
