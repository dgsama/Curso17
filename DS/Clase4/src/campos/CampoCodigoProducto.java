package campos;

import clase4.Campo;

public class CampoCodigoProducto extends Campo {

	public CampoCodigoProducto(String etiqueta) {
		super(etiqueta);
	}

	@Override
	public boolean comprobar() {
		if (getString().length() != 4) {
			return false;
		} else {
			return true;
		}
	}

}
