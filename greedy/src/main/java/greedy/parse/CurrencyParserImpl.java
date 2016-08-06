package greedy.parse;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class CurrencyParserImpl implements CurrencyParser {
	
	private Currency currencyOfLastParsedInput;
	String[][] localeCodesForAcceptedCurrencyFormats;
	ArrayList<NumberFormat> acceptedCurrencyFormats;
	String input;
	ParsePosition parsePosition;
	
	
	public CurrencyParserImpl() {
		acceptedCurrencyFormats = new ArrayList<NumberFormat>();
	}
	
	public void setLocaleCodesForAcceptedCurrencyFormats(String[][] localeCodes) {
		this.localeCodesForAcceptedCurrencyFormats  = (String[][])localeCodes.clone();
	}
	
	public Currency getCurrencyOfLastParsedInput() {
		return currencyOfLastParsedInput;
	}
	
	public int parse(String input) throws ParseException {
		processLocaleInformation();
		Number amountOfMoney = attemptParseUsingAllAcceptedCurrencyFormats(input);
		int moneyValueInCents = convertNumberToInt(amountOfMoney);
		return moneyValueInCents;
	}
	
	void processLocaleInformation() {
		for (String[] localeCodes: localeCodesForAcceptedCurrencyFormats) {
			addNewAcceptedCurrencyFormat(localeCodes);
		}
	}
	
	private void addNewAcceptedCurrencyFormat(String[] localeCodes) {
		String language = localeCodes[0];
		String country = localeCodes[1];
		Locale locale = new Locale(language, country);
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		acceptedCurrencyFormats.add(format);
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
					+ "currency format", 0);
		}
		return amountOfMoney;
	}
	
	void throwExceptionIfDidNotParseEntireString() throws ParseException {
		if (parsePosition.getIndex() < input.length())
			throw new ParseException("Did not parse entire string", 0);	
	}
	
	int convertNumberToInt(Number amountOfMoney) {
		double moneyValue = amountOfMoney.doubleValue();	
		int moneyValueInCents = (int)Math.round(moneyValue * 100); 
		return moneyValueInCents;
	}

}
