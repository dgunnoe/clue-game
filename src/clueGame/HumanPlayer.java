package clueGame;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, int startRow, int startColumn, String color) {
		super(name, startRow, startColumn, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HumanPlayer " + getName();
	}
	
	

}
