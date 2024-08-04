package clueGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private int startRow, startColumn;
	private boolean isHuman;
	private Set<Card> seenCards;
	private ArrayList<Card> hand;
	
	
	public Player(String name, int startRow, int startColumn, String stringColor) {
		this.setName(name);
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.color = Color.decode(stringColor);
		this.hand = new ArrayList<Card>();
	}
	
	public void updateHand(Card card) {
		 hand.add(card);
	}

	public void updateSeen(Card seenCard) {
		
	}

	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> disprovable = new ArrayList<Card>(); // create list of potentials 
		if (hand.contains(suggestion.getPerson())) { // checks person
			disprovable.add(suggestion.getPerson());
		} 
		if (hand.contains(suggestion.getWeapon())) { // checks weapon
			disprovable.add(suggestion.getWeapon());
		}
		if (hand.contains(suggestion.getRoom())) { // checks room
			disprovable.add(suggestion.getRoom());
		}
		
		// check for presence of disprovable cards
		if (disprovable.size() == 0) {
			return null;
		}
		
		Collections.shuffle(disprovable); // randomly shuffle
		return disprovable.get(0); // return the first item
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

