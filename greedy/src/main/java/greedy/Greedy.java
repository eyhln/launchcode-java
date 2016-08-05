package greedy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import greedy.calculator.CoinCalculator;
import greedy.calculator.CoinCalculatorFactory;
import greedy.parse.CurrencyParser;

public class Greedy {
	
	private CoinCalculatorFactory coinCalculatorFactory;
	private ResourceBundleMessageSource messageSource;
	CurrencyParser currencyParser;
	private StringBuilder stringBuilder;

	private Locale locale;
	private String[] input;

	public Greedy(CoinCalculatorFactory coinCalculatorFactory, 
			ResourceBundleMessageSource messageSource, CurrencyParser currencyParser) {
		
		this.coinCalculatorFactory = coinCalculatorFactory;
		this.messageSource = messageSource;
		this.currencyParser = currencyParser;
		this.stringBuilder = new StringBuilder();
		
		locale = Locale.getDefault();
	}
	
	public static void main(String[] args) {
		Greedy greedy;
		try (ClassPathXmlApplicationContext context = 
			 new ClassPathXmlApplicationContext("application-context.xml")) {
		greedy = (Greedy)context.getBean("greedy");
		}
		greedy.setInput(args);
		greedy.runProgram();
	}
	
	void setInput(String[] args) {
		input = args;
	}
	
	public void setLanguageCode(String langCode) {
		locale = new Locale(langCode);
	}
	
	public void runProgram() {
		try {
			calculateLeastNumberOfCoins();
		} catch (ParseException e) {
			System.err.println(messageSource.getMessage("errorMsg", null, locale));
		}
	}
	
	void calculateLeastNumberOfCoins() throws ParseException {
		int moneyValueInCents = processInput();
		CoinCalculator coinCalculator = getCoinCalculator();
		ArrayList<Object[]> coinsUsed = coinCalculator.calculateChange(moneyValueInCents);
		printOutput(coinsUsed);
	}
	
	int processInput() throws ParseException {
		String moneyAmount = convertInputToString();
		int moneyValueInCents = currencyParser.parse(moneyAmount);
		return moneyValueInCents;
	}
	
	private CoinCalculator getCoinCalculator() {
		Currency currencyOfInput = currencyParser.getCurrencyOfLastParsedInput();
		CoinCalculator coinCalc = coinCalculatorFactory.getCoinCalculator(currencyOfInput);
		return coinCalc;
	}
	
	String convertInputToString() {
		for (String str : input) {
			stringBuilder.append(str);
			stringBuilder.append(" ");
		}
		String processedInput = stringBuilder.toString().trim();
		return processedInput;
	}
	
	void printOutput(ArrayList<Object[]> coinsUsed) {
		for (Object[] entry : coinsUsed) {
			System.out.println(messageSource.getMessage((String)entry[0], null, locale) + 
					": " + (int)entry[1]);
		}
	}
	 

}
