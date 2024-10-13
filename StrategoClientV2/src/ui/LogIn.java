package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;

import model.PlayerHandler;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private String password;
	private String username;


	public LogIn(PlayerHandler player) {
		
		JFrame login = new JFrame("Stratego");
		
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setBounds(100, 100, 727, 465);
		login.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		login.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setBounds(339, 149, 150, 36);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(339, 207, 150, 36);
		contentPane.add(passwordField);
		
		JLabel lbl1 = new JLabel("Username");
		lbl1.setForeground(new Color(255, 0, 0));
		lbl1.setFont(new Font("Stencil", Font.PLAIN, 16));
		lbl1.setBounds(212, 155, 101, 25);
		contentPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("Password");
		lbl2.setForeground(new Color(255, 0, 0));
		lbl2.setFont(new Font("Stencil", Font.PLAIN, 16));
		lbl2.setBounds(212, 213, 101, 25);
		contentPane.add(lbl2);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnLogIn.setForeground(new Color(255, 0, 0));
		btnLogIn.setBounds(183, 279, 150, 36);
		contentPane.add(btnLogIn);
		
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					username = textUsername.getText();
					password = new String(passwordField.getPassword());
					System.out.println("Username: "+username+" Password: "+password);
					
					player.pr.println("login");
					player.pr.flush();
					player.pr.println(username);
					player.pr.flush();
					player.pr.println(password);
					player.pr.flush();
					
					String result = player.bf.readLine();
					if(result.equals("success")) {
						
						player.setUser(username);
						player.setPass(password);
						player.setPlayerId(Integer.valueOf(player.bf.readLine()));
						System.out.println("Player Logged In!! "+player.getPlayerId());	
						login.setVisible(false);
						login.dispose();
						new Menu(player);
					}else if(result.equals("fail")){
						JOptionPane.showMessageDialog(contentPane, "Wrong Username or Password");
					}
					
				}catch (IOException e1) {
					e1.printStackTrace();
				}
				}
		});
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setFont(new Font("Stencil", Font.PLAIN, 13));
		btnCreateAccount.setForeground(new Color(255, 0, 0));
		btnCreateAccount.setBounds(410, 279, 150, 36);
		contentPane.add(btnCreateAccount);
		
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					username = textUsername.getText();
					password = new String(passwordField.getPassword());
					System.out.println("Username: "+username+" Password: "+password);
					
					player.pr.println("create");
					player.pr.flush();
					player.pr.println(username);
					player.pr.flush();
					player.pr.println(password);
					player.pr.flush();
					
					String result = player.bf.readLine();
					if(result.equals("success")) {
						JOptionPane.showMessageDialog(contentPane, "Account Created");
					}else if(result.equals("fail")){
						JOptionPane.showMessageDialog(contentPane, "Username already exists");
					}
				
				}catch (IOException e1) {
					e1.printStackTrace();
				}
				
				}
		});
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(LogIn.class.getResource("/image/logInScreen.jpg")));
		lblBackground.setBounds(0, 0, 711, 426);
		contentPane.add(lblBackground);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		login.setLocation(dim.width / 2 - login.getSize().width / 2, dim.height / 2 - login.getSize().height / 2);
		login.setVisible(true);
		login.setEnabled(true);
	}
}
