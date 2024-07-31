package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card (String cardName, CardType c) {
		this.cardName = cardName;
		this.cardType = c;
	}
	
	public boolean equals(Card target) {
		return false;
	}
	
	public CardType getCardType() {
		return cardType;
	}
	
//	public equals()

}
