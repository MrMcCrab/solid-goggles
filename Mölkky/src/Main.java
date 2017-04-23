
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JInternalFrame;

public class Main extends JFrame{
	
	static JButton startButton;
	static JButton editDbButton;
	static JButton quitButton;
	static JLabel labelSay;
	static JButton rulesButton;
	
	
	public Main() {
		super("Mölkky scorer");
		
		getContentPane().setLayout(null);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		
		startButton = new JButton("New game");
		startButton.setBounds(175, 110, 140, 60);
		getContentPane().add(startButton);
		
		JLabel welcomeLabel = new JLabel("\tWelcome");
		welcomeLabel.setBounds(207, 42, 140, 60);
		getContentPane().add(welcomeLabel);
		
		editDbButton = new JButton("Edit database");
		editDbButton.setBounds(175, 180, 140, 60);
		getContentPane().add(editDbButton);
		
		quitButton = new JButton("Quit");
		quitButton.setBounds(175, 320, 140, 60);
		getContentPane().add(quitButton);
		
		rulesButton = new JButton("Rules");
		rulesButton.setBounds(175, 250, 140, 60);
		getContentPane().add(rulesButton);
		
		labelSay = new JLabel();
		labelSay.setBounds(175, 390, 140, 40);
		getContentPane().add(labelSay);
			
		MyEventHandler commandHandler = new MyEventHandler();
		startButton.addActionListener(commandHandler);
		editDbButton.addActionListener(commandHandler);
		quitButton.addActionListener(commandHandler);
		rulesButton.addActionListener(commandHandler);		
	}
	
	
	private class MyEventHandler implements ActionListener{

		public void actionPerformed(ActionEvent myEvent) {
			if(myEvent.getSource() == startButton){
				//labelSay.setText("game started");
				Game game = new Game();
				game.setVisible(true);
			}else if (myEvent.getSource() == editDbButton){
				//labelSay.setText("Editing db");
				Query query = new Query();
				query.connect();
				Game game = new Game();
				game.setVisible(true);
			}else if (myEvent.getSource() == editDbButton){
				Database db = new Database();
				db.connect();
			}else if(myEvent.getSource() == quitButton){
				System.exit(ERROR);
			}else if(myEvent.getSource() == rulesButton){
				rules();
			}else{
				labelSay.setText("Unknown error");
			}			
		}				
	}
	
	
	
	public static void rules(){
		Rules rule = new Rules();
		rule.setVisible(true);	
	}

	
	public static void main(String[] args) {
		Main frame = new Main();
		frame.setVisible(true);
		
		
	}
}
