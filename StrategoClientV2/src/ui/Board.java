package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

import model.PlayerHandler;

public abstract class Board extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static JLayeredPane contentPane;

	protected static final int[] x = { 0, 90, 180, 270, 360, 450, 540, 630, 720, 810, 900 };
	public static int[][] pieceLocation = new int[10][10]; 
	public PlayerHandler player;
	
	public Board(PlayerHandler player) {
		this.player = player;
    	player.pr.println("removePlayer");
		player.pr.flush();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 935);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setTitle("Stratego");
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBoard = new JLabel("");
		lblBoard.setIcon(new ImageIcon(Board.class.getResource("/image/board.png"))); 
		lblBoard.setBounds(0, 0, 900, 900);
		contentPane.add(lblBoard, 1);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		this.setVisible(true);
		this.setEnabled(true);
		
	}

	public abstract void initilizePawns(); 


}
