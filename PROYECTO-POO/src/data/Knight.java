package data;

import java.util.ArrayList;

public class Knight extends Piece {	

	public Knight(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<BoardPosition> possibleMoves(BoardPosition currentPosition, Board board) {
		
		ArrayList<BoardPosition> possibleMoves = new ArrayList<BoardPosition>();
		possibleMoves.add(new BoardPosition(currentPosition.getRow()+1, currentPosition.getColumn()-2));		
		possibleMoves.add(new BoardPosition(currentPosition.getRow()+2, currentPosition.getColumn()-1));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()+2, currentPosition.getColumn()+1));		
		possibleMoves.add(new BoardPosition(currentPosition.getRow()+1, currentPosition.getColumn()+2));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()-1, currentPosition.getColumn()+2));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()-2, currentPosition.getColumn()+1));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()-2, currentPosition.getColumn()-1));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()-1, currentPosition.getColumn()-2));
		
		return ValidMoves (possibleMoves, currentPosition, board);
	}

}
