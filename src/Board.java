// Emily Xie
// 251035713

public class Board implements BoardADT {
	private int boardSize;
	private int emptyPositions;
	private int maxLevels;

	public Board (int boardSize, int emptyPositions, int maxLevels) {
		this.boardSize = boardSize;
		this.emptyPositions = emptyPositions;
		this.maxLevels = maxLevels;
		this.theBoard = new char[boardSize][boardSize];
		//Initializing all row and colum values on board to empty.
		for (int i = 0; i<boardSize; i++) {
			for (int j=0; j<boardSize; j++) {
				theBoard[i][j] = 'e';
			}
		}
	}
	
	char[][] theBoard;
	
	public Dictionary makeDictionary() {
		// Create new dictionary with given size.
		Dictionary dictionary = new Dictionary(11);
		return dictionary;
	}
	
	public int repeatedLayout(Dictionary dict) {
		String s = "";
		for (int row=0; row<boardSize; row++) {
			for (int col=0; col<boardSize; col++) {
				// Concatenate the string with next value.
				s = s + theBoard[row][col];
			}
		}
		// Return score if there is associated score. Returns -1 in function if not.
		return dict.getScore(s);
	}
	
	public void storeLayout(Dictionary dict, int score) {
		String s = "";
		for (int row=0; row<boardSize; row++) {
			for (int col=0; col<boardSize; col++) {
				// Concatenate the string with next value.
				s = s + theBoard[row][col];
			}
		}
		// Create new layout.
		Layout layout = new Layout(s, score);
		try {
			dict.put(layout);
		}
		catch(Exception e) {
		}
	}
	
	public void saveTile(int row, int col, char symbol) {
		// Input symbol character into board at specified location.
		theBoard[row][col] = symbol;
	}

	public boolean positionIsEmpty (int row, int col) {
		//Compare array with character e. Return boolean True or False.
		return theBoard[row][col] == 'e';
	}
	
	public boolean isComputerTile (int row, int col) {
		//Compare array with character c. Return boolean True or False.
		return theBoard[row][col] == 'c';
	}
	
	public boolean isHumanTile (int row, int col) {
		//Compare array with character h. Return boolean True or False.
		return theBoard[row][col] == 'h';
	}
	
	public boolean winner (char symbol) {
		// Check rows for horizontal victory.
		for (int row=0; row<boardSize; row++) {
			int counter = 0;
			for (int col=0; col<boardSize; col++) {
				if (theBoard[row][col] == symbol) {
					counter++;
				}
			}
			if (counter == boardSize) {
				return true;
			}
		}
		// Check columns for vertical victory.
		for (int col=0; col<boardSize; col++) {
			int counter = 0;
			for (int row=0; row<boardSize; row++) {
				if (theBoard[row][col] == symbol) {
					counter++;
				}
			}
			if (counter == boardSize) {
				return true;
			}
		}
		// Check diagonal from top left down.
		int counter = 0;
		int col = 0;
		for (int row=0; row<boardSize; row++) {
			if (theBoard[row][col] == symbol) {
				counter++;
			}
			col++;
		}
		if (counter == boardSize) {
			return true;
		}
		// Check diagonal from top right down.
		counter = 0;
		col = boardSize-1;
		for (int row=0; row<boardSize; row++) {
			if (theBoard[row][col] == symbol) {
				counter++;
			}
			col--;
		}
		if (counter == boardSize) {
			return true;
		}
	return false;
	}
	
	public boolean isDraw(char symbol, int emptyPositions) {
		if (emptyPositions ==0) {
			// Check if there are empty positions. Return false if there are.
			for (int row=0; row<boardSize; row++) {
				for (int col=0; col<boardSize; col++) {
					if (theBoard[row][col] == 'e') {
						return false;
					}
				}
			}
		
		}
		else {
			// Count empty squares.
			int counter=0;
			for (int row=0; row<boardSize; row++) {
				for (int col=0; col<boardSize; col++) {
					if (theBoard[row][col] == 'e') {
						counter++;
					}
				}
			}
			if (counter == emptyPositions) {
				// Iterate through all values on board.
				for (int row=0; row<boardSize; row++) {
					for (int col=0; col<boardSize; col++) {
						// Check for empty spaces.
						if (theBoard[row][col] == 'e') {
							// Check for all adjacent sides.
							if (getValue(row+1, col+1, '0') == symbol){
								return false;
							}
							
							if (getValue(row+1, col-1, '0') == symbol){
								return false;
							}
							
							if (getValue(row-1, col-1, '0') == symbol){
								return false;
							}

							if (getValue(row-1, col+1, '0') == symbol){
								return false;
							}
							
							if (getValue(row-1, col, '0') == symbol){
								return false;
							}
							
							if (getValue(row, col+1, '0') == symbol){
								return false;
							}
							
							if (getValue(row, col-1, '0') == symbol){
								return false;
							}
							
							if (getValue(row+1, col, '0') == symbol){
								return false;
							}
							
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public int evaluate(char symbol, int emptyPositions) {
		
		// Check if winner is computer.
		if (winner('c') == true) {
			return 3;
		}
		// Check if winner is human.
		if (winner('h') == true) {
			return 0;
		}
		// Check if there is a draw.
		if (isDraw(symbol, emptyPositions) == true) {
			return 2;
		}
		// If game is still undecided
		else { 
			return 1;
		}
	}
	
	private char getValue(int row, int col, char def) {
		// Identify where board edge is.
		if (row>=boardSize || col>=boardSize || row<0 ||col<0) {
			return def;
		}
		// Return value if board is not over boundary.
		else {
			return theBoard[row][col];
		}
	}
}
