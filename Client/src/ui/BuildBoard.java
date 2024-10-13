package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class BuildBoard extends Board {

	private static final long serialVersionUID = 1L;
	private boolean isRed;
	private ArrayList<Pawn> pawns;
	
	protected static final int[] y = { 540, 630, 720, 810, 900 };
	protected int numberPlaced = 0; 
	protected String piece;
	protected boolean selected = false;
	
	protected JButton btnReady = new JButton("READY");
	protected JButton btnSave = new JButton("SAVE PRESET");
	
	protected Bomb[] bombs = new Bomb[Pawn.BOMBS];
	protected int bombPlaced = 0; 
	protected JLabel lblNumberBombs = new JLabel(String.valueOf(Pawn.BOMBS));
	protected JButton btnBomb = new JButton(""); 
	
	protected Marshal marshal = new Marshal();
	protected int marshalPlaced = 0; 
	protected JLabel lblNumberMarshal = new JLabel(String.valueOf(Pawn.MARSHAL));
	protected JButton btnMarshal = new JButton("");
	
	protected General general = new General();
	protected int generalPlaced = 0; 
	protected JLabel lblNumberGeneral = new JLabel(String.valueOf(Pawn.GENERAL));
	protected JButton btnGeneral = new JButton("");

	protected Colonel[] colonels = new Colonel[Pawn.COLONEL];
	protected int colonelPlaced = 0; 
	protected JLabel lblNumberColonel = new JLabel(String.valueOf(Pawn.COLONEL));
	protected JButton btnColonel = new JButton("");
	
	protected Major[] majors = new Major[Pawn.MAJOR];
	protected int majorPlaced = 0; 
	protected JLabel lblNumberMajor = new JLabel(String.valueOf(Pawn.MAJOR));
	protected JButton btnMajor = new JButton("");
	
	protected Captain[] captains = new Captain[Pawn.CAPTAIN];
	protected int captainPlaced = 0; 
	protected JLabel lblNumberCaptain = new JLabel(String.valueOf(Pawn.CAPTAIN));
	protected JButton btnCaptain = new JButton("");
	
	protected Lieutenant[] lieutenants = new Lieutenant[Pawn.LIEUTENANT];
	protected int lieutenantPlaced = 0; 
	protected JLabel lblNumberLieutenant = new JLabel(String.valueOf(Pawn.LIEUTENANT));
	protected JButton btnLieutenant = new JButton("");
	
	protected Sergeant[] sergeants = new Sergeant[Pawn.SERGEANT];
	protected int sergeantPlaced = 0; 
	protected JLabel lblNumberSergeant = new JLabel(String.valueOf(Pawn.SERGEANT));
	protected JButton btnSergeant = new JButton("");
	
	protected Miner[] miners = new Miner[Pawn.MINER];
	protected int minerPlaced = 0; 
	protected JLabel lblNumberMiner = new JLabel(String.valueOf(Pawn.MINER));
	protected JButton btnMiner = new JButton("");
	
	protected Scout[] scouts = new Scout[Pawn.SCOUT];
	protected int scoutPlaced = 0; 
	protected JLabel lblNumberScout = new JLabel(String.valueOf(Pawn.SCOUT));
	protected JButton btnScout = new JButton("");
	
	protected Spy spy = new Spy();
	protected int spyPlaced = 0; 
	protected JLabel lblNumberSpy = new JLabel(String.valueOf(Pawn.SPY));
	protected JButton btnSpy = new JButton("");
	
	protected Flag flag = new Flag();
	protected int flagPlaced = 0; 
	protected JLabel lblNumberFlag = new JLabel(String.valueOf(Pawn.FLAG));
	protected JButton btnFlag = new JButton("");

	public BuildBoard(boolean isRed, ArrayList<Pawn> pawns, PlayerHandler player){
		super(player);
		this.isRed = isRed;
		this.pawns = pawns;
		pawns.removeAll(pawns);
		for(int i = 0; i<10; i++)
			for(int j = 0; j<10; j++)
				pieceLocation[i][j] = 0;
		
	    initilizePawns();
	    PiecePlace place = new PiecePlace();
	    
		btnBomb.setToolTipText("Bomb");
		btnBomb.setIcon(bombs[0].getPawnIcon());
		btnBomb.setBounds(910, 10, 65, 65);
		contentPane.add(btnBomb);

		lblNumberBombs.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberBombs.setBounds(985, 25, 46, 32);
		contentPane.add(lblNumberBombs);
		
		btnBomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnBomb.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnMarshal.setToolTipText("Marshal");
		btnMarshal.setIcon(marshal.getPawnIcon());
		btnMarshal.setBounds(1041, 10, 65, 65);
		contentPane.add(btnMarshal);

		lblNumberMarshal.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberMarshal.setBounds(1128, 10, 65, 65);
		contentPane.add(lblNumberMarshal);
		
		btnMarshal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnMarshal.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnGeneral.setToolTipText("General");
		btnGeneral.setIcon(general.getPawnIcon());
		btnGeneral.setBounds(910, 110, 65, 65);
		contentPane.add(btnGeneral);

		lblNumberGeneral.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberGeneral.setBounds(985, 125, 46, 32);
		contentPane.add(lblNumberGeneral);
		
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnGeneral.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnColonel.setToolTipText("Colonel");
		btnColonel.setIcon(colonels[0].getPawnIcon());
		btnColonel.setBounds(1041, 110, 65, 65);
		contentPane.add(btnColonel);

		lblNumberColonel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberColonel.setBounds(1128, 125, 46, 32);
		contentPane.add(lblNumberColonel);
		
		btnColonel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnColonel.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnMajor.setToolTipText("Major");
		btnMajor.setIcon(majors[0].getPawnIcon());
		btnMajor.setBounds(910, 210, 65, 65);
		contentPane.add(btnMajor);

		lblNumberMajor.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberMajor.setBounds(985, 225, 46, 32);
		contentPane.add(lblNumberMajor);
		
		btnMajor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnMajor.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnCaptain.setToolTipText("Captain");
		btnCaptain.setIcon(captains[0].getPawnIcon());
		btnCaptain.setBounds(1041, 210, 65, 65);
		contentPane.add(btnCaptain);

		lblNumberCaptain.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberCaptain.setBounds(1128, 225, 46, 32);
		contentPane.add(lblNumberCaptain);
		
		btnCaptain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnCaptain.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnLieutenant.setToolTipText("Lieutenant");
		btnLieutenant.setIcon(lieutenants[0].getPawnIcon());
		btnLieutenant.setBounds(910, 310, 65, 65);
		contentPane.add(btnLieutenant);

		lblNumberLieutenant.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberLieutenant.setBounds(985, 325, 46, 32);
		contentPane.add(lblNumberLieutenant);
		
		btnLieutenant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnLieutenant.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnSergeant.setToolTipText("Sergeant");
		btnSergeant.setIcon(sergeants[0].getPawnIcon());
		btnSergeant.setBounds(1041, 310, 65, 65);
		contentPane.add(btnSergeant);

		lblNumberSergeant.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberSergeant.setBounds(1128, 325, 46, 32);
		contentPane.add(lblNumberSergeant);
		
		btnSergeant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnSergeant.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnMiner.setToolTipText("Miner");
		btnMiner.setIcon(miners[0].getPawnIcon());
		btnMiner.setBounds(910, 410, 65, 65);
		contentPane.add(btnMiner);

		lblNumberMiner.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberMiner.setBounds(985, 425, 46, 32);
		contentPane.add(lblNumberMiner);
		
		btnMiner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnMiner.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnScout.setToolTipText("Scout");
		btnScout.setIcon(scouts[0].getPawnIcon());
		btnScout.setBounds(1041, 410, 65, 65);
		contentPane.add(btnScout);

		lblNumberScout.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberScout.setBounds(1128, 425, 46, 32);
		contentPane.add(lblNumberScout);
		
		btnScout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnScout.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnSpy.setToolTipText("Spy");
		btnSpy.setIcon(spy.getPawnIcon());
		btnSpy.setBounds(910, 510, 65, 65);
		contentPane.add(btnSpy);

		lblNumberSpy.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberSpy.setBounds(985, 525, 46, 32);
		contentPane.add(lblNumberSpy);
		
		btnSpy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnSpy.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnFlag.setToolTipText("Flag");
		btnFlag.setIcon(flag.getPawnIcon());
		btnFlag.setBounds(1041, 510, 65, 65);
		contentPane.add(btnFlag);

		lblNumberFlag.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNumberFlag.setBounds(1128, 525, 46, 32);
		contentPane.add(lblNumberFlag);
		
		btnFlag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = btnFlag.getToolTipText();
				System.out.println("Piece: "+piece);
				selected = true;

			}
		});
		
		btnReady.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnReady.setForeground(new Color(255, 0, 0));
		btnReady.setBounds(1041, 807, 116, 57);
		btnReady.setEnabled(false);
		contentPane.add(btnReady);
		
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Ready!!!");
				btnReady.setEnabled(false);
		    	player.pr.println("ready");
				player.pr.flush();
				JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
				topFrame.setVisible(false);
				topFrame.setEnabled(false);
				ReadyAnswer readygAnswer = new ReadyAnswer(player, pawns);
				Thread thread = new Thread(readygAnswer);
				thread.start();
			}
		});
		
		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnReset.setForeground(new Color(255, 0, 0));
		btnReset.setEnabled(true);
		btnReset.setBounds(915, 807, 116, 57);
		contentPane.add(btnReset);
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetVar();
				resetPawns();
			}
		});
		
		btnSave.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnSave.setForeground(new Color(255, 0, 0));
		btnSave.setEnabled(false);
		btnSave.setBounds(915, 670, 242, 57);
		contentPane.add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Give the preset a name");
				if(name != null) {
					name = "presets/"+ name + ".bin";
					System.out.println("File name "+name);
					try {
						FileOutputStream fileOs = new FileOutputStream(name);
						ObjectOutputStream os = new ObjectOutputStream(fileOs);
						
						for(int i = 0; i<10; i++) {
							for(int j = 6; j<10; j++) {
								int a = pieceLocation[i][j];
								os.writeInt(a);
								System.out.print(pieceLocation[i][j]);
							}
							System.out.println();
						}
						
						os.close();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton btnLoad = new JButton("LOAD PRESET");
		btnLoad.setFont(new Font("Stencil", Font.PLAIN, 20));
		btnLoad.setForeground(new Color(255, 0, 0));
		btnLoad.setEnabled(true);
		btnLoad.setBounds(915, 739, 242, 57);
		contentPane.add(btnLoad);
		
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String path = "presets/";
				ArrayList<String> presets = new ArrayList<String>();
				
				File folder = new File(path);
				File[] files = folder.listFiles();
				
				for(File file:files) {
					if(file.isFile()) {
						System.out.println("Preset: "+file.getName());
						presets.add(file.getName());
					}
				}

		        String name = (String)JOptionPane.showInputDialog(null, "Select a preset", 
		                "Stratego", JOptionPane.QUESTION_MESSAGE, null, presets.toArray(), null);
		        System.out.println(name);
		        if(name != null) {
		        	
		        	name = "presets/"+ name;
		        	System.out.println(name);
		        	resetVar();
					resetPawns();

					try {
						FileInputStream  fileIs = new FileInputStream(name);
						ObjectInputStream is = new ObjectInputStream(fileIs);

						for(int i = 0; i<10; i++) {
							for(int j = 6; j<10; j++) {
								piece = convertPawn(is.readInt());
								System.out.print(piece+" ");	
								if(piece != null)
									addPawn(i*90, j*90);
							}
							System.out.println();
						}
						
						is.close();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
		        	
		        	
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
		
		contentPane.addMouseListener(place);
	}

	@Override
	public void initilizePawns() {
		for(int i =0; i<Pawn.BOMBS; i++) {
			bombs[i] = new Bomb(isRed);
			contentPane.add(bombs[i].getPawnLabel(), 0);
			pawns.add(bombs[i]);
		}
		
		marshal = new Marshal(isRed);
		contentPane.add(marshal.getPawnLabel(), 0);
		pawns.add(marshal);
		
		general = new General(isRed);
		contentPane.add(general.getPawnLabel(), 0);
		pawns.add(general);
		
		for(int i =0; i<Pawn.COLONEL; i++) {
			colonels[i] = new Colonel(isRed);
			contentPane.add(colonels[i].getPawnLabel(), 0);
			pawns.add(colonels[i]);
		}
		
		for(int i =0; i<Pawn.MAJOR; i++) {
			majors[i] = new Major(isRed);
			contentPane.add(majors[i].getPawnLabel(), 0);
			pawns.add(majors[i]);
		}
		
		for(int i =0; i<Pawn.CAPTAIN; i++) {
			captains[i] = new Captain(isRed);
			contentPane.add(captains[i].getPawnLabel(), 0);
			pawns.add(captains[i]);
		}
		
		for(int i =0; i<Pawn.LIEUTENANT; i++) {
			lieutenants[i] = new Lieutenant(isRed);
			contentPane.add(lieutenants[i].getPawnLabel(), 0);
			pawns.add(lieutenants[i]);
		}
		
		for(int i =0; i<Pawn.SERGEANT; i++) {
			sergeants[i] = new Sergeant(isRed);
			contentPane.add(sergeants[i].getPawnLabel(), 0);
			pawns.add(sergeants[i]);
		}
		
		for(int i =0; i<Pawn.MINER; i++) {
			miners[i] = new Miner(isRed);
			contentPane.add(miners[i].getPawnLabel(), 0);
			pawns.add(miners[i]);
		}
		
		for(int i =0; i<Pawn.SCOUT; i++) {
			scouts[i] = new Scout(isRed);
			contentPane.add(scouts[i].getPawnLabel(), 0);
			pawns.add(scouts[i]);
		}
		
		spy = new Spy(isRed);
		contentPane.add(spy.getPawnLabel(), 0);
		pawns.add(spy);
		
		flag = new Flag(isRed);
		contentPane.add(flag.getPawnLabel(), 0);
		pawns.add(flag);
	}
	
	class PiecePlace implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (selected && arg0.getY() >= 540 && arg0.getX() <= 900) {
				int a = 0;
				int b = 0;

				for (int i = 0; i < 11; i++)
					if (x[i] > arg0.getX()) {
						a = x[i - 1];
						break;
					}

				for (int i = 0; i < 5; i++)
					if (y[i] > arg0.getY()) {
						b = y[i - 1];
						break;
					}
				addPawn(a, b);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
	
	public void addPawn(int a, int b) {
		if (pieceLocation[a / 90][b / 90] == 0) {
			switch(piece) {
			case"Bomb":	
				pieceLocation[a / 90][b / 90] = 11;	
				bombs[bombPlaced].getPawnLabel().setVisible(true);
				bombs[bombPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				bombs[bombPlaced].getPawnLabel().setIcon(bombs[bombPlaced].getIcon());
				bombs[bombPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				bombs[bombPlaced].setCol(b/90);// thesi einai 8-8
				bombPlaced++;
				lblNumberBombs.setText(String.valueOf(Pawn.BOMBS - bombPlaced));
				selected = false;
				numberPlaced++;
				if (bombPlaced == Pawn.BOMBS)
					btnBomb.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("11");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();

				break;
			case"Marshal":
				pieceLocation[a / 90][b / 90] = 10;	
				marshal.getPawnLabel().setVisible(true);
				marshal.getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				marshal.getPawnLabel().setIcon(marshal.getIcon());
				marshal.setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				marshal.setCol(b/90);// thesi einai 8-8
				marshalPlaced++;
				lblNumberMarshal.setText(String.valueOf(Pawn.MARSHAL - marshalPlaced));
				selected = false;
				numberPlaced++;
				if (marshalPlaced == Pawn.MARSHAL)
					btnMarshal.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("10");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"General":
				pieceLocation[a / 90][b / 90] = 9;	
				general.getPawnLabel().setVisible(true);
				general.getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				general.getPawnLabel().setIcon(general.getIcon());
				general.setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				general.setCol(b/90);// thesi einai 8-8
				generalPlaced++;
				lblNumberGeneral.setText(String.valueOf(Pawn.GENERAL - generalPlaced));
				selected = false;
				numberPlaced++;
				if (generalPlaced == Pawn.GENERAL)
					btnGeneral.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("9");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Colonel":
				pieceLocation[a / 90][b / 90] = 8;	
				colonels[colonelPlaced].getPawnLabel().setVisible(true);
				colonels[colonelPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				colonels[colonelPlaced].getPawnLabel().setIcon(colonels[colonelPlaced].getIcon());
				colonels[colonelPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				colonels[colonelPlaced].setCol(b/90);// thesi einai 8-8
				colonelPlaced++;
				lblNumberColonel.setText(String.valueOf(Pawn.COLONEL - colonelPlaced));
				selected = false;
				numberPlaced++;
				if (colonelPlaced == Pawn.COLONEL)
					btnColonel.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("8");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Major":
				pieceLocation[a / 90][b / 90] = 7;	
				majors[majorPlaced].getPawnLabel().setVisible(true);
				majors[majorPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				majors[majorPlaced].getPawnLabel().setIcon(majors[majorPlaced].getIcon());
				majors[majorPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				majors[majorPlaced].setCol(b/90);// thesi einai 8-8
				majorPlaced++;
				lblNumberMajor.setText(String.valueOf(Pawn.MAJOR - majorPlaced));
				selected = false;
				numberPlaced++;
				if (majorPlaced == Pawn.MAJOR)
					btnMajor.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("7");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Captain":
				pieceLocation[a / 90][b / 90] = 6;	
				captains[captainPlaced].getPawnLabel().setVisible(true);
				captains[captainPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				captains[captainPlaced].getPawnLabel().setIcon(captains[captainPlaced].getIcon());
				captains[captainPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				captains[captainPlaced].setCol(b/90);// thesi einai 8-8
				captainPlaced++;
				lblNumberCaptain.setText(String.valueOf(Pawn.CAPTAIN - captainPlaced));
				selected = false;
				numberPlaced++;
				if (captainPlaced == Pawn.CAPTAIN)
					btnCaptain.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("6");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Lieutenant":
				pieceLocation[a / 90][b / 90] = 5;	
				lieutenants[lieutenantPlaced].getPawnLabel().setVisible(true);
				lieutenants[lieutenantPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				lieutenants[lieutenantPlaced].getPawnLabel().setIcon(lieutenants[lieutenantPlaced].getIcon());
				lieutenants[lieutenantPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				lieutenants[lieutenantPlaced].setCol(b/90);// thesi einai 8-8
				lieutenantPlaced++;
				lblNumberLieutenant.setText(String.valueOf(Pawn.LIEUTENANT - lieutenantPlaced));
				selected = false;
				numberPlaced++;
				if (lieutenantPlaced == Pawn.LIEUTENANT)
					btnLieutenant.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("5");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Sergeant":
				pieceLocation[a / 90][b / 90] = 4;	
				sergeants[sergeantPlaced].getPawnLabel().setVisible(true);
				sergeants[sergeantPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				sergeants[sergeantPlaced].getPawnLabel().setIcon(sergeants[sergeantPlaced].getIcon());
				sergeants[sergeantPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				sergeants[sergeantPlaced].setCol(b/90);// thesi einai 8-8
				sergeantPlaced++;
				lblNumberSergeant.setText(String.valueOf(Pawn.SERGEANT - sergeantPlaced));
				selected = false;
				numberPlaced++;
				if (sergeantPlaced == Pawn.SERGEANT)
					btnSergeant.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("4");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Miner":
				pieceLocation[a / 90][b / 90] = 3;	
				miners[minerPlaced].getPawnLabel().setVisible(true);
				miners[minerPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				miners[minerPlaced].getPawnLabel().setIcon(miners[minerPlaced].getIcon());
				miners[minerPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				miners[minerPlaced].setCol(b/90);// thesi einai 8-8
				minerPlaced++;
				lblNumberMiner.setText(String.valueOf(Pawn.MINER - minerPlaced));
				selected = false;
				numberPlaced++;
				if (minerPlaced == Pawn.MINER)
					btnMiner.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("3");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Scout":
				pieceLocation[a / 90][b / 90] = 2;	
				scouts[scoutPlaced].getPawnLabel().setVisible(true);
				scouts[scoutPlaced].getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				scouts[scoutPlaced].getPawnLabel().setIcon(scouts[scoutPlaced].getIcon());
				scouts[scoutPlaced].setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				scouts[scoutPlaced].setCol(b/90);// thesi einai 8-8
				scoutPlaced++;
				lblNumberScout.setText(String.valueOf(Pawn.SCOUT - scoutPlaced));
				selected = false;
				numberPlaced++;
				if (scoutPlaced == Pawn.SCOUT)
					btnScout.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("2");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Spy":
				pieceLocation[a / 90][b / 90] = 1;	
				spy.getPawnLabel().setVisible(true);
				spy.getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				spy.getPawnLabel().setIcon(spy.getIcon());
				spy.setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				spy.setCol(b/90);// thesi einai 8-8
				spyPlaced++;
				lblNumberSpy.setText(String.valueOf(Pawn.SPY - spyPlaced));
				selected = false;
				numberPlaced++;
				if (spyPlaced == Pawn.SPY)
					btnSpy.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("1");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			case"Flag":
				pieceLocation[a / 90][b / 90] = -1;	
				flag.getPawnLabel().setVisible(true);
				flag.getPawnLabel().setBounds(a + 13, b + 13, 65, 65);
				flag.getPawnLabel().setIcon(flag.getIcon());
				flag.setRow(a/90);// tis sitetagmenes tis metraei apo to 0 opote an grafei 7,7 i pragmatiki 
				flag.setCol(b/90);// thesi einai 8-8
				flagPlaced++;
				lblNumberFlag.setText(String.valueOf(Pawn.FLAG - flagPlaced));
				selected = false;
				numberPlaced++;
				if (flagPlaced == Pawn.FLAG)
					btnFlag.setEnabled(false);
				
				player.pr.println("placePiece");
				player.pr.flush();
				player.pr.println("-1");
				player.pr.flush();
				player.pr.println(a/90);
				player.pr.flush();
				player.pr.println(b/90);
				player.pr.flush();
				
				break;
			default:
				
		}
			
			System.out.println("Row : "+a+" Col: "+b);
			if(numberPlaced == 40) 
				btnReady.setEnabled(true);
	
			if(numberPlaced == 1)
				btnSave.setEnabled(true);
		}
	}
	
	protected void resetVar() {
		numberPlaced = 0;
		piece = "";
		selected = false;
		btnReady.setEnabled(false);
		btnSave.setEnabled(false);
		
		bombPlaced = 0;
		marshalPlaced = 0;
		generalPlaced = 0;
		colonelPlaced = 0;
		majorPlaced = 0;
		captainPlaced = 0;
		lieutenantPlaced = 0;
		sergeantPlaced = 0;
		minerPlaced = 0;
		scoutPlaced = 0;
		spyPlaced = 0;
		flagPlaced = 0;
		
		btnBomb.setEnabled(true);
		btnMarshal.setEnabled(true);
		btnGeneral.setEnabled(true);
		btnColonel.setEnabled(true);
		btnMajor.setEnabled(true);
		btnCaptain.setEnabled(true);
		btnLieutenant.setEnabled(true);
		btnSergeant.setEnabled(true);
		btnMiner.setEnabled(true);
		btnScout.setEnabled(true);
		btnSpy.setEnabled(true);
		btnFlag.setEnabled(true);
		
		lblNumberBombs.setText(String.valueOf(Pawn.BOMBS));
		lblNumberMarshal.setText(String.valueOf(Pawn.MARSHAL));
		lblNumberGeneral.setText(String.valueOf(Pawn.GENERAL));
		lblNumberColonel.setText(String.valueOf(Pawn.COLONEL));
		lblNumberMajor.setText(String.valueOf(Pawn.MAJOR));
		lblNumberCaptain.setText(String.valueOf(Pawn.CAPTAIN));
		lblNumberLieutenant.setText(String.valueOf(Pawn.LIEUTENANT));
		lblNumberSergeant.setText(String.valueOf(Pawn.SERGEANT));
		lblNumberMiner.setText(String.valueOf(Pawn.MINER));
		lblNumberScout.setText(String.valueOf(Pawn.SCOUT));
		lblNumberSpy.setText(String.valueOf(Pawn.SPY));
		lblNumberFlag.setText(String.valueOf(Pawn.FLAG));
		
	}
	
	protected void resetPawns() {
		
		player.pr.println("deletePawns");
		player.pr.flush();
		
		for(int i = 0; i<40; i++) 
			pawns.get(i).getPawnLabel().setVisible(false);
		
		for(int i = 0; i<10; i++)
			for(int j = 0; j<10; j++)
				pieceLocation[i][j] = 0;
	}
	
	protected String convertPawn(int pawn) {
		
		switch(pawn) {
			case 11:
				return "Bomb";
			case 10:
				return "Marshal";
			case 9:
				return "General";
			case 8:
				return "Colonel";
			case 7:
				return "Major";
			case 6:
				return "Captain";
			case 5:
				return "Lieutenant";
			case 4:
				return "Sergeant";
			case 3:
				return "Miner";
			case 2:
				return "Scout";
			case 1:
				return "Spy";
			case -1:
				return "Flag";
		}
		return null;
	}

	public static void start(String answer, PlayerHandler player, ArrayList<Pawn> pawns) {
		new GameBoard(player, pawns);
	}
	
	public static void wait(String answer, PlayerHandler player, ArrayList<Pawn> pawns) {
		new Waiting(player, pawns);
	}
}
