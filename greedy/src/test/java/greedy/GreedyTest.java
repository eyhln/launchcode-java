package greedy;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.*;
import java.text.*;
import java.util.*;

import org.springframework.context.support.ResourceBundleMessageSource;

import greedy.calculator.*;
import greedy.parse.*;

public class GreedyTest {

	private Greedy greedy;
	CoinCalculatorFactory coinCalculatorFactory;
	ResourceBundleMessageSource messageSource;
	CurrencyParser parser;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void initialize() {
		coinCalculatorFactory = new CoinCalculatorFactory();
		messageSource = new ResourceBundleMessageSource();
		parser = new CurrencyParserImpl();
		messageSource.setBasename("languages/messages");
		greedy = new Greedy(coinCalculatorFactory, messageSource, parser, "en");
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testThrowingErrorShouldCauseWritetoStErr() throws ParseException {
		greedy = new Greedy(coinCalculatorFactory, messageSource,
				new CurrencyParserAlwaysThrowsException(), "en");
		greedy.runProgram();
		assertNotNull("Standard error content should not be null", errContent.toString());
	}
	
	@Test
	public void testEnglishErrorMessageIsRetrieved() {
		greedy = new Greedy(coinCalculatorFactory, messageSource,
				new CurrencyParserAlwaysThrowsException(), "en");
		greedy.runProgram();
		assertEquals("Error: invalid input", errContent.toString());
	}
	
	@Test
	public void testProcessInput() throws ParseException {
		greedy = new Greedy(coinCalculatorFactory, messageSource,
				new CurrencyParserReturnsCannedOutput(), "en");
		String[] args = {"test"};
		greedy.setInput(args);
		
		int moneyValueInCents = greedy.processInput();
		
		assertEquals(100, moneyValueInCents);
	}
	
	@Test
	public void testGetCoinCalculator() {
		greedy = new Greedy(coinCalculatorFactory, messageSource,
				new CurrencyParserReturnsUSDollarAsLastParsedCurrency(), "en");
		int valueRequiredToEnforceTemporalOrder = 0;
		
		CoinCalculator coinCalculator = greedy.getCoinCalculator
				(valueRequiredToEnforceTemporalOrder);
		
		assertEquals(USDollarCoinCalculator.class, coinCalculator.getClass());
	}
	
	@Test
	public void testConvertInputToString() {
		String[] array = {"$", "1.00"};
		greedy.setInput(array);
		assertEquals("$ 1.00", greedy.convertInputToString());	
	}
	
	@Test
	public void testPrintOutput() {
		ArrayList<OutputEntry> test = new ArrayList<OutputEntry>();
		OutputEntry entry1 = new OutputEntry("Euro.100", 1);
		OutputEntry entry2 = new OutputEntry("Euro.50", 10);
		test.add(entry1);
		test.add(entry2);
		greedy.printOutput(test);
	    assertEquals("1 Euro: 1\n50 cents: 10\n", outContent.toString());
	}
	
	
	private class CurrencyParserAlwaysThrowsException implements CurrencyParser {

		@Override
		public int parse(String input) throws ParseException {
			throw new ParseException(input, 0);
		}

		@Override
		public Currency getCurrencyOfLastParsedInput() {
			return null;
		}
		
	}
	
	private class CurrencyParserReturnsUSDollarAsLastParsedCurrency implements CurrencyParser {

		@Override
		public int parse(String input) throws ParseException {
			return 0;
		}

		@Override
		public Currency getCurrencyOfLastParsedInput() {
			Currency USDollar = Currency.getInstance("USD");
			return USDollar;
		}
		
	}
	
	private class CurrencyParserReturnsCannedOutput implements CurrencyParser {

		@Override
		public int parse(String input) throws ParseException {
			return 100;
		}

		@Override
		public Currency getCurrencyOfLastParsedInput() {
			return null;
		}
		
	}
	
	
	

}
