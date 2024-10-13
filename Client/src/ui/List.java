package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Game;
import model.PlayerHandler;

public class List extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String name;
	
	private static DefaultListModel<String> model = new DefaultListModel<String>();
	
	public List(PlayerHandler player) {
		
		JFrame playerList = new JFrame("Stratego");
		
		playerList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerList.setBounds(100, 100, 600, 398);
		playerList.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		model = fillList(player);
		
		playerList.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList<String> list = new JList<String>(model);
		list.setForeground(new Color(255, 0, 0));
		list.setFont(new Font("Stencil", Font.PLAIN, 16));
		list.setBackground(Color.ORANGE);
		list.setVisibleRowCount(4);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 50, 500, 200);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		contentPane.add(scrollPane);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				name = (String) model.get(list.getSelectedIndex());
			}
		});
		
		JButton btnCreate = new JButton("Create Game\r\n");
		btnCreate.setForeground(new Color(255, 0, 0));
		btnCreate.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnCreate.setBounds(350, 282, 150, 46);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.pr.println("addPlayer");
				player.pr.flush();
				playerList.setVisible(false);
				playerList.dispose();
				new Waiting(player);
			}
		});
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setForeground(new Color(255, 0, 0));
		btnPlay.setFont(new Font("Stencil", Font.PLAIN, 14));
		btnPlay.setBounds(80, 282, 150, 46);
		contentPane.add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(name);
				player.pr.println("play");
				player.pr.flush();
				player.pr.println(name);
				player.pr.flush();
				try {
					String color = player.bf.readLine();
					if(!color.equals("fail")) {
						playerList.setVisible(false);
						playerList.dispose();
						Game game = new Game(player);
						game.GameStart(color);
					}else {
						JOptionPane.showMessageDialog(contentPane, "Could not connect to opponent please refresh");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(new Color(255, 0, 0));
		btnRefresh.setFont(new Font("Stencil", Font.PLAIN, 11));
		btnRefresh.setBounds(369, 15, 89, 23);
		contentPane.add(btnRefresh);
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				playerList.setVisible(false);
				playerList.dispose();
				new List(player);

			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.setForeground(new Color(255, 0, 0));
		btnBack.setFont(new Font("Stencil", Font.PLAIN, 11));
		btnBack.setBounds(467, 15, 73, 23);
		contentPane.add(btnBack);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	player.pr.println("removePlayer");
				player.pr.flush();
				playerList.setVisible(false);
				playerList.dispose();
				new Menu(player);

			}
		});
		
		JLabel lblName = new JLabel("Player Names");
		lblName.setForeground(new Color(255, 0, 0));
		lblName.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblName.setBounds(40, 11, 150, 28);
		contentPane.add(lblName);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(List.class.getResource("/image/background.png"))); 
		lblNewLabel.setBounds(0, 0, 584, 363);
		contentPane.add(lblNewLabel);
		
		playerList.addWindowListener(new WindowAdapter() {
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
		playerList.setLocation(dim.width / 2 - playerList.getSize().width / 2, dim.height / 2 - playerList.getSize().height / 2);
		playerList.setVisible(true);
		playerList.setEnabled(true);
	}
	
	public static DefaultListModel<String> fillList(PlayerHandler player) {
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		
		player.pr.println("list");
		player.pr.flush();

		try {

			int count = Integer.parseInt(player.bf.readLine());
			
			for(int i = 0; i<count; i++) {
				
				model.addElement(player.bf.readLine());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return model;
	}
}
