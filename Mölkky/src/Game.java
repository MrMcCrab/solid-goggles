
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextArea;

public class Game extends JFrame {
	
	static int gameId;
	static int numPlayers = 0;
	static String[] tempPlayers = new String[10];
	static int[] points;
	
	static String[] playerArray;
	static int[] pointsArray;
	
	static JButton addPlayer; 
	static JButton startGame;
	static JButton rules;
	static JButton btnMenu;
	
	static TextArea playerList;
	
	public Game(){
		
		getContentPane().setLayout(null);		
		
		addPlayer = new JButton("Add player");
		addPlayer.setBounds(10, 378, 140, 60);
		getContentPane().add(addPlayer);
		
		startGame = new JButton("Start game");
		startGame.setBounds(171, 378, 140, 60);
		getContentPane().add(startGame);
		
		playerList = new TextArea();
		playerList.setEditable(false);
		playerList.setBounds(59, 38, 380, 276);
		getContentPane().add(playerList);
		
		rules = new JButton("Rules");
		rules.setBounds(334, 378, 140, 60);
		getContentPane().add(rules);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(187, 328, 110, 39);
		getContentPane().add(btnMenu);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		
		MyEventHandler commandHandler = new MyEventHandler();
		addPlayer.addActionListener(commandHandler);
		startGame.addActionListener(commandHandler);
		rules.addActionListener(commandHandler);
		btnMenu.addActionListener(commandHandler);
		
	}
	
	//Add player to the game and list players
	public void addPlayer(String playerName){
		
		tempPlayers[numPlayers] = playerName;
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
					playerList.append(tempPlayers[numPlayers - 1] + "\n");	
				}
				
			}
			//Show rules window
			if(myEvent.getSource() == rules){
				
				Rules rules = new Rules();
				rules.setVisible(true);
			}
			//Starts new game, create player array from temp player array, create player points array
			if(myEvent.getSource() == startGame && numPlayers >= 2){
				
				playerArray = new String[numPlayers];
				pointsArray = new int[numPlayers];
				
				System.arraycopy(tempPlayers, 0, playerArray, 0, numPlayers);
				
				
				gameLogic(playerArray, pointsArray);
				
				
			}else if(myEvent.getSource() == startGame && numPlayers < 2){
				JOptionPane.showConfirmDialog(null, "At least two players are needed", "Error", JOptionPane.OK_OPTION);
			}
				
		}
		
	}
	
	public void gameLogic(String[] playerArray, int[] pointsArray){
		
		
		int playerNumber;
		Database db = new Database();
		playerList.setText("game started \n");
		addPlayer.setEnabled(false);
		playerNumber = 0;
		
		do{
			if(playerNumber >= playerArray.length){
				playerNumber = 0;
				
				String roundResults = "";
				for(int i = 0; i < playerArray.length; i++){
					roundResults += playerArray[i] + " " + pointsArray[i] + "\n";
				}
				System.out.println("list");
				playerList.setText("Results for round: \n");
				playerList.append(roundResults);
				
			}else{
				playerList.append("Next player: " + playerArray[playerNumber] + " you have " + pointsArray[playerNumber] + " points. \n");
				int points = Integer.parseInt(JOptionPane.showInputDialog("Enter points for player " + playerArray[playerNumber]));
				pointsArray[playerNumber] += points;
				if(pointsArray[playerNumber] > 50){
					playerList.append("You exceeded 50 points, your currents points are reduced to 25 \n");
					pointsArray[playerNumber] = 25;
				}
				if(pointsArray[playerNumber] == 50){
					playerList.setText("Player " + playerArray[playerNumber] + " has won the game!");
					db.addWinner(playerArray[playerNumber]);
					break;
				}
				playerNumber++;
			}
			
			
		}while(true);
				
	}
}
