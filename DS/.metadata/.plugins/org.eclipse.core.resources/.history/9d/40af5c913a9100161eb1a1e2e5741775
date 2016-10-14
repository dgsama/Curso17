package campos;
import java.io.*;

import clase4.Campo;

public class CampoTexto extends Campo {


	public CampoTexto(String etiqueta) {
		super(etiqueta);
	}

	@Override
	public boolean comprobar() {
		boolean valido = true;
		
		for (char ch : getString().toCharArray()) {
			if (!Character.isLetter(ch)) {
				valido = false;
				break;
			}
		}
		return valido;
	}
}
