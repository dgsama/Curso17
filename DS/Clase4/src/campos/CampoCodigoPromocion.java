package campos;

import clase4.Campo;

public class CampoCodigoPromocion extends Campo {

	public CampoCodigoPromocion(String etiqueta) {
		super(etiqueta);
	}

	@Override
	public boolean comprobar() {
		boolean valido = true;

		for (char ch : getString().toCharArray()) {
			if (!Character.isLetter(ch)) {
				valido = false;
				break;
			}
		}
		if (valido == false) {
			if (getString().length() == 3) {
				valido = true;

				for (char ch : getString().toCharArray()) {
					if (!Character.isDigit(ch)) {
						valido = false;
						break;
					}
				}
			}
		}

		return valido;
	}

}
