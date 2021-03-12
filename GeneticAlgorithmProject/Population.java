package main;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * The Population class handles all the calculations about all of the Chromosomes being used
 * 
 * The Population class instantiates all of the @see Chromosome objects
 * 
 * This class' main job is to instantiate all the chromosomes, and to run the evolutionary loop
 * 
 * @author brownea1
 *
 */
public class Population {
	public static final int DEFAULT_POPULATION_SIZE = 100;
	public static final int DEFAULT_GENOME_LENGTH = 100;
	public static final int DEFUALT_ELITISM = 0;
	public static final boolean DEFAULT_CROSS_OVER = false;
	public static final int DEFAULT_MUTATION_RATE = Chromosome.DEFAULT_MUTATION_RATE;
	
	//Evolution Loop fields
	private int currentGeneration;
	//Evolutionary traits fields
	private int mutationRate;
	private int elitism;
	private boolean crossOver;
	//Size fields
	private int populationSize;
	private int genomeLength;
	//Chromosome fields
	private ArrayList<Chromosome> chromosomes;
	private Chromosome fittestChromosome;
	private Chromosome leastFitChromosome;
	
	
	public Population() {
		//Set evolutionLoop variables
		currentGeneration = 0;
		elitism=0;
		//Set evolutionary traits
		mutationRate = DEFAULT_MUTATION_RATE;
		elitism = DEFUALT_ELITISM;
		crossOver = DEFAULT_CROSS_OVER;
		
		//Set sizes
		genomeLength = DEFAULT_GENOME_LENGTH;
		populationSize = DEFAULT_POPULATION_SIZE;
		
		//Set initial values for all the chromosomes
		fittestChromosome = null;
		leastFitChromosome = null;
		
		//Create and sort the population of chromosomes
		chromosomes = new ArrayList<Chromosome>();	
		generatePopulation();
		//sortPopulation();
	}

	public Population(int population, int length, int r, int elitism, boolean crossOver) {
		//Set evolutionLoop variables
		currentGeneration = 0;
		
		//Set evolutionary traits
		mutationRate = r;
		this.elitism = elitism;
		this.crossOver = crossOver;
		
		//Set sizes
		genomeLength = length;
		populationSize = population;
		
		//Set initial values for all the chromosomes
		fittestChromosome = null;
		leastFitChromosome = null;
		
		//Create the population of chromosomes
		chromosomes = new ArrayList<Chromosome>();	
		generatePopulation();
		sortPopulation();
	}
	
	/******************************************************************************************************************************
	 * 
	 */
	public void evolutionaryLoop() {
		ArrayList<Chromosome> temp = new ArrayList<Chromosome>();
		
		//Generate offspring
		for (int k = populationSize-1; k>populationSize-1-elitism; k--) 
				temp.add(chromosomes.get(k));
		
		for(int i=0; i<populationSize; i+=2) {
			//********************selection********************\\
			Random r = new Random();
			int parent1Index = r.nextInt(populationSize/2) + (populationSize/2);
			int parent2Index = r.nextInt(populationSize/2) + (populationSize/2);
			
			Chromosome child1 = new Chromosome(chromosomes.get(parent1Index));
			Chromosome child2 = new Chromosome(chromosomes.get(parent2Index));			
			//Add the children to the chromosome list
			temp.add(child1);
			temp.add(child2);
			
			//********************crossover********************\\
			if (crossOver) {
				int rand = r.nextInt(genomeLength);
				child1.crossover(child2, rand);
			}
				
			//********************mutation********************\\
			child1.mutate();
			child2.mutate();
			
			
		}
		
		if (temp.size()>populationSize)
			for (int k = temp.size()-1;k>populationSize-1;k--)
				temp.remove(k);

		chromosomes = temp;
		currentGeneration++;
}	
	
	public double measureDiversity() {
		double total = 0, hamming=0;
		for (int k =0; k<chromosomes.size()-1;k++) {
			for (int j=k+1;j<chromosomes.size();j++) {
				hamming = hamming + chromosomes.get(k).getHammingDistance(chromosomes.get(j));
				total++;
			}
		}
		
		return hamming/total;
	}


	/******************************************************************************************************************************
	 *
	 */
	public void sortPopulation() {
		Comparator<Chromosome> c = new Comparator<Chromosome>() {
			@Override
			public int compare(Chromosome c1, Chromosome c2) {
				return new Integer(c1.getFitness()).compareTo(c2.getFitness());
			}
		};
		
		chromosomes.sort(c);
		
		fittestChromosome = chromosomes.get(populationSize - 1);
		leastFitChromosome = chromosomes.get(0);
	}
	
	/******************************************************************************************************************************
	 * 
	 */
	private void generatePopulation() {
		for(int i=0; i<populationSize; i++) {
			chromosomes.add(new Chromosome(genomeLength, mutationRate));
		}
	}
	
	/******************************************************************************************************************************
	 * Calculating Fitness stats
	 */
	public void calculateFitness(int type, Chromosome ideal) {
		for(Chromosome temp: chromosomes)
			temp.calculateFitness(type, ideal);
	}
	
	public int getSumFitness() {
		int sum = 0;
		for(Chromosome c: chromosomes)
			sum += c.getFitness();
		return sum;
	}
	public int getAvgFitness() {
		int sum = 0;
		
		for(int i=0; i<chromosomes.size(); i++)
			sum += chromosomes.get(i).getFitness();
		
		if(chromosomes.size() == 0)
			return 0;
		
		return sum / chromosomes.size();
	}
	public int getHighestFitness() {
		return fittestChromosome.getFitness();
	}
	public void setFittestChromosome(Chromosome fittestChromosome) {
		this.fittestChromosome = fittestChromosome;
	}
	
	/******************************************************************************************************************************
	 * GETTERS AND SETTERS
	 */
	public int getCurrentGen() {
		return currentGeneration;
	}
	public ArrayList<Chromosome> getChromosomes() {
		return chromosomes;
	}
	public void setChromosomes(ArrayList<Chromosome> chromosomes) {
		this.chromosomes = chromosomes;
	}
	public Chromosome getFittestChromosome() {
		return fittestChromosome;
	}
	public Chromosome getLeastFitChromosome() {
		return leastFitChromosome;
	}
	public int getLowestFitness() {
		return leastFitChromosome.getFitness();
	}
	public void setLeastFitChromosome(Chromosome leastFitChromosome) {
		this.leastFitChromosome = leastFitChromosome;
	}
	public void setElitism(int elitism)
	{
		this.elitism = elitism;
	}
	public void setCrossover(boolean crossOver) {
		this.crossOver = crossOver;
	}
	public void setRate(int rate) {
		mutationRate = rate;
		for(int k=0; k<chromosomes.size(); k++)
			chromosomes.get(k).setMutationRate(this.mutationRate);
	}
	

	
	
}
