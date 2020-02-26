package data;

import java.util.ArrayList;

import businessLogic.Game;

public class Rook extends Piece {	

	public Rook(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<BoardPosition> possibleMoves(BoardPosition currentPosition, Board board) {
		
		ArrayList<BoardPosition> possibleMoves = new ArrayList<BoardPosition>();
		
		for (int i = currentPosition.getRow() + 1; i < 8; i++) {			
			possibleMoves.add(new BoardPosition(i, currentPosition.getColumn()));
			if (!((board.getBoard()[i][currentPosition.getColumn()]) instanceof NoPiece)){
				break;
			}	
		}
		for (int i = currentPosition.getRow() - 1; i > -1; i--) {			
			possibleMoves.add(new BoardPosition(i, currentPosition.getColumn()));
			if (!((board.getBoard()[i][currentPosition.getColumn()]) instanceof NoPiece)){
				break;
			}	
		}
		for (int i = currentPosition.getColumn() - 1; i > -1; i--) {			
			possibleMoves.add(new BoardPosition(currentPosition.getRow(), i));
			if (!((board.getBoard()[currentPosition.getRow()][i]) instanceof NoPiece)){
				break;
			}	
		}
		for (int i = currentPosition.getColumn() + 1; i < 8; i++) {			
			possibleMoves.add(new BoardPosition(currentPosition.getRow(), i));
			if (!((board.getBoard()[currentPosition.getRow()][i]) instanceof NoPiece)){
				break;
			}	
		}		
			
		return ValidMoves (possibleMoves, currentPosition, board);
	}

}
