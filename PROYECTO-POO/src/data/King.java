package data;

import java.util.ArrayList;

public class King extends Piece{	

	public King(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<BoardPosition> possibleMoves(BoardPosition currentPosition, Board board) {
		int i=1;
		ArrayList<BoardPosition> possibleMoves = new ArrayList<BoardPosition>();
		possibleMoves.add(new BoardPosition(currentPosition.getRow(),currentPosition.getColumn()+i ));
		possibleMoves.add(new BoardPosition(currentPosition.getRow(),currentPosition.getColumn()-i));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()+i,currentPosition.getColumn()));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()-i,currentPosition.getColumn()));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()+i,currentPosition.getColumn()+i ));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()-i,currentPosition.getColumn()-i));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()-i,currentPosition.getColumn()+i));
		possibleMoves.add(new BoardPosition(currentPosition.getRow()+i,currentPosition.getColumn()-i));
		return ValidMoves (possibleMoves, currentPosition, board);
	}

}
