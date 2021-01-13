import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestThisClassTest {
	
	@Test
	public void testNumberOfXs() {
		String toTest;
		
		/*
		 * I tested for an edge case of a string with no Xs
		 * 
		 * this test passed
		 */
		toTest = "this does not have that letter";
		assertEquals(TestThisClass.numberOfXs(toTest), 0);
		
		/*
		 * This is different because I tested to see
		 * if the function counts an x at the begging and end
		 * 
		 * this test passed
		 */
		toTest = "x at the beginning and one at the end x";
		assertEquals(TestThisClass.numberOfXs(toTest), 2);
		
		/*
		 * This test is different because I tried both a capital
		 * and a lower case x
		 * 
		 * this test failed because it counted the character 'y' as an x
		 */
		toTest = "Try having a capital X and also a lowercase x";
		assertEquals(TestThisClass.numberOfXs(toTest), 2);
		
		/*
		 * This test is different because I used multiple x's next to each
		 * other to see if the function skipped over after counting an x
		 * 
		 * this test failed because it counted the character 'y' as an x
		 */
		toTest = "xXXx try testing multiple x's together xxXX Xx X";
		assertEquals(TestThisClass.numberOfXs(toTest), 12);
	}

	@Test
	public void testCountChocula() {
		String toTest;
		
		/*
		 * This tests the special case of chocula instead of Chocula
		 * 
		 * This test passes
		 */
		toTest = "chocula";
		assertEquals(TestThisClass.countChocula(toTest), 0);
		
		/*
		 * This tests to see if the function counts a Chocula at the end
		 * 
		 * This test fails because the loop ends before the last Chocula can be counted
		 */
		toTest = "Test if Chocula can be in the middle and end Chocula";
		assertEquals(TestThisClass.countChocula(toTest), 2);
		
		/*
		 * This tests to see if Chocula can be read in between 2 words
		 * 
		 * This test passes
		 */
		toTest = "choculaChoculachocula";
		assertEquals(TestThisClass.countChocula(toTest), 1);
		
		/*
		 * This tests to see if it will count a censored version of Chocula
		 * 
		 * This test passes
		 */
		toTest = "See if Choc*la will work";
		assertEquals(TestThisClass.countChocula(toTest), 0);
		
		
		
	}

	@Test
	public void testPaintCost() {
		/*
		 * These tests to see if paintCost will return -1
		 * if one of the input parameters are negative
		 * 
		 * All three of these tests pass
		 */
		assertEquals(TestThisClass.paintCost(-1, 10, 7), -1);
		assertEquals(TestThisClass.paintCost(1, -10, 7), -1);
		assertEquals(TestThisClass.paintCost(1, 10, -7), -1);
		
		/*
		 * This tests to make sure that the program returns -1
		 * if 0 is one of the parameters, since 0 is not a positive number
		 * 
		 * This test fails because when checking the minimum value,
		 * The conditional is true only if the smallest number is LESS
		 * than 0
		 */
		assertEquals(TestThisClass.paintCost(0, 10, 7), -1);
		
		/*
		 * This is a test with just normal numbers to make sure
		 * all the calculations are correct
		 * 
		 * This test passes
		 */
		assertEquals(TestThisClass.paintCost(3, 7, 10), 210);
		
		/*
		 * This tests to make sure that paintCost returns -1 if any of the
		 * parameters are negative. This is different from the first test because
		 * if all these numbers are multiplied together, then the result will be positive
		 * 
		 * This test passes
		 */
		assertEquals(TestThisClass.paintCost(-3, -10, 7), -1);
		
	}

	@Test
	public void testPizzaCostCalculator() {
		ArrayList<String> list = new ArrayList<String>();
		
		/*
		 * This tests to make sure the cost of the pizza is correct
		 * if there are no toppings on it
		 * 
		 * This test passes
		 */
		assertEquals(TestThisClass.pizzaCostCalculator(list), 8); //list: {}
		
		/*
		 * This tests to make sure that the pizza is still considered a special
		 * even if the toppings aren't placed in the right order in the list
		 * 
		 * This test passes
		 */
		list.add("onions");
		list.add("sausage");
		list.add("pepperoni");
		assertEquals(TestThisClass.pizzaCostCalculator(list), 12); //list: {"onions", "sausage", "pepperoni"}
		
		
		
		/*
		 * Tests to see if you will still get the special price if the array contains
		 * all the special toppings
		 * 
		 * This test passes
		 */
		list.add("chicken");
		list.add("olives");
		assertEquals(TestThisClass.pizzaCostCalculator(list), 15); //list: {"onions", "sausage", "pepperoni", "chicken", "olives"}
		
		/*
		 * This tests to make sure that the price of a special pizza is not returned
		 * if only some of the special toppings are ordered for the pizza
		 * 
		 * This test passes
		 */
		list.remove("sausage");
		list.remove("pepperoni");
		list.remove("chicken");
		list.remove("olives");
		assertEquals(TestThisClass.pizzaCostCalculator(list), 10); //list: {"onions"}
		
		
	}

}
