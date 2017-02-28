import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;


public class Rules extends JFrame {
	static JButton okButton;

	public Rules(){
		super("Rules");
		
		getContentPane().setLayout(null);		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 400, 500);
		setLocationRelativeTo(null);
		
		okButton = new JButton("OK");
		okButton.setBounds(133, 393, 125, 45);
		getContentPane().add(okButton);
		
		JTextArea txtrArea = new JTextArea();
		txtrArea.setText("");
		txtrArea.setBounds(120, 70, 138, 86);
		getContentPane().add(txtrArea);
	
		
		MyEventHandler commandHandler = new MyEventHandler();
		okButton.addActionListener(commandHandler);
		
	}
	
	
	private class MyEventHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent myEvent){
			if (myEvent.getSource() == okButton){
				setVisible(false);
			}
			
		}
	}
}
