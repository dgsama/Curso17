package game;

import java.awt.Point;

import adapter.AdaptadorPlataforma;
import adapter.AndroidAdapter;
import adapter.PSAdapter;
import adapter.WindowsAdapter;
import platform.*;
import platform.android.*;
import platform.playstation.*;
import platform.windows.*;

/* Esta clase/paquete ser�a el c�digo del videojuego, el cual se quiere reutilizar
 * en las distintas plataformas 
 */

enum Platform {
	ANDROID, WINDOWS, PLAYSTATION
};

public class BallGame {

	// Seleccionar para qu� plataforma se quiere generar el juego
	private Platform platform = Platform.ANDROID;
	// private Platform platform = Platform.WINDOWS;
	// private Platform platform = Platform.PLAYSTATION;

	private AdaptadorPlataforma consola;

	public void play() {

		// Inicializar la API adecuada
		setAPI();

		Image2D image = loadImage("Bola.jpg");

		// L�gica principal del juego
		for (int i = 0; i < 10; i++) {
			Point point = getPosition();
			// Comprobar colisiones.return playsta..
			// Imprimir marcador...
			// Otra l�gica del juego...
			drawBall(image, point);
		}
	}

	private void setAPI() {
		if (platform == Platform.ANDROID)
			consola = new AndroidAdapter();
		else if (platform == Platform.WINDOWS)
			consola = new WindowsAdapter();
		else
			consola = new PSAdapter();
	}

	private Image2D loadImage(String file) {
		return consola.loadImage(file);
	}

	private Point getPosition() {
		return consola.getPosition();
	}

	private void drawBall(Image2D image, Point point) {
		consola.drawBall(image, point);
	}

}