package adapter;

import java.awt.Point;

import platform.Image2D;

public interface AdaptadorPlataforma {


	public Image2D loadImage(String file);

	public Point getPosition();

	public void drawBall(Image2D image, Point point);

}
