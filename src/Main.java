import org.newdawn.slick.SlickException;

import app.AppContainer;
import app.AppGame;

public final class Main {

	public static final void main (String [] arguments) throws SlickException {
		AppGame appGame = new AppGame ("Sans titre");
		AppContainer container = new AppContainer (appGame, 1280, 720, false);
		container.setTargetFrameRate (60);
		container.setVSync (true);
		container.setShowFPS (false);
		container.start ();
	}

}
