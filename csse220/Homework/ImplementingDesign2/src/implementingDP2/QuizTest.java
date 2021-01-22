package implementingDP2;
import java.util.ArrayList;
/**
 * Class: QuizTest
 * @author brownea1
 * Purpose: to hold an id and a target String for the QuizTest
 * and to take the quizzes and determine if their guess is correct
 */
public class QuizTest {
	private int id;
	private String target;
	
	/**
	 * ensures: this.id is set to id and that the target is set to t
	 * @param id
	 * @param t
	 */
	public QuizTest(int id, String t) {
		this.id = id;
		target = t;
	}
	
	/**
	 * ensures: the average quiz score is calculated, with help
	 * from getQuizScore method in this class
	 * @param list The list of the quizzes QuizTest needs to take
	 * @return The average score of all of the Quiz's in list
	 */
	public double getOverallScore(ArrayList<Quiz> list) {
		double totalQuizScore = 0;
		
		for(int i=0; i<list.size(); i++) {
			totalQuizScore += getQuizScore(list.get(i));
		}
		
		if(list.size() == 0) 
			return 0;
		
		return totalQuizScore / (double) list.size();
	}
	
	/**
	 * ensures: The score for the quiz is calculated and the average
	 * grade out of 1.0 is returned
	 * @param quiz the Quiz that is being examined and graded
	 * @return the average grade for the Quiz
	 */
	public double getQuizScore(Quiz quiz) {
		ArrayList<Question> questions = quiz.getQuestoins();
		double score = 0;
		
		for(int i=0; i<questions.size(); i++) {
			Question q = questions.get(i);
			String str = q.getPrompt();
			
			score += (guess(str) == q.getAnswer()) ? 1 : 0;
		}
		
		if(questions.size() == 0)
			return 0;
		
		return score / (double) questions.size();
	}
	
	/**
	 * ensures: if the target string contains the QuizTest target
	 * @param str 
	 * @return If str contains QuizTest target String, then the
	 * guess is true, else the guess is false
	 */
	private boolean guess(String str) {
		return str.contains(target);
	}
	
	//Getters
	public int getId() { return id; }
	public String getTarget() { return target; }
	
	//Setters
	public void setId(int id) { this.id = id; }
	public void setTarget(String t) {target = t; }
	

}
