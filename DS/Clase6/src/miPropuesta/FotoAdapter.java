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
		return foto.getDescripci�n();
	}

	@Override
	public Coordenadas getCoordinates() {
		return foto.getCoordenadas();
	}

	@Override
	public String getTooltipText() {
		return foto.getDescripci�n() + "\n\n\tUsuario: " + foto.getUsuario();
	}

	@Override
	public void open() {
		foto.descargar();

	}

}
