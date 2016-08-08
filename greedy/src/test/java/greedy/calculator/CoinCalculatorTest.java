package greedy.calculator;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class CoinCalculatorTest {

	CoinCalculator coinCalculator;
	ArrayList<OutputEntry> testOutput;
	ArrayList<OutputEntry> output;

	@Before
	public void initialize() {
		coinCalculator = new USDollarCoinCalculator();
		testOutput = new ArrayList<OutputEntry>();
	}
	
	@Test
	public void testCreateNameCodes() {
		int[] simpleCoinValues = {0, 1};
		coinCalculator.coinValues = simpleCoinValues;
		coinCalculator.coinNameCodePrefix = "test";
		
		String[] nameCodes = coinCalculator.createNameCodes();
		
		assertEquals("test.0", nameCodes[0]);
		assertEquals("test.1", nameCodes[1]);
	}
	
	@Test
	public void testCalculateChangeZero() {
		assertEquals(testOutput, coinCalculator.calculateChange(0));
	}
	
	
	static void assertAllEntriesMatch(List<OutputEntry> testOutput,
			List<OutputEntry> output){
		for (int i = 0; i < output.size(); i++) {
			assertEquals("Item number " + i+1 + " of the list does not match", 
					testOutput.get(i).toString(), output.get(i).toString());
		}
	}

}
