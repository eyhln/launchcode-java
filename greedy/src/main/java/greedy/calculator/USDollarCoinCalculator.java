package greedy.calculator;

public class USDollarCoinCalculator extends CoinCalculator {
	
	public USDollarCoinCalculator() {
		coinValues = new int[] {100, 25, 10, 5, 1}; 
		coinNameCodePrefix = "USDollar";
	}
}
