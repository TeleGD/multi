package pages;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.EmptyImageData;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppInput;
import app.AppPage;
import app.AppPlayer;

public class Welcome extends AppPage {

	private int ID;

	private Image logo;

	private boolean logoVisibility;

	protected int logoBoxWidth;
	protected int logoBoxHeight;
	protected int logoBoxX;
	protected int logoBoxY;

	private int logoWidth;
	private int logoHeight;
	private int logoX;
	private int logoY;

	private int logoNaturalWidth;
	private int logoNaturalHeight;

	public Welcome (int ID) {
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

		this.hintBoxX = this.contentX;
		this.hintBoxY = this.contentY;

		this.logoBoxX = this.contentX;
		this.logoBoxY = this.hintBoxY + this.hintBoxHeight + AppPage.gap;
		this.logoBoxWidth = this.contentWidth;
		this.logoBoxHeight = this.contentY + this.contentHeight - this.logoBoxY;

		this.logoVisibility = true;

		this.titleVisibility = false;
		this.subtitleVisibility = false;
		this.hintBlink = true;

		this.setHint ("PRESS [START]");
		Image logo;
		try {
			logo = new Image ("images/logo.png");
		} catch (SlickException exception) {
			logo = new Image (new EmptyImageData (0, 0));
		}
		this.setLogo (logo);
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int  delta) {
		super.update (container, game, delta);
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		if (appInput.isKeyDown (AppInput.KEY_ESCAPE)) {
			container.exit ();
		} else {
			int gameMasterID = AppInput.ANY_CONTROLLER;
			if (appInput.isKeyDown (AppInput.KEY_ENTER)) {
				gameMasterID = 0; /* Magic number */
			} else {
				for (int i = appInput.getControllerCount () - 1; i >= 0; i--) {
					if (appInput.isButtonPressed (AppInput.BUTTON_A | AppInput.BUTTON_PLUS, i)) {
						gameMasterID = i;
						break;
					}
				}
			}
			if (gameMasterID != AppInput.ANY_CONTROLLER) {
				int colorID = appGame.availableColorIDs.remove (0);
				String name = "Joueur " + AppPlayer.COLOR_NAMES [colorID]; // TODO: set user name
				appGame.appPlayers.add (0, new AppPlayer (colorID, gameMasterID, name, AppInput.BUTTON_A | AppInput.BUTTON_PLUS));
				appGame.enterState (AppGame.PAGES_GAMES, new FadeOutTransition (), new FadeInTransition ());
			}
		}
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render (container, game, context);
		this.renderLogo (container, game, context);
	}

	private void renderLogo (GameContainer container, StateBasedGame game, Graphics context) {
		if (this.logoVisibility) {
			context.drawImage (
				this.logo,
				this.logoX,
				this.logoY,
				this.logoX + this.logoWidth,
				this.logoY + this.logoHeight,
				0,
				0,
				this.logoNaturalWidth,
				this.logoNaturalHeight
			);
		}
	}

	public void setLogo (Image logo) {
		this.logo = logo.copy ();
		this.logoNaturalWidth = logo.getWidth ();
		this.logoNaturalHeight = logo.getHeight ();
		this.logoWidth = Math.min (this.logoBoxWidth, this.logoNaturalWidth);
		this.logoHeight = Math.min (this.logoBoxHeight, this.logoNaturalHeight);
		if (this.logoWidth * this.logoNaturalHeight < this.logoNaturalWidth * this.logoHeight) {
			this.logoHeight = this.logoNaturalHeight * this.logoWidth / this.logoNaturalWidth;
		} else {
			this.logoWidth = this.logoNaturalWidth * this.logoHeight / this.logoNaturalHeight;
		}
		this.logoX = this.logoBoxX + (this.logoBoxWidth - this.logoWidth) / 2;
		this.logoY = this.logoBoxY + (this.logoBoxHeight - this.logoHeight) / 2;
	}

	public Image getLogo () {
		return logo.copy ();
	}

}
