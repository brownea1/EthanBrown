package linearLightsOut;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Runs the Linear Lights Out application.
 * 
 * @author Ethan Brown
 */
public class LinearMain {
	/**
	 * Starts here.
	 * 
	 * @param args
	 */

	ArrayList<JButton> buttons;
	JFrame myFrame;
	public static void main(String[] args) {
		new LinearMain();
	}
	
	public LinearMain() {
		newGame();
	}
	
	/**
	 * Initializes a new game, including the frame and 
	 * all the frame's components
	 */
	private void newGame() {
		buttons = new ArrayList<JButton>();
		String nButtonsString = JOptionPane
				.showInputDialog("How many buttons would you like?");
		int nButtons = Integer.parseInt(nButtonsString);
		myFrame = new JFrame();
		JPanel panel = new JPanel();
		JPanel bottomPanel = new JPanel();
		Random rand = new Random();
		
		myFrame.add(panel);
		myFrame.add(bottomPanel, BorderLayout.SOUTH);
		
		for(int i=0; i<nButtons; i++) {
			String buttonText = rand.nextBoolean() ? "X" : "O";
			JButton temp = new JButton(buttonText);
			Listener l = new Listener(i, myFrame, buttons);
			
			temp.addActionListener(l);
			panel.add(temp);
			buttons.add(temp);
		}
		
		JButton newGameButton = new JButton("New Game");
		JButton quitButton = new JButton("Quit");
		
		bottomPanel.add(newGameButton);
		bottomPanel.add(quitButton);
		
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitGame();
				newGame();
			}
		});
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitGame();
			}
		});
		
		myFrame.setTitle("Linear Lights Out!");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.pack();
		myFrame.setVisible(true);
	}

	/**
	 * Exits the application
	 */
	private void quitGame() {
		myFrame.setVisible(false);
		myFrame.dispose();
	}
}
