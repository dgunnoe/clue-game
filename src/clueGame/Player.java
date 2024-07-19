package clueGame;
import java.awt.Color;

public abstract class Player {
	private String name;
	private Color color;
	private int startRow, startColumn;
	private boolean isHuman;
	
	public Player(String name, int startRow, int startColumn) {
		this.setName(name);
		this.startRow = startRow;
		this.startColumn = startColumn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

