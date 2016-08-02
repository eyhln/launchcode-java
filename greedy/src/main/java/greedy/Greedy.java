package greedy;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

public class Greedy {
	
	private ResourceBundleMessageSource messageSource;
	private CurrencyParser currencyParser;
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

	public Greedy(CoinCalculator coinCalculator, ResourceBundleMessageSource messageSource,
			CurrencyParser currencyParser) {
		this.coinCalculator = coinCalculator;
		this.messageSource = messageSource;
		this.currencyParser = currencyParser;
		defaultLocale = Locale.getDefault();
		coinsUsed = new HashMap<String,Integer>();
	}
	
	public void calculateLeastNumberOfCoins(String[] varArgs) {
		this.varArgs = varArgs;
		try {
			runProgram();
		} catch (ParseException e) {
			System.err.println(messageSource.getMessage("errorMsg", null, defaultLocale));
			e.printStackTrace();
		}
	}
	
	private void runProgram() throws ParseException {
		String input = convertInputToString();
		int moneyValueInCents = currencyParser.parseInput(input);
		coinsUsed = coinCalculator.calculateChange(moneyValueInCents);
		printOutput();
	}
	
	String convertInputToString() {
		String input = "";
		for (String str : varArgs) 
			input = input + str + " ";
		input = input.trim();
		return input;
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
