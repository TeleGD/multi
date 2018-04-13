package app;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

public class AppContainer extends AppGameContainer {

	public AppContainer (Game game, int width, int height, boolean fullscreen) throws SlickException {
		super (game, width, height, fullscreen);
		this.input = new AppInput (this.getScreenHeight ());
	}

}
