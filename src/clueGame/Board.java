/**
 * @author Desiree
 */

package clueGame;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//import experiments.TestBoardCell;

import java.io.FileReader;


public class Board {
	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	
	private String layoutConfigFile;
	private String setupConfigFile;
	
	private Map<Character, Room> roomMap;
    private static Board theInstance = new Board();
	private Room room;
	private BoardCell cell;
	
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	
	private Set<Card> deck;
	private List<Card> deckList;
	private Solution theAnswer;
	
	private ArrayList<Player> players;

  
    // constructor is private to ensure only one can be created
    private Board() {
    	super() ;
    }
    
    // this method returns the only Board
    public static Board getInstance() {
    	return theInstance;
    }
    /*
     * initialize the board (since we are using singleton pattern)
     */
	public void initialize() {
		roomMap = new HashMap<Character, Room>();
		
		try {
			loadSetupConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			loadLayoutConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		
		calcAdjLists();
		
	}
	
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException {
		File setupFile = new File(setupConfigFile);
		boolean firstRun = true;
		deck = new HashSet<Card>();
		Scanner reader = new Scanner(setupFile);
		players = new ArrayList<Player>();
		
		while (reader.hasNextLine()) {
		// arr[0] is Room/Player/Weapon
		// arr[1] is name
			String data = reader.nextLine();
			String[] arr = data.split(", "); // each line
						
			if (arr[0].equals("Room") || arr[0].equals("Space")) {
				Room newRoom = new Room(arr[1]);
				roomMap.put(arr[2].charAt(0), newRoom); // roomMap(char, Room)
				if (arr[0].equals("Room")) {
					Card newCard = new Card(arr[1], CardType.ROOM);
					deck.add(newCard);
				}
				//System.out.println(roomMap.size());
//				for (String s: arr) {
//					System.out.println(s);
//				}
			} else if (arr[0].equals("Player")) {
				//System.out.print(firstRun);
				if (firstRun) {
					Player newPlayer = new HumanPlayer(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), arr[4].toUpperCase());
					players.add(newPlayer);
					System.out.print("New Human Player made. ");
					Card newCard = new Card(arr[1], CardType.PERSON);
					deck.add(newCard);
					System.out.println(arr[1]);
					firstRun = false;
				} else {
					Player newPlayer = new ComputerPlayer(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), arr[4].toUpperCase());
					players.add(newPlayer);
					System.out.print("New Computer Player made. ");
					System.out.println(arr[4].toUpperCase());
					Card newCard = new Card(arr[1], CardType.PERSON);
					deck.add(newCard);
					System.out.println(Integer.parseInt(arr[2]));
			
				}
			} else if (arr[0].equals("Weapon")) {
				Card newCard = new Card(arr[1], CardType.WEAPON);
				deck.add(newCard);
				//System.out.println(arr[1]);
			}
			
			
		}
//		for (Map.Entry<Character, Room> entry : roomMap.entrySet()) {
//		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//		}
		reader.close();
		
	}
	
	public void loadLayoutConfig() throws FileNotFoundException, BadConfigFormatException {
		try {
			File layoutFile = new File(layoutConfigFile);
			Scanner reader1 = new Scanner(layoutFile);
			boolean firstRun = true;
			
			while (reader1.hasNextLine()) {
				String data = reader1.nextLine();
				String[] cols = data.split("\\s*,\\s*"); // This regex will split on commas surrounded by any amount of whitespace
				numColumns = cols.length;
				//System.out.println();
				numRows++;
				
			}
			reader1.close();

			
			grid = new BoardCell[numRows][numColumns];
			int tempRow = 0;
			//int counter = 0;
			Scanner reader2 = new Scanner(layoutFile);
			while (reader2.hasNextLine()) {
				String data = reader2.nextLine();
				String[] splitRow = data.split("\\s*,\\s*"); // This regex will split on commas surrounded by any amount of whitespace
				for (int tempCol = 0; tempCol < numColumns; tempCol++) {
					String splitCol = splitRow[tempCol]; // splitRow gets each row and splitCol is each value in the arr
					//counter++;
					//System.out.println(splitCol);
					grid[tempRow][tempCol] = new BoardCell(tempRow, tempCol, splitCol);
				
					BoardCell newCell = grid[tempRow][tempCol];
					Room newRoom = roomMap.get(newCell.getInitial());
					
					// if label has # in it, setLabelCell
					if (newCell.isLabel()) { // Cell knows if it's center/label
						newRoom.setLabelCell(newCell);
					} else if (newCell.isRoomCenter()) {
						newRoom.setCenterCell(newCell);
					}
				}
				tempRow++;
					
			}
			reader2.close();	
			//System.out.println(counter);
		} catch (FileNotFoundException e) {
			System.out.println("error: ");
			e.printStackTrace();
		}
	}
	
	public void calcAdjLists() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				boolean isWalkway = false;
				boolean isCenterRoom = false;
				boolean isRoom = false;
				boolean isDoorway = false;
				Set <BoardCell> tempAdjList = new HashSet <BoardCell>();
				BoardCell currentCell = grid[i][j];
				
				if (currentCell.isDoorway()) {
					isDoorway = true;
				} 
				
				if (currentCell.getInitial() == 'W') {
					isWalkway = true;
				}
				
				if (currentCell.isRoomCenter()) {
					isCenterRoom = true;
				}
				
				// if it is a walkway, each surrounding walkway is apart of adjList
				if (isWalkway) {
					if (i - 1 >= 0 && i - 1 <= numRows && grid[i-1][j].getInitial() == 'W') { // looking at upper neighbor
						tempAdjList.add(grid[i-1][j]);
					} 
					if (i + 1 >= 0 && i + 1 < numRows && grid[i+1][j].getInitial() == 'W') { // looking at lower neighbor
						tempAdjList.add(grid[i+1][j]);
					} 
					if (j - 1 >= 0 && j - 1 <= numColumns && grid[i][j-1].getInitial() == 'W') { // looking at left neighbor
						tempAdjList.add(grid[i][j-1]);
					} 
					if (j + 1 >= 0 && j + 1 < numColumns && grid[i][j+1].getInitial() == 'W') { // looking at right neighbor
						tempAdjList.add(grid[i][j+1]);
					}
					
					// if the walkway is also a doorway, that room's center is in addition apart of adjList 
					if (isDoorway) {
						int doorwayRow = i;
						int doorwayCol = j;
						
						// Calculate adjustment
						if (currentCell.getDoorDirection() == DoorDirection.DOWN) {
							doorwayRow++;
						} else if (currentCell.getDoorDirection() == DoorDirection.UP) {
							doorwayRow--;
						} else if (currentCell.getDoorDirection() == DoorDirection.LEFT) {
							doorwayCol--;
						} else if (currentCell.getDoorDirection() == DoorDirection.RIGHT) {
							doorwayCol++;
						}
						
						BoardCell centerCell = this.getRoom(grid[doorwayRow][doorwayCol]).getCenterCell(); // get center cell of room
						
						tempAdjList.add(centerCell); // Add room center to this cell
						centerCell.addAdjacency(currentCell); // Add the doorway to the center cell's adjList
					}
				currentCell.setAdjList(tempAdjList);
				
				} else if (currentCell.getSecretPassage() != '\0') { // '\0' null character
					BoardCell currentCenterCell = getRoom(currentCell).getCenterCell(); // Get center cell of current room
					
					char secretRoom = currentCell.getSecretPassage();
					
					BoardCell otherCenterCell = getRoom(secretRoom).getCenterCell(); // Get center cell of other room
					
					currentCenterCell.addAdjacency(otherCenterCell); // Add to each other's adjLists
					otherCenterCell.addAdjacency(currentCenterCell); 				
					
				}
				
			}
		}
	}
	
	
	public void calcTargets(BoardCell thisCell, int pathLength) {
		// TODO Auto-generated method stub
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		visited.add(thisCell); 
		findAllTargets(thisCell, pathLength);
	}
	
	public void findAllTargets(BoardCell thisCell, int pathLength) {
		Set <BoardCell> tempAdjList = thisCell.getAdjList();
		
		//System.out.println("here" + tempAdjList.size());
		for (BoardCell adjCell: tempAdjList) {
			// System.out.println()
			if (visited.contains(adjCell)) {
				continue;
			} else {
				visited.add(adjCell);
				if (pathLength == 1 && !(adjCell.getIsOccupied())) {
					targets.add(adjCell);
				} else if (adjCell.getIsRoom()){
					targets.add(adjCell);
				} else if (adjCell.getIsOccupied()) {
					continue;
				} else {
					findAllTargets(adjCell, pathLength - 1);
				}
				visited.remove(adjCell);
			}
		}
	
	}
	
	public void deal() {
		deckList = new ArrayList<>(deck);
		boolean roomSolutionSet = false;
		boolean personSolutionSet = false;
		boolean weaponSolutionSet = false;

		
		//System.out.println(deck);
		System.out.println(deck.size());
		
		Collections.shuffle(deckList);
		
		theAnswer = new Solution();
		
		// deal the answer
		for (int i = 0; i < deckList.size(); i++) {
			Card c = deckList.get(i);
			if (c.getCardType() == CardType.ROOM && roomSolutionSet == false) {
				theAnswer.addRoom(c);
				deckList.remove(i);
				roomSolutionSet = true;
			} else if (c.getCardType() == CardType.PERSON && personSolutionSet == false) {
				theAnswer.addPerson(c);
				deckList.remove(i);
				personSolutionSet = true;
			} else if (c.getCardType() == CardType.WEAPON && weaponSolutionSet == false) {
				theAnswer.addWeapon(c);
				deckList.remove(i);
				weaponSolutionSet = true;
			}			
		}
		
		System.out.println(theAnswer);
		//System.out.println(deckList); proves that shuffle worked
		System.out.println(deckList.size());
		
		// deal to the players
		while (deckList.size() > 0) {
		    System.out.println("deckList size: " + deckList.size());
		    
		    // Iterate in reverse order to prevent IndexOutOfBoundsException
		    // By iterating from the end of the list to the beginning, you avoid shifting issues.
		    for (int i = Math.min(players.size(), deckList.size()) - 1; i >= 0; i--) {
		        //System.out.println("i " + i);
		        players.get(i).updateHand(deckList.get(i));
		        deckList.remove(i);
		    }
		}
	
	}
	
	public boolean checkAccusation(Card room, Card person, Card weapon) {
		
		if (room.equals(theAnswer.getRoom()) && person.equals(theAnswer.getPerson()) && weapon.equals(theAnswer.getWeapon())) {
			return true;
		}
		return false;
	}
	
	public Card handleSuggestion() {
		return null;
	}
	
	public Solution getTheAnswer() {
		return theAnswer;
	}
	
	public void setConfigFiles(String string, String string2) {
		layoutConfigFile = string;
		setupConfigFile = string2;
		
	}
	
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCell(int i, int j) {
		return grid[i][j];
	}
	
	public Room getRoom(char c) {
		return roomMap.get(c);
	}

	public Room getRoom(BoardCell cell) {
		return roomMap.get(cell.getInitial());	
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}

	public Set<BoardCell> getAdjList(int row, int col) {
		return grid[row][col].getAdjList();
	}

	public Set<Card> getDeckOfCards() {
		return deck;
	}
	
	public List<Card> getDeckList() {
		return deckList;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
     
    
     

}
