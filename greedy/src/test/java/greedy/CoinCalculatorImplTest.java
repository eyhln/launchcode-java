package greedy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;

public class CoinCalculatorImplTest {
	
	CoinCalculatorImpl cc;
	CoinSpecification coinSpec;
	HashMap<String,Integer> coinsNeeded;

	@Before
	public void initialize() {
		coinSpec = (CoinSpecification) new USDollarCoinSpec();
		cc = new CoinCalculatorImpl(coinSpec);
		coinsNeeded = new HashMap<String,Integer>();
	}
	
	@Test
	public void testProcessCoinValuesEmptyArray() {
		Object[] empty = new Object[0];
		cc.coinValues = empty;
		cc.processCoinValues();
		for (int i = 0; i < empty.length; i++) {
			assertEquals(empty[i], cc.coinValues[i]);
		}
	}
	
	@Test
	public void testProcessCoinValuesUnorderedArray() {
		Object[] unordered = {1,3,90,43,18,0};
		int[] sorted = {90,43,18,3,1,0};
		cc.coinValues = unordered;
		cc.processCoinValues();
		for (int i = 0; i < unordered.length; i++) {
			assertEquals(sorted[i], cc.coinValues[i]);
		}
	}
	
	@Test
	public void testCalculateChangeZero() {
		assertEquals(coinsNeeded, cc.calculateChange(0));
	}
	
	@Test
	public void testCalculateChangeFive() {
		coinsNeeded.put("coin.4", 1);
		assertEquals(coinsNeeded, cc.calculateChange(5));
	}
	
	@Test
	public void testCalculateChangeSix() {
		coinsNeeded.put("coin.4", 1);
		coinsNeeded.put("coin.5", 1);
		assertEquals(coinsNeeded, cc.calculateChange(6));
	}
	
	@Test
	public void testCalculateChangeThirtyTwo() {
		coinsNeeded.put("coin.4", 1);
		coinsNeeded.put("coin.2", 1);
		coinsNeeded.put("coin.5", 2);
		assertEquals(coinsNeeded, cc.calculateChange(32));
	}
	
	@Test
	public void testCalculateChangeFourHundredEleven() {
		coinsNeeded.put("coin.1",400);
		coinsNeeded.put("coin.3", 1);
		coinsNeeded.put("coin.5", 1);
		assertEquals(coinsNeeded, cc.calculateChange(40011));
	}

}
