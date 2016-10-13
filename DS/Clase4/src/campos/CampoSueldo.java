package campos;

import clase4.Campo;

public class CampoSueldo extends Campo {

	public CampoSueldo(String etiqueta) {
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
			int sueldo = Integer.parseInt(getString());
			if (sueldo < 800 || sueldo > 1200) {
				valido = false;
			}
		}

		return valido;
	}

}
