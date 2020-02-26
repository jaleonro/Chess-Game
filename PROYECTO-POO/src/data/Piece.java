package data;

import java.io.Serializable;
import java.util.ArrayList;

import businessLogic.Game;

public abstract class Piece implements Serializable{		
	
	private static final long serialVersionUID = 1L;
	char color;
	

	public char getColor() {
		return color;
	}

	public Piece(char color) {
		this.color = color;
	}

	public abstract ArrayList<BoardPosition> possibleMoves (BoardPosition currentPosition, Board board);
	
	public static ArrayList<BoardPosition> ValidMoves (ArrayList<BoardPosition> possibleMoves, BoardPosition currentPosition, Board board){
		ArrayList<BoardPosition> positionsToRemove = new ArrayList<BoardPosition>();
		for ( BoardPosition position : possibleMoves ) {
			 if (position.getColumn()>7 || position.getRow()>7 || position.getColumn()<0 || position.getRow()<0){
				 positionsToRemove.add(position);
			 }else{
			 if (((board.getBoard()[position.getRow()][position.getColumn()]).getColor()) == 
					 ((board.getBoard()[currentPosition.getRow()][currentPosition.getColumn()]).getColor())){
				 positionsToRemove.add(position);
			 }
			 }
			 
	}		
		if(!positionsToRemove.isEmpty()){
		possibleMoves.removeAll(positionsToRemove);}
		
		return possibleMoves;
	}

}
