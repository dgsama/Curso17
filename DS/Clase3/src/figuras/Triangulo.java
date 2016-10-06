package figuras;

import java.awt.Point;

public class Triangulo implements Figura {

	int x, y; // Coordenadas del lugar donde se ha pinchado el rat�n
	Point v1, v2, v3; // Los tres v�rtices del tri�ngulo

	Point posicion = new Point(x, y);

	@Override
	public boolean hePinchado() {
		boolean pinchado = posicion.equals(v1) || posicion.equals(v2)
				|| posicion.equals(v3);
		return pinchado;
	}

	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		
	}

}