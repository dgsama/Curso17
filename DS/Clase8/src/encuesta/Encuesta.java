package encuesta;

import java.util.ArrayList;

import actualizadores.Actualizar;
import actualizadores.BaseDeDatos;

public class Encuesta {

	private int si, no;
	private String pregunta;
	private ArrayList<Actualizar> actualizaciones;
	private BaseDeDatos bd;

	public Encuesta(String pregunta) {
		this.pregunta = pregunta;
		actualizaciones = new ArrayList<Actualizar>();
		bd = new BaseDeDatos();
		actualizaciones.add(bd);
	}

	public void addActualizador(Actualizar actualizador) {
		actualizaciones.add(actualizador);
	}

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
		avisar();
	}

	public void incrementaNo() {
		no++;
		avisar();
	}

	private void avisar() {
		for (Actualizar act : actualizaciones) {
			act.execute(getVotosSi(), getVotosNo());
		}
	}

}