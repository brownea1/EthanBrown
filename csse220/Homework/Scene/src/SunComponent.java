import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Draw a sun on a graphics area
 * @author brownea1
 *
 */
public class SunComponent extends JComponent {
	private static final double LITTLE_SUN_SIZE = 30.0;
    private static final double LITTLE_SUN_SEPARATION = 100.0;
    private static final int NUM_LITTLE_SUNS = 5;
    private static final double LITTLE_SUNS_Y = 400.0;
    private static final Color LITTLE_SUN_COLOR = Color.RED;
    private static final double LITTLE_SUNS_X_OFFSET = 50;
    
    /**
     * Draws the sun
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Get the 2D graphics object
        Graphics2D g2 = (Graphics2D)g;
        // Create a Sun using the default (parameterless) constructor, 
        // then draw it to the frame
        Sun s = new Sun();
        s.drawOn(g2);

        // Draw a rectangle around the default sun
        g2.drawRect(30, 30, 240, 240);

        // Draw a rectangle around a new sun in a particular position
        s = new Sun(550, 100, 50, Color.BLUE);
        s.drawOn(g2);
        g2.drawRect(515, 65, 120, 120);

        // Draw little suns
        double x = SunComponent.LITTLE_SUNS_X_OFFSET;
        for (int i = 0; i < SunComponent.NUM_LITTLE_SUNS; i++) {	
            s = new Sun(x,
                  SunComponent.LITTLE_SUNS_Y,
                  SunComponent.LITTLE_SUN_SIZE, 
                  SunComponent.LITTLE_SUN_COLOR);
            s.drawOn(g2);
            x+= SunComponent.LITTLE_SUN_SEPARATION;
        }
    }

    
    
    
}
