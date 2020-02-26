package data;

import java.io.Serializable;

public class Board implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Piece board [][];
	
	public Board() {
		board = new Piece[8][8];
		
		for ( int i=1; i < 2; i++ ) { 
			for ( int j=0; j < 8; j++ ) 
				board [i][j] = new BlackPawn('b'); 
			} 
		for ( int i=6; i < 7; i++ ) { 
			for ( int j=0; j < 8; j++ ) 
				board [i][j] = new WhitePawn('w'); 
			}
		board [0][0] = new Rook('b');
		board [7][0] = new Rook('w');
		board [7][7] = new Rook('w');
		board [0][7] = new Rook('b');
		board [0][1] = new Knight('b');
		board [0][6] = new Knight('b');
		board [7][1] = new Knight('w');
		board [7][6] = new Knight('w');
		board [0][2] = new Bishop('b');
		board [0][5] = new Bishop('b');
		board [7][2] = new Bishop('w');
		board [7][5] = new Bishop('w');
		board [0][3] = new Queen('b');
		board [7][3] = new Queen('w');
		board [0][4] = new King('b');
		board [7][4] = new King('w');
		
		for ( int i=2; i < 6; i++ ) { 
			for ( int j=0; j < 8; j++ ) 
				board [i][j] = new NoPiece('o');
			} 
	}
	
	public Piece [][] getBoard() {
		return board;
	}
	
}
