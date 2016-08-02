package greedy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class CurrencyParser {
	
	String[] localeCodesForAcceptedCurrencyFormats;
	ArrayList<NumberFormat> acceptedCurrencyFormats;
	String input;
	private Currency currencyOfLastParsedInput;
	private ParsePosition parsePosition;
	
	
	public CurrencyParser() {
		acceptedCurrencyFormats = new ArrayList<NumberFormat>();
	}
	
	public void setLocaleCodesForAcceptedCurrencyFormats(String[] locales) {
		this.localeCodesForAcceptedCurrencyFormats  = (String[])locales.clone();
	}
	
	public Currency getCurrencyOfLastParsedInput() {
		return currencyOfLastParsedInput;
	}
	
	int parseInput(String input) throws ParseException {
		processLocaleInformation();
		Number amountOfMoney = attemptParseUsingAllAcceptedCurrencyFormats(input);
		int moneyValueInCents = convertNumberToInt(amountOfMoney);
		return moneyValueInCents;
	}
	
	void processLocaleInformation() {
		for (String localeCode: localeCodesForAcceptedCurrencyFormats) {
			addNewAcceptedCurrencyFormat(localeCode);
		}
	}
	
	private void addNewAcceptedCurrencyFormat(String localeCode) {
		String language = localeCode.substring(1,3);
		String country = localeCode.substring(4,6);
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
