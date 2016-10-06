package editor;

import java.awt.Point;
import java.util.*;

import figuras.Circulo;
import figuras.Cuadrado;
import figuras.Figura;
import figuras.Triangulo;

public class Dibujo {

	private Figura figura;
	private Point origen;
	private Point destino;
	private ArrayList<Figura> figuras;
	private boolean estaPinchado;

	public Dibujo() {
		figuras = new ArrayList<Figura>();
	}

	/*
	 * public void elegir() { if (figura.equals("Cuadrado")) { this.figura = new
	 * Cuadrado(); } else if (figura.equals("Circulo")) { this.figura = new
	 * Circulo(); } else if (figura.equals("Triangulo")) { this.figura = new
	 * Triangulo(); } }
	 * 
	 * public void pinchar(int x, int y) { origen = new Point(x, y);
	 * estaPinchado = true;
	 * 
	 * }
	 * 
	 * public void mover(int x, int y) { if (estaPinchado) { destino = new
	 * Point(x, y); } }
	 */

	public void dibujar() {
		for (Figura f : figuras) {
			f.dibujar();
		}

	}

}
