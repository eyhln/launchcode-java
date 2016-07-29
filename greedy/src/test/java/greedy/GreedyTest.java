package greedy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

public class GreedyTest {

	Greedy greedy;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	ParseException testException = new ParseException(null, 0);
	
	@Before
	public void initialize() {
		greedy = new Greedy(new CoinCalculator());
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Test
	public void testGreetUser() {
		greedy.greetUser();
		assertEquals("Enter an amount of money or press ESC to quit.", 
				outContent.toString().trim());
	}
	
	@Test
	public void testParseInputShouldSuccessfullyParseDifferentFormats() {
		try {
			assertEquals(100, greedy.parseInput("$1.00"));
			assertEquals(45, greedy.parseInput("$.45"));
			assertEquals(100, greedy.parseInput("$1"));
			assertEquals(80, greedy.parseInput("$.8"));
			assertEquals(80, greedy.parseInput("$.80"));
			assertEquals(45, greedy.parseInput("$0.45"));
			assertEquals(45, greedy.parseInput("  $0.45 "));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionForNoCurrencySymbol() {
		boolean exceptionCaught = attemptParse("45.00");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionForInvalidSpaces() {
		boolean exceptionCaught = attemptParse("$ 45");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionForWords() {
		boolean exceptionCaught = attemptParse("hw34-");
		assertEquals(true, exceptionCaught);
	}
	
	@Test
	public void testParseInputShouldThrowAnExceptionForNothing() {
		boolean exceptionCaught = attemptParse("");
		assertEquals(true, exceptionCaught);
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
