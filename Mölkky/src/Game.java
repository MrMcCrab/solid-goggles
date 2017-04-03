
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.corba.se.impl.activation.CommandHandler;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.TextArea;

public class Game extends JFrame {
	
	static int gameId;
	static int numPlayers = 0;
	static String[] t_players = new String[10];
	static int[] points;
	
	static JButton addPlayer; 
	static JButton startGame;
	static TextArea playerList;
	
	public Game(){
		
		getContentPane().setLayout(null);		
		
		addPlayer = new JButton("Add player");
		addPlayer.setBounds(67, 378, 140, 60);
		getContentPane().add(addPlayer);
		
		startGame = new JButton("Start game");
		startGame.setBounds(270, 378, 140, 60);
		getContentPane().add(startGame);
		
		playerList = new TextArea();
		playerList.setEditable(false);
		playerList.setBounds(59, 38, 380, 160);
		getContentPane().add(playerList);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		
		MyEventHandler commandHandler = new MyEventHandler();
		addPlayer.addActionListener(commandHandler);
		startGame.addActionListener(commandHandler);
		
	}
	
	public void addPlayer(String playerName){
		
		t_players[numPlayers] = playerName;
		numPlayers++;
		
		
	}
	
	private class MyEventHandler implements ActionListener{

		public void actionPerformed(ActionEvent myEvent) {
			
			Database db = new Database();
			
			if(myEvent.getSource() == addPlayer){
				
				JTextField playerNameField = new JTextField(12);			
				JPanel myPanel = new JPanel();
				myPanel.add(new Label("Player name: "));
				myPanel.add(playerNameField);
				int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter new player", JOptionPane.OK_CANCEL_OPTION);
				
				if(result == JOptionPane.OK_OPTION){
					addPlayer(playerNameField.getText());
					playerList.append(t_players[numPlayers - 1] + "\n");	
				}
				
			}
			
			if(myEvent.getSource() == startGame){
				
				String[] players = new String[numPlayers];
				points = new int[numPlayers];
				
				System.arraycopy(t_players, 0, players, 0, numPlayers);
				
				
			}
				
		}
		
	}
		
}
