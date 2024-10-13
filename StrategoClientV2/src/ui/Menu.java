package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.PlayerHandler;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	
	public Menu(PlayerHandler player) {
		
		JFrame menu = new JFrame("Stratego");
		
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setBounds(100, 100, 700, 465);
		menu.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		menu.setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("Play\r\n");
		btnPlay.setForeground(new Color(255, 0, 0));
		btnPlay.setFont(new Font("Stencil", Font.BOLD, 16));
		btnPlay.setBounds(251, 85, 174, 46);
		contentPane.add(btnPlay);
		
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				menu.dispose();
				new List(player);
			}
		});
		
		JButton btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.setForeground(new Color(255, 0, 0));
		btnLeaderboard.setFont(new Font("Stencil", Font.BOLD, 15));
		btnLeaderboard.setBounds(251, 175, 174, 46);
		contentPane.add(btnLeaderboard);
		
		btnLeaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				menu.dispose();
				new Leaderboard(player);
			}
		});
		
		JButton btnExit = new JButton("Exit\r\n");
		btnExit.setForeground(new Color(255, 0, 0));
		btnExit.setFont(new Font("Stencil", Font.BOLD, 16));
		btnExit.setBounds(251, 265, 174, 46);
		contentPane.add(btnExit);
		
		btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					player.pr.println("exit");
					player.pr.flush();
					player.closeSocket();
					System.exit(0);
			}
			});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/image/background.png")));
		lblNewLabel.setBounds(0, 0, 705, 455);
		contentPane.add(lblNewLabel);

		menu.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	player.pr.println("exit");
				player.pr.flush();
				player.closeSocket();
				System.exit(0);
		    }
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		menu.setLocation(dim.width / 2 - menu.getSize().width / 2, dim.height / 2 - menu.getSize().height / 2);
		menu.setVisible(true);
		menu.setEnabled(true);
	}
}
