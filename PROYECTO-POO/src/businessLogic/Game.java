package businessLogic;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.BlackPawn;
import data.Board;
import data.BoardPosition;
import data.King;

import data.NoPiece;
import data.Piece;
import data.WhitePawn;
import ui.Ui;

public class Game {
	public static Board board;
	public static Ui mainFrame;
	public static Timer timer;
	private static BoardPosition PositionSelected;
	private static BoardPosition PositionSaved;	
	private static BoardPosition PositionOfKing;
	private static Piece currentPiece;
	private static Piece savedPiece;
	private static Piece PieceToCapture;
	public static char currentColor='o';	
	public static ArrayList<Piece> BlackPiecesCaptured = new ArrayList<Piece>();
	public static ArrayList<Piece> WhitePiecesCaptured = new ArrayList<Piece>();


	public static void main(String[] args) {
		currentColor = BlackOrWhiteMovesFirst ();
		board = new Board();		
		mainFrame= new Ui();
		mainFrame.setVisible( true );		

	}
	

	public static void ActionsToSelection (int row, int column) {

		PositionSelected = new BoardPosition(row, column);

		if (!((board.getBoard()[PositionSelected.getRow()][PositionSelected.getColumn()]) instanceof NoPiece)){	
			currentPiece = board.getBoard()[PositionSelected.getRow()][PositionSelected.getColumn()];

			if ((currentPiece.getColor()) ==  currentColor){
				savedPiece = board.getBoard()[PositionSelected.getRow()][PositionSelected.getColumn()];
				PositionSaved = new BoardPosition(PositionSelected.getRow(), PositionSelected.getColumn());
				Ui.showValidMoves(currentPiece.possibleMoves(PositionSaved, board));
				Ui.showThreats(enemiesThatCouldKillYou (PositionSaved));

			}else{				
				if(PositionSaved!= null){
					PieceToCapture = board.getBoard()[PositionSelected.getRow()][PositionSelected.getColumn()];
					if(((savedPiece.possibleMoves(PositionSaved, board)).contains(PositionSelected))){						
						
						if(PieceToCapture.getColor() == 'b'){
							BlackPiecesCaptured.add(PieceToCapture);
						}else{
							WhitePiecesCaptured.add(PieceToCapture);
						}
						//the piece is moved
						board.getBoard()[PositionSelected.getRow()][PositionSelected.getColumn()] = savedPiece;
						board.getBoard()[PositionSaved.getRow()][PositionSaved.getColumn()] = new NoPiece('o');
						Ui.movePiece(PositionSaved, PositionSelected);
						Ui.cleanBoard();
						savedPiece=null;
						PositionSaved=null;						
						ChangeTurn();
					}
				}

			}	

		}else{
			if(savedPiece != null){				
				if((savedPiece.possibleMoves(PositionSaved, board).contains(PositionSelected))){					
					//the piece is moved
					board.getBoard()[PositionSelected.getRow()][PositionSelected.getColumn()] = savedPiece;
					Ui.movePiece(PositionSaved, PositionSelected);
					Ui.cleanBoard();
					board.getBoard()[PositionSaved.getRow()][PositionSaved.getColumn()] = new NoPiece('o');						
					savedPiece=null;
					PositionSaved=null;
					ChangeTurn();
				}
			}

		}
		if(Jake()){
			Ui.showJakeMessage(mainFrame);
			if(JakeMate(PositionOfKing)){				
				Ui.showJakeMateMessage(mainFrame);
				NewGame();
				Ui.updateBoard(board);
				PositionOfKing=null;
			}
		}

	}
	
	public static void ChangeTurn(){
		if(currentColor == 'b'){
			currentColor = 'w';
		}else{
			currentColor = 'b';
		}			
		Ui.ChangeOfTurn=Ui.ChangeOfTurn+1;
		if(Ui.ClockPanel.getBackground() == Color.ORANGE){
			Ui.ClockPanel.setBackground(Color.WHITE);
		}else{
			Ui.ClockPanel.setBackground(Color.ORANGE);
		}	
	}	


	public static boolean Jake (){
		boolean flag = false;
		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ) {
				if(!(board.getBoard() [i][j] instanceof NoPiece)){
					if((board.getBoard() [i][j] instanceof King)){
						if(someBodyCouldKillYou (new BoardPosition(i, j))){
							PositionOfKing=new BoardPosition(i, j);
							flag = true;							
						}
					}	
				}
			}
		}

		return flag;

	}

	public static boolean JakeMate (BoardPosition Position){
		boolean flag = true;
		ArrayList<BoardPosition> movesOfKing = new ArrayList<BoardPosition>();
		movesOfKing = board.getBoard() [Position.getRow()][Position.getColumn()].possibleMoves(Position, board);
		for ( BoardPosition position : movesOfKing ) {
			if(!(someBodyCouldKillYou (position))){
				flag = false;
			}
		}
		return flag;

	}

	public static boolean someBodyCouldKillYou (BoardPosition Position){
		BoardPosition currentPosition;
		boolean flag = false;
		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ) {
				currentPosition = new BoardPosition(i,j);
				if(!(board.getBoard() [i][j] instanceof NoPiece)){
					if(board.getBoard() [i][j].getColor() != currentColor){
						if(board.getBoard() [i][j].possibleMoves(currentPosition, board).contains(Position)){
							flag = true;							
						}
					}	
				}
			}
		}
		return flag;
	}

	public static ArrayList<BoardPosition> enemiesThatCouldKillYou (BoardPosition Position){
		ArrayList<BoardPosition> threats = new ArrayList<BoardPosition>();
		BoardPosition currentPosition;
		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ) {
				currentPosition = new BoardPosition(i,j);
				if(!(board.getBoard() [i][j] instanceof NoPiece)){
					if(board.getBoard() [i][j].getColor() != board.getBoard() [Position.getRow()][Position.getColumn()].getColor()){
						if(board.getBoard() [i][j].possibleMoves(currentPosition, board).contains(Position)){
							threats.add(currentPosition);
						}
					}			
				}
			}
		} 
		return threats;
	}

	public static char BlackOrWhiteMovesFirst () {
		Random randGeneratorFirstMove = new Random();
		char bOrW = 'o';
		if (randGeneratorFirstMove.nextInt(2) == 0) 
		{
			bOrW= 'b';}
		else {
			bOrW= 'w';
		}
		return bOrW;
	}


	public static void NewGame(){
		board=new Board();
		Ui.updateBoard(board);
	}	

	public static void saveGame(){
		try {
			FileOutputStream fileout = new FileOutputStream("SavedGame.ser"); 
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(board);
			out.close();
		}catch(IOException i){
			i.printStackTrace();
		}	
	}

	public static void loadBoard(){	
		Board b = null ;
		try {
			FileInputStream fileIn = new FileInputStream("SavedGame.ser"); 
			ObjectInputStream in = new ObjectInputStream(fileIn);
			b = (Board) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i){
			i.printStackTrace();
		}catch(ClassNotFoundException c){
			System.out.println("Message class not found");
			c.printStackTrace();
		}	
		board = b;
		Ui.updateBoard(board);

	}

}
