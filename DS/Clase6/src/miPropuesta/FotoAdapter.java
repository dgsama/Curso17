package miPropuesta;

import components.Coordenadas;
import model.Foto;
import vista.Lugar;

public class FotoAdapter implements Lugar {

	private Foto foto;

	public FotoAdapter(Foto foto) {
		this.foto = foto;
	}

	@Override
	public String getNombre() {
		return foto.getDescripción();
	}

	@Override
	public Coordenadas getCoordinates() {
		return foto.getCoordenadas();
	}

	@Override
	public String getTooltipText() {
		return foto.getDescripción() + "\n\n\tUsuario: " + foto.getUsuario();
	}

	@Override
	public void open() {
		foto.descargar();

	}

}
