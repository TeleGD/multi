import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.AppGame;

public final class Main {

	public static final void main(String[] arguments) {
		String title = "Sans titre";
		int width = 1280;
		int height = 720;
		boolean fullscreen = false;
		String request = "Voulez-vous jouer en plein écran ?";
		String[] options = {
			"Oui",
			"Non"
		};
		JFrame frame = new JFrame();
		frame.setIconImage(new ImageIcon(System.class.getResource("/images/icon.png")).getImage());
		int returnValue = JOptionPane.showOptionDialog(
			frame,
			request,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);
		frame.dispose();
		if (returnValue == -1) {
			return;
		}
		fullscreen = returnValue == 0;
		new AppGame(title, width, height, fullscreen) {

			@Override
			public void init() {
				this.addState(new pages.Welcome(AppGame.PAGES_WELCOME));
				this.addState(new pages.Games(AppGame.PAGES_GAMES));
				this.addState(new pages.Players(AppGame.PAGES_PLAYERS));
				this.addState(new pages.Pause(AppGame.PAGES_PAUSE));
				this.addState(new games.test.World(AppGame.GAMES_TEST_WORLD));
			}

		};
	}

}
