package figuras;

import java.awt.Point;

public class Cuadrado implements Figura {
	int x, y; // Coordenadas del lugar donde se ha pinchado el ratón
	Point esquina;
	int ancho, alto; // Atributos del triángulo


	@Override
	public boolean hePinchado() {
		boolean pinchado = (esquina.x <= x && x <= esquina.x + ancho)
				&& (esquina.y <= y && y <= esquina.y + alto);
		return pinchado;
	}


	@Override
	public void dibujar() {
		// TODO Auto-generated method stub
		
	}
}
