package simulationObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;

public class SimObject {
	private int centerX, centerY, radius, velX, velY, moveSpeed;
	private Color color;
	
	public SimObject(int x, int y, int r, int s, Color c) {
		this.centerX = x;
		this.centerY = y;
		this.velX = (int)(moveSpeed - Math.random() * moveSpeed * 2);
		this.velY = (int)(moveSpeed - Math.random() * moveSpeed * 2);
		
		color = c;
		radius = r;
		moveSpeed = s;
	}
	
	public void drawOn(Graphics2D g) {
		//avoid having to untranslate by mutating a copy of the graphics content
		g = (Graphics2D)g.create();
		g.setColor( this.color );
		g.translate(centerX-radius, centerY-radius);
		g.fillOval(0, 0, radius*2, radius*2);	
	}
	
	public void update(Dimension2D dim) {
		centerX += velX;
		centerY += velY;
		if (centerX > dim.getWidth() || centerX < 0) {
			centerX = (int)Math.min( Math.max(centerX, 0), dim.getWidth());
			velX = -velX;
		}
		if (centerY > dim.getHeight() || centerY < 0) {
			velY = -velY;
			centerY = (int)Math.min( Math.max(centerY, 0), dim.getHeight());
		}
	}
	
	public boolean overlapsWith(SimObject other) {
		int xDiff =  centerX - other.centerX;
		int yDiff =  centerY - other.centerY;
		double distance = Math.sqrt(  xDiff*xDiff + yDiff * yDiff );
		return this.radius + other.radius >= distance;
	}
	
	//move in opposite direction of collision with other particle.
	public void bounce(SimObject other) {
		velX = this.centerX - other.centerX;
		velY = this.centerY - other.centerY;
		normalizeVelocity();
	}
	
	// Make sure that velocity remains fixed and non-zero
	public void normalizeVelocity() {
		//move down, right if velocity is set to zero
		if (this.velX == 0 && this.velY == 0) {
			this.velX = 1;
			this.velY = 1;
		}
		//normalize vector
		double vectorLength = Math.sqrt(velX*velX + velY*velY);
		this.velX = (int)(this.velX / vectorLength * moveSpeed * 2);
		this.velY = (int)(this.velY / vectorLength * moveSpeed * 2);
	}
	
	//Getters
	public int getRadius() { return radius; }
	public int getCenterX() { return centerX; }
	public int getCenterY() { return centerY; }
	public int getVelX() { return velX; }
	public int getVelY() { return velY; }
	public int getMoveSpeed() { return moveSpeed; }
	public Color getColor() { return color; }
	
	//Setters
	public void setRadius(int r) { radius = r; }
	public void setCenterX(int x) { centerX = x; }
	public void setCenterY(int y) { centerY = y; }
	public void setVelX(int x) { velX = x; }
	public void setVelY(int y) { velY = y; }
	public void setMoveSpeed(int s) { moveSpeed = s; }
	public void setColor(Color c) { color = c; }
	
	
}
