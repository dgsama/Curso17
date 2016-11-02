package cambios;

import editor.Dibujo;
import editor.Figura;

public class CambioCrear implements Cambio {

	private Dibujo dibujo;
	private Figura figura;

	public CambioCrear(Dibujo dibujo, Figura figura) {
		this.dibujo = dibujo;
		this.figura = figura;
	}

	@Override
	public void rehacer() {
		dibujo.AddFigura(figura);
	}

	@Override
	public void deshacer() {
		dibujo.RemoveFigura(figura);
		
	}

}