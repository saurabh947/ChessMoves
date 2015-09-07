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

import java.util.HashMap;
import java.util.Scanner;


public class Board {

	// 2D board array indicating squares
	String[][] positions = new String[][]{
			//  0    1     2     3     4     5     6     7
			{ "A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8" },	//0
			{ "A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7" },	//1
			{ "A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6" },	//2
			{ "A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5" },	//3
			{ "A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4" },	//4
			{ "A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3" },	//5
			{ "A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2" },	//6
			{ "A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1" }	//7
	};

	// Hashmap for mapping "Board Square" -> "Player,Chess piece"
	HashMap<String, String> mappings = new HashMap<String, String>();
	Scanner scan = new Scanner(System.in);


	// Function called to configure the chess board at the starting of the game
	public void configInitial() {
		System.out.println("Starting initial configuration\n........");

		// Initialize pawns
		for(int i=0 ; i<8 ; i++) {
			mappings.put(positions[1][i], "black,pawn");
			mappings.put(positions[6][i], "white,pawn");
		}
		// Initialize rooks
		mappings.put(positions[0][0], "black,rook");
		mappings.put(positions[0][7], "black,rook");
		mappings.put(positions[7][0], "white,rook");
		mappings.put(positions[7][7], "white,rook");

		// Initialize knights
		mappings.put(positions[0][1], "black,knight");
		mappings.put(positions[0][6], "black,knight");
		mappings.put(positions[7][1], "white,knight");
		mappings.put(positions[7][6], "white,knight");

		// Initialize bishops
		mappings.put(positions[0][2], "black,bishop");
		mappings.put(positions[0][5], "black,bishop");
		mappings.put(positions[7][2], "white,bishop");
		mappings.put(positions[7][5], "white,bishop");

		// Initialize King and Queen
		mappings.put(positions[0][4], "black,king");
		mappings.put(positions[0][3], "black,queen");
		mappings.put(positions[7][4], "white,king");
		mappings.put(positions[7][3], "white,queen");

		System.out.println("End initial configuration");
	}


	public void configAlternate() {

		System.out.println("Starting alternate configuration\n........");
		System.out.println("Assuming the black pieces are away from you, and white pieces are on your side, "
				+ "\nenter the positions of pieces based on row-column, starting from 0 upto 7."
				+ "\nFor eg. If you want to add king at row 0 and column 4, enter 0-4.");


		// System.out.println("\nLet's start with the black pieces first.");

		// Initialize pawns
		System.out.println("\nPlease enter the positions of pawns");
		for(int i=0 ; i<8 ; i++) {
			System.out.println("black pawn " + i);
			String pos1 = scan.nextLine();
			String a[] = pos1.split("-");
			mappings.put(positions[Integer.parseInt(a[0])] [Integer.parseInt(a[1])], "black,pawn");

			System.out.println("white pawn " + i);
			String pos2 = new String(scan.nextLine());
			String b[] = pos2.split("-");
			mappings.put(positions[Integer.parseInt(b[0])] [Integer.parseInt(b[1])], "white,pawn");
		}

		// Initialize rooks
		System.out.println("\nPlease enter the positions of rooks");
		for(int i=0 ; i<2 ; i++) {
			System.out.println("black rook " + i);
			String pos1 = scan.nextLine();
			String a[] = pos1.split("-");
			mappings.put(positions[Integer.parseInt(a[0])] [Integer.parseInt(a[1])], "black,rook");

			System.out.println("white rook " + i);
			String pos2 = new String(scan.nextLine());
			String b[] = pos2.split("-");
			mappings.put(positions[Integer.parseInt(b[0])] [Integer.parseInt(b[1])], "white,rook");
		}

		// Initialize knights
		System.out.println("\nPlease enter the positions of knights");
		for(int i=0 ; i<2 ; i++) {
			System.out.println("black knight " + i);
			String pos1 = scan.nextLine();
			String a[] = pos1.split("-");
			mappings.put(positions[Integer.parseInt(a[0])] [Integer.parseInt(a[1])], "black,knight");

			System.out.println("white knight " + i);
			String pos2 = new String(scan.nextLine());
			String b[] = pos2.split("-");
			mappings.put(positions[Integer.parseInt(b[0])] [Integer.parseInt(b[1])], "white,knight");
		}

		// Initialize bishops
		System.out.println("\nPlease enter the positions of bishops");
		for(int i=0 ; i<2 ; i++) {
			System.out.println("black bishop " + i);
			String pos1 = scan.nextLine();
			String a[] = pos1.split("-");
			mappings.put(positions[Integer.parseInt(a[0])] [Integer.parseInt(a[1])], "black,bishop");

			System.out.println("white bishop " + i);
			String pos2 = new String(scan.nextLine());
			String b[] = pos2.split("-");
			mappings.put(positions[Integer.parseInt(b[0])] [Integer.parseInt(b[1])], "white,bishop");
		}

		// Initialize Kings
		System.out.println("\nPlease enter the positions of kings");

		System.out.println("black king " + 1);
		String pos1 = scan.nextLine();
		String a[] = pos1.split("-");
		mappings.put(positions[Integer.parseInt(a[0])] [Integer.parseInt(a[1])], "black,king");

		System.out.println("white king " + 1);
		String pos2 = new String(scan.nextLine());
		String b[] = pos2.split("-");
		mappings.put(positions[Integer.parseInt(b[0])] [Integer.parseInt(b[1])], "white,king");

		// Initialize Queens
		System.out.println("\nPlease enter the positions of queens");
		System.out.println("black queen " + 1);
		String pos3 = scan.nextLine();
		String c[] = pos3.split("-");
		mappings.put(positions[Integer.parseInt(c[0])] [Integer.parseInt(c[1])], "black,queen");

		System.out.println("white king " + 1);
		String pos4 = new String(scan.nextLine());
		String d[] = pos4.split("-");
		mappings.put(positions[Integer.parseInt(d[0])] [Integer.parseInt(d[1])], "white,queen");

		
		System.out.println("\nEnd alternate configuration");

	}


}
