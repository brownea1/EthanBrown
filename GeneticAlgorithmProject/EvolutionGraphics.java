package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class EvolutionGraphics extends JComponent {
	
	private ArrayList<GraphLines> bestLine = new ArrayList<>();
	private ArrayList<GraphLines> avgLine = new ArrayList<>();
	private ArrayList<GraphLines> worstLine = new ArrayList<>();
	private ArrayList<GraphLines> diverseLine = new ArrayList<>();
	
	private int yMax=0, yMin=0, yAvg=0, yMax2, yMin2, yAvg2, yDiv, yDiv2, population=10;
	private double x=0, xDistance=0;
	
	public EvolutionGraphics() {
		super();
		this.setPreferredSize(new Dimension(1050, 500));
	}
	
	public void displayStats(double[] items) {
		int max = 100 - (int)Math.round(items[0]*100);
		int avg = 100 - (int)Math.round(items[1]*100);
		int min = 100 - (int)Math.round(items[2]*100);
		int diversity = 100-(int)Math.round(items[3]*100);
		
		yMax2 = 50 + 4*max;
		yMin2 = 50 + 4*min;
		yAvg2 = 50 + 4*avg;
		yDiv2 = 50 + 4*diversity;
		
		//Draw the best line
		GraphLines g= new GraphLines((int)Math.round(x), yMax, (int)Math.round(x+xDistance), yMax2,Color.CYAN);
		bestLine.add(g);
		
		//Draw the average line
		g= new GraphLines((int)Math.round(x), yAvg, (int)Math.round(x+xDistance), yAvg2,Color.ORANGE);
		avgLine.add(g);
		
		//Draw the worst line
		g= new GraphLines((int)Math.round(x), yMin, (int)Math.round(x+xDistance), yMin2,Color.RED);
		worstLine.add(g);
		
		//draws hamming distance
		g = new GraphLines((int)Math.round(x), yDiv, (int)Math.round(x+xDistance), yDiv2,Color.BLACK);
		diverseLine.add(g);
		yMax= yMax2;
		yMin = yMin2;
		yAvg = yAvg2;
		x= x+xDistance;
		yDiv = yDiv2;
		this.repaint();
	}
	
	public void initialStats(double[] items, int generation) {	
		bestLine.clear();
		avgLine.clear();
		worstLine.clear();
		diverseLine.clear();
		xDistance = 900.0/(double)(generation-1);
		x = 50 + xDistance;
		
		population = (generation-1)/10;
		
		int max = 100 - (int)Math.round(items[0]*100);
		int avg = 100 - (int)Math.round(items[1]*100);
		int min = 100 - (int)Math.round(items[2]*100);
		int diversity = 100-(int)Math.round(items[3]*100);
		yMax =50 + 4*max;
		yMin =50 + 4*min;
		yAvg =50 + 4*avg;
		yDiv = 50 + 4*diversity;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(50,50,1000,420);
		g.drawString("population",10,10);
		g.drawString("Fitness over Generations",500,20);
		
		for (int k = 0; k<=10;k++) {	
			g.drawString(Integer.toString(100-k*10), 10, 50+k*40);
			g2.drawLine(40, 50+k*40, 60, 50+k*40);
		}
		for (int k = 0; k<=10;k++) {
			g2.drawLine(50+90*k, 460, 50+90*k, 480);
			g.drawString(Integer.toString(k*population), 50+90*k, 500);
		}
		
		g.drawString("Best fitness",970,315);
		g.drawString("Ave fitness",970,345);
		g.drawString("Low fitness",970,375);
		g.drawString("Hamming",970,405);
		g2.setColor(Color.CYAN);
		g2.fillRect(940, 300, 20, 20);
		g2.setColor(Color.ORANGE);
		g2.fillRect(940, 330, 20, 20);
		g2.setColor(Color.RED);
		g2.fillRect(940, 360, 20, 20);
		g2.setColor(Color.BLACK);
		g2.fillRect(940, 390, 20, 20);
		
		for (int k = 0; k< bestLine.size(); k++) {
			bestLine.get(k).drawOn(g2);
			avgLine.get(k).drawOn(g2);
			worstLine.get(k).drawOn(g2);
			diverseLine.get(k).drawOn(g2);
		}
		
	}
}
