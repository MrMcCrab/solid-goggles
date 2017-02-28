
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.BorderLayout;


public class Rules extends JFrame {
	static JButton okButton;
	
	public Rules() {
				
		getContentPane().setLayout(null);
		setBounds(0, 0, 400, 500);
		setLocationRelativeTo(null);
		
		okButton = new JButton("OK");
		okButton.setBounds(125, 380, 140, 60);
		getContentPane().add(okButton);
		myEventHandler commandHandler = new myEventHandler(); 
		okButton.addActionListener(commandHandler);
		
	}
	
	
	public class myEventHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent myEvent){
			if(myEvent.getSource() == okButton){
				setVisible(false);
			}
			
		}
	}
	
	
	
	
	
}
