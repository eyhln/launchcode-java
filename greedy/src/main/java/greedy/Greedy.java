package greedy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Greedy {
	
	 public static void main(String[] varArgs) {
		 ApplicationContext context = 
				 new ClassPathXmlApplicationContext("application-context.xml");

		 Greedy greedy = (Greedy)context.getBean("greedy");
		 greedy.start();
	    }
	
	Scanner scanner; 
	CoinCalculator coinCalculator;
	NumberFormat defaultLocaleCurrencyFormat = NumberFormat.getCurrencyInstance();

	public Greedy(CoinCalculator coinCalculator) {
		this.coinCalculator = coinCalculator;
		scanner = new Scanner(System.in);
	}
	
	public void start() {
		try {
			runProgram();
		} catch (ParseException e) {
			System.out.println("invalid.");
		}
	}
	
	private void runProgram() throws ParseException {
		greetUser();
		int moneyValueInCents = getInput();
	}
	
	void greetUser() {
		System.out.println("Enter an amount of money or press ESC to quit.");
	}
	
	int getInput() throws ParseException {
		System.out.println("Amount: ");
		String input = scanner.nextLine();
		return parseInput(input);
	}
	
	int parseInput(String input) throws ParseException {
		input = input.trim();
		Number amountOfMoney = defaultLocaleCurrencyFormat.parse(input);
		double moneyValue = amountOfMoney.doubleValue();
		int moneyValueInCents = (int)Math.round(moneyValue * 100); 
		return moneyValueInCents;
	}
   

}
