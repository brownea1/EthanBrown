import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Draws a bunch of Pine Trees, via the
 * PineTreesComponent and PineTrees classes
 * 
 * @author brownea1
 *
 */
public class PineTreesViewer {
	public static final Dimension PINE_TREES_VIEWER_SIZE = new Dimension(500, 400);

	/**
	 * Constructs and displays the JFrame which displays PineTrees via a
	 * PineTreesComponent. 
	 * 
	 * @param args
	 *            Command-line arguments, ignored here.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(PINE_TREES_VIEWER_SIZE);
		frame.setTitle("Pine Trees");
		
		frame.add(new PineTreesComponent());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
