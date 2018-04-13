package app;

import org.newdawn.slick.Color;

public class AppPlayer {

	static public final int GOLD = 0;
	static public final int PINK = 1;
	static public final int BLUE = 2;
	static public final int LIME = 3;
	// static public final int TEAL = 4;
	// static public final int PERU = 5;
	// static public final int PLUM = 6;
	// static public final int GRAY = 7;

	static public final String [] COLOR_NAMES = new String [] {
		"Gold",
		"Pink",
		"Blue",
		"Lime"/*,
		"Teal",
		"Peru",
		"Plum",
		"Gray"*/
	};

	static public final Color [] FILL_COLORS = new Color [] {
		new Color (255, 204, 51),
		new Color (255, 204, 204),
		new Color (153, 204, 255),
		new Color (51, 153, 102)
	};

	static public final Color [] STROKE_COLORS = new Color [] {
		new Color (204, 153, 0),
		new Color (255, 153, 153),
		new Color (102, 153, 204),
		new Color (0, 102, 51)
	};

	private int colorID;
	private int controllerID;
	private String name;
	private int buttonPressedRecord;

	public AppPlayer (int colorID, int controllerID, String name, int buttonPressedRecord) {
		this.setColorID (colorID);
		this.setControllerID (controllerID);
		this.setName (name);
		this.setButtonPressedRecord (buttonPressedRecord);
	}

	public void setColorID (int colorID) {
		this.colorID = colorID;
	}

	public int getColorID () {
		return this.colorID;
	}

	public void setControllerID (int controllerID) {
		this.controllerID = controllerID;
	}

	public int getControllerID () {
		return this.controllerID;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getName () {
		return this.name;
	}

	public void setButtonPressedRecord (int buttonPressedRecord) {
		this.buttonPressedRecord = buttonPressedRecord;
	}

	public int getButtonPressedRecord () {
		return this.buttonPressedRecord;
	}

}
