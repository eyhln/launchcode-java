package greedy.parse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import greedy.parse.CurrencyParserImpl;

public class CurrencyParserImplTest {
	
	String[][] localeCodes;
	CurrencyParserImpl cp;
	NumberFormat testNumberFormat;

	@Before
	public void initialize() {
		createLocaleCodes();
		createCurrencyParser();
		createTestNumberFormat();
	}
	
	private void createLocaleCodes() {
		String[][] localeCodes = { {"en", "US"} };
		this.localeCodes = localeCodes;
	}
	
	private void createCurrencyParser() {
		cp = new CurrencyParserImpl(localeCodes);
	}
	
	private void createTestNumberFormat() {
		Locale testLocale = new Locale(localeCodes[0][0], 
				localeCodes[0][1]);
		testNumberFormat = NumberFormat.getCurrencyInstance(testLocale);
	}
	
	@Test 
	public void testParse() throws ParseException {
		String value = testNumberFormat.format(1200.25);

		int valueInCents = cp.parse(value);
		
		assertEquals(120025, valueInCents);
	}
	
	@Test
	public void testRecordsCurrencyOfLastSuccessfulParse() throws ParseException {
		String[][] localeCodes = { {"en","US"}, {"en", "IE"} };
		cp = new CurrencyParserImpl(localeCodes);
		
		cp.parse("$1.00");
		assertEquals(Currency.getInstance("USD"), cp.getCurrencyOfLastParsedInput());
		cp.parse("€1.00" );
		assertEquals(Currency.getInstance("EUR"), cp.getCurrencyOfLastParsedInput());
	}
	
	@Test
	public void testShouldThrowAnExceptionNonCurrencyFormat() {
		assertParseThrowsException("4.56", "Did not successfully parse with any accepted "
					+ "currency format");
	}
	
	@Test
	public void testShouldThrowAnExceptionWords() {
		assertParseThrowsException("hello", "Did not successfully parse with any accepted "
					+ "currency format");
	}
	
	@Test
	public void testShouldThrowAnExceptionNegativeNumber() {
		String test = testNumberFormat.format(1);
		test = "-" + test;
		assertParseThrowsException(test, "Did not successfully parse with any accepted "
				+ "currency format");
	}
	
	@Test
	public void testShouldThrowAnExceptionEmptyString() {
		String nothing = "";
		assertParseThrowsException(nothing, "Empty string");
	}
	
	@Test
	public void testShouldThrowAnExceptionOnlyBeginningIsValid() {
		assertParseThrowsException("$0.45abcde", "Did not parse entire string");
	}
	
	@Test
	public void testShouldThrowAnExceptionIntegerOverflow() {
		String tooLarge = "$1000000000000000";
		assertParseThrowsException(tooLarge, "Value too large");
	}
	
	private void assertParseThrowsException(String toParse, String expectedMessage) {
		try {
			cp.parse(toParse);
			fail("Failed to throw an exception");
		} catch (ParseException expected) {
			assertEquals(expectedMessage, expected.getMessage());
		}
	}

	
	
}
