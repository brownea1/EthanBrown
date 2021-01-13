package hourtimer;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


/**
 * Represents a circular clock with a single hand--a minute hand.
 *
 * @author TODO <PUT_YOUR_NAME_HERE>.
 *         Created Dec 18, 2013.
 */
public class HourTimer {

	
	public static final int DEFAULT_RADIUS = 300;
	public static final int DEFAULT_CENTER_X = 300;
	public static final int DEFAULT_CENTER_Y = 300;
	public static final double RATIO_OF_HAND_LENGTH_TO_RADIUS = .9;
	public static final double RATIO_OF_HAND_WIDTH_TO_RADIUS = .1;
	private int centerX;
	private int centerY;
	private int radius;
	
	//feel free to add constructors or fields to this class as you need
	public HourTimer() {
		centerX = DEFAULT_CENTER_X;
		centerY = DEFAULT_CENTER_Y;
		radius = DEFAULT_RADIUS;
	}
	public HourTimer(int x, int y, int r) {
		centerX = x;
		centerY = y;
		radius = r;
	}

	/**
	 * Draws the timer
	 * 
	 * @param g the graphics object to draw on
	 */
	public void drawOn(Graphics2D g) {
		// Implement this method.
		Graphics2D tempG = (Graphics2D) g.create();
		
		tempG.translate(this.centerX, this.centerY);
		Ellipse2D.Double circle = new Ellipse2D.Double(-this.radius, -this.radius, this.radius*2, this.radius*2);
		tempG.draw(circle);
		
	}
	
	/**
	 * 
	 * Set time sets the position of the hand of the clock.
	 * 0/60 puts the hand facing upwards.
	 * 30 puts the hand facing downwards, etc.  
	 * 
	 * @param minutes the number of minutes to represent with rotation
	 */
	public void setTime(double minutes) {
		// implement this method.
		

	}
	
}
