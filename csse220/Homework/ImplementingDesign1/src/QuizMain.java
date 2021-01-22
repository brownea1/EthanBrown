import java.util.ArrayList;
import java.util.HashMap;

/**
  *  This class is used to demonstrate a functional design
  *  involving Quizzes and Questions which can be updated and displayed
  * 
  */
public class QuizMain {
	private ArrayList<Question> questionList;
	private ArrayList<Quiz> quizList;
	
	public QuizMain() {
		// DONE In order to demonstrate functionality, please follow the TODOs below
		// You will have to create questions and quizzes when a QuizMain is created
		questionList = new ArrayList<Question>();
		quizList = new ArrayList<Quiz>();
		
		
		// DONE 1 Create five questions (can be silly/basic questions) use id 1,2,3,4,5 ...
		questionList.add(new Question(1, "Question 1"));
		questionList.add(new Question(2, "Question 2"));
		questionList.add(new Question(3, "Question 3"));
		questionList.add(new Question(4, "Question 4"));
		questionList.add(new Question(5, "Question 5"));
		
	

		// DONE 2 Create three or more quizzes  use id 1,2,3...
		//      (One quiz should share at least one question with another )
		Quiz q1 = new Quiz(1);
		q1.addQuestion(questionList.get(0));
		q1.addQuestion(questionList.get(3));
		quizList.add(q1);
		
		Quiz q2 = new Quiz(2);
		q2.addQuestion(questionList.get(0));
		q2.addQuestion(questionList.get(1));
		q2.addQuestion(questionList.get(4));
		quizList.add(q2);
		
		Quiz q3 = new Quiz(3);
		q3.addQuestion(questionList.get(3));
		q3.addQuestion(questionList.get(2));
		q3.addQuestion(questionList.get(4));
		quizList.add(q3);

		
		
	}
	
	
	
	public static void main(String[] args) {
		//We want to use instance variables of the QuizMain class so we need to construct a QuizMain object
		QuizMain myQuizSimulator = new QuizMain();
		
		// DONE 3 Display three or more different quizzes
		System.out.println("--------------------------------------------------");
		System.out.println("Showing three or more original quizzes:");
		System.out.println("--------------------------------------------------");
		myQuizSimulator.handleDisplayQuiz(1);
		myQuizSimulator.handleDisplayQuiz(2);
		myQuizSimulator.handleDisplayQuiz(3);
		
		
		
		// DONE 4 Change two quiz questions 
		// A. (One should be shared with two or more quizzes)
		// B. (One should be unique to one quiz)
		myQuizSimulator.handleUpdateQuizQuestion(1,"What is different 1?");
		myQuizSimulator.handleUpdateQuizQuestion(2,"What is different 2?");

		
		// DONE 5 Display the same three (or more) quizzes
		//	   A. One that has a unique question which changed
		//	   B. Two which share a question that has been changed		
		System.out.println("--------------------------------------------------");
		System.out.println("Showing three or more changed quizzes:");
		System.out.println("--------------------------------------------------");
		myQuizSimulator.handleDisplayQuiz(1);
		myQuizSimulator.handleDisplayQuiz(2);
		myQuizSimulator.handleDisplayQuiz(3);
		
	}
	
	/**
	 *  This method should display a quiz in a very similar fashion to the output provided
	 *  in exampleOutput.txt, which is located in your repository
	 * 
	 * 
	 * @param quizId
	 */
	public void handleDisplayQuiz(int quizId) {
		//DONE complete this method
		Quiz quizToDisplay = null;
		for(int i=0; i<quizList.size(); i++) {
			Quiz temp = quizList.get(i);
			if(temp.getId() == quizId)
				quizToDisplay = temp;
		}
		
		if(quizToDisplay == null)
			return;
		
		ArrayList<Question> questions = quizToDisplay.getQuestions();
		System.out.println("Quiz: " +quizId);
		for(int i=0; i<questions.size(); i++) {
			Question temp = questions.get(i);
			System.out.println("\tQuestion[" +temp.getId()+ "]: " + temp.getData());
		}
	}
	
	/**
	 * 
	 * This method should replace the data in the question with id=questionId with the new questionData 
	 * 
	 * @param questionId
	 * @param questionData
	 */
	public void handleUpdateQuizQuestion(int questionId, String questionData) {
		Question q;
		for(int i=0; i<questionList.size(); i++) {
			q = questionList.get(i);
			if(q.getId() == questionId)
				q.setData(questionData);
		}
			
	}

}
