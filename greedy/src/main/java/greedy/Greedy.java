package greedy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

public class Greedy {
	
	private ResourceBundleMessageSource messageSource;
	private NumberFormat defaultLocaleCurrencyFormat;
	private Locale defaultLocale;
	
	private CoinCalculator coinCalculator;
	HashMap<String,Integer> coinsUsed;
	String[] varArgs;
	
	 public static void main(String[] varArgs) {
		 ApplicationContext context = 
				 new ClassPathXmlApplicationContext("application-context.xml");

		 Greedy greedy = (Greedy)context.getBean("greedy");
		 greedy.calculateLeastNumberOfCoins(varArgs);
	    }

	public Greedy(CoinCalculator coinCalculator, ResourceBundleMessageSource messageSource) {
		this.coinCalculator = coinCalculator;
		this.messageSource = messageSource;
		defaultLocale = Locale.getDefault();
		defaultLocaleCurrencyFormat = NumberFormat.getCurrencyInstance();
		coinsUsed = new HashMap<String,Integer>();
	}
	
	public void calculateLeastNumberOfCoins(String[] varArgs) {
		this.varArgs = varArgs;
		try {
			runProgram();
		} catch (ParseException e) {
			System.err.println(messageSource.getMessage("errorMsg", null, defaultLocale));
		}
	}
	
	private void runProgram() throws ParseException {
		String input = convertInputToString();
		int moneyValueInCents = parseInput(input);
		coinsUsed = coinCalculator.calculateChange(moneyValueInCents);
		printOutput();
	}
	
	String convertInputToString() {
		String input = "";
		for (String str : varArgs) 
			input = input + str;
		input = input.trim();
		return input;
	}
	
	int parseInput(String input) throws ParseException {
		ParsePosition parsePosition = new ParsePosition(0);
		Number amountOfMoney = 
				(Number)defaultLocaleCurrencyFormat.parseObject(input, parsePosition);
		if (input.length() == 0)
			throw new ParseException("Empty string", 0);
		if (parsePosition.getIndex() < input.length())
			throw new ParseException("Did not parse entire string", 0);
		double moneyValue = amountOfMoney.doubleValue();	
		int moneyValueInCents = (int)Math.round(moneyValue * 100); 
		return moneyValueInCents;
	}

	void printOutput() {
		Object[] coinCodes = coinsUsed.keySet().toArray();
		Arrays.sort(coinCodes);
		for (Object code : coinCodes) {
			System.out.println(messageSource.getMessage((String)code, null, defaultLocale) + 
					": " + coinsUsed.get(code));
		}
	}
   

}
