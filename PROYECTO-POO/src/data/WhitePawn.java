package data;

import java.util.ArrayList;

public class WhitePawn extends Pawn{	

	public WhitePawn(char color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<BoardPosition> possibleMoves(BoardPosition currentPosition, Board board) {
		ArrayList<BoardPosition> possibleMoves = new ArrayList<BoardPosition>();

		if (currentPosition.getRow() == 6){
			if (((board.getBoard()[currentPosition.getRow()-2][currentPosition.getColumn()]) instanceof NoPiece)) {	           
				possibleMoves.add(new BoardPosition(currentPosition.getRow()-2, currentPosition.getColumn()));
			}
		}

		if (((board.getBoard()[currentPosition.getRow()-1][currentPosition.getColumn()]) instanceof NoPiece)) {	           
			possibleMoves.add(new BoardPosition(currentPosition.getRow()-1, currentPosition.getColumn()));	
		}			

		if (!(currentPosition.getColumn()-1>7 || currentPosition.getRow()-1>7 || currentPosition.getColumn()-1<0 || currentPosition.getRow()-1<0)){
			if (!((board.getBoard()[currentPosition.getRow()-1][currentPosition.getColumn()-1]) instanceof NoPiece)) {			
				possibleMoves.add(new BoardPosition(currentPosition.getRow()-1, currentPosition.getColumn()-1));	
			}
		}

		if (!(currentPosition.getColumn()+1>7 || currentPosition.getRow()-1>7 || currentPosition.getColumn()+1<0 || currentPosition.getRow()-1<0)){
			if (!((board.getBoard()[currentPosition.getRow()-1][currentPosition.getColumn()+1]) instanceof NoPiece)) {			
				possibleMoves.add(new BoardPosition(currentPosition.getRow()-1, currentPosition.getColumn()+1));	
			}
		}		


		return ValidMoves (possibleMoves, currentPosition, board);
	}
}
