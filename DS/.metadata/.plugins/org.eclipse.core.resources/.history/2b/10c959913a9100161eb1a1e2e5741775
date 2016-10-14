package campos;
import java.io.*;

import clase4.Campo;

public class CampoNumero extends Campo {

	

	public CampoNumero(String etiqueta) {
		super(etiqueta);
	}

	@Override
	public boolean comprobar() {
		boolean valido = true;
		for (char ch : getString().toCharArray()) {
			if (!Character.isDigit(ch)) {
				valido = false;
				break;
			}
		}
		
		return valido;
	}

	
}
