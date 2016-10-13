package campos;

import clase4.Campo;

public class CampoCodigoPostal extends Campo {

	public CampoCodigoPostal(String etiqueta) {
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
			if (getString().length() != 5) {
				valido = false;
			}
		}

		return valido;
	}

}
