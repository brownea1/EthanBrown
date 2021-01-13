/**
  * 
  * TODO (1) Implement this class and (2) Document using Javadoc comments as well as regular comments
  *
  * If you are running a recent version of Eclipse, you can command Eclipse to generate the Javadoc .html file
  * by using the command from the menu bar: Project | Generate Javadoc...
  * 
 */
public class Question {
	private int id;
	private String data;
	
	public Question(int id, String data) {
		this.id = id;
		this.data = data;
	}

	//Getters and Setters for id and data
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
