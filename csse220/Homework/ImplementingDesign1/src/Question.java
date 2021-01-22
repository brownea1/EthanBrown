/**
 * Class: Question
 * @author brownea1
 * Purpose: To hold all the information for a question (id, data)
 * Example: 
 * 		Question question1 = new Question(6, "What's 9x7?");
 *
 * A question has a question id, which is a good way to 
 * find a question, because each question has a unique id
 * The data contains the actual question
 *
 */
public class Question {
	private int id;
	private String data;
	
	/**
	 * Question object constructor
	 * 
	 * @param id
	 * @param data
	 */
	public Question(int id, String data) {
		this.id = id;
		this.data = data;
	}

	//Getters and Setters for id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	//Getter and setter for data
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
