package greedy.parse;

import java.text.*;
import java.util.*;

public class CurrencyParserImpl implements CurrencyParser {
	
	private Currency currencyOfLastParsedInput;
	ArrayList<NumberFormat> acceptedCurrencyFormats;
	String input;
	ParsePosition parsePosition;
	
	
	public CurrencyParserImpl(String[][] localeCodesForAcceptedCurrencyFormats) {
		acceptedCurrencyFormats = new ArrayList<NumberFormat>();
		createCurrencyFormats(localeCodesForAcceptedCurrencyFormats);
	}
	
	public void createCurrencyFormats(String[][] localeCodes) {
		for (String[] codes: localeCodes) {
			addNewAcceptedCurrencyFormat(codes);
		}
	}
	
	void addNewAcceptedCurrencyFormat(String[] localeCodes) {
		String language = localeCodes[0];
		String country = localeCodes[1];
		Locale locale = new Locale(language, country);
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		acceptedCurrencyFormats.add(format);
	}
	
	public Currency getCurrencyOfLastParsedInput() {
		return currencyOfLastParsedInput;
	}
	
	public int parse(String input) throws ParseException {
		Number amountOfMoney = attemptParseUsingAllAcceptedCurrencyFormats(input);
		int moneyValueInCents = convertNumberToInt(amountOfMoney);
		return moneyValueInCents;
	}
	
	Number attemptParseUsingAllAcceptedCurrencyFormats(String input) throws ParseException {
		resetVariables(input);
		throwExceptionForEmptyStringInput();
		Number amountOfMoney = tryParsingUsingAcceptedCurrencyFormats();
		throwExceptionIfDidNotParseEntireString();
		return amountOfMoney;
	}
	
	void resetVariables(String input) {
		this.input = input;
		parsePosition = new ParsePosition(0);
	}
	
	void throwExceptionForEmptyStringInput() throws ParseException {
		if (input.length() == 0)
			throw new ParseException("Empty string", 0);
	}
	
	Number tryParsingUsingAcceptedCurrencyFormats() throws ParseException {
		Number amountOfMoney = null;
		for (NumberFormat currencyFormat : acceptedCurrencyFormats) {
			Number parsedCurrency = null;
			try {
			parsedCurrency = (Number)currencyFormat.parseObject(input, parsePosition);
			} catch (NullPointerException e) {};
			if (parsedCurrency != null) {
				amountOfMoney = parsedCurrency;
				currencyOfLastParsedInput = currencyFormat.getCurrency();
			}
		}
		if (amountOfMoney == null) {
			throw new ParseException("Did not successfully parse with any accepted "
					+ "currency format", parsePosition.getIndex());
		}
		return amountOfMoney;
	}
	
	void throwExceptionIfDidNotParseEntireString() throws ParseException {
		int position = parsePosition.getIndex();
		if (position < input.length())
			throw new ParseException("Did not parse entire string", position);	
	}
	
	int convertNumberToInt(Number amountOfMoney) throws ParseException {
		double moneyValue = amountOfMoney.doubleValue();	
		if ((moneyValue * 100) > Integer.MAX_VALUE)
				throw new ParseException("Value too large", 0);
		int moneyValueInCents = (int)Math.round(moneyValue * 100); 
		return moneyValueInCents;
	}

}
