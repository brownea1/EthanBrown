package ballStrikeCounter;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Runs the Tracker application
 * 
 * @author brownea1
 *
 */
public class Tracker {
	private JLabel label = new JLabel();
	private int ballCount = 0;
	private int strikeCount = 0;

	// Call this to update the text on the label.
	public void updateLabel(int numBalls, int numStrikes) {
		this.label.setText("<html>Balls: " + numBalls + "<br />Strikes: " + numStrikes + "</HTML>");
	}

	public static void main(String[] args) {
		new Tracker();
	}

	public Tracker() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(new BorderLayout());

		frame.add(label, BorderLayout.CENTER);
		
		// TODO: Add buttons and listeners to make things work.
		JButton ballButton = new JButton("Add ball");
		JButton strikeButton = new JButton("Add strike");
		
		panel.add(ballButton, BorderLayout.NORTH);
		panel.add(strikeButton, BorderLayout.SOUTH);
		frame.add(panel, BorderLayout.EAST);
		
		//Use anonymous classes as the listeners to make my life easier
		ballButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				addBall();
			}
		});
		strikeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				addStrike();
			}
		});
		
		// The following line is given to show you how to use the given method:
		updateLabel(0, 0);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Increments the ball count, and rests if over
	 */
	private void addBall() {
		ballCount ++;
		if(ballCount >= 4) {
			ballCount = 0;
			strikeCount = 0;
		}
		updateLabel(ballCount, strikeCount);
	}
	/**
	 * Increments strike count and resets if over
	 */
	private void addStrike() {
		strikeCount ++;
		if(strikeCount >= 3) {
			strikeCount = 0;
			ballCount = 0;
		}
		updateLabel(ballCount, strikeCount);
	}
	
	
}
