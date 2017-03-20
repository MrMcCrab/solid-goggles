
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Game extends JFrame {
	
	static JButton addPlayer; 
	static JButton startGame;
	
	public Game(){
		
		getContentPane().setLayout(null);		
		
		addPlayer = new JButton("Add player");
		addPlayer.setBounds(67, 378, 140, 60);
		getContentPane().add(addPlayer);
		
		startGame = new JButton("Start game");
		startGame.setBounds(270, 378, 140, 60);
		getContentPane().add(startGame);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		
		MyEventHandler commandHandler = new MyEventHandler();
		addPlayer.addActionListener(commandHandler);
		startGame.addActionListener(commandHandler);
		
	}
	
	private class MyEventHandler implements ActionListener{

		public void actionPerformed(ActionEvent myEvent) {
			
			Database db = new Database();
			
			JTextField playerNameField = new JTextField(12);
			
			JPanel myPanel = new JPanel();
			myPanel.add(new Label("Player name: "));
			myPanel.add(playerNameField);
			int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter new player", JOptionPane.OK_CANCEL_OPTION);
			
			if(result = JOptionPane.OK_OPTION){
				 String name = playerNameField.getText();
				 db.addPlayer(name);
			}
		}
		
	}
}
