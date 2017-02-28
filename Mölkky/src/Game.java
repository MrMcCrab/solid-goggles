import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Event;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Game extends JFrame{
	
	static JButton startButton;
	static JButton editDbButton;
	static JButton quitButton;
	static JLabel labelSay;
	
	
	public Game() {
		super("Mölkky scorer");
		
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		setLocationRelativeTo(null);
		
		startButton = new JButton("Start game");
		startButton.setBounds(160, 110, 140, 60);
		getContentPane().add(startButton);
		
		JLabel welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setBounds(209, 44, 117, 60);
		getContentPane().add(welcomeLabel);
		
		editDbButton = new JButton("Edit database");
		editDbButton.setBounds(160, 176, 140, 60);
		getContentPane().add(editDbButton);
		
		quitButton = new JButton("Quit");
		quitButton.setBounds(160, 247, 140, 60);
		getContentPane().add(quitButton);
		
		labelSay = new JLabel();
		labelSay.setBounds(160, 342, 140, 40);
		getContentPane().add(labelSay);
			
		MyEventHandler commandHandler = new MyEventHandler();
		startButton.addActionListener(commandHandler);
		editDbButton.addActionListener(commandHandler);
		quitButton.addActionListener(commandHandler);
		
	}
	
	
	
	private class MyEventHandler implements ActionListener{

		public void actionPerformed(ActionEvent myEvent) {
			if(myEvent.getSource() == startButton){
				labelSay.setText("game started");
			}else if (myEvent.getSource() == editDbButton){
				labelSay.setText("Editing db");
			}else if(myEvent.getSource() == quitButton){
				System.exit(ERROR);
			}else{
				labelSay.setText("Unknown error");
			}
			
		}
		
		
	}
	

	
	public static void main(String[] args) {
		Game frame = new Game();
		frame.setVisible(true);
		
		
	}
}
