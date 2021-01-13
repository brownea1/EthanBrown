import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * A PineTree consists of a trunk, which
 * is a rectangle has a width of 1/3 the width field
 * and a height of 1/3 the height field
 * The color of the trunk is a constant Color object
 * 
 * A PineTree also has pines, that is a triangle
 * (represented as a Polygon object), that has a width
 * equal to the width field and a height equal to 2/3
 * the height field. The color of the pines is a 
 * constant Color object
 * 
 * @author brownea1
 *
 */
public class PineTrees {
	private static final Color BRANCH_COLOR = new Color(145, 112,33);
	private static final Color PINE_COLOR = new Color(40, 135, 22);
	private int startX, startY;
	private int width, height;
	
	public PineTrees(int x, int y, int w, int h) {
		startX = x;
		startY = y;
		width = w;
		height = h;
	}
	
	/**
	 * Draws the PineTree - which is broken up
	 * into a trunk and the pines
	 * drawOn draws the pinetree on the Graphics2D g
	 * @param g
	 */
	public void drawOn(Graphics2D g) {
		Graphics2D tempG = (Graphics2D) g.create();
		
		//Draw Trunk
		tempG.setColor(BRANCH_COLOR);
		tempG.translate(startX+(width/3), startY+(height*2)/3);
		Rectangle trunk = new Rectangle(width/3, height/3);
		tempG.fill(trunk);
		tempG.draw(trunk);
		
		//Reset Translation
		tempG.translate(-(width/3), -(height*2)/3);
		
		//Draw Pines
		tempG.translate(0, (height*2)/3);
		tempG.setColor(PINE_COLOR);
		
		int[] xPoints = {0, width/2, width};
		int[] yPoints = {0, -(height*2)/3, 0};
		
		Polygon triangle = new Polygon(xPoints, yPoints, 3);
		tempG.fill(triangle);
		tempG.draw(triangle);
		
	}
	
}
