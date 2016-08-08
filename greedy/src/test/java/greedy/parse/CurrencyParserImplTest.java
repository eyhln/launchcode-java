package greedy.parse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import greedy.parse.CurrencyParserImpl;

public class CurrencyParserImplTest {
	
	String[][] localeCodes;
	CurrencyParserImpl cp;
	NumberFormat testFormat0;
	NumberFormat testFormat1;

	@Before
	public void initialize() {
		initializeLocaleCodes();
		initializeCurrencyParser();
		initializeTestFormats();
	}
	
	private void initializeLocaleCodes() {
		String[][] localeCodes = {	{"en", "US"}, //
									{"es", "US"}, //
									{"en", "IE"}, //
									{"de", "DE"}, //
									{"fr", "FR"}, //
									{"it", "IT"}}; //
		this.localeCodes = localeCodes;
	}
	
	private void initializeCurrencyParser() {
		cp = new CurrencyParserImpl();
		cp.setLocaleCodesForAcceptedCurrencyFormats(localeCodes);
	}
	
	private void initializeTestFormats() {
		testFormat0 = setUpTestFormat(0);
		testFormat1 = setUpTestFormat(1);
	}
	
	private NumberFormat setUpTestFormat(int indexInLocaleCodes) {
		Locale testLocale = new Locale(localeCodes[indexInLocaleCodes][0], 
				localeCodes[indexInLocaleCodes][1]);
		NumberFormat testFormat = NumberFormat.getCurrencyInstance(testLocale);
		return testFormat;
	}
	
	
	@Test @Ignore("refactoring makes this test unintelligible")
	public void testAddNewAcceptedCurrencyFormat() {
		assertEquals("$100.00", cp.acceptedCurrencyFormats.get(0).format(100));
		assertEquals("US$100.00", cp.acceptedCurrencyFormats.get(1).format(100));
	}
	
	@Test
	public void testParseForEachAcceptedCurrency() throws ParseException {
		for (String[] localeCode : localeCodes) {
			Locale locale = new Locale(localeCode[0], localeCode[1]);
			NumberFormat format = NumberFormat.getCurrencyInstance(locale);
			String value = format.format(1200.25);
			int valueInCents = cp.parse(value);
			assertEquals(120025, valueInCents);
		}
	}
	
	@Test
	public void testRecordsCurrencyOfLastSuccessfulParse() throws ParseException {
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
		String test = testFormat0.format(1);
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
