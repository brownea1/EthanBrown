package ballStrikeCounter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Listener implements ActionListener{
	private JFrame frame;
	private boolean ballOrStrike;
	
	/**
	 * 
	 * @param f
	 * @param ballOrStrike true if this listener is for strikes, false if this is for balls
	 */
	public Listener(JFrame f, boolean bOrs) {
		frame = f;
		ballOrStrike = bOrs;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
	
	

}
