import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Draws the whole scene from the
 * Sun, House and PineTree classes
 * 
 * @author brownea1
 *
 */
public class SceneViewer {
	public static final Dimension WINDOW_DIMENSIONS = new Dimension(750, 600);
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("My scene");
		frame.setSize(WINDOW_DIMENSIONS);
		
		frame.add(new SceneComponent());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
