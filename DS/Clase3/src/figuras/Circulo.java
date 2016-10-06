package figuras;

import java.awt.Point;

public class Circulo implements Figura {

	Point centro;
	int radio;
	int xP;
	int yP;// Coordenadas del lugar donde se ha pinchado el rat�n
	int xS;
	int yS;

	public Circulo() {
		centro = new Point(xS - xP, yS - yP);
	}

	public double distancia() {
		double aux = 0;

		aux = Math.sqrt(Math.pow(xP - centro.getX(), 2)
				+ Math.pow(yP - centro.getY(), 2));

		return aux;
	}

	@Override
	public boolean hePinchado() {
		boolean pinchado = distancia() < radio;
		return pinchado;
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub

	}

}