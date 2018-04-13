package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppMenu;
import app.elements.MenuItem;

public class Games extends AppMenu {

	private int ID;

	public Games (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);
		this.setTitle ("Menu des jeux");
		this.setSubtitle ("Sans sous-titre");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem (AppGame.TITLES [AppGame.GAMES_TEST_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_TEST_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem ("Retour") {
				public void itemSelected () {
					AppGame appGame = (AppGame) game;
					for (int i = appGame.appPlayers.size () - 1; i >= 0; i--) {
						appGame.availableColorIDs.add (0, appGame.appPlayers.remove (i).getColorID ());
					};
					appGame.enterState (AppGame.PAGES_WELCOME, new FadeOutTransition (), new FadeInTransition ());
				}
			}
		}));
		this.setHint ("SELECT A GAME");
	}

}
