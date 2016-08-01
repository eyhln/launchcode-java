package greedy;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class CurrencyParserTest {
	
	CurrencyParser cp;
	
	@Before
	public void initialize() {
		cp = new CurrencyParser();
	}

	@Test
	public void testParseInputSucessfullyParseStandard() throws ParseException {
		assertEquals(100, cp.parseInput("$1.00"));
	}
	
	@Test
	public void testParseInputSucessfullyParseNoDecimal() throws ParseException {
		assertEquals(100, cp.parseInput("$1"));
	}
	
	@Test
	public void testParseInputSucessfullyParseLeadingDecimal() throws ParseException {
		assertEquals(80, cp.parseInput("$.8"));
	}
	
	@Test
	public void testParseInputSucessfullyParseThousandsSeparator() throws ParseException {
		assertEquals(100000, cp.parseInput("$1,000.00"));
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionForNoCurrencySymbol() {
		boolean exceptionCaught = attemptParse("45.00");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionForWords() {
		boolean exceptionCaught = attemptParse("hw$34-");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionForNothing() {
		boolean exceptionCaught = attemptParse("");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionInvalidCurrencyFormat() {
		boolean exceptionCaught = attemptParse("$1,00.00.00");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionOnlyBeginningIsValid() {
		boolean exceptionCaught = attemptParse("$0.45abcde");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionNegativeNumber() {
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
