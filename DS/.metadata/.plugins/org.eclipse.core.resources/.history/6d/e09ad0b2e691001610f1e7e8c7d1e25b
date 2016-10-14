package comprobaciones;

public class ComprobacionTexto implements Comprobacion {

	@Override
	public boolean comprobar(String texto) {
		boolean valido = true;

		for (char ch : texto.toCharArray()) {
			if (!Character.isLetter(ch)) {
				valido = false;
				break;
			}
		}
		return valido;
	}

}
