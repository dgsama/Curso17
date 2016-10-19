package comprobaciones;

public class ComprobacionPredefinido implements Comprobacion {

	private String[] pruebas;

	public ComprobacionPredefinido(String... ciudad) {
		this.pruebas = ciudad;
	}

	@Override
	public boolean comprobar(String texto) {
		boolean valido = false;

		for (String valor : pruebas) {
			if (texto.toLowerCase().equals(valor.toLowerCase())) {
				valido = true;
				break;
			}
		}

		return valido;
	}

}