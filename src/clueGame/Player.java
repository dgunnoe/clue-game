package clueGame;
import java.awt.Color;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private int startRow, startColumn;
	private boolean isHuman;
	private Set<Card> seenCards;
	
	
	public Player(String name, int startRow, int startColumn, String stringColor) {
		this.setName(name);
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.color = Color.decode(stringColor);
	}
	
	public void updateHand(Card card) {
		 
	}

	public void updateSeen(Card seenCard) {
		
	}

	public Card disproveSuggestion() {
		return null;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

