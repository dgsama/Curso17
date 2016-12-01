package game;

import java.awt.Point;

import platform.*;
import platform.android.*;
import platform.playstation.*;
import platform.windows.*;
import template.AndroidTemplate;
import template.PSTemplate;
import template.WindowsTemplate;

/* Esta clase/paquete sería el código del videojuego, el cual se quiere reutilizar
 * en las distintas plataformas 
 */

enum Platform {
	ANDROID, WINDOWS, PLAYSTATION
};

public abstract class BallGame {

	public void play() {

		Image2D image = loadImage("Bola.jpg");

		// Lógica principal del juego
		for (int i = 0; i < 10; i++) {
			Point point = getPosition();
			// Comprobar colisiones...
			// Imprimir marcador...
			// Otra lógica del juego...
			drawBall(image, point);
		}
	}

	public abstract Image2D loadImage(String file);

	public abstract Point getPosition();

	public abstract void drawBall(Image2D image, Point point);

}
