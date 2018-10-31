package app;

import org.newdawn.slick.state.BasicGameState;

public abstract class AppState extends BasicGameState {

	private int ID;

	public AppState (int ID) {
		this.setID (ID);
	}

	private void setID (int ID) {
		this.ID = ID;
	}

	@Override
	public final int getID () {
		return this.ID;
	}

}
