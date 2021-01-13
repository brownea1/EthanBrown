import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * Draw some pine trees on a graphics area
 * @author brownea1
 *
 */
public class PineTreesComponent extends JComponent {

	/**
	 * Draws the PineTrees
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		PineTrees tree1 = new PineTrees(100, 100, 100, 200);
		tree1.drawOn(g2d);
		
		PineTrees tree2 = new PineTrees(300, 200, 50, 100);
		tree2.drawOn(g2d);
		
	}

	
}
