package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChromosomeViewer {
	public static final int GENE_SIZE = 30;
	public static final int GENE_SPACING = 1;
	private static final String DEFAULT_IDEAL_CHROMOSOME_PATH = "initial100.txt";
	
	
	private int viewerType; //0 = mostFit, 1 = ideal, 2 = population
	private int geneSize;
	private int geneSpacing;
	private String frameName;
	private JFrame frame;
	
	private Chromosome chromosome;
	private ChromosomeComponent cc;
	private boolean[] genes;
	private ArrayList<Chromosome> population;
	private ArrayList<ChromosomeComponent> components;

	/*
	 * Most fit constructor
	 */
	public ChromosomeViewer(Chromosome c) {
		viewerType = 0;
		frameName = "Individual Viewer";
		chromosome = c;
		genes = chromosome.getGenes();
		geneSize = GENE_SIZE;
		geneSpacing = GENE_SPACING;
		
		cc = new ChromosomeComponent(c);
		cc.setPreferredSize(calculateSize());
		
		displayChromosome();
	}
	/*
	 * Ideal Chromosome constructor
	 */
	public ChromosomeViewer() {
		viewerType = 1;
		frameName = DEFAULT_IDEAL_CHROMOSOME_PATH;
		loadInitial();
		chromosome = cc.getChromosome();
		genes = chromosome.getGenes();
		geneSize = GENE_SIZE;
		geneSpacing = GENE_SPACING;
		
		displayChromosome();
	}
	/*
	 * Population Constructor
	 */
	public ChromosomeViewer(ArrayList<Chromosome> c) {
		viewerType = 2;
		population = c;
		geneSize = 4;
		geneSpacing = 0;
		frameName = "Population Viewer";
		components = new ArrayList<ChromosomeComponent>();
		
		for(Chromosome e: population)
			e.setSizeAndSpacing(geneSize, geneSpacing);
			
		displayChromosome();
	}

	public void displayChromosome() {
		frame = new JFrame(frameName);
		
		// Add the Chromosome to the Frame
		if(viewerType != 2)
			frame.add(cc);

		if(viewerType == 0) {
			cc.setPreferredSize(new Dimension(350, 350));
			chromosome.setSizeAndSpacing(geneSize, geneSpacing);
		}
		if(viewerType==1)
			displayIdealChromosome();
		if(viewerType == 2) {
			displayPopulation();
			frame.setPreferredSize(new Dimension(600, 600));
		}
			
		frame.setTitle(frameName);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void displayPopulation() {
		JPanel popPanel = new JPanel();
		
		for(Chromosome c: population) {
			ChromosomeComponent temp = new ChromosomeComponent(c);
			genes = c.getGenes();
			temp.setPreferredSize(calculateSize());
			components.add(temp);
			popPanel.add(temp);
		}
		
		frame.add(popPanel);
	}
	
	public void updateMostFit(Chromosome c) {
		chromosome = new Chromosome(c);
		genes = chromosome.getGenes();
		chromosome.setSizeAndSpacing(geneSize, geneSpacing);
		
		cc.setChromosome(chromosome);
		cc.repaint();
	}
	
	public void updatePopulation(ArrayList<Chromosome> chromes) {
		for(int i=0; i<chromes.size()&&i<components.size(); i++) {
			chromes.get(i).setSizeAndSpacing(geneSize, geneSpacing);
			components.get(i).setChromosome(chromes.get(i));
			components.get(i).repaint();
		}
	}
	
	private Dimension calculateSize() { 
		int x = 2;
		int y = 2;
		int index = 0;
		
		for(int i=0; i<genes.length/10; i++) {
			x = 2;
			for(int j=0; j<10 && index < genes.length; j++) {
				index++;
				x += geneSize + geneSpacing;
			}
			y += geneSize + geneSpacing;
		}
		
		return new Dimension(x, y);
	}
	
	private void displayIdealChromosome() {
		// Make a panel for the buttons at the bottom of frame
		JPanel buttonPanel = new JPanel();
		JPanel loadSaveButtonPanel = new JPanel();
		frame.add(buttonPanel, BorderLayout.SOUTH);

		// Make JComponents for buttonPanel
		JButton mutate = new JButton("mutate");
		JButton load = new JButton("load");
		JButton save = new JButton("save");
		JTextField textField = new JTextField();

		// Setting up the textField
		textField.setColumns(6);
		textField.setToolTipText("M Rate: x/N");

		// Adding Action Listeners to Each JComponent
		mutate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int rate = Integer.parseInt(textField.getText());
					cc.setMRate(rate);
					mutate();
					cc.repaint();
				}
				catch(Exception e)
				{
					System.out.println("Type a rate");
				}
			}
		});
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rate = Integer.parseInt(textField.getText());
				cc.setMRate(rate);
			}
		});

		// Adding Buttons to the panels
		buttonPanel.add(mutate);
		buttonPanel.add(textField);
		loadSaveButtonPanel.add(load, BorderLayout.WEST);
		loadSaveButtonPanel.add(save, BorderLayout.EAST);
		buttonPanel.add(loadSaveButtonPanel, BorderLayout.SOUTH);

		// Add all chromosome buttons
		int x = 2, y = 2;
		int length = cc.getChromosome().getGenomeLength();
		int index = 0;

		for (int i = 0; i < (length/10) + 1; i++) {
			for (int j = 0; j < 10 && index < length; j++) {
				JButton b = new JButton();
				b.setBounds(x, y, geneSize, geneSize);
				genes = cc.getChromosome().getGenes();// altered this code-Andrew

				Integer tempIndex = new Integer(index);

				// Add ActionListener
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						genes[tempIndex] = !genes[tempIndex];
					}
				});

				// Makes Button Transparent
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setBorderPainted(false);

				cc.add(b);

				x += geneSpacing + geneSize;
				index++;
			}
			x = 2;
			y += geneSpacing + geneSize;
		}
		cc.setPreferredSize(new Dimension(350, y));
	}
		
	private void mutate() {
		chromosome.mutate();
		frameName += " (mutated) ";
		frame.setTitle(frameName);
	}
	private void loadInitial() {	
		String path ="";

		File file = new File("text//" + frameName);
		Scanner fileIn;
		
		try {
			fileIn = new Scanner(file);
			path = fileIn.next();
			boolean[] genes = new boolean[path.length()];
			fileIn.close();
			
			for (int k=0; k<path.length();k++)
				genes[k] = path.charAt(k) == '1';
			
			cc = new ChromosomeComponent(genes);
			
		} catch (Exception e) {
			System.out.println("File not found!!!!");
		}
	}
	private void load() {
		JFileChooser fc = new JFileChooser();
		File file;
		Scanner fileIn;
		int result = -99;
		String path = "";
		fc.setCurrentDirectory(new java.io.File("text"));
		fc.setDialogTitle("Load");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			try {

				fileIn = new Scanner(file);
				path = fileIn.next();
				fileIn.close();
				frame.dispose();
				boolean[] genes = new boolean[path.length()];
				
				for (int k=0; k<path.length();k++)
					genes[k] = path.charAt(k) == '1';
				
				frameName = file.getName();
				cc = new ChromosomeComponent(genes);
				displayChromosome();
			} catch (Exception e) {
				System.out.println("File not found!!!!");
			}
		}
	}
	private void save() {
		String save = "";
		for (int k = 0; k < chromosome.getGenes().length; k++) {
			if (chromosome.getGenes()[k]) {
				save = save + "1";
			} else {
				save = save + "0";
			}
		}

		JFileChooser fc = new JFileChooser();
		int result = -99;

		String fileName = "";
		fc.setCurrentDirectory(
				new java.io.File("text"));
		fc.setDialogTitle("Save");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		result = fc.showSaveDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			File file = fc.getSelectedFile();

			if (!file.getName().toLowerCase().endsWith(".txt")) {
				fileName = fc.getSelectedFile() + ".txt";
				file = new File(fileName);
			}

			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(save);
				bw.close();
				file.createNewFile();

			}

			catch (IOException e) {
				System.err.println("Error opening the file" + fileName);
				System.exit(0);
			}

		}
		fileName = fc.getSelectedFile().getName();
		frame.setTitle(fileName);
	}

	public ChromosomeComponent getChromosomeComponent() { return cc; }
	public JFrame getFrame() { return frame; }
	
}
