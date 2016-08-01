package greedy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

public class CurrencyParser {
	
	NumberFormat defaultLocaleCurrencyFormat = NumberFormat.getCurrencyInstance();
	
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

}
