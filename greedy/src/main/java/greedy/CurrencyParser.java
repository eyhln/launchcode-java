package greedy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Locale;

public class CurrencyParser {
	
	String[] localeCodesForAcceptedCurrencyFormats;
	ArrayList<NumberFormat> acceptedCurrencyFormats;
	String input;
	
	public CurrencyParser() {
		acceptedCurrencyFormats = new ArrayList<NumberFormat>();
	}
	
	public void setLocationCodesForAcceptedCurrencyFormats(String[] locations) {
		this.localeCodesForAcceptedCurrencyFormats  = (String[])locations.clone();
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
		this.input = input;
		throwExceptionForEmptyStringInput();
		ParsePosition parsePosition = new ParsePosition(0);
		Number amountOfMoney = null;
		for (NumberFormat currencyFormat : acceptedCurrencyFormats) {
			Number parsedCurrency = null;
			try {
			parsedCurrency = (Number)currencyFormat.parseObject(input, parsePosition);
			} catch (NullPointerException e) {};
			if (parsedCurrency != null)
				amountOfMoney = parsedCurrency;
		}
		if (parsePosition.getIndex() < input.length())
			throw new ParseException("Did not parse entire string", 0);	
		if (amountOfMoney == null) {
			throw new ParseException("Did not successfully parse with any accepted "
					+ "currency format", 0);
		}
		return amountOfMoney;
	}
	
	void throwExceptionForEmptyStringInput() throws ParseException {
		if (input.length() == 0)
			throw new ParseException("Empty string", 0);
	}
	
	
	int convertNumberToInt(Number amountOfMoney) {
		double moneyValue = amountOfMoney.doubleValue();	
		int moneyValueInCents = (int)Math.round(moneyValue * 100); 
		return moneyValueInCents;
	}

}
