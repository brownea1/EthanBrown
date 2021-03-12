package main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

/**
 * A Chromosome is an array of boolean primitives
 * This class handles all 
 * @author brownea1
 *
 */
public class Chromosome {
	public static final int DEFAULT_GENOME_LENGTH = 100;
	public static final int DEFAULT_MUTATION_RATE = 1;
	public static final int SIZE = 30;
	public static final int SPACING = 1;
	
	private Random rand;
	private int spacing;
	private int size;
	private int mutationRate;
	private int fitness;
	private int genomeLength;
	private boolean[] genes;
	
	public Chromosome() {
		genomeLength = DEFAULT_GENOME_LENGTH;
		mutationRate = DEFAULT_MUTATION_RATE;
		genes = new boolean[genomeLength];
		rand = new Random();
		spacing = SPACING;
		size = SIZE;
		
		generateGeneString();
	}
	public Chromosome(Chromosome parent) {
		genomeLength = parent.getGenomeLength();
		mutationRate = parent.getMutationRate();
		genes = copyParentGenes(parent.getGenes());
		spacing = SPACING;
		size = SIZE;
		
		rand = new Random();
	}
	public Chromosome(int l, int r) {
		genomeLength = l;
		genes = new boolean[genomeLength];
		rand = new Random();
		mutationRate = r;
		spacing = SPACING;
		size = SIZE;
		
		generateGeneString();
	}	
	//Constructor Used to load in Chromosome
	public Chromosome(boolean[] g) {
		genomeLength = g.length;
		genes = g;
		rand = new Random();
		mutationRate = 1;
		spacing = SPACING;
		size = SIZE;
	}
	public Chromosome(int length) {
		genomeLength = length;
		genes = new boolean[genomeLength];
		rand = new Random();
		mutationRate = 1;
		spacing = SPACING;
		size = SIZE;
	}
	
	
	public void crossover(Chromosome sibling, int c) {
		boolean[] siblingGenes = sibling.getGenes();
		for(int i=0; i<c; i++) {
			boolean temp = genes[i];
			genes[i] = siblingGenes[i];
			siblingGenes[i] = temp;
		}
	
	}
	
	/*******************************************************************************************
	 * Fitness Functions
	 * 
	 */
	public void calculateFitness(int fitType, Chromosome ideal) {
		switch(fitType) {
		case 0:
			calculateFitness0();
			break;
		case 1:
			calculateFitness1(ideal);
			break;
		case 2:
			calculateFitness2();
			break;
		default:
			break;
		}
	}
	
	//Calculate fitness based on number of true
	private void calculateFitness0() {
		int count = 0;
		
		for(int i=0; i<genes.length; i++)
			if(genes[i])
				count++;
		
		fitness = count;
	}	
	//Calculates fitness based on similarity to ideal
	private void calculateFitness1(Chromosome ideal) {
		int count = 0;
		boolean[] list2 = ideal.getGenes();
		
		for(int i=0; i<genes.length; i++)
			if(genes[i] == list2[i])
				count++;					
		fitness = count;
	}
	//Calculate fitness based on max num of consecutive 1s
	private void calculateFitness2() {
		int maxCount = 0;
		int currCount = 0;
		
		for(int i=0; i<genes.length; i++) {
			if(genes[i]) {
				currCount++;
				if(currCount > maxCount)
					maxCount = currCount;
			}
			else
				currCount = 0;
		}
		
		fitness = maxCount;
	}

	public int getHammingDistance(Chromosome c) {
		int k=0;
		
		for (int j=0; j <this.genomeLength; j++) {
			if (genes[j] != c.getGenes()[j]) {
				k++;
			}
		}
		return k;	
	}
	
	public void drawOn(Graphics2D g) {
		//Graphics2D g2 = (Graphics2D) g.create();
		
		int x = 2;
		int y = 2;
		int index = 0;
		
		for(int i=0; i<genomeLength/10 + 1; i++) {
			for(int j=0; j<10 && index < genomeLength; j++) {
				Color c = genes[index] ? Color.cyan : Color.black;
				g.setColor(c);
				
				
				Rectangle rect = new Rectangle(x, y, size, size);
				g.fill(rect);
				
				x += spacing + size;
				index++;
			}
			x = 2;
			y += spacing + size;
		}	
		
	}
	
	/**
	 * Generates a random string of Genes
	 */
	private void generateGeneString() {
		for(int i=0; i<genomeLength; i++) {
			genes[i] = rand.nextBoolean();
		}
	}
	
	private boolean[] copyParentGenes(boolean[] parentGenes) {
		boolean[] geneCopy = new boolean[genomeLength];
		for(int i=0; i<genomeLength; i++)
			geneCopy[i] = parentGenes[i];
		
		return geneCopy;
	}
	
	public void mutate() {	
		for(int i=0; i<genomeLength; i++) {
			int index = rand.nextInt(100);
			int rate = mutationRate-1;
			if (index<=rate)
				genes[i] = !genes[i];
		}
	}

	
	
	//Getters and Setters
	public boolean[] getGenes() {
		return genes;
	}
	public void setGenes(boolean[] genes) {
		this.genes = genes;
	}
	public int getFitness() {
		return fitness;
	}
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	public int getGenomeLength() {
		return genomeLength;
	}
	public void setGenomeLength(int genomeLength) {
		this.genomeLength = genomeLength;
	}

	public int getMutationRate() {
		return mutationRate;
	}
	public void setMutationRate(int r) {
		mutationRate = r;
	}
	public void setSizeAndSpacing(int si, int sp) {
		size = si;
		spacing = sp;
	}
	
}
