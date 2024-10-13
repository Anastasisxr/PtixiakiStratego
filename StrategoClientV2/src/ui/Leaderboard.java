package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.PlayerHandler;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Leaderboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel[] board = new JLabel[22];

	public Leaderboard(PlayerHandler player) {
		
		player.pr.println("leader");
		player.pr.flush();
		
		JFrame leader = new JFrame("Stratego");
		
		leader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		leader.setBounds(100, 100, 704, 522);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		leader.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			JLabel lblName1 = new JLabel(player.bf.readLine());
			lblName1.setForeground(new Color(255, 0, 0));
			lblName1.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName1.setBounds(29, 56, 126, 30);
			contentPane.add(lblName1);
			
			JLabel lblScore1 = new JLabel(player.bf.readLine());
			lblScore1.setForeground(new Color(255, 0, 0));
			lblScore1.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore1.setBounds(204, 56, 103, 30);
			contentPane.add(lblScore1);
			
			JLabel lblName2 = new JLabel(player.bf.readLine());
			lblName2.setForeground(Color.RED);
			lblName2.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName2.setBounds(29, 97, 126, 30);
			contentPane.add(lblName2);
			
			JLabel lblScore2 = new JLabel(player.bf.readLine());
			lblScore2.setForeground(Color.RED);
			lblScore2.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore2.setBounds(204, 97, 103, 30);
			contentPane.add(lblScore2);
			
			JLabel lblName3 = new JLabel(player.bf.readLine());
			lblName3.setForeground(Color.RED);
			lblName3.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName3.setBounds(29, 138, 126, 30);
			contentPane.add(lblName3);
			
			JLabel lblScore3 = new JLabel(player.bf.readLine());
			lblScore3.setForeground(Color.RED);
			lblScore3.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore3.setBounds(204, 138, 103, 30);
			contentPane.add(lblScore3);
			
			JLabel lblName4 = new JLabel(player.bf.readLine());
			lblName4.setForeground(Color.RED);
			lblName4.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName4.setBounds(29, 179, 126, 30);
			contentPane.add(lblName4);
			
			JLabel lblScore4 = new JLabel(player.bf.readLine());
			lblScore4.setForeground(Color.RED);
			lblScore4.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore4.setBounds(204, 179, 103, 30);
			contentPane.add(lblScore4);
			
			JLabel lblName5 = new JLabel(player.bf.readLine());
			lblName5.setForeground(Color.RED);
			lblName5.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName5.setBounds(29, 220, 126, 30);
			contentPane.add(lblName5);
			
			JLabel lblScore5 = new JLabel(player.bf.readLine());
			lblScore5.setForeground(Color.RED);
			lblScore5.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore5.setBounds(204, 220, 103, 30);
			contentPane.add(lblScore5);
			
			JLabel lblName6 = new JLabel(player.bf.readLine());
			lblName6.setForeground(Color.RED);
			lblName6.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName6.setBounds(29, 261, 126, 30);
			contentPane.add(lblName6);
			
			JLabel lblScore6 = new JLabel(player.bf.readLine());
			lblScore6.setForeground(Color.RED);
			lblScore6.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore6.setBounds(204, 261, 103, 30);
			contentPane.add(lblScore6);
			
			JLabel lblName7 = new JLabel(player.bf.readLine());
			lblName7.setForeground(Color.RED);
			lblName7.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName7.setBounds(29, 302, 126, 30);
			contentPane.add(lblName7);
			
			JLabel lblScore7 = new JLabel(player.bf.readLine());
			lblScore7.setForeground(Color.RED);
			lblScore7.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore7.setBounds(204, 302, 103, 30);
			contentPane.add(lblScore7);
			
			JLabel lblName8 = new JLabel(player.bf.readLine());
			lblName8.setForeground(Color.RED);
			lblName8.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName8.setBounds(29, 343, 126, 30);
			contentPane.add(lblName8);
			
			JLabel lblScore8 = new JLabel(player.bf.readLine());
			lblScore8.setForeground(Color.RED);
			lblScore8.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore8.setBounds(204, 343, 103, 30);
			contentPane.add(lblScore8);
			
			JLabel lblName9 = new JLabel(player.bf.readLine());
			lblName9.setForeground(Color.RED);
			lblName9.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName9.setBounds(29, 384, 126, 30);
			contentPane.add(lblName9);
			
			JLabel lblScore9 = new JLabel(player.bf.readLine());
			lblScore9.setForeground(Color.RED);
			lblScore9.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore9.setBounds(204, 384, 103, 30);
			contentPane.add(lblScore9);
			
			JLabel lblName10 = new JLabel(player.bf.readLine());
			lblName10.setForeground(Color.RED);
			lblName10.setFont(new Font("Stencil", Font.BOLD, 16));
			lblName10.setBounds(29, 425, 126, 30);
			contentPane.add(lblName10);
			
			JLabel lblScore10 = new JLabel(player.bf.readLine());
			lblScore10.setForeground(Color.RED);
			lblScore10.setFont(new Font("Stencil", Font.BOLD, 16));
			lblScore10.setBounds(204, 425, 103, 30);
			contentPane.add(lblScore10);

			JLabel lblMyScore = new JLabel(player.bf.readLine());
			lblMyScore.setForeground(new Color(255, 0, 0));
			lblMyScore.setFont(new Font("Stencil", Font.BOLD, 30));
			lblMyScore.setBounds(470, 224, 92, 63);
			contentPane.add(lblMyScore);
			
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblMyScore2 = new JLabel("MY SCORE");
		lblMyScore2.setForeground(new Color(255, 0, 0));
		lblMyScore2.setFont(new Font("Stencil", Font.PLAIN, 18));
		lblMyScore2.setBounds(451, 179, 111, 39);
		contentPane.add(lblMyScore2);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setForeground(new Color(255, 0, 0));
		lblName.setFont(new Font("Stencil", Font.BOLD, 30));
		lblName.setBounds(29, 11, 92, 34);
		contentPane.add(lblName);
		
		JLabel lblScore = new JLabel("SCORE");
		lblScore.setForeground(Color.RED);
		lblScore.setFont(new Font("Stencil", Font.BOLD, 30));
		lblScore.setBounds(178, 11, 113, 34);
		contentPane.add(lblScore);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setForeground(new Color(255, 0, 0));
		btnBack.setFont(new Font("Stencil", Font.BOLD, 20));
		btnBack.setBounds(418, 320, 161, 51);
		contentPane.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	player.pr.println("removePlayer");
				player.pr.flush();
				leader.setVisible(false);
				leader.dispose();
				new Menu(player);

			}
		});

		JLabel lblNewLabel_1 = new JLabel("");

		lblNewLabel_1.setIcon(new ImageIcon(Leaderboard.class.getResource("/image/background.png"))); 
		lblNewLabel_1.setBounds(0, 0, 697, 483);
		contentPane.add(lblNewLabel_1);
		
		leader.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	player.pr.println("exit");
				player.pr.flush();
				player.closeSocket();
				System.exit(0);
		    }
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		leader.setLocation(dim.width / 2 - leader.getSize().width / 2, dim.height / 2 - leader.getSize().height / 2);
		leader.setVisible(true);
		leader.setEnabled(true);
		
	}
}
