package greedy.calculator;

import org.junit.Test;

import java.util.*;
import org.junit.Before;

public class EuroCoinCalculatorTest {
	
	CoinCalculator coinCalculator;
	List<OutputEntry> testOutput;
	List<OutputEntry> output;

	@Before
	public void initialize() {
		coinCalculator = new EuroCoinCalculator();
		testOutput = new ArrayList<OutputEntry>();
	}
	
	@Test
	public void testCalculateChangeThirtyNine() {
		int valueInCents = 39;
		testOutput.add(new OutputEntry("Euro.20", 1));
		testOutput.add(new OutputEntry("Euro.10", 1));
		testOutput.add(new OutputEntry("Euro.5", 1));
		testOutput.add(new OutputEntry("Euro.2", 2));

		output = coinCalculator.calculateChange(valueInCents);
		
		CoinCalculatorTest.assertAllEntriesMatch(testOutput, output);
	}
	
	@Test
	public void testCalculateChangeThreeHundredFiftyOne() {
		int valueInCents = 351;
		testOutput.add(new OutputEntry("Euro.200", 1));
		testOutput.add(new OutputEntry("Euro.100", 1));
		testOutput.add(new OutputEntry("Euro.50", 1));
		testOutput.add(new OutputEntry("Euro.1", 1));

		output = coinCalculator.calculateChange(valueInCents);
		
		CoinCalculatorTest.assertAllEntriesMatch(testOutput, output);
	}


}
