package clueGame;
import java.awt.Color;

public abstract class Player {
	private String name;
	private Color color;
	private int startRow, startColumn;
	private boolean isHuman;
	
	public Player(String name, int startRow, int startColumn) {
		this.name = name;
		this.startRow = startRow;
		this.startColumn = startColumn;
	}
}

