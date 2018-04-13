package app;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class AppGame extends StateBasedGame {

	public static final int PAGES_WELCOME = 0;
	public static final int PAGES_GAMES = 1;
	public static final int PAGES_PLAYERS = 2;
	public static final int GAMES_TEST_WORLD = 3;

	public static final String [] TITLES = new String [] {
		"Accueil",
		"Menu des jeux",
		"Menu des joueurs",
		"Jeu de test"
	};

	public List <AppPlayer> appPlayers;
	public List <Integer> availableColorIDs;

	public AppGame (String name) {
		super (name);
		this.appPlayers = new ArrayList <AppPlayer> ();
		this.availableColorIDs = new ArrayList <Integer> ();
		for (int i = 0, l = AppPlayer.COLOR_NAMES.length; i < l; i++) {
			this.availableColorIDs.add (i);
		}
	}

	@Override
	public void initStatesList (GameContainer container) {
		this.addState (new pages.Welcome (AppGame.PAGES_WELCOME));
		this.addState (new pages.Games (AppGame.PAGES_GAMES));
		this.addState (new pages.Players (AppGame.PAGES_PLAYERS));
		this.addState (new games.test.World (AppGame.GAMES_TEST_WORLD));
	}

}
