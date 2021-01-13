import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 * Draws a sun, row of red houses,
 * and some rows of trees on the graphics area
 * @author brownea1
 *
 */
public class SceneComponent extends JComponent {
	
	/**
	 * Draws the whole scene
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);;
	
		Graphics2D g2 = (Graphics2D) g;
		//Draw the sky and grass
		Rectangle sky = new Rectangle(750, 600);
		g2.setColor(Color.BLUE);
		g2.draw(sky);
		g2.fill(sky);

		Rectangle grass = new Rectangle(0, 375, 750, 225);
		g2.setColor(Color.GREEN);
		g2.draw(grass);
		g2.fill(grass);
		
		//Draw the objects
		drawSun(g2);
		drawHouses(g2);
		drawTrees(g2, 10, 40, 175, 345, 25, 10);
		drawTrees(g2, 20, 80, 185, 365, 15, 20);
		
	}
	
	/**
	 * Draws just the sun object
	 * @param g
	 */
	private void drawSun(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		Sun s = new Sun();
		s.drawOn(g2);
	}
	
	/**
	 * Draws a row of houses
	 * @param g
	 */
	private void drawHouses(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		int spacing = 120;
		int currX = 100;
		int currY = 475;
		
		for(int i=0; i<5; i++) {			
			House h = new House(currX, currY, Color.RED);
			h.drawOn(g2);
			currX += spacing;
		}
	}
	
	/**
	 * Draws a row of small trees
	 * @param g
	 */
	private void drawTrees(Graphics g, int width, int height, int x, int y, int n, int seperation) {
		Graphics2D g2 = (Graphics2D) g.create();
		
		for(int i=0; i<n; i++) {
			PineTrees t = new PineTrees(x, y, width, height);
			t.drawOn(g2);
			
			x += seperation + (int) (Math.random()*10+1);
		}
		
	}
	
	
	

	
}
