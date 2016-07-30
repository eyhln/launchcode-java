package greedy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class CoinCalculatorImplTest {
	
	CoinCalculatorImpl cc;

	@Before
	public void initialize() {
		cc = new CoinCalculatorImpl();
	}
	
	@Test
	public void testProcessCoinValuesEmptyArray() {
		int[] empty = new int[0];
		cc.coinValues = empty;
		cc.processCoinValues();
		for (int i = 0; i < empty.length; i++) {
			assertEquals(empty[i], cc.coinValues[i]);
		}
	}
	
	@Test
	public void testProcessCoinValuesUnorderedArray() {
		int[] unordered = {1,3,90,43,18,0};
		int[] sorted = {90,43,18,3,1,0};
		cc.coinValues = unordered;
		cc.processCoinValues();
		for (int i = 0; i < unordered.length; i++) {
			assertEquals(sorted[i], cc.coinValues[i]);
		}
	}
	
	@Test
	public void testCalculateChangeZero() {
		assertEquals(0,cc.calculateChange(0));
	}
	
	@Test
	public void testCalculateChangeFive() {
		assertEquals(1,cc.calculateChange(5));
	}
	
	@Test
	public void testCalculateChangeSix() {
		assertEquals(2,cc.calculateChange(6));
	}
	
	@Test
	public void testCalculateChangeThirtyTwo() {
		assertEquals(4,cc.calculateChange(32));
	}
	
	@Test
	public void testCalculateChangeFourHundredEleven() {
		assertEquals(402,cc.calculateChange(40011));
	}

}
