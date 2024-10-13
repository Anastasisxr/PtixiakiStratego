package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.PlayerHandler;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class EndingScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public EndingScreen(PlayerHandler player, boolean result) {
		
		JFrame end = new JFrame("Stratego");
		
		end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		end.setBounds(100, 100, 712, 397);
		end.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		end.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(result){
			JLabel lblVictory = new JLabel("VICTORY!!!");
			lblVictory.setForeground(new Color(255, 0, 0));
			lblVictory.setFont(new Font("Stencil", Font.BOLD, 75));
			lblVictory.setBounds(141, 54, 436, 114);
			contentPane.add(lblVictory);
		}else{
			JLabel lblDefeat = new JLabel("DEFEAT");
			lblDefeat.setForeground(new Color(255, 0, 0));
			lblDefeat.setFont(new Font("Stencil", Font.BOLD, 75));
			lblDefeat.setBounds(180, 54, 436, 114);
			contentPane.add(lblDefeat);
		}
		
		JButton btnBack = new JButton("BACK TO MENU");
		btnBack.setForeground(new Color(255, 0, 0));
		btnBack.setFont(new Font("Stencil", Font.BOLD, 20));
		btnBack.setBounds(70, 227, 194, 57);
		contentPane.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				end.setVisible(false);
				end.setEnabled(false);
				new Menu(player);
		}
		});
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(new Color(255, 0, 0));
		btnExit.setFont(new Font("Stencil", Font.BOLD, 20));
		btnExit.setBounds(419, 227, 199, 57);
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
		lblNewLabel.setIcon(new ImageIcon(EndingScreen.class.getResource("/image/background.png"))); 
		lblNewLabel.setBounds(0, 0, 704, 358);
		contentPane.add(lblNewLabel);
		
		end.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	player.pr.println("exit");
				player.pr.flush();
				player.closeSocket();
				System.exit(0);
		    }
		});
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		end.setLocation(dim.width / 2 - end.getSize().width / 2, dim.height / 2 - end.getSize().height / 2);
		end.setVisible(true);
		end.setEnabled(true);
	}
}
