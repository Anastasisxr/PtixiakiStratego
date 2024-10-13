package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Game;
import model.Pawn;
import model.PlayerHandler;

public class Waiting extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static String answer;
	private static JFrame waiting = new JFrame("Stratego");
	ArrayList<Pawn> pawns;

	public Waiting(PlayerHandler player) {
		
		waiting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		waiting.setBounds(100, 100, 450, 300);
		waiting.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		waiting.setContentPane(contentPane);
		
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Waiting For Opponent");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 14));
		lblNewLabel.setBounds(95, 62, 237, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Waiting.class.getResource("/image/background.png"))); 
		lblNewLabel_1.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_1);
		
		waiting.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	player.pr.println("removePlayer");
				player.pr.flush();
		    	player.pr.println("exit");
				player.pr.flush();
				player.closeSocket();
				System.exit(0);
		    }
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		waiting.setLocation(dim.width / 2 - waiting.getSize().width / 2, dim.height / 2 - waiting.getSize().height / 2);
		waiting.setVisible(true);
		waiting.setEnabled(true);
		
		WaitingAnswer waitingAnswer = new WaitingAnswer(player);
		Thread thread = new Thread(waitingAnswer);
		thread.start();
		
	}
	
	public Waiting(PlayerHandler player, ArrayList<Pawn> pawns) {
		
		this.pawns = pawns;
		
		waiting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		waiting.setBounds(100, 100, 450, 300);
		waiting.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		waiting.setContentPane(contentPane);
		
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Waiting For Opponent");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Stencil", Font.BOLD, 14));
		lblNewLabel.setBounds(95, 62, 237, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Waiting.class.getResource("/image/background.png"))); 
		lblNewLabel_1.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_1);
		
		waiting.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	player.pr.println("removePlayer");
				player.pr.flush();
		    	player.pr.println("exit");
				player.pr.flush();
				player.closeSocket();
				System.exit(0);
		    }
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		waiting.setLocation(dim.width / 2 - waiting.getSize().width / 2, dim.height / 2 - waiting.getSize().height / 2);
		waiting.setVisible(true);
		waiting.setEnabled(true);
		
		WaitingAnswer waitingAnswer = new WaitingAnswer(player, pawns);
		Thread thread = new Thread(waitingAnswer);
		thread.start();
		
	}
	
	public static void answer(String answer, PlayerHandler player) {
			waiting.setVisible(false);
			waiting.dispose();
			if(answer.equals("red") || answer.equals("blue")) {
				player.pr.println("setColor");
				player.pr.flush();
				player.pr.println(answer);
				player.pr.flush();
				Game game = new Game(player);
				game.GameStart(answer);
			}
	}
	
	public static void answer(String answer, PlayerHandler player, ArrayList<Pawn> pawns) {
		waiting.setVisible(false);
		waiting.dispose();
		if(answer.equals("start")) {
			player.pr.println("populate");
			player.pr.flush();
			new GameBoard(player, pawns);
		}
	}
}
