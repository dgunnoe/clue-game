package clueGame;
import java.awt.Color;

public abstract class Player {
	private String name;
	private Color color;
	private int startRow, startColumn;
	private boolean isHuman;
	
	
	public Player(String name, int startRow, int startColumn, String stringColor) {
		this.setName(name);
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.color = Color.decode(stringColor);
	}
	
	public void updateHand(Card card) {
		 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

