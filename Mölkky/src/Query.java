import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	
	private static PreparedStatement addPlayer = null;

	public static void connect(){
		Connection connection = null;
		Statement tableCreator = null;
		
		try{
			String url = "jdbc:sqlite:/game.db";
			connection = DriverManager.getConnection(url);			
			addPlayer = connection.prepareStatement("insert into player(name) values(?)");
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}
	
	public void addPlayer(String name){
		
		try{
			addPlayer.setString(1, name);			
			addPlayer.executeUpdate();
			
		}catch (Exception e) {		
			e.printStackTrace();
			System.exit(1);
			
		}
	}
}

