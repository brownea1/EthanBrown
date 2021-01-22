package implementingDP2;
import java.util.ArrayList;

/**
 * Class: Quiz
 * @author brownea1
 *
 * Purpose: To hold a list of questions that are on the quiz, and to hold
 * an id to identify the quiz
 * Example:
 * 		Quiz(12);
 */
public class Quiz {
	private ArrayList<Question> questions;
	private int id;
	
	/**
	 * ensures: Initializes this.id to id, and creates a
	 * new, empty ArrayList of Questions
	 * @param id
	 */
	public Quiz(int id) {
		this.id = id;
		questions = new ArrayList<Question>();
	}
	
	/**
	 * ensures: Adds the parameter 'q' to the this Quiz's ArrayList of questions
	 * @param q used to populate the questions list
	 */
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	/**
	 * ensures: That toString is overriden
	 * @return str the String version of the Quiz class
	 */
	@Override
	public String toString() {
		String str = "";
		for(int i=0; i<questions.size(); i++){
			Question q = questions.get(i);
			str += "Question [" +Integer.toString(q.getId())+ "]\t";
			str += q.getPrompt();
			str += "\n";
		}
		return str;
	}
	
	/**
	 * ensures: that the size of questions is returned
	 * @return the current size of the questions ArrayList
	 */
	public int getNumQuestions() {
		return questions.size();
	}
	
	//Getters
	public int getId() { return id; }
	public ArrayList<Question> getQuestoins() { return questions; }
	
	//Setters
	public void setId(int id) { this.id = id; }	
}
