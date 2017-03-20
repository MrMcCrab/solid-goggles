import java.sql.*;

public class Database {
	
	public void connect(){
	
		Connection conn = null;
		Statement stmt = null; 
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			stmt = conn.createStatement();
			String sql = "create table if not exists game " + 
			"(player	text	not null," +
			"games		int				," + 
			"throws		int				," + 
			"misses		int				," + 
			"wins		int				)";
			stmt.executeUpdate(sql);
			
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
		

	
