package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.Bomb;
import model.Captain;
import model.Colonel;
import model.Flag;
import model.General;
import model.Lieutenant;
import model.Major;
import model.Marshal;
import model.Miner;
import model.Pawn;
import model.PlayerHandler;
import model.Scout;
import model.Sergeant;
import model.Spy;

public class GameBoard extends Board{

	private static final long serialVersionUID = 1L;
	private PlayerHandler player;
	static ArrayList<Pawn> pawns;
	static ArrayList<Pawn> oppPawns = new ArrayList<Pawn>();
	
	private static int pieceLeft = 40;
	private static int oppPieceLeft = 40;
	
	private static int[][] oppPieceLocation = new int[10][10];
	
	public static boolean myTurn = true;
	
	protected JButton btnSur = new JButton("SURRENDER");
	protected static JLabel lblTurn = new JLabel("YOUR TURN", SwingConstants.CENTER);
	
	public GameBoard(PlayerHandler player, ArrayList<Pawn> pawns){
		super(player);
		this.player = player;
		this.pawns = pawns;
		initilizePawns();
		
		PieceSelect select = new PieceSelect();
		
		contentPane.addMouseListener(select);
		
		lblTurn.setForeground(new Color(255, 0, 0));
		lblTurn.setFont(new Font("Stencil", Font.BOLD, 25));
		lblTurn.setBounds(910, 54, 250, 120);
		contentPane.add(lblTurn, 0);
		
		if(!this.pawns.get(0).isRed()) {
			myTurn = false;
			lblTurn.setText("OPPONENT TURN");
			OpponentTurn opponent = new OpponentTurn(player);
			Thread thread = new Thread(opponent);
			thread.start();
		}
		System.out.println("done painting");
		
		btnSur.setForeground(new Color(255, 0, 0));
		btnSur.setFont(new Font("Stencil", Font.BOLD, 18));
		btnSur.setBounds(970, 717, 150, 57);
		btnSur.setEnabled(true);
		contentPane.add(btnSur);
		
		btnSur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(myTurn) {
					player.pr.println("surrender");
					player.pr.flush();
					varReset();
					System.out.println("You lose!!");
					JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
					topFrame.setVisible(false);
					topFrame.setEnabled(false);
					new EndingScreen(player, false);
				}
			}
		});
		
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
		topFrame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	player.pr.println("removePlayer");
				player.pr.flush();
		    	player.pr.println("exit");
				player.pr.flush();
				player.closeSocket();
				System.exit(0);
		    	
		    }
		});

	}

	@Override
	public void initilizePawns() {
		for(int i = 0; i<40; i++) {	
				contentPane.add(pawns.get(i).getPawnLabel(), 0);
				pieceLocation[pawns.get(i).getRow()][pawns.get(i).getCol()] = pawns.get(i).getStrength();
		}
	
		int count = 0;
		
		for(int i = 0; i<4; i++) {	
			for(int j = 0; j<10; j++) {
				Pawn p = new Pawn(!pawns.get(i).isRed(), true);
				oppPawns.add(p);
				oppPawns.get(count).getPawnLabel().setVisible(true);
				oppPawns.get(count).getPawnLabel().setBounds((j * 90) + 13, (i * 90) + 13, 65, 65);
				oppPawns.get(count).setRow(j);
				oppPawns.get(count).setCol(i);
				contentPane.add(oppPawns.get(count).getPawnLabel(), 0);
				oppPieceLocation[j][i] = 1;
				count++;
			}
		}
	}
	
	public static void turn(String answer, PlayerHandler player) {
		
		switch(answer) {
			case "move":
				try {
					int row = Integer.parseInt(player.bf.readLine());
					int col = Integer.parseInt(player.bf.readLine());
					int rowMove = Integer.parseInt(player.bf.readLine());
					int colMove = Integer.parseInt(player.bf.readLine());
					
					for(int i = 0; i<oppPieceLeft; i++) {	
						if(oppPawns.get(i).getRow() == row && oppPawns.get(i).getCol() == col ) {
							oppPawns.get(i).getPawnLabel().setBounds((rowMove * 90) + 13, (colMove * 90) + 13, 65, 65);
							oppPawns.get(i).setRow(rowMove);
							oppPawns.get(i).setCol(colMove);
							oppPieceLocation[rowMove][colMove] = oppPieceLocation[row][col];
							oppPieceLocation[row][col] = 0;
							break;
						}
					}
					
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}

				myTurn = true;
				break;
			case "win":
				try {
					int row = Integer.parseInt(player.bf.readLine());
					int col = Integer.parseInt(player.bf.readLine());
					int rowMove = Integer.parseInt(player.bf.readLine());
					int colMove = Integer.parseInt(player.bf.readLine());
					int type = Integer.parseInt(player.bf.readLine());
	
					for(int i = 0; i<oppPieceLeft; i++) {	
						if(oppPawns.get(i).getRow() == row && oppPawns.get(i).getCol() == col ) {
							oppPawns.get(i).getPawnLabel().setVisible(false);
							oppPawns.get(i).getPawnLabel().setEnabled(false);
							oppPawns.remove(i);
							oppPieceLocation[row][col] = 0;
							oppPieceLeft--;
							break;
						}
					}
					
					myTurn = true;
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
					break;
			
			case "tie":
				try {
					int row = Integer.parseInt(player.bf.readLine());
					int col = Integer.parseInt(player.bf.readLine());
					int rowMove = Integer.parseInt(player.bf.readLine());
					int colMove = Integer.parseInt(player.bf.readLine());
					int type = Integer.parseInt(player.bf.readLine());
	
					for(int i = 0; i<oppPieceLeft; i++) {	
						if(oppPawns.get(i).getRow() == row && oppPawns.get(i).getCol() == col ) {
							oppPawns.get(i).getPawnLabel().setVisible(false);
							oppPawns.get(i).getPawnLabel().setEnabled(false);
							oppPawns.remove(i);
							oppPieceLocation[row][col] = 0;
							oppPieceLeft--;
							break;
						}
					}
					
					for(int i = 0; i<pieceLeft; i++) {
						if(pawns.get(i).getRow() == rowMove && pawns.get(i).getCol() == colMove ) {
							pawns.get(i).getPawnLabel().setVisible(false);
							pawns.remove(i);
							pieceLocation[rowMove][colMove] = 0;
							pieceLeft--;
							break;
						}
						
					}
					myTurn = true;
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
				break;
				
			case "lose":
				try {
					int row = Integer.parseInt(player.bf.readLine());
					int col = Integer.parseInt(player.bf.readLine());
					int rowMove = Integer.parseInt(player.bf.readLine());
					int colMove = Integer.parseInt(player.bf.readLine());
					int type = Integer.parseInt(player.bf.readLine());
	
					for(int i = 0; i<oppPieceLeft; i++) {	
						if(oppPawns.get(i).getRow() == row && oppPawns.get(i).getCol() == col ) {
							Pawn temp = new Pawn(type, !pawns.get(0).isRed(), rowMove, colMove);
							oppPawns.add(temp);
							oppPawns.get(oppPawns.size()-1).getPawnLabel().setBounds((rowMove * 90) + 13, (colMove * 90) + 13, 65, 65);
							oppPawns.get(oppPawns.size()-1).getPawnLabel().setVisible(true);
							contentPane.add(oppPawns.get(oppPawns.size()-1).getPawnLabel(), 0);
							
							oppPawns.get(i).getPawnLabel().setVisible(false);
							oppPawns.get(i).getPawnLabel().setEnabled(false);
							oppPawns.remove(i);
							oppPieceLocation[rowMove][colMove] = oppPieceLocation[row][col];
							oppPieceLocation[row][col] = 0;
							break;
						}
					}
					
					for(int i = 0; i<pieceLeft; i++) {
						if(pawns.get(i).getRow() == rowMove && pawns.get(i).getCol() == colMove ) {
							pawns.get(i).getPawnLabel().setVisible(false);
							pawns.remove(i);
							pieceLocation[rowMove][colMove] = 0;
							pieceLeft--;
							break;
						}
						
					}
					myTurn = true;
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
				break;
			case"victory":
				System.out.println("You Win!!");
				varReset();
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
				topFrame.setVisible(false);
				topFrame.setEnabled(false);
				new EndingScreen(player, true);
				break;
			case"defeat":
				System.out.println("You Lose!!");
				varReset();
				topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
				topFrame.setVisible(false);
				topFrame.setEnabled(false);
				new EndingScreen(player, false);
				break;
		}
		
		lblTurn.setText("YOUR TURN");
		
	}
	
	class PieceSelect implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(Pawn.selected && myTurn) {
				int tempRow = 0;
				int tempCol = 0;
				String answer;
	
				for (int i = 0; i < 11; i++)
					if (x[i] > arg0.getX()) {
						tempRow = x[i - 1];
						break;
					}
	
				for (int i = 0; i < 11; i++)
					if (x[i] > arg0.getY()) {
						tempCol = x[i - 1];
						break;
					}
				
				System.out.println("Got signal");
				
				for(int i = 0; i<pieceLeft; i++) {
					if(pawns.get(i).getRow() == Pawn.tempRow && pawns.get(i).getCol() == Pawn.tempCol) {			
						if(pawns.get(i).move(tempRow, tempCol, pieceLocation, oppPieceLocation) && pieceLocation[tempRow/90][tempCol/90] == 0 && oppPieceLocation[tempRow/90][tempCol/90] == 0){
							System.out.println("Send signal to move");
							player.pr.println("move");
							player.pr.flush();
							player.pr.println(pawns.get(i).getRow());
							player.pr.flush();
							player.pr.println(pawns.get(i).getCol());
							player.pr.flush();
							player.pr.println(tempRow/90);
							player.pr.flush();
							player.pr.println(tempCol/90);
							player.pr.flush();
							player.pr.println(pawns.get(i).getStrength());
							player.pr.flush();
							System.out.println("Send data");
							try {
								answer = player.bf.readLine();
								if(answer.equals("ok")) {
									System.out.println("Got answer to move");
									pieceLocation[tempRow/90][tempCol/90] = pieceLocation[pawns.get(i).getRow()][pawns.get(i).getCol()];
									pieceLocation[pawns.get(i).getRow()][pawns.get(i).getCol()] = 0;
									
									pawns.get(i).getPawnLabel().setBounds(tempRow+ 13, tempCol + 13, 65, 65);
									pawns.get(i).setRow(tempRow/90);
									pawns.get(i).setCol(tempCol/90);
									Pawn.selected = false;
									myTurn = false;
									System.out.println("Sent opponent message to play");
									OpponentTurn opponent = new OpponentTurn(player);
									Thread thread = new Thread(opponent);
									thread.start();
									System.out.println("Move complete");
									lblTurn.setText("OPPONENT TURN");
									break;
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else if(pawns.get(i).move(tempRow, tempCol, pieceLocation, oppPieceLocation) && pieceLocation[tempRow/90][tempCol/90] == 0 && oppPieceLocation[tempRow/90][tempCol/90] != 0) {
							System.out.println("Got to the start of the attack");
							player.pr.println("attack");
							player.pr.flush();
							player.pr.println(pawns.get(i).getRow());
							player.pr.flush();
							player.pr.println(pawns.get(i).getCol());
							player.pr.flush();
							player.pr.println(tempRow/90);
							player.pr.flush();
							player.pr.println(tempCol/90);
							player.pr.flush();
							player.pr.println(pawns.get(i).getStrength());
							player.pr.flush();
							
							System.out.println("Send data to server");
							lblTurn.setText("OPPONENT TURN");
							try {
								answer = player.bf.readLine();
								System.out.println("Got answer");
								switch(answer) {
									case"win":
										pieceLocation[tempRow/90][tempCol/90] = pieceLocation[pawns.get(i).getRow()][pawns.get(i).getCol()];
										pieceLocation[pawns.get(i).getRow()][pawns.get(i).getCol()] = 0;
										
										pawns.get(i).getPawnLabel().setBounds(tempRow+ 13, tempCol + 13, 65, 65);
										pawns.get(i).setRow(tempRow/90);
										pawns.get(i).setCol(tempCol/90);
										
										for(int j = 0; j<oppPieceLeft; j++) {	
											if(oppPawns.get(j).getRow() == tempRow/90 && oppPawns.get(j).getCol() == tempCol/90) {
												oppPawns.get(j).getPawnLabel().setVisible(false);
												oppPawns.remove(j);
												oppPieceLocation[tempRow/90][tempCol/90] = 0;
												break;
											}
										}
										oppPieceLeft--;
										Pawn.selected = false;
										myTurn = false;
										System.out.println("Attack won");
										OpponentTurn opponent = new OpponentTurn(player);
										Thread thread = new Thread(opponent);
										thread.start();
										
										break;
									case"tie":
										pieceLocation[pawns.get(i).getRow()][pawns.get(i).getCol()] = 0;
										
										pawns.get(i).getPawnLabel().setVisible(false);
										pawns.remove(i);
										pieceLeft--;
										
										for(int j = 0; j<oppPieceLeft; j++) {	
											if(oppPawns.get(j).getRow() == tempRow/90 && oppPawns.get(j).getCol() == tempCol/90) {
												oppPawns.get(j).getPawnLabel().setVisible(false);
												oppPawns.remove(j);
												oppPieceLocation[tempRow/90][tempCol/90] = 0;
												break;
											}
										}
										oppPieceLeft--;
										Pawn.selected = false;
										myTurn = false;
										System.out.println("Attack tied");
										opponent = new OpponentTurn(player);
										thread = new Thread(opponent);
										thread.start();
										break;
										
									case"lose":
										System.out.println("Attacking player got lose signal");

										int str = Integer.parseInt(player.bf.readLine());
										System.out.println("Attacking player got str of enemy piece "+str);
										for(int j = 0; j<oppPieceLeft; j++) {	
											if(oppPawns.get(j).getRow() == tempRow/90 && oppPawns.get(j).getCol() == tempCol/90) {
			
												Pawn temp = new Pawn(str, !pawns.get(0).isRed(), tempRow/90, tempCol/90);
												System.out.println("Created enemy piece");
												oppPawns.add(temp);
												oppPawns.get(oppPawns.size()-1).getPawnLabel().setBounds((tempRow) + 13, (tempCol) + 13, 65, 65);
												oppPawns.get(oppPawns.size()-1).getPawnLabel().setVisible(true);
												contentPane.add(oppPawns.get(oppPawns.size()-1).getPawnLabel(), 0);
												System.out.println("Placed it on the board");
												
												oppPawns.get(j).getPawnLabel().setVisible(false);
												oppPawns.remove(j);
												System.out.println("Deleted enemy piece");
		
												break;
											}
										}
										
										pieceLocation[pawns.get(i).getRow()][pawns.get(i).getCol()] = 0;
										pawns.get(i).getPawnLabel().setVisible(false);
										pawns.remove(i);
										pieceLeft--;
										System.out.println("Attacking player deleted piece");
										Pawn.selected = false;
										myTurn = false;
										System.out.println("Attack lost");
										opponent = new OpponentTurn(player);
										thread = new Thread(opponent);
										thread.start();
										break;
									case"victory":
										System.out.println("You Win!!");
										varReset();
										JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
										topFrame.setVisible(false);
										topFrame.setEnabled(false);
										new EndingScreen(player, true);
										break;
								}
								break;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}	
				}
				

			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static Pawn createPawn(int type, boolean isRed, int row, int col) {
		
		switch(type) {
			case 11:
				Bomb bomb = new Bomb(isRed, row, col);
				bomb.setOppPawn(true);
				return bomb;
			case 10:
				Marshal marshal = new Marshal(isRed, row, col);
				marshal.setOppPawn(true);
				return marshal;
			case 9:
				General general = new General(isRed, row, col);
				general.setOppPawn(true);
				return general;
			case 8:
				Colonel colonel = new Colonel(isRed, row, col);
				colonel.setOppPawn(true);
				return colonel;
			case 7:
				Major major = new Major(isRed, row, col);
				major.setOppPawn(true);
				return major;
			case 6:
				Captain captain = new Captain(isRed, row, col);
				captain.setOppPawn(true);
				return captain;
			case 5:
				Lieutenant lieutenant = new Lieutenant(isRed, row, col);
				lieutenant.setOppPawn(true);
				return lieutenant;
			case 4:
				Sergeant sergeant = new Sergeant(isRed, row, col);
				sergeant.setOppPawn(true);
				return sergeant;
			case 3:
				Miner miner = new Miner(isRed, row, col);
				miner.setOppPawn(true);
				return miner;
			case 2:
				Scout scout = new Scout(isRed, row, col);
				scout.setOppPawn(true);
				return scout;
			case 1:
				Spy spy = new Spy(isRed, row, col);
				spy.setOppPawn(true);
				return spy;
			case -1:
				Flag flag = new Flag(isRed, row, col);
				flag.setOppPawn(true);
				return flag;

		}
		
		Flag test = new Flag();
		return test;
	}
	
	private static void varReset() {
		myTurn = true;
		pawns.removeAll(pawns);
		oppPawns.removeAll(oppPawns);
		pieceLeft = 40;
		oppPieceLeft = 40;
				
		for(int i = 0; i<10; i++)
			for(int j = 0; j<10; j++) {
				pieceLocation[i][j] = 0;
				oppPieceLocation[i][j] = 0;
			}
	}
}
