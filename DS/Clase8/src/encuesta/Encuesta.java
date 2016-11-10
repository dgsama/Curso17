package encuesta;

import java.util.ArrayList;
import java.util.Base64;

import actualizar.Actualizar;
import actualizar.BaseDeDatos;

public class Encuesta {

	private int si, no;
	private String pregunta;
	private ArrayList<Actualizar> actualizaciones;
	private BaseDeDatos bd;

	public Encuesta(String pregunta, Actualizar... listaAct) {
		this.pregunta = pregunta;
		actualizaciones.add(bd);
		for (Actualizar a : listaAct) {
			actualizaciones.add(a);
		}
	}

	public String getPregunta() {
		return pregunta;
	}

	public int getVotosSi() {
		return si;
	}

	public int getVotosNo() {
		return no;
	}

	public void incrementaSi() {
		si++;
	}

	public void incrementaNo() {
		no++;
	}

}
