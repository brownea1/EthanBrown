import java.util.ArrayList;
/**
 * Class: Quiz
 * @author brownea1
 *
 * Purpose: To hold a quiz's id and a list of quiz questions (id, questions)
 * Example:
 * 		Quiz exam = new Quiz(2);
 * 
 * A quiz is distinguished by it's id. Each quiz has a list
 * of questions that will be on the quiz. =
 */
public class Quiz {
	private int id;
	private ArrayList<Question> questions;
	
	public Quiz(int id) {
		this.id = id;
		questions = new ArrayList<Question>();
	}
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	public int getId() {
		return id;
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}

}
