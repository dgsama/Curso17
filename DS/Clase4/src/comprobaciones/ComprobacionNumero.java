package comprobaciones;

public class ComprobacionNumero implements Comprobacion {

	@Override
	public boolean comprobar(String texto) {
		boolean valido = true;
		for (char ch : texto.toCharArray()) {
			if (!Character.isDigit(ch)) {
				valido = false;
				break;
			}
		}

		return valido;
	}

}