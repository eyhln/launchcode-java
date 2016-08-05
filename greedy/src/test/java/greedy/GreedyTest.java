package greedy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ResourceBundleMessageSource;

import greedy.calculator.CoinCalculatorFactory;
import greedy.parse.CurrencyParser;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/test-application-context.xml")
public class GreedyTest {

	private Greedy greedy;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void initialize() {
		greedy = new Greedy(new CoinCalculatorFactory(), new ResourceBundleMessageSource(),
				new CurrencyParser());
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testParsingInvalidInputShouldCauseWritetoStErr() throws ParseException {
		String[] input = {"abcde"};
		greedy.setInput(input);
		greedy.runProgram();
		assertNotNull("Err content should not be null", errContent.toString());
	}
	
	@Test
	public void testProcessInput() {
		fail("implement");
	}
	
	@Test
	public void testGetCoinCalculator() {
		fail("implement"); //will have to experiment with mocks here perhaps?
	}
	
	@Test
	public void testConvertInputToString() {
		String[] array = {"$", "1.00"};
		greedy.setInput(array);
		assertEquals("$ 1.00", greedy.convertInputToString());	
	}
	
	@Test
	public void testPrintOutput() {
		ArrayList<Object[]> test = new ArrayList<Object[]>();
		Object[] entry1 = {"Euro.100", 10};
		Object[] entry2 = {"Euro.50", 10};
		test.add(entry1);
		test.add(entry2);
		greedy.printOutput(test);
	    assertEquals("test1: 0\ntest2: 0", outContent.toString());
	}
	
	

}
