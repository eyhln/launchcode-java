package greedy;

import java.util.*;
import java.text.ParseException;

import org.springframework.context.MessageSource;
import org.springframework.context.support.*;

import greedy.calculator.*;
import greedy.calculator.factory.CoinCalculatorFactory;
import greedy.parse.CurrencyParser;

public class Greedy {
	
	private CoinCalculatorFactory coinCalculatorFactory;
	private MessageSource messageSource;
	private CurrencyParser currencyParser;
	private StringBuilder stringBuilder;

	private Locale language;
	String[] input;

	public Greedy(CoinCalculatorFactory coinCalculatorFactory, MessageSource messageSource, 
			CurrencyParser currencyParser, String languageCode) {
		this.coinCalculatorFactory = coinCalculatorFactory;
		this.messageSource = messageSource;
		this.currencyParser = currencyParser;
		language = new Locale(languageCode);
		stringBuilder = new StringBuilder();
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
			System.err.println(messageSource.getMessage("errorMsg", null,  language));
		}
	}
	
	void calculateLeastNumberOfCoinsNeeded() throws ParseException {
		int moneyValueInCents = processInput();
		CoinCalculator coinCalculator = getCoinCalculator(moneyValueInCents);
		List<OutputEntry> coinsUsed = coinCalculator.calculateChange(moneyValueInCents);
		printOutput(coinsUsed);
	}
	
	int processInput() throws ParseException {
			String moneyAmount = convertInputToString();
			int moneyValueInCents = currencyParser.parse(moneyAmount);
			return moneyValueInCents;
	}
	
	CoinCalculator getCoinCalculator(int moneyValueInCents) {
		Currency currencyOfInput = currencyParser.getCurrencyOfLastParsedInput();
		CoinCalculator coinCalculator = coinCalculatorFactory.getCoinCalculator(currencyOfInput);
		return coinCalculator;
	}
	
	void printOutput(List<OutputEntry> coinsUsed) {
		for (OutputEntry entry : coinsUsed) {
			System.out.println(
				messageSource.getMessage(entry.getCoinCode(), null, language) + 
				": " + entry.getNumberOfCoins());
		}
	}
	
	String convertInputToString() {
		for (String str : input) {
			stringBuilder.append(str);
			stringBuilder.append(" ");
		}
		return stringBuilder.toString().trim();
	}
	 

}
