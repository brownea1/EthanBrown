package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

//TODO: JavaDocs
@SuppressWarnings("serial")
public class ChromosomeComponent extends JComponent{
	
	private Chromosome chromosome;

	public ChromosomeComponent() {
		chromosome = new Chromosome();
	}
	public ChromosomeComponent(boolean[] genes) {
		chromosome = new Chromosome(genes);
	}
	public ChromosomeComponent(Chromosome c) {
		chromosome = c;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g.create();
		chromosome.drawOn(g2);
	}
	
	public void setMRate(int r) {
		chromosome.setMutationRate(r);
	}
	
	public Chromosome getChromosome() {
		return chromosome;
	}
	public void setChromosome(Chromosome c) {
		chromosome = c;
	}
	
	
}
