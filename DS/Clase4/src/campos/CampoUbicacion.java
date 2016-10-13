package campos;

import clase4.Campo;

public class CampoUbicacion extends Campo {

	private String[] valores;

	public CampoUbicacion(String etiqueta, String... valores) {
		super(etiqueta);
		this.valores = valores;
	}

	@Override
	public boolean comprobar() {
		boolean valido = false;

		for (String valor : valores) {
			if (getString().toLowerCase().equals(valor.toLowerCase())) {
				valido = true;
				break;
			}
		}

		if (valido == false) {
			valido = true;
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
		}

		return valido;
	}

}
