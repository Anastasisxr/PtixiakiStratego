package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ClientHandler implements Runnable{
	
	public static ArrayList<PlayerDetails> playerDetails = new ArrayList<>();
	public static ArrayList<String> playerList = new ArrayList<>();
	public static int logedPlayers;
	public static int count;
	public int[][] pieceLocation = new int[10][10]; 
	private Socket s;
	private InputStreamReader in;
	private BufferedReader bf;
	private PrintWriter pr;
	private int playerId;
	private String username;
	private String password;
	private String opponent;
	private String color;
	private int result;
	private PlayerDetails playerDetail;
	private boolean loggedIn = false;

	public ClientHandler(Socket s) {
		try {
			
			this.s = s;
			in = new InputStreamReader(s.getInputStream());
			bf = new BufferedReader(in);
			pr = new PrintWriter(s.getOutputStream());	
			
		}catch (IOException e) {
			closeSocket();
		}
	}
	
	@Override
	public void run() {
		try {
			
			PieceHelper helper = new PieceHelper();
			
			while(s != null) {
				
				String function = bf.readLine();	
				
				switch(function) {
					case "login":
						username = bf.readLine();
						password = bf.readLine();
						
						result = DbHandler.playerLogIn(username, password);
						
						if(result != 0) {
							if(!playerDetails.isEmpty()) {
								for(PlayerDetails p : playerDetails) {
									if(p.getPlayerId() == result) {
										loggedIn = true;
										System.out.println("Player already in");
										pr.println("fail");
										pr.flush();
										break;
									}
									loggedIn = false;
								}
							}
							
							if(loggedIn == false) {
								System.out.println("Logged In");
								playerDetail = new PlayerDetails(result, s, in, bf, pr);
								playerDetails.add(playerDetail);
								playerId = result;
								pr.println("success");
								pr.flush();
								pr.println(result);
								pr.flush();
								logedPlayers++;
							}
								
							}else {
								System.out.println("worng password or username");
								pr.println("fail");
								pr.flush();
							}
						break;
						
					case "create":
						username = bf.readLine();
						password = bf.readLine();
						
						result = DbHandler.playerCreate(username, password);
						
						if(result == 1) {
							System.out.println("User created");
							pr.println("success");
							pr.flush();
						}else {
							System.out.println("User exists");
							pr.println("fail");
							pr.flush();
						}
						
						break;
						
					case "list":
						sendList();
						break;
						
					case "addPlayer":
						addPlayer();
						break;
						
					case "removePlayer":
						System.out.println("Thread of "+username);
						removePlayer();
						break;
						
					case "play":
						opponent = bf.readLine();
						System.out.println("Read opponent "+opponent);
						result = DbHandler.findOpponent(opponent);
						System.out.println("Got Id "+result);
						
						if(result != 0 && findPlayerInList(opponent)) {
							color = getColor();
							if(color.equals("red")) {
								for(int i=0; i<logedPlayers; i++) {
									if(playerDetails.get(i).getPlayerId() == result) {
										playerDetails.get(i).getPr().println("blue");
										playerDetails.get(i).getPr().flush();
										break;
									}
								}
								pr.println("red");
								pr.flush();	
							}else {
								for(int i=0; i<logedPlayers; i++) {
									if(playerDetails.get(i).getPlayerId() == result) {
										playerDetails.get(i).getPr().println("red");
										playerDetails.get(i).getPr().flush();
										break;
									}
								}
								pr.println("blue");
								pr.flush();
							}
							count--;
							System.out.println("About to add opponent");
							DbHandler.addOpponent(playerId, result);
							DbHandler.deletePawns(playerId);
							DbHandler.deletePawns(result);
						}else {
							pr.println("fail");
							pr.flush();
						}
						break;
						
					case"setColor":
						color = bf.readLine();
						break;
					case "placePiece":
						int type = Integer.parseInt(bf.readLine());
						int row = Integer.parseInt(bf.readLine());
						int col = Integer.parseInt(bf.readLine());
						DbHandler.addPiece(type, color, row, col, playerId);
						pieceLocation[row][col] = type;
						
						break;
					case "deletePawns":
						DbHandler.deletePawns(playerId);
						break;
					case "ready":
						
						playerDetail.setReady(true);
						result = DbHandler.findOpponent(playerId);
						for(int i=0; i<logedPlayers; i++) {
							if(playerDetails.get(i).getPlayerId() == result) {
								if(playerDetails.get(i).isReady()) {
									pr.println("start");
									pr.flush();
									playerDetails.get(i).getPr().println("start");
									playerDetails.get(i).getPr().flush();
									
									DbHandler.populateBoard(pieceLocation, playerId);
									
									for(int a = 0; a < 10; a++) {
										for(int b = 0; b<10; b++) {
											System.out.print("|"+pieceLocation[b][a]);
										}
										System.out.print("|");
										System.out.println("|");
									}
									
									break;
								}else {
									pr.println("wait");
									pr.flush();
									break;
								}
							}
						}
						
						break;
					case "populate":
						DbHandler.populateBoard(pieceLocation, playerId);
						
						for(int a = 0; a < 10; a++) {
							for(int b = 0; b<10; b++) {
								System.out.print("|"+pieceLocation[b][a]);
							}
							System.out.print("|");
							System.out.println("|");
						}
						break;
					case "move":
						System.out.println(username+" Got Signal to move");
						result = DbHandler.findOpponent(playerId);
						System.out.println(username+" found opponent");
						helper.setRow(Integer.parseInt(bf.readLine()));
						helper.setCol(Integer.parseInt(bf.readLine()));
						helper.setRowMove(Integer.parseInt(bf.readLine()));
						helper.setColMove(Integer.parseInt(bf.readLine()));
						helper.setType(Integer.parseInt(bf.readLine()));
						
						System.out.println(username+" got data");
						
						if(executeMove(helper, pieceLocation, playerId)) {

							System.out.println(username+" Execute move");
							pr.println("ok");
							pr.flush();
							for(int i=0; i<logedPlayers; i++) {
								if(playerDetails.get(i).getPlayerId() == result) {
									if(playerDetails.get(i).isReady()) {
										System.out.println("Sent opponent message to play");
										playerDetails.get(i).getPr().println("move");
										playerDetails.get(i).getPr().flush();	
										playerDetails.get(i).getPr().println(helper.getRow());
										playerDetails.get(i).getPr().flush();	
										playerDetails.get(i).getPr().println(helper.getCol());
										playerDetails.get(i).getPr().flush();	
										playerDetails.get(i).getPr().println(helper.getRowMove());
										playerDetails.get(i).getPr().flush();	
										playerDetails.get(i).getPr().println(helper.getColMove());
										playerDetails.get(i).getPr().flush();	
										break;
									}
								}
							}
						}else {
							pr.println("fail");
							pr.flush();
						}
						break;
						
					case "attack":
						System.out.println("Got attack signal");
						helper.setRow(Integer.parseInt(bf.readLine()));
						helper.setCol(Integer.parseInt(bf.readLine()));
						helper.setRowMove(Integer.parseInt(bf.readLine()));
						helper.setColMove(Integer.parseInt(bf.readLine()));
						helper.setType(Integer.parseInt(bf.readLine()));
						
						System.out.println("Got data");
						
						String attResult = attackCheck(helper, playerId);
						
						System.out.println("Result returned "+attResult);
						
						if(attResult.equals("win")) {
							if(executeMove(helper, pieceLocation, playerId)) {
								pr.println("win");
								pr.flush();
								System.out.println("Send win signal to player");
								for(int i=0; i<logedPlayers; i++) {
									if(playerDetails.get(i).getPlayerId() == result) {
										if(playerDetails.get(i).isReady()) {
											System.out.println("Sent opponent lose signal");
											playerDetails.get(i).getPr().println("lose");
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getRow());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getCol());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getRowMove());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getColMove());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getType());
											playerDetails.get(i).getPr().flush();
											break;
										}
									}
								}
							}else {
								pr.println("fail");
								pr.flush();
							}	
						}else if(attResult.equals("tie")) {			
							if(executeMove(helper, pieceLocation, playerId)) {
								pr.println("tie");
								pr.flush();
								System.out.println("Send tie signal to player");
								for(int i=0; i<logedPlayers; i++) {
									if(playerDetails.get(i).getPlayerId() == result) {
										if(playerDetails.get(i).isReady()) {
											System.out.println("Sent opponent tie signal");
											playerDetails.get(i).getPr().println("tie");
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getRow());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getCol());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getRowMove());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getColMove());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getType());
											playerDetails.get(i).getPr().flush();
											break;
										}
									}
								}
							}else {
								pr.println("fail");
								pr.flush();
							}
							
						}else if(attResult.equals("lose")) {
							if(executeMove(helper, pieceLocation, playerId)) {
								pr.println("lose");
								pr.flush();
								helper.setType(DbHandler.findPiece(helper.getRowMove(), helper.getColMove(), DbHandler.findOpponent(playerId)));
								pr.println(helper.getType());
								pr.flush();
								System.out.println("Send lose signal to player");
								for(int i=0; i<logedPlayers; i++) {
									if(playerDetails.get(i).getPlayerId() == result) {
										if(playerDetails.get(i).isReady()) {
											System.out.println("Sent opponent win signal");
											playerDetails.get(i).getPr().println("win");
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getRow());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getCol());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getRowMove());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getColMove());
											playerDetails.get(i).getPr().flush();	
											playerDetails.get(i).getPr().println(helper.getType());
											playerDetails.get(i).getPr().flush();
											break;
										}
									}
								}
							}else {
								pr.println("fail");
								pr.flush();
							}
						}else if(attResult.equals("victory")) {
							if(executeMove(helper, pieceLocation, playerId)) {
								pr.println("victory");
								pr.flush();
								DbHandler.updateScore(playerId);
								System.out.println("Send victory signal to player");
								for(int i=0; i<logedPlayers; i++) {
									if(playerDetails.get(i).getPlayerId() == result) {
										if(playerDetails.get(i).isReady()) {
											playerDetails.get(i).setReady(false);
											System.out.println("Sent opponent defeat signal");
											playerDetails.get(i).getPr().println("defeat");
											playerDetails.get(i).getPr().flush();	
											break;
										}
									}
								}
							}else {
								pr.println("fail");
								pr.flush();
							}
							
							playerDetail.setReady(false);
							DbHandler.updateScore(DbHandler.findOpponent(playerId));
							DbHandler.deletePawns(playerId);
							DbHandler.deletePawns(DbHandler.findOpponent(playerId));
							DbHandler.addOpponent(playerId, 0);
							DbHandler.addOpponent(DbHandler.findOpponent(playerId), 0);
							
						}
						break;
					case "surrender":
						for(int i=0; i<logedPlayers; i++) {
							if(playerDetails.get(i).getPlayerId() == result) {
								if(playerDetails.get(i).isReady()) {
									playerDetails.get(i).setReady(false);
									System.out.println("Sent opponent defeat signal");
									playerDetails.get(i).getPr().println("victory");
									playerDetails.get(i).getPr().flush();	
									break;
								}
							}
						}
						playerDetail.setReady(false);
						DbHandler.updateScore(DbHandler.findOpponent(playerId));
						DbHandler.deletePawns(playerId);
						DbHandler.deletePawns(DbHandler.findOpponent(playerId));
						DbHandler.addOpponent(playerId, 0);
						DbHandler.addOpponent(DbHandler.findOpponent(playerId), 0);
						break;
					case"leader":
						
						String[] results = new String[20];
						
						String score = DbHandler.getLeaderboard(results, playerId);
						
						for(int i = 0; i<20; i++) {
							pr.println(results[i]);
							pr.flush();
						}
						
						pr.println(score);
						pr.flush();
						
						break;
					case "exit":
						pr.println("exit");
						pr.flush();
						DbHandler.deletePawns(playerId);
						DbHandler.addOpponent(playerId, 0);
						closeSocket();
						break;
				
					default:
				
				}
							
			}
		} catch (IOException e) {
			closeSocket();
		}
		
	}
	
	
	public void closeSocket() {
		try {
			if(s != null) {
				for(PlayerDetails p : playerDetails) {
					if(p.getPlayerId() == playerId) {
						playerDetails.remove(p);
						logedPlayers--;
						break;
					}
				}
				s.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addPlayer() {
		
		playerList.add(username);
		count++;
		System.out.println("Count after adding player"+count);
	}
	
	public void removePlayer() {

		System.out.println("Remove Player called, count: "+count);
		for(String p : playerList) {
			System.out.println("playerList"+p);
			if(p.equals(username)) {
				playerList.remove(p);
				count--;
				System.out.println("Count after removing player"+count);
				break;
			}
		}
		System.out.println("failed to remove, count: "+count);
		
	}
	
	public void sendList() {
		
		pr.println(count);
		pr.flush();
		for(int i = 0; i<count; i++) {
			pr.println(playerList.get(i));
			pr.flush();
		}
		
	}
	
	public String getColor() {
		
		Random rand = new Random();
		
		int randInt = rand.nextInt(2);
		System.out.println("random number "+randInt);
		if(randInt == 1)
			return "red";
		else
			return "blue";
	}
	
	public boolean findPlayerInList(String username) {
		
		for(String p : playerList) {
			if(p.equals(username)) {
				playerList.remove(p);
				return true;
			}
		}
		
		return false;
		
	}
	
	public boolean executeMove(PieceHelper helper, int[][] pieceLocation, int playerId) {
		
		if(moveCheck(helper)) {
					
			DbHandler.updatePiece(helper, playerId);
			
			int[][] temp1 = new int[10][10];
			int[][] temp2 = new int[10][10];
			
			temp1[helper.getRow()][helper.getCol()] = 1;
			
			int a = 9;
			int b = 9;
			for(int i = 0; i<10; i++) {
				b = 9;
				for(int j = 0; j<10; j++) {
					temp2[b][a] = temp1[j][i];
					b--;
				}
				a--;
			}
			
			temp1[helper.getRow()][helper.getCol()] = 0;
			
			for(int i = 0; i<10; i++) {
				for(int j = 0; j<10; j++)
					if(temp2[j][i] == 1) {
						helper.setRow(j);
						helper.setCol(i);
						temp2[j][i] = 0;
						break;
					}
			}
			
			a = 9;
			b = 9;
			
			temp1[helper.getRowMove()][helper.getColMove()] = 1;
			
			for(int i = 0; i<10; i++) {
				b = 9;
				for(int j = 0; j<10; j++) {
					temp2[b][a] = temp1[j][i];
					b--;
				}
				a--;
			}
			
			temp1[helper.getRowMove()][helper.getColMove()] = 0;
			
			for(int i = 0; i<10; i++) {
				for(int j = 0; j<10; j++)
					if(temp2[j][i] == 1) {
						helper.setRowMove(j);
						helper.setColMove(i);
						temp2[j][i] = 0;
						break;
					}
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean moveCheck(PieceHelper helper) {
		
		if(helper.getType() != 11 && helper.getType() != -1 && helper.getType() != 2) {
			int x  = helper.getRowMove();
			int y  = helper.getColMove();
			int difX = x - helper.getRow();
			int difY = y - helper.getCol();
			
			System.out.println("got to move check");
			
			if(!(x == 2 && y == 4 || x == 3 && y == 4 ||
			     x == 2 && y == 5 || x == 3 && y == 5 ||
			     x == 6 && y == 4 || x == 7 && y == 4 ||
			     x == 6 && y == 5 || x == 7 && y == 5)) {		
				if(((difX == 0 || difX == 1 || difX == -1)&& difY == 0)||
				   ((difY == 0 || difY == 1 || difY == -1)&& difX == 0))	  
					return true;		
			}

		}else if(helper.getType() == 2) {
			
			int x  = helper.getRowMove();
			int y  = helper.getColMove();
			int difX = x - helper.getRow();
			int difY = y - helper.getCol();
			
			if(!(x == 2 && y == 4 || x == 3 && y == 4 ||
			     x == 2 && y == 5 || x == 3 && y == 5 ||
			     x == 6 && y == 4 || x == 7 && y == 4 ||
			     x == 6 && y == 5 || x == 7 && y == 5)) {
				if(difX == 0 || difY == 0)	  
					return true;
			}
		}
		return false;
	}
	
	public String attackCheck(PieceHelper helper, int playerId) {
		
		int x = 0;
		int y = 0;
		
		int a = 9;
		int b = 9;
		
		int[][] temp1 = new int[10][10];
		int[][] temp2 = new int[10][10];
		
		temp1[helper.getRowMove()][helper.getColMove()] = 1;
		
		for(int i = 0; i<10; i++) {
			b = 9;
			for(int j = 0; j<10; j++) {
				temp2[b][a] = temp1[j][i];
				b--;
			}
			a--;
		}
		
		temp1[helper.getRowMove()][helper.getColMove()] = 0;
		
		for(int i = 0; i<10; i++) {
			for(int j = 0; j<10; j++)
				if(temp2[j][i] == 1) {
					x = j;
					y = i;
					temp2[j][i] = 0;
					break;
				}
		}
		
		
		int type = DbHandler.findPiece(x, y, DbHandler.findOpponent(playerId));
		
		if(helper.getType() > type) {
			
			if(type == -1) {
				return "victory";
			}
			
			DbHandler.deletePawn(DbHandler.findOpponent(playerId), x, y);
			return "win";
		}
			
		
		if(helper.getType() == type) {
			return "tie";
		}
			
		
		if(helper.getType() < type) {
			if(type == 11 && helper.getType() == 3) {
				return "win";
			}
			if(type == 10 && helper.getType() == 1) {
				return "win";
			}
			return "lose";
		}
			
		
		return "lose";
		
	}
}
