/* *******************************
 * Humble Bundle Coding Challenge
 * -------------------------------
 * Submitted By: Saurabh Agrawal
 * -------------------------------
 * Assumption: In the 2-D repr-
 * resentation of the chess board,
 * White is at the lower part of 
 * the board, and black is at 
 * the upper part.
 * *******************************
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class MainProgram {

	static char config = '\0';
	static Board board = new Board();

	public static void main(String[] args) {

		// Initialization
		Scanner scanner = new Scanner(System.in);

		System.out.println("This is a game of chess. \nPlease input your configuration of pieces. \n");

		// Configure the chess board
		System.out.println("Do you want the board in initial configuration? (Y/N)");
		config = scanner.next(".").charAt(0);
		switch(config) {
		case 'Y':
			System.out.println("\n------- Initial Configuration -------");
			board.configInitial();
			break;

		case 'N':
			System.out.println("\n------- Alternate configuration -------");
			board.configAlternate();
			break;

		default:
			System.out.println("\n------- Invalid input. Please try again -------");
			System.exit(0);
		}


		// Determine the player and calculate moves
		System.out.println("\n------- Which player's turn is it to make the move? ------- \nEnter B for black, W for white.");
		char player = scanner.next().charAt(0);

		switch(player) {
		case 'W':
			System.out.println("\nWhite Selected.\n");
			calculateMoves(player);
			break;

		case 'B':
			if(config == 'Y') {
				System.out.println("Black cannot play first for initial configuration! Please try again.");
				System.exit(0);
			}
			else {
				System.out.println("Black Selected\n");
				calculateMoves(player);
			}
			break;

		default:
			System.out.println("\nInvalid input. Please try again.");
			System.exit(0);
		}

		scanner.close();

	}


	/* This method calculates moves of pieces
	 * based on given configuration of pieces.
	 */
	public static void calculateMoves(char player) {

		int moveCounter = 0;
		Set<String> pieceCounter = new HashSet<String>();
		List<String> squares;
		String color = "";
		
		if(player == 'W')
			color = "white";
		else if(player == 'B')
			color = "black";
		else {
			System.out.println("Invalid input.");
			System.exit(0);
		}
		

		// ******************************** Check for PAWNS and its moves **********************************************
		// Extract the squares that have white pawns
		if(color == "white") {
			squares = new ArrayList<String>();
			squares = findPositionsForPieces(color, "pawn");

			for(int i = 0; i<8 ; i++){
				for(int j = 0; j<8 ; j++){

					// Check to find the piece's exact position
					if( board.positions[i][j].toString().equals( squares.get(j).toString() ) ) {

						// Check to see if any empty squares are present or not -- ONE ABOVE
						if (!board.mappings.containsKey(board.positions[i-1][j])) {
							System.out.println("Pawn at " + board.positions[i][j] + " can move to " + board.positions[i-1][j] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						// Check if in initial configuration
						if (config == 'Y') {
							// Check to see if any empty squares are present or not -- TWO ABOVE
							if (!board.mappings.containsKey(board.positions[i-2][j])) {
								System.out.println("Pawn at " + board.positions[i][j] + " can move to " + board.positions[i-2][j] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}
					}
				}
			}
		}
		
		// Extract the squares that have black pawns
		else if (color == "black") {
			squares = new ArrayList<String>();
			squares = findPositionsForPieces(color, "pawn");

			for(int i = 0; i<8 ; i++){
				for(int j = 0; j<8 ; j++){

					// Check to find the piece's exact position
					if( board.positions[i][j].toString().equals( squares.get(j).toString() ) ) {

						// Check to see if any empty squares are present or not -- ONE BELOW
						if (!board.mappings.containsKey(board.positions[i+1][j])) {
							System.out.println("Pawn at " + board.positions[i][j] + " can move to " + board.positions[i+1][j] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						// Check if in initial configuration
						if (config == 'Y') {
							// Check to see if any empty squares are present or not -- TWO BELOW
							if (!board.mappings.containsKey(board.positions[i+2][j])) {
								System.out.println("Pawn at " + board.positions[i][j] + " can move to " + board.positions[i+2][j] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}
					}
				}
			}
		}
		// *************************************************************************************************************


		// ******************************** Check for ROOKS and its moves **********************************************
		// Extract the squares that have white rooks
		squares = new ArrayList<String>();
		squares = findPositionsForPieces(color, "rook");

		for (int x = 0; x < 2; x++) {
			for(int i = 0; i<8 ; i++){
				for(int j = 0; j<8 ; j++){

					// Check to find the rook's exact position
					if( board.positions[i][j].toString().equals( squares.get(x).toString() ) ) {

						for(int k=i-1; k>=0; k--) {
							// Check to see if any empty squares are present or not -- UPPER VERTICAL
							if (! (board.mappings.containsKey(board.positions[k][j]) ) ) {
								System.out.println("Rook at " + board.positions[i][j] + " can move to " + board.positions[k][j] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}

						for(int k=i+1; k<=7; k++) {
							// Check to see if any empty squares are present or not -- LOWER VERTICAL
							if (! (board.mappings.containsKey(board.positions[k][j]) ) ) {
								System.out.println("Rook at " + board.positions[i][j] + " can move to " + board.positions[k][j] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}

						for(int k=j-1; k>=0; k--) {
							// Check to see if any empty squares are present or not -- LEFT HORIZONTAL
							if (! (board.mappings.containsKey(board.positions[i][k]) ) ) {
								System.out.println("Rook at " + board.positions[i][j] + " can move to " + board.positions[i][k] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}

						for(int k=j+1; k<=7; k++) {
							// Check to see if any empty squares are present or not -- RIGHT HORIZONTAL
							if (! (board.mappings.containsKey(board.positions[i][k]) ) ) {
								System.out.println("Rook at " + board.positions[i][j] + " can move to " + board.positions[i][k] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}
					}
				}
			}
		}
		// *************************************************************************************************************


		// ******************************** Check for BISHOPS and its moves **********************************************
		// Extract the squares that have white rooks
		squares = new ArrayList<String>();
		squares = findPositionsForPieces(color, "bishop");

		for (int x = 0; x < 2; x++) {
			for(int i = 0; i<8 ; i++){
				for(int j = 0; j<8 ; j++){

					// Check to find the bishop's exact position
					if( board.positions[i][j].toString().equals( squares.get(x).toString() ) ) {

						for(int k=i+1, l=j+1; k<=7 && l<=7; k++, l++) {
							// Check to see if any empty squares are present or not -- DOWN RIGHT DIAGONAL
							if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
								System.out.println("Bishop at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}

						for(int k=i-1, l=j-1; k>=0 && l>=0; k--, l--) {
							// Check to see if any empty squares are present or not -- UPPER LEFT DIAGONAL
							if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
								System.out.println("Bishop at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}

						for(int k=i+1, l=j-1; k<=7 && l>=0; k++, l--) {
							// Check to see if any empty squares are present or not -- DOWN LEFT DIAGONAL
							if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
								System.out.println("Bishop at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}

						for(int k=i-1, l=j+1; k>=0 && l<=7; k--, l++) {
							// Check to see if any empty squares are present or not -- UPPER RIGHT DIAGONAL
							if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
								System.out.println("Bishop at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
							else
								break;
						}
					}
				}
			}
		}
		// *************************************************************************************************************


		// ******************************** Check for QUEEN and its moves **********************************************
		// Extract the squares that have white rooks
		squares = new ArrayList<String>();
		squares = findPositionsForPieces(color, "queen");

		for(int i = 0; i<8 ; i++){
			for(int j = 0; j<8 ; j++){

				// Check to find the queen's exact position
				if( board.positions[i][j].toString().equals( squares.get(0).toString() ) ) {

					for(int k=i+1, l=j+1; k<=7 && l<=7; k++, l++) {
						// Check to see if any empty squares are present or not -- DOWN RIGHT DIAGONAL
						if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}

					for(int k=i-1, l=j-1; k>=0 && l>=0; k--, l--) {
						// Check to see if any empty squares are present or not -- UPPER LEFT DIAGONAL
						if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}

					for(int k=i+1, l=j-1; k<=7 && l>=0; k++, l--) {
						// Check to see if any empty squares are present or not -- DOWN LEFT DIAGONAL
						if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}

					for(int k=i-1, l=j+1; k>=0 && l<=7; k--, l++) {
						// Check to see if any empty squares are present or not -- UPPER RIGHT DIAGONAL
						if (! (board.mappings.containsKey(board.positions[k][l]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[k][l] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}

					for(int k=i-1; k>=0; k--) {
						// Check to see if any empty squares are present or not -- UPPER VERTICAL
						if (! (board.mappings.containsKey(board.positions[k][j]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[k][j] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}

					for(int k=i+1; k<=7; k++) {
						// Check to see if any empty squares are present or not -- LOWER VERTICAL
						if (! (board.mappings.containsKey(board.positions[k][j]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[k][j] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}

					for(int k=j-1; k>=0; k--) {
						// Check to see if any empty squares are present or not -- LEFT HORIZONTAL
						if (! (board.mappings.containsKey(board.positions[i][k]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[i][k] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}

					for(int k=j+1; k<=7; k++) {
						// Check to see if any empty squares are present or not -- RIGHT HORIZONTAL
						if (! (board.mappings.containsKey(board.positions[i][k]) ) ) {
							System.out.println("Queen at " + board.positions[i][j] + " can move to " + board.positions[i][k] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
						else
							break;
					}
				}
			}
		}
		// *************************************************************************************************************


		// ******************************** Check for KING and its moves **********************************************
		// Extract the squares that have white pawns
		squares = new ArrayList<String>();
		squares = findPositionsForPieces(color, "king");

		for(int i = 0; i<8 ; i++){
			for(int j = 0; j<8 ; j++){

				// Check to find the piece's exact position
				if( board.positions[i][j].toString().equals( squares.get(0).toString() ) ) {

					try {
						// Check to see if any empty squares are present or not -- ONE ABOVE
						if (!board.mappings.containsKey(board.positions[i-1][j])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i-1][j] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}

						// Check to see if any empty squares are present or not -- ONE BELOW
						if (!board.mappings.containsKey(board.positions[i+1][j])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i+1][j] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}

						// Check to see if any empty squares are present or not -- ONE LEFT
						if (!board.mappings.containsKey(board.positions[i][j-1])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i][j-1] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}

						// Check to see if any empty squares are present or not -- ONE RIGHT
						if (!board.mappings.containsKey(board.positions[i][j+1])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i][j+1] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}

						// Check to see if any empty squares are present or not -- ONE UPPER LEFT DIAGONAL
						if (!board.mappings.containsKey(board.positions[i-1][j-1])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i-1][j-1] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}

						// Check to see if any empty squares are present or not -- ONE DOWN RIGHT DIAGONAL
						if (!board.mappings.containsKey(board.positions[i+1][j+1])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i+1][j+1] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}

						// Check to see if any empty squares are present or not -- ONE DOWN LEFT DIAGONAL
						if (!board.mappings.containsKey(board.positions[i+1][j-1])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i+1][j-1] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}

						// Check to see if any empty squares are present or not -- ONE UPPER RIGHT DIAGONAL
						if (!board.mappings.containsKey(board.positions[i-1][j+1])) {
							System.out.println("King at " + board.positions[i][j] + " can move to " + board.positions[i-1][j+1] );
							moveCounter++; pieceCounter.add(board.positions[i][j]);
						}
					}
					catch (Exception e) {
					}
				}
			}
		}
		// *************************************************************************************************************


		// ******************************** Check for KNIGHTS and its moves **********************************************
		// Extract the squares that have white rooks
		squares = new ArrayList<String>();
		squares = findPositionsForPieces(color, "knight");

		for (int x = 0; x < 2; x++) {
			for(int i = 0; i<8 ; i++){
				for(int j = 0; j<8 ; j++){

					// Check to find the bishop's exact position
					if( board.positions[i][j].toString().equals( squares.get(x).toString() ) ) {

						try {
							// Check to see if any empty squares are present or not -- UPPER LEFT TWO & HALF MOVES
							if (! (board.mappings.containsKey(board.positions[i-1][j-2]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i-1][j-2] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}
						try {
							if (! (board.mappings.containsKey(board.positions[i-2][j-1]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i-2][j-1] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}

						try {
							// Check to see if any empty squares are present or not -- UPPER RIGHT TWO & HALF MOVES
							if (! (board.mappings.containsKey(board.positions[i-1][j+2]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i-1][j+2] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}
						try {
							if (! (board.mappings.containsKey(board.positions[i-2][j+1]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i-2][j+1] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}

						try {
							// Check to see if any empty squares are present or not -- LOWER RIGHT TWO & HALF MOVES
							if (! (board.mappings.containsKey(board.positions[i+1][j+2]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i+1][j+2] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}
						try {
							if (! (board.mappings.containsKey(board.positions[i+2][j+1]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i+2][j+1] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}

						try {
							// Check to see if any empty squares are present or not -- LOWER LEFT TWO & HALF MOVES
							if (! (board.mappings.containsKey(board.positions[i+1][j-2]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i+1][j-2] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}
						try {
							if (! (board.mappings.containsKey(board.positions[i+2][j-1]) ) ) {
								System.out.println("Knight at " + board.positions[i][j] + " can move to " + board.positions[i+2][j-1] );
								moveCounter++; pieceCounter.add(board.positions[i][j]);
							}
						}	catch (Exception e) {
						}
					}
				}
			}
		}
		// *************************************************************************************************************
		
		// Print the final counters
		System.out.println(moveCounter + " legal moves (" + pieceCounter.size() + " unique pieces) for " + color + " player." );


	}

	// Method for calculating the exact position of the piece in question, on the chess board.
	public static List<String> findPositionsForPieces (String color, String piece) {

		// For each combination of color and piece, find the exact position of the piece on the board.
		List<String> keys = new ArrayList<String>();
		for (Entry<String, String> entry : board.mappings.entrySet()) {
			if (Objects.equals( color+","+piece, entry.getValue())) {
				keys.add(entry.getKey());
			}
		}

		// Sort them, so they are easier to work with and return to the caller
		Collections.sort(keys);
		return keys;
	}

}