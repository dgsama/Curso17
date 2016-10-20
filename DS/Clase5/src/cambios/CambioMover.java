package cambios;

import editor.Figura;

public class CambioMover implements Cambio {

	private Figura figure;
	private int incX;
	private int incY;

	public CambioMover(Figura figure, int incX, int incY) {
		this.incX = incX;
		this.incY = incY;
		this.figure = figure;
	}

	public void rehacer() {
		figure.mover(incX, incY);
	}

	public void deshacer() {
		figure.mover(incY * (-1), incY * (-1));
	}

}
