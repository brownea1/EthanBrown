package linearLightsOut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Listens for when a button is pressed
 * Then switches that button's text and the text
 * of all the buttons around it
 * 
 * takes the index of the button it's listening for,
 * The List of all buttons and the frame
 * @author brownea1
 *
 */
public class Listener implements ActionListener{
	private int index;
	private ArrayList<JButton> buttons;
	private JFrame frame;
	
	public Listener(int i, JFrame f, ArrayList<JButton> b) {
		index = i;
		buttons = b;
		frame = f;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//Change the value of the button clicked
		switchLabel(buttons.get(index));
		
		//Check if label is at the end, and change if not
		if(index > 0) 
			switchLabel(buttons.get(index-1));
		if(index < buttons.size()-1)
			switchLabel(buttons.get(index+1));
		
		if(gameOver()) 
			frame.setTitle("We Have a Winner!");
		else
			frame.setTitle("Linear Lights Out");
		
	}
	
	private void switchLabel(JButton button) {
		String thisButtonLabel = button.getText();
		thisButtonLabel = thisButtonLabel.equals("X") ? "O" : "X"; //Switches the value of the label
		button.setText(thisButtonLabel);
	}
	
	private boolean gameOver() {
		String str = buttons.get(0).getText();
		for(int i=0; i<buttons.size(); i++) {
			String curr = buttons.get(i).getText();
			if(!curr.equals(str))
				return false;
		}
		
		return true;		
	}


}
