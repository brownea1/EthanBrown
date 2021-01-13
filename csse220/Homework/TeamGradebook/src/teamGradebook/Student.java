package teamGradebook;

//import java.util.ArrayList;

/**
 * A single student, which has a name, number of absences, totalPoints and totalAssignments
 * totalPoints and totalAssignments are used to calculate avgGrade
 * The class has getters and setters for it's fields, except totalPoints and totalAssignments
 * because the getter instead returns the average grade
 * 
 * @author brownea1
 *
 */
public class Student {
	private String name;
	private int absences;
	private double totalPoints;
	private int totalAssignments;

	/**
	 * ensures: creates a new student object
	 * 
	 * @param newName is the name of the student requires: newName to be unique
	 *                among all student objects
	 */
	public Student(String newName) {
		name = newName;
		totalPoints = 0;
		totalAssignments = 0;
		absences = 0;
	}

	/**
	 * ensures: returns the name of the student
	 * 
	 * @return the name of the student
	 */
	public String getName() {
		return name;
	}

	/**
	 * ensures: returns average grade of the student
	 * 
	 * @return the average grade of the student
	 */
	public double getAverageGrade() {
		if (totalAssignments == 0)
			return 0;
		else
			return totalPoints / totalAssignments;
	}

	/**
	 * ensures: returns number of absences for the student
	 * 
	 * @return the number of absences for the student
	 */
	public int getAbsences() {
		return absences;
	}

	/**
	 * adds one to current student's absence count
	 * 
	 * @return void
	 */
	public void addAbsence() {
		absences++;
	}

	/**
	 * Changes the name of the student to the new given name
	 * 
	 * @param name
	 * @return void
	 */
	public void changeName(String newName) {
		name = newName;
	}

	/**
	 * Adds a new grade for the student, which adds 1 to the totalAssignments and
	 * increases totalPoints by num
	 * 
	 * @param num
	 * @return void
	 */
	public void addGrade(double num) {
		totalPoints += num;
		totalAssignments++;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", absences=" + absences + ", totalPoints=" + totalPoints
				+ ", totalAssignments=" + totalAssignments + ", avgGrade=" + this.getAverageGrade() + "]";
	}

}
