package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DbHandler {
	
	public static Connection conn = null;
	
	public static void connect() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stratego", "root", "Pass123!@#");
			System.out.println("Connection to DataBase Ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void disconnect() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Connection to DataBase END");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int playerLogIn(String username, String password) {
		
		try {
			String sql = "SELECT id FROM player WHERE username = '"+username+"' AND password = '"+password+"';";
			Statement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			if(result.next() == true) {
				int id = result.getInt(1);
				System.out.println(id);	
				return id;
			}else {
				System.out.println("empty");
				return 0;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static int playerCreate(String username, String password) {
			if(playerExist(username)) {
				try {
					String sql = "INSERT INTO `stratego`.`player` (`username`, `password`, `score`, `opponent_id`) VALUES ('"+username+"', '"+password+"', '0', '0');";
					Statement pstm = conn.prepareStatement(sql);
					pstm.execute(sql);
					return 1;
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}

		return 0;
	}
	
	private static boolean playerExist(String username) {
		
		try {
			String sql = "SELECT username FROM player WHERE username = '"+username+"';";
			Statement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			if(result.next() == true) {
				return false;
			}else {
				System.out.println("Player does not exist");
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public static int findOpponent(String username) {
		try {
			String sql = "SELECT id FROM player WHERE username = '"+username+"';";
			Statement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			if(result.next() == true) {
				int id = result.getInt(1);
				System.out.println(id);	
				return id;
			}else {
				System.out.println("Failed to find opponent");
				return 0;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		

		return 0;
	}
	public static int findOpponent(int id) {
		try {
			String sql = "SELECT opponent_id FROM player WHERE id = '"+id+"';";
			Statement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			if(result.next() == true) {
				int temp = result.getInt(1);
				System.out.println("Dbhandler: Opponent ID"+temp);	
				return temp;
			}else {
				System.out.println("Failed to find opponent");
				return 0;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		

		return 0;
	}
	
	public static void addOpponent(int id, int oppId) {
		
		try {
			String sql = "UPDATE player SET opponent_id = "+oppId+" WHERE id = "+id+";";
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
			
			sql = "UPDATE player SET opponent_id = "+id+" WHERE id = "+oppId+";";
			pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
			System.out.println("opponent added");

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deletePawns(int playerId) {
		try {
			String sql = "DELETE FROM piece WHERE player_id = "+playerId+";";
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deletePawn(int playerId, int row, int col) {
		try {
			String sql = "DELETE FROM piece WHERE player_id = "+playerId+" AND x_poss = "+row+" AND y_poss = "+col+";";
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addPiece(int type, String color, int row, int col, int playerId) {
		try {
			String sql = "INSERT INTO `stratego`.`piece` (`type`, `color`, `x_poss`, `y_poss`, `player_id`) VALUES ('"+type+"', '"+color+"', '"+row+"', '"+col+"', '"+playerId+"');";
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void populateBoard(int[][] pieceLocation, int playerId) {
		
		int opp = findOpponent(playerId);
		int[][] temp = new int[10][10]; 
		
		try {
			String sql = "SELECT type, x_poss, y_poss FROM piece WHERE player_id = "+opp+";";
			Statement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			
			while(result.next()) {
				int type = result.getInt("type");
				int row = result.getInt("x_poss");
				int col = result.getInt("y_poss");
				temp[row][col] = type;
			}
			
			int a = 9;
			int b = 9;
			
			for(int i = 0; i < 4; i++) {
				b = 9;
				for(int j = 0; j<10; j++) {
					if(pieceLocation[j][i] == 0) {
						pieceLocation[j][i] = temp[b][a];
					}
					b--;
				}
				a--;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updatePiece(PieceHelper helper, int playerId) {
		try {
			System.out.println("Got to delete a piece");
			String sql = "UPDATE piece SET x_poss = "+helper.getRowMove()+", y_poss = "+helper.getColMove()+" WHERE player_id = "+playerId+" AND x_poss = "+helper.getRow()+" AND y_poss = "+helper.getCol()+";";
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static int findPiece(int x, int y, int playerId) {
		try {
			String sql = "SELECT type FROM piece WHERE player_id = "+playerId+" AND x_poss = "+x+" AND y_poss = "+y+";";
			Statement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			if(result.next() == true) {
				int temp = result.getInt(1);
				System.out.println("Found piece "+temp);	
				return temp;
			}else {
				System.out.println("DbHandler: Failed to find opponent piece");
				return 0;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static void updateScore(int playerId) {
		try {
			System.out.println("Got to delete a piece");
			String sql = "UPDATE player SET score = score + 1 WHERE id = "+playerId+";";
			Statement pstm = conn.prepareStatement(sql);
			pstm.execute(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getLeaderboard(String[] results, int playerId) {
		
		try {
			String sql = "SELECT username, score FROM player ORDER BY score DESC LIMIT 10;";
			Statement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery(sql);
			
			int i = 0;
			while(result.next()) {
				results[i] = result.getString("username");
				results[i+1] = String.valueOf(result.getInt("score"));
				System.out.println("DbHandler: "+results[i]+" "+results[i+1]);
				i = i + 2;
			}
			
			sql = "SELECT score FROM player WHERE id = "+playerId+";";
			pstm = conn.prepareStatement(sql);
			result = pstm.executeQuery(sql);
			
			if(result.next() == true) {
				String score = result.getString(1);
				System.out.println("Found score "+score);	
				return score;
			}else {
				System.out.println("DbHandler: Failed to find opponent piece");
				return "fail";
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return "";
	}
}
