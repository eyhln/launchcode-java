package greedy;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class CurrencyParserTest {
	
	CurrencyParser cp;
	
	@Before
	public void initialize() {
		String[] localeCodes = {"_en_US", "_es_US"};
		cp = new CurrencyParser();
		cp.setLocaleCodesForAcceptedCurrencyFormats(localeCodes);
	}
	
	void setCurrencyParser(CurrencyParser cp) {
		this.cp = cp;
	}
	
	@Test
	public void testProcessLocaleInformation() {
		cp.processLocaleInformation();
		assertEquals("$100.00", cp.acceptedCurrencyFormats.get(0).format(100));
		assertEquals("US$100.00", cp.acceptedCurrencyFormats.get(1).format(100));
	}
	
	@Test
	public void testRecordsCurrencyOfLastSuccessfulParse() throws ParseException {
		cp.parseInput("$1.00");
		assertEquals(Currency.getInstance(Locale.US), cp.currencyOfLastParsedInput);
	}

	@Test
	public void testSucessfullyParseStandardEnUS() throws ParseException {
		assertEquals(100, cp.parseInput("$1.00"));
	}
	
	@Test
	public void testSucessfullyParseStandardEsUS() throws ParseException {
		assertEquals(100, cp.parseInput("US$1.00"));
	}
	
	@Test
	public void testSucessfullyParseNoDecimalEnUS() throws ParseException {
		assertEquals(100, cp.parseInput("$1"));
	}
	
	@Test
	public void testSucessfullyParseNoDecimalEsUS() throws ParseException {
		assertEquals(100, cp.parseInput("US$1"));
	}
	
	@Test
	public void testSucessfullyParseLeadingDecimalEnUS() throws ParseException {
		assertEquals(80, cp.parseInput("$.8"));
	}
	
	@Test
	public void testSucessfullyParseThousandsSeparatorEnUS() throws ParseException {
		assertEquals(100000, cp.parseInput("$1,000.00"));
	}
	
	@Test
	public void testShouldThrowAnExceptionForNoCurrencySymbol() {
		boolean exceptionCaught = attemptParse("45.00");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testtShouldThrowAnExceptionForWords() {
		boolean exceptionCaught = attemptParse("hw$34-");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testShouldThrowAnExceptionForNothing() {
		boolean exceptionCaught = attemptParse("");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testShouldThrowAnExceptionInvalidCurrencyFormat() {
		boolean exceptionCaught = attemptParse("$1,00.00.00");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testShouldThrowAnExceptionOnlyBeginningIsValid() {
		boolean exceptionCaught = attemptParse("$0.45abcde");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testShouldThrowAnExceptionNegativeNumber() {
		boolean exceptionCaught = attemptParse("-$1.00");
		assertEquals(true, exceptionCaught);
	}
	
	private boolean attemptParse(String toParse) {
		boolean exceptionCaught = false;
		try {
			cp.parseInput(toParse);
		} catch (ParseException e) {
			exceptionCaught = true;
		}
		return exceptionCaught;
	}

}
