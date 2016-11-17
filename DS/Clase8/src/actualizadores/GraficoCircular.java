package actualizadores;

public class GraficoCircular implements Actualizar {
	private int counter;

	public GraficoCircular() {
		counter = 0;
	}

	@Override
	public void execute(int i, int j) {
		counter++;
		if (counter > 3) {
			actualizaGraficoCircular();
		}

	}

	private void actualizaGraficoCircular() {
		System.out.println("Dibujando gr�fico circular");
	}

}
