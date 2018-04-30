package games.test;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppInput;
import app.AppPlayer;
import app.AppWorld;

public class World extends AppWorld {

	private int ID;

	private int width;
	private int height;

	private Player [] players;

	public World (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		this.width = container.getWidth ();
		this.height = container.getHeight ();
	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		int n = appGame.appPlayers.size ();
		this.players = new Player [n];
		for (int i = 0; i < n; i++) {
			this.players [i] = new Player (appGame.appPlayers.get (i));
		}
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		AppInput appInput = (AppInput) container.getInput ();
		appInput.clearKeyPressedRecord ();
		appInput.clearControlPressedRecord ();
	}

	@Override
	public void leave (GameContainer container, StateBasedGame game) {}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		for (Player player: this.players) {
			String name = player.getName ();
			int controllerID = player.getControllerID ();
			for (int i = 0, l = appInput.getControlCount (controllerID); i < l; i++) {
				if (appInput.isControlPressed (1 << i, controllerID)) {
					System.out.println ("(" + name + ").isControlPressed: " + i);
				}
			}
			for (int i = 0, l = appInput.getButtonCount (controllerID); i < l; i++) {
				if (appInput.isButtonPressed (1 << i, controllerID)) {
					System.out.println ("(" + name + ").isButtonPressed: " + i);
				}
			}
			for (int i = 0, l = appInput.getAxisCount (controllerID); i < l; i++) {
				float j = appInput.getAxisValue (i, controllerID);
				if (j <= -.5f || j >= .5f) {
					System.out.println ("(" + name + ").getAxisValue: " + i + " -> " + j);
				}
			}
		}
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE)) {
			appGame.enterState (AppGame.PAGES_GAMES, new FadeOutTransition (), new FadeInTransition ());
		}
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {}

}
