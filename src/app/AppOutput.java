package app;

import org.newdawn.slick.Graphics;

public class AppOutput extends Graphics {

	public AppOutput (int width, int height) {
		super (width, height);
	}

	public void setDimensions (int width, int height) {
		super.screenWidth = width;
		super.screenHeight = height;
	}

}
