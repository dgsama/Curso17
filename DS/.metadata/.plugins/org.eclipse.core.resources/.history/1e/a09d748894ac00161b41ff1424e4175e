package main;

import java.io.*;

import actualizadores.GraficoBarras;
import actualizadores.GraficoCircular;
import actualizadores.LineaDeEstado;
import java.io.*;

import encuesta.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Encuesta encuesta = new Encuesta("�Está a favor de la energia nucelar?");

		encuesta.addActualizador(new GraficoCircular());
		encuesta.addActualizador(new GraficoBarras());
		encuesta.addActualizador(new LineaDeEstado());

		UserInterface userInterface = new UserInterface();
		userInterface.rellena(encuesta);
	}

}
