package greedy;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.text.*;
import java.util.*;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import greedy.calculator.*;
import greedy.parse.*;

public class GreedyTest {

	private CoinCalculatorFactory coinCalculatorFactory;
	private MessageSource messageSource;
	private CurrencyParser parser;
	private Greedy greedy;
	private Greedy parseExceptionGreedy;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void initialize() {
		createDependencies();
		createGreedyVariants();
		setUpStreamsToTestOutputs();
	}
	
	private void createDependencies() {
		coinCalculatorFactory = new CoinCalculatorFactory();
		messageSource = new MessageSourceReturnsFixedOutput();
		String[][] localeCodes = { {"en", "US"} };	
		parser = new CurrencyParserImpl(localeCodes);
	}
	
	private void createGreedyVariants() {
		greedy = new Greedy(coinCalculatorFactory, messageSource, parser, "en");
		parseExceptionGreedy = new Greedy(coinCalculatorFactory, messageSource, 
				new CurrencyParserAlwaysThrowsException(), "en");
	}
	
	private void setUpStreamsToTestOutputs() {
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
		setInputForExceptionHandlingTesting();
		parseExceptionGreedy.runProgram();
		assertNotNull("Standard error content should not be null", errContent.toString());
	}
	
	@Test
	public void testErrorMessageIsRetrieved() {
		setInputForExceptionHandlingTesting();
		parseExceptionGreedy.runProgram();
		assertEquals("Test\n", errContent.toString());
	}
	
	private void setInputForExceptionHandlingTesting() {
		String[] array = {"$1.00"};
		parseExceptionGreedy.setInput(array);
	}
	
	@Test
	public void testConvertInputToString() {
		String[] array = {"$", "1.00"};
		greedy.setInput(array);
		
		String processedInput = greedy.convertInputToString();
		
		assertEquals("$ 1.00", processedInput);	
	}
	
	@Test
	public void testPrintOutput() {
		ArrayList<OutputEntry> test = new ArrayList<OutputEntry>();
		OutputEntry entry1 = new OutputEntry(null, 1);
		OutputEntry entry2 = new OutputEntry(null, 10);
		test.add(entry1);
		test.add(entry2);
		
		greedy.printOutput(test);
		
	  assertEquals("Test: 1\nTest: 10\n", outContent.toString());
	}
	
	@Test
	public void testProcessInput() throws ParseException {
		greedy = new Greedy(coinCalculatorFactory, messageSource,
				new CurrencyParserReturnsFixedOutput(), "en");
		String[] args = {"test"};
		greedy.setInput(args);
		
		int moneyValueInCents = greedy.processInput();
		
		assertEquals(100, moneyValueInCents);
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
		
	private class CurrencyParserReturnsFixedOutput implements CurrencyParser {
		@Override
		public int parse(String input) throws ParseException {
			return 100;
		}
		@Override
		public Currency getCurrencyOfLastParsedInput() {
			return null;
		}
	}
	
	private class MessageSourceReturnsFixedOutput implements MessageSource {
		@Override
		public String getMessage(MessageSourceResolvable arg0, Locale arg1)
				throws NoSuchMessageException {
			return "Test";
		}
		@Override
		public String getMessage(String arg0, Object[] arg1, Locale arg2)
				throws NoSuchMessageException {
			return "Test";
		}
		@Override
		public String getMessage(String arg0, Object[] arg1, String arg2, Locale arg3) {
			return "Test";
		}
		
	}
	
}
