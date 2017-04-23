
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

public class Game extends JFrame {
	
	static JButton btnAddPlayer;
	static JButton btnStartGame;
	static JTable tablePlayer;
	final static int MAX_QTY = 4;
	
	private Query query;
	
	public Game(){
		
		super("Mölkky scorer");
		
		getContentPane().setLayout(null);				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		
		btnAddPlayer = new JButton("Add player");
		btnAddPlayer.setBounds(50, 390, 140, 60);
		getContentPane().add(btnAddPlayer);
		
		btnStartGame = new JButton("Start game");
		btnStartGame.setBounds(290, 390, 140, 60);
		getContentPane().add(btnStartGame);
		
		JLabel lblPlayers = new JLabel("Players:");
		lblPlayers.setBounds(208, 33, 52, 40);
		getContentPane().add(lblPlayers);
			
		MyEventHandler commandHandler = new MyEventHandler();
		btnAddPlayer.addActionListener(commandHandler);
		btnStartGame.addActionListener(commandHandler);
	}
	
	private class MyEventHandler implements ActionListener{

		public void actionPerformed(ActionEvent myEvent) {
			if (myEvent.getSource() == btnAddPlayer){
				addPlayer();
			}
						
		}
		
	}
	
	public void addPlayer(){
		
		JTextField playerNameField = new JTextField(15);
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Player name: "));
		myPanel.add(playerNameField);
		int result = JOptionPane.showConfirmDialog(null, myPanel, "", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION){
			query.addPlayer(playerNameField.getText());
		}
		
		
		
	}
}
