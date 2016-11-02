package propuesta2;

import model.*;
import components.*;
import vista.*;

public class LugarMonumento extends Monumento implements Lugar {

	public LugarMonumento(String name, String author, String address) {
		super(name, author, address);
	}

	@Override
	public Coordenadas getCoordinates() {
		return new GPS().getCoordenadas(getDirección());
	}

	@Override
	public String getTooltipText() {
		return "Nombre: " + getNombre() + ".\nAutor: " + getAutor() + ".";
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub

	}

}
