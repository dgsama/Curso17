package actualizadores;

public class BaseDeDatos implements Actualizar {

	@Override
	public void execute(int i, int j) {
		guardaResultados();

	}

	private void guardaResultados() {
		System.out.println("Guardando resultados");
	}

}
