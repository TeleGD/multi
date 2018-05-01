package app;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AppWorld extends BasicGameState {

	private int ID;

	public AppWorld (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	public void play (GameContainer container, StateBasedGame game) {}

	// public void stop (GameContainer container, StateBasedGame game) {}

	// public void pause (GameContainer container, StateBasedGame game) {}

	// public void resume (GameContainer container, StateBasedGame game) {}

}
