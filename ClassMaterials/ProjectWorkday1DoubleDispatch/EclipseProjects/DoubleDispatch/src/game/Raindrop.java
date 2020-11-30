package game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Raindrop {
	private double x;
	private double y;
	private double yVelocity = 3;
	// All raindrops are the same constant size
	private static final double SIZE = 5.0;
	
	public Raindrop(int range) {
		this.x = Math.random()*range;
		this.y = 0;
	}
	
	public Raindrop(Rectangle2D.Double box) {
		this.x = box.getMinX() + Math.random() * (box.getWidth());
		this.y = box.getMinY() + Math.random() * (box.getHeight());
	}
	
	/**
	 * @param bottom The y-coordinate of the bottom of the screen.
	 * @return True if the raindrop fell off the bottom of the screen
	 */
	public boolean fall(int bottom) {
		this.y += this.yVelocity;
		return this.y > bottom;
	}
	
	public void drawOn(Graphics2D g) {
		g.setColor(Color.BLUE);
		Ellipse2D.Double drop = 
				new Ellipse2D.Double(this.x, this.y, SIZE, SIZE);
		g.fill(drop);
		g.setColor(Color.BLACK);
	}
	
	// Use to detect if the raindrops hit the box
	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(x,y, SIZE, SIZE);
	}
	
	public boolean insidePlatform(Platform platform) {
		
		return platform.getBoundingBox().intersects(x, y, SIZE, SIZE);
	}
	
	
	
}
