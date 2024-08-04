package clueGame;
public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	
	public Solution() {
		super();
	}
	
	public Solution(Card r, Card p, Card w) {
		room = r;
		person = p;
		weapon = w;
	}
	
	
	public void addRoom(Card c) {
		this.room = c;
	}
	
	public void addPerson(Card c) {
		this.person = c;
	}
	
	public void addWeapon(Card c) {
		this.weapon = c;
	}
	
	public Card getRoom() {
		return room;
	}
	
	public Card getPerson() {
		return this.person;
	}
	
	public Card getWeapon() {
		return weapon;
	}
	
	@Override
	public String toString() {
		return "Solution [room=" + room + ", person=" + person + ", weapon=" + weapon + "]";
	}
	
	
}
