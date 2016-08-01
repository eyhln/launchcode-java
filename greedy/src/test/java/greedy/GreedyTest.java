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
		greedy = new Greedy(new CoinCalculatorImpl(), new ResourceBundleMessageSource(),
				new CurrencyParser());
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
	public void testPrint() {
		HashMap<String,Integer> test = new HashMap<String,Integer>();
		test.put("coin.1", 0);
		test.put("coin.2", 0);
		greedy.coinsUsed = test;
		greedy.printOutput();
	    assertEquals("test1: 0\ntest2: 0", outContent.toString());
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

}
