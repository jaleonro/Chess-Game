package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import businessLogic.Game;
import data.Board;
import data.BoardPosition;
import data.NoPiece;


public class Ui extends JFrame{
	private static Image[][] chessPieceImages = new Image[2][6];
	private static final long serialVersionUID = 1L;
	public static JPanel ClockPanel = new JPanel();
	private static JLabel time = new JLabel(); 
	private static JButton[][] chessBoardSquares = new JButton[8][8];
	private static JButton New =new JButton("New");
	private static JButton Save =new JButton("Save");
	private static JButton Load =new JButton("Load");
	private static JPanel mainPanel = new JPanel();
	public static int ChangeOfTurn=1;

	private static JPanel boardPanel= new JPanel();
	private JToolBar funtions = new JToolBar();

	public Ui()	{

		this.setTitle( "ULTIMATE CHESS" );
		this.setSize( 800, 600);
		this.setBackground( Color.white );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.setLayout( new BorderLayout());
		funtions.setFloatable(false);
		mainPanel.add(funtions, BorderLayout.PAGE_START);
		Save.addActionListener(new SaveListener());
		Load.addActionListener(new LoadListener()); 
		New.addActionListener(new NewListener());
		funtions.add(New);
		funtions.add(Save);
		funtions.add(Load);

		//Imagenes de los iconos de los botones	
		try {
			URL url = new URL("http://i.stack.imgur.com/memI0.png");
			BufferedImage bi = ImageIO.read(url);
			for (int ii = 0; ii < 2; ii++) {
				for (int jj = 0; jj < 6; jj++) {
					chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		

		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ){
				chessBoardSquares[i][j] = new JButton();
				chessBoardSquares[i][j].setMargin(buttonMargin);
				chessBoardSquares[i][j].setIcon(icon);
				if ((j % 2 == 1 && i % 2 == 1)
						//) {
						|| (i % 2 == 0 && j % 2 == 0)) {
					chessBoardSquares[i][j].setBackground(Color.WHITE);
				} else {
					chessBoardSquares[i][j].setBackground(Color.BLACK);
				}

			}
		}

		//asignanado imagenes a botones

		for (  int i=1; i < 2; i++ ) { 
			for ( int j=0; j < 8; j++ ) {
				chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[1][5])); 
			}
		} 
		for (  int i=6; i < 7; i++ ) { 
			for ( int j=0; j < 8; j++ ) 
				chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][5])); 
		}

		chessBoardSquares [0][0].setIcon(new ImageIcon(chessPieceImages[1][2]));
		chessBoardSquares [0][1].setIcon(new ImageIcon(chessPieceImages[1][3]));
		chessBoardSquares [0][2].setIcon(new ImageIcon(chessPieceImages[1][4]));
		chessBoardSquares [0][7].setIcon(new ImageIcon(chessPieceImages[1][2]));
		chessBoardSquares [0][6].setIcon(new ImageIcon(chessPieceImages[1][3]));
		chessBoardSquares [0][5].setIcon(new ImageIcon(chessPieceImages[1][4]));
		chessBoardSquares [0][4].setIcon(new ImageIcon(chessPieceImages[1][1]));
		chessBoardSquares [0][3].setIcon(new ImageIcon(chessPieceImages[1][0]));

		chessBoardSquares [7][0].setIcon(new ImageIcon(chessPieceImages[0][2]));
		chessBoardSquares [7][1].setIcon(new ImageIcon(chessPieceImages[0][3]));
		chessBoardSquares [7][2].setIcon(new ImageIcon(chessPieceImages[0][4]));
		chessBoardSquares [7][7].setIcon(new ImageIcon(chessPieceImages[0][2]));
		chessBoardSquares [7][6].setIcon(new ImageIcon(chessPieceImages[0][3]));
		chessBoardSquares [7][5].setIcon(new ImageIcon(chessPieceImages[0][4]));
		chessBoardSquares [7][4].setIcon(new ImageIcon(chessPieceImages[0][1]));
		chessBoardSquares [7][3].setIcon(new ImageIcon(chessPieceImages[0][0]));


		boardPanel.setLayout(new GridLayout(8,8));
		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ){
				boardPanel.add(chessBoardSquares[i][j]);
			}
		} 

		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ){
				chessBoardSquares[i][j].addActionListener(new chessBoardSquaresListener());
			}
		} 
		
		time.setPreferredSize(new Dimension(50, 50));
		ClockPanel.setLayout( new BorderLayout()); 
		ClockPanel.setBackground(Color.WHITE);
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setFont(new Font("Times New Roman", Font.BOLD, 38));		
		ClockPanel.add(time, BorderLayout.CENTER); 
		DigitalClock();
		ClockPanel.setPreferredSize(new Dimension(100, 100));
		boardPanel.setPreferredSize(new Dimension(100, 100));
		mainPanel.add(boardPanel, BorderLayout.CENTER);
		mainPanel.add(ClockPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
	}		


	private class chessBoardSquaresListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton curentButton = (JButton) (e.getSource());
			for ( int i=0; i < 8; i++ ) { 
				for ( int j=0; j < 8; j++ ){
					if (chessBoardSquares[i][j].equals(curentButton)){
						Game.ActionsToSelection(i,j);	 						
					}
				}

			}
		}
	}
	private class NewListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Game.NewGame();
			updateBoard(Game.board);
		}
	}

	private class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Game.saveGame();
		}
	}

	private class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Game.loadBoard();
		}
	}
	
	public static void cleanBoard(){
		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ){				
				if ((j % 2 == 1 && i % 2 == 1)	                        
						|| (i % 2 == 0 && j % 2 == 0)) {
					chessBoardSquares[i][j].setBackground(Color.WHITE);
				} else {
					chessBoardSquares[i][j].setBackground(Color.BLACK);
				}

			}
		}		
	}

	public static void showValidMoves(ArrayList<BoardPosition> validMoves){
		cleanBoard();
		boardPanel.repaint();
		for ( BoardPosition position : validMoves ) {			
			chessBoardSquares[position.getRow()][position.getColumn()].setBackground(Color.blue);
		}		
		boardPanel.repaint();
		
	}
	
	public static void showThreats(ArrayList<BoardPosition> threats){		
		for ( BoardPosition position : threats ) {
			chessBoardSquares[position.getRow()][position.getColumn()].setBackground(Color.red);
		}		
		boardPanel.repaint();
	}

	public static void movePiece(BoardPosition position1,BoardPosition position2){
		Icon iconPosition1;

		iconPosition1=chessBoardSquares[position1.getRow()][position1.getColumn()].getIcon();
		chessBoardSquares[position2.getRow()][position2.getColumn()].setIcon(iconPosition1);
		chessBoardSquares[position1.getRow()][position1.getColumn()].setIcon(null);			
		boardPanel.repaint();		
	}
	
	public static void showJakeMessage(JFrame frame) {
		JLabel jakeMessage= new JLabel();
		jakeMessage.setText("Jake");
		jakeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		jakeMessage.setFont(new Font("Times New Roman", Font.BOLD, 50));
		JOptionPane.showMessageDialog(frame,jakeMessage);
	}
	
	public static void showJakeMateMessage(JFrame frame) {
		JLabel jakeMessage= new JLabel();
		jakeMessage.setText("JakeMate");
		jakeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		jakeMessage.setFont(new Font("Times New Roman", Font.BOLD, 50));
		JOptionPane.showMessageDialog(frame,jakeMessage);
	}

	public static void updateBoard(Board board){
		String type= "";
		for ( int i=0; i < 8; i++ ) { 
			for ( int j=0; j < 8; j++ ){
				type = board.getBoard()[i][j].getClass().getName();
				
					switch (type) {
					case "data.Rook":
						if(board.getBoard()[i][j].getColor()=='b'){
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[1][2]));			

						}else{
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][2]));
						}
						break;

					case "data.Knight":
						if(board.getBoard()[i][j].getColor()=='b'){
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[1][3]));
						}else{
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][3]));
						}

						break;
					case "data.Bishop":
						if(board.getBoard()[i][j].getColor()=='b'){
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[1][4]));
						}else{
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][4]));
						}
						break;					
					case "data.King":
						if(board.getBoard()[i][j].getColor()=='b'){
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[1][1]));
						}else{
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][1]));
						}
						break;
					case "data.Queen":
						if(board.getBoard()[i][j].getColor()=='b'){
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[1][0]));
						}else{
							chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][0]));
						}
						break;
					case "data.WhitePawn":
						chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[0][5]));
						break;
					case "data.BlackPawn":
						chessBoardSquares[i][j].setIcon(new ImageIcon(chessPieceImages[1][5]));
						break;
					case "data.NoPiece":
						chessBoardSquares[i][j].setIcon(null);
						break;
				}		
			}		
		}
	}

	public void DigitalClock() {               

		Timer t1 = new Timer();		
		t1.scheduleAtFixedRate(new TurnTime(),0, 1000);               

	}

	private class TurnTime extends TimerTask{		
		int minute, second, reference; 

		@Override
		public void run() {				
			
			if(ChangeOfTurn>reference){
				second=0;
				minute=0;				
				reference=reference+1;
			}			
			second = (second+1)%60;
			if(second==59){
				minute= minute+1;
			}
			if(minute == 1){
				second=0;
				minute=0;
				ClockPanel.setBackground(Color.ORANGE);
				if(Game.currentColor == 'b'){
					Game.currentColor = 'w';
				}else{
					Game.currentColor = 'b';
				}	
			}
			
			time.setText(String.format("%02d:%02d",minute,second));
			time.updateUI();
			ClockPanel.repaint();	
			
		}

	}


}
