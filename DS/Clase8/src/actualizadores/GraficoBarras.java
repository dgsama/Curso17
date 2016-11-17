package actualizadores;

public class GraficoBarras implements Actualizar {

	private int counter;

	public GraficoBarras() {
		counter = 0;
	}

	@Override
	public void execute(int i, int j) {
		counter++;
		if (counter == 4) {
			counter = 0;
			actualizaGraficoBarras();
		}
	}

	private void actualizaGraficoBarras() {
		System.out.println("Dibujando gr�fico de barras");
	}

}
