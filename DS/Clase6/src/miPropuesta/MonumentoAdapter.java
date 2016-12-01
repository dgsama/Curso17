package miPropuesta;

import components.Coordenadas;
import components.GPS;
import model.Monumento;
import vista.Lugar;

public class MonumentoAdapter implements Lugar {

	private Monumento monument;

	public MonumentoAdapter(Monumento monument) {
		this.monument = monument;
	}

	@Override
	public String getNombre() {
		return monument.getNombre();
	}

	@Override
	public Coordenadas getCoordinates() {
		return new GPS().getCoordenadas(monument.getDirección());
	}

	@Override
	public String getTooltipText() {
		return "Nombre: " + monument.getNombre() + ".\nAutor: " + monument.getAutor() + ".";
	}

	@Override
	public void open() {
		new GPS().navigate(monument.getDirección());

	}

}
