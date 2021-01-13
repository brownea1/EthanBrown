package teamGradebook;
import java.util.ArrayList;

/**
 * A team is an ArrayList of student objects.
 * 
 * Along with an Array List of student objects, a 
 * team also has a teamName and a totalTeamAssignments
 * and totalTeamPoints. totalTeamAssignments and totalTeamPoints
 * are used to calculate the total overall grade for each team
 * 
 * @author brownea1
 *
 */
public class Team {
	
	// You'll need to add fields, constructors, and methods here
	private ArrayList<Student> students;
	private String teamName;
	private int totalTeamAssignments;
	private double totalTeamPoints;
	
	public Team(String n, ArrayList<Student> s) {
		students = s;
		teamName = n;
		totalTeamAssignments = 0;
		totalTeamPoints = 0;
	}
	
	public void addTeamMember(Student s) {
		students.add(s);
	}
	
	public ArrayList<Student> getTeamMembers(){
		return students;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String n) {
		teamName = n;
	}
	
	/**
	 * Each addTeamGrade adds just one assignment grade
	 * to the team. The function is called when someone from
	 * a team gets a grade added. addTeamGrade increments
	 * totalTeamAssignments by one each time it's called
	 * and adds g to the totalTeamPoints
	 * 
	 * @param g
	 */
	public void addTeamGrade(double g) {
		totalTeamAssignments++;
		totalTeamPoints += g;
	}
	
	/**
	 * Returns the average grade, which is computed
	 * by taking the totalTeamPoints and dividing
	 * it by the totalTeamAssignments
	 * 
	 * @return 0 if the team has no assignments, else the average grade
	 */
	public double getTeamGrade() {
		if(totalTeamAssignments == 0)
			return 0;
		
		return totalTeamPoints / totalTeamAssignments;
	}

	@Override
	public String toString() {
		return "Team [students=" + students + ", teamName=" + teamName + ", totalTeamAssignments="
				+ totalTeamAssignments + ", totalTeamPoints=" + totalTeamPoints  + ", avgGrade=" + this.getTeamGrade() + "]";
	}

}
