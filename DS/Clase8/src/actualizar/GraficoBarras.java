package actualizar;

public class GraficoBarras implements Actualizar {

	@Override
	public void execute() {
		actualizaGráficoBarras();
	}

	private void actualizaGráficoBarras() {
		System.out.println("Dibujando gráfico de barras");
	}

}
