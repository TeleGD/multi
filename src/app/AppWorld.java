package app;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppPlayer;

public abstract class AppWorld extends BasicGameState {

	private int ID;

	public AppWorld (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		AppPlayer gameMaster = appGame.appPlayers.get (0);
		int gameMasterID = gameMaster.getControllerID ();
		boolean BUTTON_PLUS = appInput.isKeyDown (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID);
		int gameMasterRecord = gameMaster.getButtonPressedRecord ();
		if (BUTTON_PLUS == ((gameMasterRecord & AppInput.BUTTON_PLUS) == 0)) {
			gameMasterRecord ^= AppInput.BUTTON_PLUS;
			if (BUTTON_PLUS) {
				this.pause (container, game);
				appGame.enterState (AppGame.PAGES_PAUSE, new FadeOutTransition (), new FadeInTransition ());
			}
		}
		gameMaster.setButtonPressedRecord (gameMasterRecord);
	}

	public void play (GameContainer container, StateBasedGame game) {}

	// public void stop (GameContainer container, StateBasedGame game) {}

	public void pause (GameContainer container, StateBasedGame game) {}

	public void resume (GameContainer container, StateBasedGame game) {}

}
