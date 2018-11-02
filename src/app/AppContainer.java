package app;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.Log;

public class AppContainer extends AppGameContainer {

	private Graphics graphics;

	public AppContainer (Game game, int width, int height, boolean fullscreen) throws SlickException {
		super (game, width, height, fullscreen);
	}

	protected void updateAndRender (int delta) throws SlickException {
		if (super.smoothDeltas) {
			if (super.getFPS () != 0) {
				delta = 1000 / super.getFPS ();
			}
		}
		super.input.poll (width, height);
		Music.poll (delta);
		((AppGame) super.game).poll ((GameContainer) this, (Input) super.input);
		if (!super.paused) {
			super.storedDelta += delta;
			if (super.storedDelta >= super.minimumLogicInterval) {
				try {
					if (super.maximumLogicInterval != 0) {
						long cycles = super.storedDelta / super.maximumLogicInterval;
						for (int i = 0; i < cycles; i++) {
							super.game.update (this, (int) super.maximumLogicInterval);
						}
						int remainder = (int) (super.storedDelta % super.maximumLogicInterval);
						if (remainder > super.minimumLogicInterval) {
							super.game.update (this, (int) (remainder % super.maximumLogicInterval));
							super.storedDelta = 0;
						} else {
							super.storedDelta = remainder;
						}
					} else {
						super.game.update (this, (int) super.storedDelta);
						super.storedDelta = 0;
					}
				} catch (Throwable e) {
					Log.error (e);
					throw new SlickException ("Game.update() failure - check the game code.");
				}
			}
		} else {
			super.game.update (this, 0);
		}
		if (super.hasFocus () || super.getAlwaysRender ()) {
			if (super.clearEachFrame) {
				GL.glClear (SGL.GL_COLOR_BUFFER_BIT | SGL.GL_DEPTH_BUFFER_BIT);
			}
			GL.glLoadIdentity ();
			this.graphics.resetTransform ();
			this.graphics.resetFont ();
			this.graphics.resetLineWidth ();
			this.graphics.setAntiAlias (false);
			try {
				super.game.render (this, this.graphics);
			} catch (Throwable e) {
				Log.error (e);
				throw new SlickException ("Game.render() failure - check the game code.");
			}
			this.graphics.resetTransform ();
			if (super.isShowingFPS ()) {
				super.getDefaultFont ().drawString (10, 10, "FPS: " + recordedFPS);
			}
			GL.flush ();
		}
		if (super.targetFPS != -1) {
			Display.sync (super.targetFPS);
		}
	}

	protected void initGL () {
		if (super.input == null) {
			super.input = new AppInput (super.height);
		}
		Graphics graphics = this.graphics;
		this.graphics = null;
		super.initGL ();
		if (graphics != null) {
			((AppOutput) graphics).setDimensions (super.width, super.height);
		}
		this.graphics = graphics;
	}

	protected void initSystem () throws SlickException {
		this.initGL ();
		super.setMusicVolume (1f);
		super.setSoundVolume (1f);
		this.graphics = new AppOutput (super.width, super.height);
		super.setDefaultFont (this.graphics.getFont ());
	}

	@Deprecated
	public Input getInput () {
		return super.input;
	}

	@Deprecated
	public Graphics getGraphics () {
		return this.graphics;
	}

}
