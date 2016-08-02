package greedy;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import greedy.calculator.CoinCalculator;
import greedy.calculator.CoinCalculatorFactory;

public class Greedy {
	
	private Locale locale;
	private ResourceBundleMessageSource messageSource;
	private CurrencyParser currencyParser;
	private CoinCalculatorFactory coinCalculatorFactory;
	HashMap<String,Integer> coinsUsed;
	String[] varArgs;
	
	public static void main(String[] varArgs) {
		Greedy greedy;
		try (ClassPathXmlApplicationContext context = 
			 new ClassPathXmlApplicationContext("application-context.xml")) {
		greedy = (Greedy)context.getBean("greedy");
		}
		greedy.calculateLeastNumberOfCoins(varArgs);
	}

	public Greedy(CoinCalculatorFactory coinCalculatorFactory, ResourceBundleMessageSource messageSource,
			CurrencyParser currencyParser) {
		this.coinCalculatorFactory = coinCalculatorFactory;
		this.messageSource = messageSource;
		this.currencyParser = currencyParser;
		locale = Locale.getDefault();
		coinsUsed = new HashMap<String,Integer>();
	}
	
	public void calculateLeastNumberOfCoins(String[] varArgs) {
		this.varArgs = varArgs;
		try {
			runProgram();
		} catch (ParseException e) {
			System.err.println(messageSource.getMessage("errorMsg", null, locale));
			e.printStackTrace();
		}
	}
	
	private void runProgram() throws ParseException {
		String input = convertInputToString();
		int moneyValueInCents = currencyParser.parseInput(input);
		Currency currency = currencyParser.currencyOfLastParsedInput;
		CoinCalculator coinCalc = coinCalculatorFactory.getCoinSpecification(currency);
		coinsUsed = coinCalc.calculateChange(moneyValueInCents);
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
			System.out.println(messageSource.getMessage((String)code, null, locale) + 
					": " + coinsUsed.get(code));
		}
	}
   

}
