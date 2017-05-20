import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Database extends JFrame{
	
	static JTextArea results;
	static JButton clearDatabase;
	static JButton close;
	static JButton insertTestData;
	
	public Database() {
		
		super("Results");
		
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setBounds(0, 0, 440, 540);
		
		results = new JTextArea();
		results.setBounds(20, 11, 390, 420);
		results.setEditable(false);
		getContentPane().add(results);
		
		clearDatabase = new JButton("Clear database");
		clearDatabase.setBounds(20, 462, 122, 28);
		getContentPane().add(clearDatabase);
		
		close = new JButton("Close");
		close.setBounds(152, 462, 122, 28);
		getContentPane().add(close);
		
		insertTestData = new JButton("Insert test data");
		insertTestData.setBounds(284, 462, 128, 28);
		getContentPane().add(insertTestData);
		
		myEventHandler commandHandler = new myEventHandler();
		clearDatabase.addActionListener(commandHandler);
		close.addActionListener(commandHandler);
		insertTestData.addActionListener(commandHandler);

	}
	
	//Method to connect to database and create tables
	public void connect(){
	
		Connection conn = null;
		Statement createGameTable = null; 
		//Statement createPlayerTable = null;
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			createGameTable = conn.createStatement();
			String sql = "create table if not exists game " + 
			"(game_id			integer		primary key		autoincrement," +
			"player_name		text		,"+
			"wins				integer		)";
			createGameTable.executeUpdate(sql);
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
	
	public void addWinner(String playerName){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			String sql = "select player_name from game where player_name = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, playerName);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("if");
				String sql2 = "update game set wins = wins + 1 where player_name = ?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, playerName);
				pstmt2.executeUpdate();

				
			}else{
				System.out.println("else");
				String sql3 = "insert into game (player_name, wins) values (?,?)";
				pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setString(1, playerName);
				pstmt3.setInt(2, 1);
				pstmt3.executeUpdate();

			}
			
			
			
			
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}
	
	public void printScores(){
		
		Connection conn = null;
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			String sql = "select * from game";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			
			results.append("Player id \t Player name \t Wins \n");
			
			while(rs.next()){
				for(int i = 1; i <= columnsNumber; i++){
					String columnValue = rs.getString(i);
					results.append(columnValue + "\t");
				}results.append("\n");
			}
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void instertTestData(){
		
		Connection conn = null;
		Statement instertTestData;
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			instertTestData = conn.createStatement();
			String sql = "insert into game (player_name, wins) values ('Player_1', 2)";
			String sql1 = "insert into game (player_name, wins) values ('Player_2', 4)";
			String sql2 = "insert into game (player_name, wins) values ('Player_3', 1)";
			instertTestData.executeUpdate(sql);
			instertTestData.executeUpdate(sql1);
			instertTestData.executeUpdate(sql2);
			System.out.println("data inserted");
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void clearDatabase(){
		
		Connection conn = null;
		Statement clearGames;
		
		
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			clearGames = conn.createStatement();
			String sql2 = "drop table game";
			clearGames.executeUpdate(sql2);
			
		}catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	private class myEventHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent myEvent){
			
			if(myEvent.getSource() == close){
				setVisible(false);
				
			}else if(myEvent.getSource() == insertTestData){
				instertTestData();
				printScores();
				
			}else if(myEvent.getSource() == clearDatabase){
				JPanel myPanel = new JPanel();
				myPanel.add(new Label("Warning! this will delete all data from the database! Are you sure?"));
				int result = JOptionPane.showConfirmDialog(null, myPanel, "", JOptionPane.OK_CANCEL_OPTION);
				if(result == JOptionPane.OK_OPTION){
					clearDatabase();
					connect();
					results.setText("");
				}
			}	
		}
	}
}


		

	
