package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GraphLines {
	private int centerX, centerY, newX, newY;
	private Color color;
	
	public GraphLines(int centerX, int centerY, int newX, int newY, Color color) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.newX = newX;
		this.newY = newY;
		this.color = color;
	}
	
	public void drawOn(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(4));
		g2d.drawLine(centerX, centerY, newX, newY);
	}
}
