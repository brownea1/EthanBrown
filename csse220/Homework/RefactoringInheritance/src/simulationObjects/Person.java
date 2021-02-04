package simulationObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;

/**
 * This class represents a Person who can either be healthy (green), infected (orange), or recovered (pink).
 * Once a Person becomes infected, they will remain infected for a certain length of time before they
 * become recovered. Once recovered, the Person cannot be infected again.
 * 
 * Inspired by this article: https://www.washingtonpost.com/graphics/2020/world/corona-simulator/
 * 
 * To visualize a population of Persons over time you should provide a method that says if a Person 
 * isHealthy, isSick, or isRecovered.
 * 
 * 
 * This class should use inheritance to make it simple to write. You should not need to copy and paste
 * 
 * TODO: Part 4 You can change this file but will need to provide each of the methods started in order to get full 
 * functionality.
 * 
 * @author Ethan Brown
 *
 */
public class Person extends SimObject{
	
	public static final int PERSON_RADIUS = 10;
	public static final int PERSON_SPEED = 5;
	
	//How many timesteps to recover once infected
	public static final int TIME_TO_RECOVER = 100;
	
	public static final Color HEALTHY_COLOR = new Color(134, 184, 184); 	//state = 0
	public static final Color INFECTED_COLOR = new Color(204, 102, 0);  	//state = 1
	public static final Color RECOVERED_COLOR = new Color(196, 116, 195);	//state = 2
	
	
	//TODO: add instance variables
	private int state;
	private int countdown;
	
	
	//TODO: add constructors
	public Person(int x, int y, int s) {
		super(x, y, PERSON_RADIUS, PERSON_SPEED, INFECTED_COLOR);
		state = s;
		
		if(isInfected())
			countdown = TIME_TO_RECOVER;
		else
			countdown = 0;
		normalizeVelocity();
	}

	public void update(Dimension2D dim) {
		super.update(dim);
		
		if(isHealthy())
			setColor(HEALTHY_COLOR);
		if(isInfected()) {
			setColor(INFECTED_COLOR);
			countdown --;
			
			if(countdown <= 0) {
				state = 2;
			}
			
		}
		if(isRecovered()) 
			setColor(RECOVERED_COLOR);
			
		
	}
	
	public void drawOn(Graphics2D g) {
		//USE COLOR CONSTANTS to draw people with different statuses
		if(isHealthy())
			setColor(HEALTHY_COLOR);
		if(isInfected())
			setColor(INFECTED_COLOR);
		if(isRecovered())
			setColor(RECOVERED_COLOR);
		
		super.drawOn(g);
	}
	
	public void bounce(Person other) {
		//TODO Infect healthy people when they bounce off of infected people
		super.bounce(other);
		
		checkIfInfected(other);
	}
	
	private void checkIfInfected(Person other) {
		if(this.isHealthy() && other.isInfected()) {
			this.state = 1;
			countdown = TIME_TO_RECOVER;
		}
	}
	
	public boolean overlapsWith(Person other) {
		// TODO return true if the person is within range of another
		return super.overlapsWith(other);
	}

	public boolean isHealthy() {
		return state == 0;
	}

	public boolean isInfected() {
		return state == 1;
	}
	
	public boolean isRecovered() {
		return state == 2;
	}

	public int getState() { return state; }


}
