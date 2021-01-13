import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class MyComponent extends JComponent{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		Graphics2D g2d = (Graphics2D) g;
		
		//Rectangle rect = new Rectangle(centerX-100, centerY-50, 200, 100);
		//g2d.draw(rect);
		
		//Using Translate
		g2d.translate(centerX-100, centerY-50);
		Rectangle rect = new Rectangle(200,100);
		g2d.draw(rect);
		
	}

}
