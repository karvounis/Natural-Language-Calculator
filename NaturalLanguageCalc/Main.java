
public class Main {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		//Loops until the user stops it manually
		while(true){			
			calc.getUserInput();
			calc.analyzeUserInput();
			System.out.println(String.format("Result: %.2f", calc.calculateResult()));
		}
	}
}
