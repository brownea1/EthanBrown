import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

/**
 * A house object that has the dimensions
 * 50x100. The starting location and color are
 * set when constructing the House
 * A house is a filled rectangle with a triangle on top
 * 
 * @author brownea1
 *
 */
public class House {
	private static final int HEIGHT = 50;
	private static final int WIDTH = 100;
	private static final int ROOF_HEIGHT = 20;

	private Color color;
	private int startX, startY;

	public House(int x, int y, Color color) {
		startX = x;
		startY = y;
		this.color = color;
	}

	/**
	 * Draws a House - which is broken up
	 * into the house and the roof
	 * drawOn draws a House on the Graphics2D g2
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		Graphics2D tempG = (Graphics2D) g2.create();
		
		tempG.setColor(this.color);
		
		//Draw the actual house
		Rectangle rect = new Rectangle(startX, startY, WIDTH, HEIGHT);
		tempG.fill(rect);
		tempG.draw(rect);
		
		//Draw the roof
		Line2D.Double leftLine = new Line2D.Double(startX, startY, startX+(WIDTH/2), startY-ROOF_HEIGHT);
		Line2D.Double rightLine = new Line2D.Double(startX+(WIDTH/2), startY-ROOF_HEIGHT, startX+WIDTH, startY);
		Line2D.Double botLine = new Line2D.Double(startX, startY, startX+WIDTH, startY);
		tempG.draw(leftLine);
		tempG.draw(rightLine);	
		tempG.draw(botLine);
		
	}

}
