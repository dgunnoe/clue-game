/**
 * @author Desiree
 * @date 6/27/2024
 */
package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;
import clueGame.Solution;

//import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.*;

import experiments.*;

public class GameSetupTests {
	private static Board board;
	private static Solution solution;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("data/ClueLayout.csv", "data/ClueSetup.txt");
		// Initialize will load config files
		board.initialize();
		solution = new Solution();
		board.deal(); // only deal once!
	}
	
	
	@Test
	//Load people and weapons from ClueSetup.txt and ensure the data was loaded properly. [20pts]
	//Create Player class with human and computer child classes.   Use people data to instantiate 6 players (1 human and 5 computer) [20pts]
	public void loadPeopleAndWeapons() {
		int players = board.getPlayers().size();
		Assert.assertEquals(6, players);
	}
	
	
	@Test
 	//Create complete deck of cards (weapons, people and rooms) [10pts]
 	public void deckCount() {
 		int deckSize = board.getDeckOfCards().size();
 		Assert.assertEquals(21, deckSize);
 	
 	}

 	@Test
 	// Deal cards to the Answer and the players (all cards dealt, players have roughly same # of cards, no card dealt twice) [20pts]
 	// All cards should be dealt.
 	public void checkDeal() {
 		//board.deal();
 		int deckSize = board.getDeckList().size();
 		Assert.assertEquals(0, deckSize);
 	}

 	@Test
 	// Ensure sol has 3 cards and of each card type
 	public void testSolution() {
		Solution answer = board.getTheAnswer();
		assertTrue(answer.getPerson().getCardType() == CardType.PERSON);
		assertTrue(answer.getWeapon().getCardType() == CardType.WEAPON);
		assertTrue(answer.getRoom().getCardType() == CardType.ROOM);
	}
 	
// 	@Test
// 	//Check an accusation
// 	public void checkAccusation() {
// 		Card a = new Card("Parking Lot", CardType.ROOM);
// 		Card b = new Card("Michael Scott", CardType.PERSON);
// 		Card c = new Card("Dundie Trophy", CardType.WEAPON);
//
// 		assertTrue(board.checkAccusation(a, b, c));
// 	}
	

}
