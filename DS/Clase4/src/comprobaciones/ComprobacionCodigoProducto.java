package comprobaciones;

public class ComprobacionCodigoProducto implements Comprobacion {

	@Override
	public boolean comprobar(String texto) {
		if (texto.length() != 4) {
			return false;
		} else {
			return true;
		}

	}

}
