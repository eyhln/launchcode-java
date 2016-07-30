package greedy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

public class Greedy {
	
	 public static void main(String[] varArgs) {
		 ApplicationContext context = 
				 new ClassPathXmlApplicationContext("application-context.xml");

		 Greedy greedy = (Greedy)context.getBean("greedy");
		 greedy.start();
	    }
	
	Scanner scanner; 
	CoinCalculator coinCalculator;
	ResourceBundleMessageSource messageSource;
	NumberFormat defaultLocaleCurrencyFormat = NumberFormat.getCurrencyInstance();
	Locale defaultLocale = new Locale("en");

	public Greedy(CoinCalculator coinCalculator, ResourceBundleMessageSource messageSource) {
		this.coinCalculator = coinCalculator;
		this.messageSource = messageSource;
		scanner = new Scanner(System.in);
	}
	
	public void start() {
		try {
			runProgram();
		} catch (ParseException e) {
			System.err.println(messageSource.getMessage("errorMsg", null, defaultLocale));
		}
	}
	
	private void runProgram() throws ParseException {
		giveInstructions();
		int moneyValueInCents = getInput();
		int minNumberOfCoins = coinCalculator.calculateChange(moneyValueInCents);
		giveOutput(minNumberOfCoins);
	}
	
	void giveInstructions() {
		System.out.println(messageSource.getMessage("instructionsMsg", null, defaultLocale));
	}
	
	int getInput() throws ParseException {
		System.out.println(messageSource.getMessage("promptMsg", null, defaultLocale)  + ": ");
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
	
	void giveOutput(int minNumberOfCoins) {
		System.out.println(minNumberOfCoins);
	}
   

}
