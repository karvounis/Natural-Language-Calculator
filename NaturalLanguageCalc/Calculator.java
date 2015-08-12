import java.util.Scanner;
import java.util.Stack;

/**
 * This class is responsible of getting user input and calculating the result of the input.
 * @author Evangelos Karvounis
 *
 */
public class Calculator {

	private static final String[] NUMBERS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private String[] splitUserInput;
	/**User input as numbers and symbols*/
	private String sequenceNum;

	/**
	 * This method gets the user's input and splits it to its consisting words.
	 */
	public void getUserInput(){

		String userInput = null;
		Scanner sc = new Scanner(System.in);
		boolean done = false;

		while(!done){
			System.out.println("Please enter a calculation: ");
			
			//Reads the user's input
			userInput = sc.nextLine();

			if(userInput != null && userInput.length() > 2){
				//Checks the user input to see if it is valid.
				String checkInp = userInput.trim().toLowerCase().replaceAll("(zero|one|two|three|four|five|six|seven|eight|nine|"
						+ "add|plus|subtract|minus|less|multiply-by|times|divide-by|over)", "").trim();
				
				//If the length is not 0, then the input is invalid.
				if(checkInp.length()==0){
					//Splits the user's input
					splitUserInput = userInput.trim().toLowerCase().split(" ");
				}else{
					System.out.println("Input is not correct. Please follow the guidelines.");
					continue;
				}
				
				break;
			}			
		}
	}

	/**
	 * Analyzes the user's input. Transforms it from natural language to mathematical notation.
	 */
	public void analyzeUserInput(){
		//Stringbuilder to build the mathematical expression
		StringBuilder str = new StringBuilder();

		for(String spl : splitUserInput){
			if(spl != null && spl.length() > 0){
				String word = spl.toLowerCase();

				//Switch statements for the words in the user input
				switch(word){
				case "add":
				case "plus":
					str.append("+");
					break;
				case "substract":
				case "minus":
				case "less":
					str.append("-");
					break;
				case "multiply-by":
				case "times":
					str.append("*");
					break;
				case "divide-by":
				case "over":
					str.append("/");
					break;
				default:
					//Default is that the word is a number.
					//indexOfWord(String) Return value will be used as the number to be appended to the sequence. 
					str.append(indexOfWord(word));
					break;
				}
			}
		}
		
		sequenceNum = str.toString();
	}

	/**
	 * Gets the word for a number and checks every word in the NUMBERS array. When two words are equal, then the number is the index of the word.
	 * @param word 
	 * @return Mathematical number
	 */
	private int indexOfWord(String word){
		for(int i = 0; i < NUMBERS.length; i++){
			if(word.equalsIgnoreCase(NUMBERS[i])){
				return i;
			}
		}
		return 0;
	}

	/**
	 * Calculates the result of the sequence. First, creates a Stack and pushes the first number on it (if the first character is '-' the negative number is pushed).
	 * Then, it has the follow rules:
	 * <ul><li>If the next operation is addition or subtraction, then it pushes the next number to the stack as a positive or negative respectively.</li>
	 * <li>If the next operation is multiply, it pops the last value of the stack, performs the multiplication with the next number and then pushes the product to the stack.</li>
	 * <li>If the next operation is division, it pops the last value of the stack, performs the division with the next number and then pushes the result to the stack.</li></ul>
	 * @return The result as Double
	 */
	public Double calculateResult(){
		Stack<Double> stack = new Stack<Double>();
		int counter = 0;
		
		//Pushes the first number
		if(sequenceNum.charAt(counter) == '-'){
			stack.push((-1) * Double.parseDouble(sequenceNum.charAt(counter + 1) + ""));
		}else{
			stack.push(Double.parseDouble(sequenceNum.charAt(counter) + ""));
		}		
		
		counter++;
		
		//The problem of having a symbol at the end by accident is solved by looping until the prelast element
		while(counter < sequenceNum.length() - 1){

			char temp = sequenceNum.charAt(counter);
			
			if(temp == '+'){
				//If addition, pushes the next positive number.
				stack.push(Double.parseDouble(sequenceNum.charAt(counter + 1) + ""));
			}else if(temp == '-'){
				//If subtraction, pushes the next number as negative.
				stack.push((-1) * Double.parseDouble(sequenceNum.charAt(counter + 1) + ""));
			}else if(temp == '*'){
				//If multiplication, pops the last number.
				Double leftNumber = stack.pop();
				//Gets the next number.
				Double rightNumber = Double.parseDouble(sequenceNum.charAt(counter + 1) + "");

				Double product = leftNumber * rightNumber;
				//Pushes the product.
				stack.push(product);
			}else if(temp == '/'){
				//If division, pops the last number.
				Double leftNumber = stack.pop();
				//Gets the next number.
				Double rightNumber = Double.parseDouble(sequenceNum.charAt(counter + 1) + "");

				Double division = leftNumber / rightNumber;
				//Pushes the result.
				stack.push(division);
			}

			counter++;
		}
		
		Double sum = 0.0;
		//Iterates through all the Doubles in the stack and adds their value
		for(Double num : stack){
			sum += num;
		}

		return sum;
	}
}
