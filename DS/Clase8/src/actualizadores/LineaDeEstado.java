package actualizadores;

public class LineaDeEstado implements Actualizar {

    @Override
    public void execute(int i, int j) {
	mostrarInfo(i, j);

    }

    private void mostrarInfo(int i, int j) {
	double si = ((double) i / (i + j));
	double no = ((double) j / (i + j));

	System.out.println("Las estadisticas son:" + "\n\tSi: " + Math.round(si * 100.0) + "%\n\tNo: "
		+ Math.round(no * 100.0) + "%");
    }
}
