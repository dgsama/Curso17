package template;

import game.BallGame;

import java.awt.Point;

import platform.Image2D;
import platform.android.AndroidAPI;

public class AndroidTemplate extends BallGame {

	private AndroidAPI android;

	public AndroidTemplate() {
		android = new AndroidAPI();
	}

	@Override
	public Image2D loadImage(String file) {
		return android.loadResource(file);
	}

	@Override
	public Point getPosition() {
		return android.getTouch();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		android.draw(point.x, point.y, image);
	}

}
