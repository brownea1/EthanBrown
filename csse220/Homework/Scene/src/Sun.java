import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 * A sun is an object of a filled in circle
 * of a size determined by the circleDiameter field
 * This sun object also has 8 rays of light at a certain distance
 * rotated about the sun
 * 
 * @author brownea1
 *
 */
public class Sun {
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final int NUMBER_OF_RAYS = 8;
    private static final double RAY_LENGTH_SCALE = 0.5;
    private static final double RAY_WIDTH_SCALE = 0.1;
    private static final double RAY_DISTANCE_FROM_SUN_SCALE = .2;
    private static final double DEFAULT_SUN_DIAMETER = 100.0;
    private static final double DEFAULT_SUN_X = 100.0;
    private static final double DEFAULT_SUN_Y = 100.0;
    private static final Color DEFAULT_SUN_COLOR = Color.YELLOW;
    private static final double LITTLE_SUNS_X_OFFSET = 50; 
    
    private double x;
    private double y;
    private double circleDiameter;
    private double rayLength;
    private double rayWidth;
    private double rayDistanceFromSun;
    private Color color;
    
    public Sun() {
    	this.x = DEFAULT_SUN_X;
    	this.y = DEFAULT_SUN_Y;
    	this.circleDiameter = DEFAULT_SUN_DIAMETER;
    	this.rayLength = RAY_LENGTH_SCALE * DEFAULT_SUN_DIAMETER;
    	this.rayWidth = RAY_WIDTH_SCALE * DEFAULT_SUN_DIAMETER;
    	this.rayDistanceFromSun = RAY_DISTANCE_FROM_SUN_SCALE * DEFAULT_SUN_DIAMETER;
    	this.color = DEFAULT_SUN_COLOR;
    }
    public Sun(double x, double y, double d, Color c) {
    	this.x = x;
    	this.y = y;
    	circleDiameter = d;
    	color = c;
    	rayLength = RAY_LENGTH_SCALE * d;
    	rayWidth = RAY_WIDTH_SCALE * d;
    	rayDistanceFromSun = RAY_DISTANCE_FROM_SUN_SCALE * d;
    }
    
    public void drawOn(Graphics2D g) {
    	Graphics2D g2 = (Graphics2D) g.create();
    	
    	//Draw Sun Border
    	g2.setColor(BORDER_COLOR);
    	Ellipse2D.Double border = new Ellipse2D.Double(x, y, circleDiameter, circleDiameter);
    	g2.draw(border);
    	
    	//Fill in Sun
    	g2.setColor(color);
    	g2.fill(border);
    	
    	//Draw the rays
    	double angle = 0;
    	g2.translate(border.getCenterX(), border.getCenterY());
    	
    	
    	for(int i=0; i<NUMBER_OF_RAYS; i++) {
    		drawRay(g2, angle);

    		angle += Math.PI/4;
    	}
    	
    	
    }
    
    private void drawRay(Graphics2D g, double angle) {
    	Graphics2D g2 = (Graphics2D) g.create();
    	g2.rotate(angle);
    	
    	Rectangle ray = new Rectangle((int) (circleDiameter/2 + rayDistanceFromSun), (int) -rayWidth/2, (int) rayLength, (int) rayWidth);
    	g2.fill(ray);
    	g2.draw(ray);
    }
    
    
    
    
    
    
    
    
}
