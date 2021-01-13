import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Draws a Sun, via the
 * SunComponent and Sun classes
 * 
 * @author brownea1
 *
 */
public class SunViewer {
	public static final Dimension SUN_VIEWER_SIZE = new Dimension(750,600);

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(SUN_VIEWER_SIZE);
		frame.setTitle("The Sun");
		
		frame.add(new SunComponent());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
