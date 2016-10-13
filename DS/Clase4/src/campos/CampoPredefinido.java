package campos;

import java.io.*;

import clase4.Campo;

public class CampoPredefinido extends Campo {

	private String[] valores;

	public CampoPredefinido(String etiqueta, String... valores) {
		super(etiqueta);
		this.valores = valores;

	}

	@Override
	public boolean comprobar() {
		boolean valido = false;

		for (String valor : valores) {
			if (getString().toLowerCase().equals(valor.toLowerCase())) {
				valido = true;
				break;
			}
		}

		return valido;
	}
}
