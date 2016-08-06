package greedy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import greedy.calculator.CoinCalculatorTemplate;
import greedy.calculator.CoinCalculatorFactory;
import greedy.calculator.OutputEntry;
import greedy.parse.CurrencyParser;

public class Greedy {
	
	private CoinCalculatorFactory coinCalculatorFactory;
	private ResourceBundleMessageSource messageSource;
	CurrencyParser currencyParser;
	private StringBuilder stringBuilder;

	private Locale language;
	private String[] input;

	public Greedy(CoinCalculatorFactory coinCalculatorFactory, 
			ResourceBundleMessageSource messageSource, CurrencyParser currencyParser,
			String languageCode) {
		
		this.coinCalculatorFactory = coinCalculatorFactory;
		this.messageSource = messageSource;
		this.currencyParser = currencyParser;
		this.stringBuilder = new StringBuilder();
		
		language = new Locale(languageCode);
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
	
	public void runProgram() {
		try {
			calculateLeastNumberOfCoinsNeeded();
		} catch (ParseException e) {
			System.err.println(messageSource.getMessage("errorMsg", null, language));
		}
	}
	
	void calculateLeastNumberOfCoinsNeeded() throws ParseException {
		int moneyValueInCents = processInput();
		CoinCalculatorTemplate coinCalculator = getCoinCalculator();
		ArrayList<OutputEntry> coinsUsed = coinCalculator.calculateChange(moneyValueInCents);
		printOutput(coinsUsed);
	}
	
	int processInput() throws ParseException {
		String moneyAmount = convertInputToString();
		int moneyValueInCents = currencyParser.parse(moneyAmount);
		return moneyValueInCents;
	}
	
	private CoinCalculatorTemplate getCoinCalculator() {
		Currency currencyOfInput = currencyParser.getCurrencyOfLastParsedInput();
		CoinCalculatorTemplate coinCalculator = 
				coinCalculatorFactory.getCoinCalculator(currencyOfInput);
		return coinCalculator;
	}
	
	String convertInputToString() {
		for (String str : input) {
			stringBuilder.append(str);
			stringBuilder.append(" ");
		}
		String processedInput = stringBuilder.toString().trim();
		return processedInput;
	}
	
	void printOutput(ArrayList<OutputEntry> coinsUsed) {
		for (OutputEntry entry : coinsUsed) {
			System.out.println(
				messageSource.getMessage(entry.getCoinCode(), null, language) + 
				": " + entry.getNumberOfCoins());
		}
	}
	 

}
