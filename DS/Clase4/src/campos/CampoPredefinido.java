package campos;

import java.io.*;

import clase4.CampoAbstracto;
import comprobaciones.Comprobacion;

public class CampoPredefinido extends CampoAbstracto {

	public CampoPredefinido(String etiqueta, Comprobacion... comprobaciones) {
		super(etiqueta, comprobaciones);
	}

}
