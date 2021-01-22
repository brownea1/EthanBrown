package implementingDP2;

/**
 * Class: Question
 * @author brownea1
 * Purpose: To hole a question prompt, an answer for
 * that question and an id for the question
 * For Example:
 * 		Question(6, false, "Is the sun green?")
 *
 */
public class Question {
	private int id;
	private boolean answer;
	private String prompt;
	
	/**
	 * ensures: Initializes this.id to id, answer to a and prompt to str
	 * @param id
	 * @param a
	 * @param str
	 */
	public Question(int id, boolean a, String str) {
		this.id = id;
		answer = a;
		prompt = str;
	}
	
	//Getters
	public int getId() { return id; }
	public boolean getAnswer() { return answer; }
	public String getPrompt() { return prompt; }
	
	//Setters
	public void setId(int id) { this.id = id; }
	public void setAnswer(boolean a) { answer = a; }
	public void setPrompt(String str) { prompt = str; }
	
	

}
