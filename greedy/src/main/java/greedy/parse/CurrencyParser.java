package greedy.parse;

import java.text.ParseException;
import java.util.Currency;

public interface CurrencyParser {
	
	public int parse(String input) throws ParseException;
	public Currency getCurrencyOfLastParsedInput();

}
