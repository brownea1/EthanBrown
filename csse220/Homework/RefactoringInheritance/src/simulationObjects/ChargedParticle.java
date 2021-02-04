package simulationObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ChargedParticle extends Particle {
	private int ring1Width, ring1Height; //ring1 is the horizontal ring
	private int ring2Width, ring2Height; //ring2 is the vertical ring
	private Color ringColor;
	
	public ChargedParticle(int x, int y) {
		super(x, y);
		
		ring1Width = getRadius() * 4;
		ring1Height = getRadius();
		ring2Width = getRadius();
		ring2Height = getRadius() * 4;
		
		Random rand = new Random();
		this.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		ringColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}
	
	public void drawOn(Graphics2D g) {
		super.drawOn(g);
		
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(ringColor);
		Ellipse2D.Double horiz = new Ellipse2D.Double(getCenterX()-(ring1Width/2), getCenterY()-(ring1Height/2), ring1Width, ring1Height);
		Ellipse2D.Double vert = new Ellipse2D.Double(getCenterX()-(ring2Width/2), getCenterY()-(ring2Height/2), ring2Width, ring2Height);
		
		g2.draw(horiz);
		g2.draw(vert);
	}
	
	public void update(Dimension2D dim) {
		super.update(dim);
	}
	public void bounce(SimObject other) {
		super.bounce(other);
		
		Random rand = new Random();
		setColor( new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)) );
		ringColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}
	
}
