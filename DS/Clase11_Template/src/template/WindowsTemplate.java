package template;

import game.BallGame;

import java.awt.Point;

import platform.Image2D;
import platform.windows.WindowsAPI;

public class WindowsTemplate extends BallGame {

	private WindowsAPI windows;

	public WindowsTemplate() {
		windows = new WindowsAPI();
	}

	@Override
	public Image2D loadImage(String file) {
		return windows.loadFile(file);
	}

	@Override
	public Point getPosition() {
		return windows.getMouseClick();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		windows.paint(point.x, point.y, image);
	}

}
