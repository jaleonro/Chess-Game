package data;

import java.util.ArrayList;

import businessLogic.Game;

public class Queen extends Piece{	

	public Queen(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<BoardPosition> possibleMoves(BoardPosition currentPosition, Board board) {
		
		ArrayList<BoardPosition> possibleMoves = new ArrayList<BoardPosition>();
		for (int j = currentPosition.getColumn() + 1, i = currentPosition.getRow() + 1; j < 8 && i < 8; j++, i++) {
			possibleMoves.add(new BoardPosition(i, j));
			if (!((board.getBoard()[i][j]) instanceof NoPiece)){
				break;
			}		        
		}
		for (int j = currentPosition.getColumn() - 1, i = currentPosition.getRow() - 1; j > -1 && i > -1; j--, i--) {
			possibleMoves.add(new BoardPosition(i, j));
			if (!((board.getBoard()[i][j]) instanceof NoPiece)){
				break;
			}		        
		}
		for (int j = currentPosition.getColumn() - 1, i = currentPosition.getRow() + 1; j > -1 && i< 8; j--, i++) {
			possibleMoves.add(new BoardPosition(i, j));
			if (!((board.getBoard()[i][j]) instanceof NoPiece)){
				break;
			}		        
		}
		for (int j = currentPosition.getColumn() + 1, i = currentPosition.getRow() - 1; j < 8 && i > -1; j++, i--) {
			possibleMoves.add(new BoardPosition(i, j));
			if (!((board.getBoard()[i][j]) instanceof NoPiece)){
				break;
			}		        
		}
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
			
		return ValidMoves (possibleMoves, currentPosition,board);
	}

}
