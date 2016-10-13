package campos;

import clase4.Campo;

public class CampoEdad extends Campo {

	public CampoEdad(String etiqueta) {
		super(etiqueta);
	}

	@Override
	public boolean comprobar() {
		boolean valido = true;
		for (char ch : getString().toCharArray()) {
			if (!Character.isDigit(ch)) {
				valido = false;
				break;
			}
		}
		if (valido == true) {
			if (Integer.parseInt(getString()) < 18) {
				valido = false;
			}
		}

		return valido;
	}

}
