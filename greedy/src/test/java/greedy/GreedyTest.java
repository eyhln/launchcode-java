package greedy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ResourceBundleMessageSource;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.HashMap;

public class GreedyTest {

	private Greedy greedy;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private ResourceBundleMessageSource messageSource;
	
	@Before
	public void initialize() {
		messageSource = new ResourceBundleMessageSource();
		greedy = new Greedy(new CoinCalculatorImpl(), new ResourceBundleMessageSource());
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Test
	public void testConvertInputToStringSimpleUS() {
		String[] array = {"$", "1.00"};
		greedy.varArgs = array;
		assertEquals("$1.00", greedy.convertInputToString());	
	}
	
	@Test
	public void testConvertInputToStringSpaceThousandsSeparator() {
		String[] array = {"$", "1", "000,00"};
		greedy.varArgs = array;
		assertEquals("$1000,00", greedy.convertInputToString());	
	}
	
	@Test
	public void testParseInputSucessfullyParseStandard() throws ParseException {
		assertEquals(100, greedy.parseInput("$1.00"));
	}
	
	@Test
	public void testParseInputSucessfullyParseNoDecimal() throws ParseException {
		assertEquals(100, greedy.parseInput("$1"));
	}
	
	@Test
	public void testParseInputSucessfullyParseLeadingDecimal() throws ParseException {
		assertEquals(80, greedy.parseInput("$.8"));
	}
	
	@Test
	public void testParseInputSucessfullyParseThousandsSeparator() throws ParseException {
		assertEquals(100000, greedy.parseInput("$1,000.00"));
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
	
	@Test
	public void testPrint() {
		HashMap<String,Integer> test = new HashMap<String,Integer>();
		test.put("coin.1", 0);
		test.put("coin.2", 0);
		greedy.coinsUsed = test;
		greedy.printOutput();
	    assertEquals("test1: 0\ntest2: 0", outContent.toString());
	}
	
	private boolean attemptParse(String toParse) {
		boolean exceptionCaught = false;
		try {
			greedy.parseInput(toParse);
		} catch (ParseException e) {
			exceptionCaught = true;
		}
		return exceptionCaught;
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

}
