package data;

import java.util.ArrayList;

public class NoPiece extends Piece{

	public NoPiece(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<BoardPosition> possibleMoves(BoardPosition currentPosition, Board board) {
		ArrayList<BoardPosition> possibleMoves = new ArrayList<BoardPosition>();
		return possibleMoves;
	}

}
