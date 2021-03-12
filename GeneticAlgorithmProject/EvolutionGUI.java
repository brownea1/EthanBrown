package main;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


/**
 * This is the main hub of the program, and is the file that needs to be ran.
 * This class holds all the information needed to run the simulation, and creates the GUI.
 * 
 * This class uses:
 * 		@see ChromosomeViewer
 * 		@see ChromosomeComponent
 * 		@see EvolutionGraphics
 * to create the user interface and data visualization
 * 
 * This class also instantiates:
 * 		@see Population
 * 
 * This class contains an ActionListener abstract method to hear when a JButton is pressed
 *  
 * @author brownea1
 *
 */
public class EvolutionGUI implements ActionListener{
	public static final int GENE_SIZE = 30;
	public static final int GENE_SPACING = 1;
	public static final String STARTING_CHROMOSOME_VIEWER_PATH = "initial100.txt";
	
	//Primitive data fields
	private boolean initial= false, declared = false, crossover = false;
	private int populationSize, genomeLength, rateInt, generationInt, elitismInt;
	private int fitnessType; //0=numTrue, 1=idealChrome, 2=numInRow
	
	//Created classes used here
	private ChromosomeComponent icc;
	private ChromosomeViewer mostFitViewer, populationViewer, idealChromosomeViewer;
	private EvolutionGraphics art;
	private Population pop;
	
	//Imported java objects
	private Timer timer;
	private JTextField popField;
	private JButton controlEvolution;
	
	public EvolutionGUI() {
		fitnessType = 1;
		timer = new Timer(100, this);
		idealChromosomeViewer = new ChromosomeViewer();
		icc = idealChromosomeViewer.getChromosomeComponent();
	}
	
	/******************************************************************************************************************************
	 * 
	 */
	private void displayGUI() {
		JFrame GUIFrame = new JFrame("Evolution Viewer");
		GUIFrame.setSize(1155, 620);
		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel dataPanel = new JPanel();
		GUIFrame.add(dataPanel, BorderLayout.SOUTH);
		JLabel rate = new JLabel("Mutation Rate (N/pop)");
		JTextField mutateField = new JTextField();
		mutateField.setText("1");//change later
		mutateField.setColumns(4);
		
		JLabel select = new JLabel("Selection");
		String [] slectionItem = {"Truncation", "Fitness Proportionate"};
		JComboBox<String> selectBox = new JComboBox<String>(slectionItem);
		
		JLabel fit = new JLabel("Fitness");
		String[] fitnessItem = {"Default", "Match Ideal", "Consecutive"};
		JComboBox<String> fitnessBox = new JComboBox<String>(fitnessItem);
	
		JLabel over = new JLabel("Crossover?");
		JCheckBox overCheck = new JCheckBox();
		
		JLabel popSize = new JLabel("Population Size");
		popField = new JTextField();
		popField.setText("100");
		popField.setColumns(4);
		
		JLabel generations = new JLabel("Generations");
		JTextField genField = new JTextField();
		genField.setText("101");
		genField.setColumns(4);
		
		JLabel genLength = new JLabel("Genome Length");
		JTextField genLenField = new JTextField();
		genLenField.setText("100");
		genLenField.setColumns(4);
		
		JLabel elitism = new JLabel("Elitism (number)");
		JTextField eliteField = new JTextField();
		eliteField.setColumns(4);
		eliteField.setText("0");
		art = new EvolutionGraphics();
		
		controlEvolution = new JButton("Start Evolution");
		/*
		 * this is the button to start the loop
		 */
		controlEvolution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (controlEvolution.getText().equals("Start Evolution")) {
					
					elitismInt = Integer.valueOf(eliteField.getText());
					rateInt = Integer.valueOf(mutateField.getText());
					crossover = overCheck.isSelected();	
					System.out.println(rateInt);
					
					if (!declared) {
						
						fitnessType = fitnessBox.getSelectedIndex();
												
						try {
							icc = idealChromosomeViewer.getChromosomeComponent();
							populationSize = Integer.valueOf(popField.getText());
							genomeLength = Integer.valueOf(genLenField.getText());
							
							generationInt = Integer.valueOf(genField.getText());
							
						} catch(NumberFormatException e) {
							System.err.println("ERROR: Need to input a number");
							System.err.println("DEFAULT PARAMETERS ARE SET");
							populationSize = 100;
							genomeLength = 100;
							elitismInt = 0;
							generationInt = 100;
							rateInt = 1;
						}
						
						if (fitnessType == 2 && genomeLength != icc.getChromosome().getGenomeLength())
							throw new IndexOutOfBoundsException();

						initial = false;
						pop = new Population(populationSize, genomeLength, rateInt, elitismInt, crossover);
						declared = true;
						
						if(populationViewer != null)
							populationViewer.getFrame().dispose();
						if(mostFitViewer != null)
							mostFitViewer.getFrame().dispose();
						
						populationViewer = new ChromosomeViewer(pop.getChromosomes());
						mostFitViewer = new ChromosomeViewer(pop.getFittestChromosome());
						
					}
					
					pop.setElitism(elitismInt);
					pop.setRate(rateInt);
					pop.setCrossover(crossover);
					controlEvolution.setText("Pause");
					timer.start(); //starts timer, which will repeat the command startEvolution multiple times
				}
				else {
					timer.stop();
					controlEvolution.setText("Start Evolution");
				}				
			}
		});
		
		//Add all theJPanels
		dataPanel.add(rate);
		dataPanel.add(mutateField);
		dataPanel.add(select);
		dataPanel.add(selectBox);
		dataPanel.add(fit);
		dataPanel.add(fitnessBox);
		dataPanel.add(over);
		dataPanel.add(overCheck);
		dataPanel.add(popSize);
		dataPanel.add(popField);
		dataPanel.add(generations);
		dataPanel.add(genField);
		dataPanel.add(genLength);
		dataPanel.add(genLenField);
		dataPanel.add(elitism);
		dataPanel.add(eliteField);
		dataPanel.add(controlEvolution);
		
		GUIFrame.add(art);
		GUIFrame.pack();
		GUIFrame.setVisible(true);
	}
	
	/******************************************************************************************************************************
	 *
	 */
	private void startEvolution() {
		
		pop.calculateFitness(fitnessType, icc.getChromosome());
		pop.sortPopulation();
		
		populationViewer.updatePopulation(pop.getChromosomes());
		mostFitViewer.updateMostFit(pop.getFittestChromosome());
		displayImportantData();

		if (generationInt==pop.getCurrentGen() || ((double) pop.getHighestFitness()/genomeLength) * 100>99) {
			//System.out.println(generationInt);
			controlEvolution.setText("Start Evolution");
			declared = false;
			timer.stop();
		}

		pop.evolutionaryLoop();
	}
	
	private void displayImportantData() {
		double max = (double) pop.getHighestFitness()/ genomeLength;
		double min = (double) pop.getLowestFitness()/ genomeLength;
		double avg = (double) pop.getAvgFitness()/ genomeLength;
		double diversity = (double)pop.measureDiversity()/genomeLength;
		double[] importantData = {max, avg, min,diversity};

		if (!initial) {
			initial = true;
			art.initialStats(importantData,generationInt);
		} else
			art.displayStats(importantData);
	}
	
	//Starts the evolution
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.startEvolution();
	}
	
	public static void main(String[] args) {
		EvolutionGUI evo = new EvolutionGUI();
		evo.displayGUI();
	}
	
}