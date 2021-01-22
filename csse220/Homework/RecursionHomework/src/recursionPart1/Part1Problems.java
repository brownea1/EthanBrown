package recursionPart1;
import java.util.ArrayList;


/**
 * Class: Part1Problems
 * @author CSSE Faculty
 * Purpose: Provide practice implementing basic recursive operations
 *  
 */
public class Part1Problems {
	
	/**
	 * Recursive summation of one to the input value.
	 * 
	 * So for example summation(3) = 3 + 2 + 1 = 6
	 * @param value starting number for summation
	 * @return sum of 1 to the given value
	 * 
	 */
	public static int summation(int value){
		if(value == 1)
			return 1;

		return value + summation(value-1);
	} // summation
	
	/**
	 * Outputs a string of 'Buffalo ' a certain number of times.
	 * 
	 * For example technicallyGramaticallyCorrect(2) returns "Buffalo Buffalo "
	 * 
	 * @param numberOfBuffalo - number of times "Buffalo " ought to be repeated
	 * @return - complete string of Buffalo
	 * 
	 */
	public static String technicallyGramaticallyCorrect(int numberOfBuffalo){
		if(numberOfBuffalo < 1)
			return "";
		
		return "Buffalo " + technicallyGramaticallyCorrect(numberOfBuffalo - 1);
	} // technicallyGramaticallyCorrect
	
	/**
	 * Removes all of the a's and b's from a string and replaces the capital C's with QQQ then returns the string.
	 * 
	 * For example the string weirdoString("aBCd") returns "BQQQd"
	 *
	 * Do NOT use the replace method on the String class! Using this method is NOT a recursive solution.
	 * 
	 * @param stringInput to do replaces/removes on
	 * @return a new string with removals and replacements
	 */
	public static String weirdoString(String stringInput){
		if(stringInput.length()==0)
			return "";
		
		String temp = "" + stringInput.charAt(0);
		if(temp.equals("a") || temp.equals("b"))
			temp = "";
		else if(temp.equals("C"))
			temp = "QQQ";
		return temp + weirdoString(stringInput.substring(1));
	} // weirdoString
	
	/**
	 * Sum the elements in an arrayList.
	 * You may modify the list (e.g. remove elements).
	 * 
	 * For example arrayListSummation with the list [1, 2] would return 3
	 * @param inputList  contains numbers for summing
	 * @return sum of values in the list
	 */
	public static int arrayListSummation(ArrayList<Integer> inputList){
		if(inputList.isEmpty())
			return 0;
		
		ArrayList<Integer> temp = new ArrayList<Integer>(inputList.subList(1, inputList.size()));
		return inputList.get(0) + arrayListSummation(temp);
	} // arrayListSummation
	
	/**
	 * Returns the number of threes in the prime factorization of the input value.
	 * 
	 * For example countNumberOf3sInPrimeFactorization(45) returns 2
	 * (because the prime factorization of 45 is 3*3*5)
	 * @param inputNumber used in the computation
	 * @return number of 3s in inputNumber's prime factorization
	 */
	public static int countNumberOf3sInPrimeFactorization(int inputNumber){
		if(inputNumber <= 1)
			return 0;
		if(inputNumber % 3 != 0)
			return 0;
		return 1 + countNumberOf3sInPrimeFactorization(inputNumber/3);
	} // countNumberOf3sInPrimeFactorization
	
	/**
	 * Determine if a string of  (),  [],  {}, and  <> has matched ends.
	 * You can assume that a string always has a bunch of starting brackets, followed by a bunch
	 * of ending brackets (like this "([<>])").  You don't have to worry about strings like this
	 * "()()".  Your function should detect strings where the braces are mismatched e.g. "(]"
	 * or situations where a bracket is missing "(()" or "())"
	 * 
	 * @param inputString consisting of starting brackets and ending brackets 
	 * @return true if the braces were appropriately matched
	 * 
	 */
	public static boolean bracketMatch(String inputString){
		if(inputString.length() == 1)
			return false;
		if(inputString.length() == 0)
			return true;
		
		int first = (int) inputString.charAt(0);
		int last = (int) inputString.charAt(inputString.length() - 1);
		int incriment = 2;
		
		if(first == 40)
			incriment--;
		if((first+incriment) != last)
			return false;
		
		return bracketMatch(inputString.substring(1, inputString.length()-1));
	} // bracketMatch
	
	/**
	 * Determine if a value is an even power of three (3^2, 3^4, 3^6).
	 * You can assume the input value is a power of three.
	 * 
	 * @param inputValue a power of 3
	 * @return true if the input value is an even power of 3, false otherwise
	 */
	public static boolean evenPowerOfThree(int inputValue){
		if(inputValue == 1)
			return true;
		if(inputValue < 9)
			return false;
		
		return evenPowerOfThree(inputValue/9);
	} // evenPowerOfThree
	
	/**
	 * Determine if there are no doubled letters in a String.
	 * 
	 * Example noDoubledLetters("abbd") returns false
	 *         noDoubledLetters("abdb") returns true
	 *         
	 * @param inputString consists of letters
	 * @return true if the string has no doubled letters, false if it has doubled letters
	 */
	public static boolean noDoubledLetters(String inputString){
		if(inputString.length() == 1)
			return true;
		
		char first = inputString.charAt(0);
		char second = inputString.charAt(1);
		if(first == second)
			return false;
		return noDoubledLetters(inputString.substring(1));
	} // noDoubledLetters
	
	/**
	 * Imagine a game where you're given a starting number and can repeatedly make one of two moves.
	 * You can either divide the number by three if it's divisible by three, or subtract seven.
	 * You win the game if you can reduce the number to 1.
	 * 
	 * For example, you can win with 15 because you can subtract 7 twice
	 * (you would also be allowed to divide 15 by 3, but that doesn't work for 15)
	 * 
	 * You can win with 3 because you can divide by 3.  You can win with 10 because you can
	 * subtract 7 and divide it by 3.
	 * 
	 * You can't win with 5 or 6
	 * 
	 * Write a function that returns if the game can be won with a particular starting number.
	 * @param startingNumber game staring number
	 * @return true if you can win the numbers game, false otherwise
	 */
	public static boolean numbersGame(int startingNumber){
		if(startingNumber < 1)
			return false;
		if(startingNumber == 1)
			return true;
		if(startingNumber %3 == 0) {
			if(numbersGame(startingNumber/3))
				return true;
		}
		return numbersGame(startingNumber - 7);
	} // numbersGame
} // end class Part1Problems
