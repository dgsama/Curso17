package game;

import template.AndroidTemplate;
import template.PSTemplate;
import template.WindowsTemplate;

public class Main {
	public static void main(String[] args) {
		BallGame gameAndroid = new AndroidTemplate();
		gameAndroid.play();

		BallGame gameWindows = new WindowsTemplate();
		gameWindows.play();

		BallGame gamePs = new PSTemplate();
		gamePs.play();
	}
}
