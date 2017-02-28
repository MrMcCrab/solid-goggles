import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


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
