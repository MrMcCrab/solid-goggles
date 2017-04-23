import java.sql.*;

public class Database {
	
	public void connect(){
	
		Connection conn = null;
		Statement createGameTable = null; 
		Statement createPlayerTable = null;
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			createGameTable = conn.createStatement();
			String sql = "create table if not exists game " + 
			"(game_id			int		not null," +
			"player_id			int		not null)";
			createGameTable.executeUpdate(sql);
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			createPlayerTable = conn.createStatement();
			String sql2 = "create table if not exists players " + 
			"(player_id		int		not null," + 
			"player_name	text	not null," +
			"throws			int				," +
			"misses			int				)";	
			createPlayerTable.executeUpdate(sql2);
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
	
	public void addPlayer(String playerName){
		
		Connection conn = null;
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			String sql = "insert into game(player) " +
						"values (?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playerName);
			pstmt.execute();
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
	
}
		

	
