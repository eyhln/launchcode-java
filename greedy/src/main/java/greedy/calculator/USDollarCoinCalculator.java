package greedy.calculator;

public class USDollarCoinCalculator extends CoinCalculator {
	
	int[] constructCoinValues = {100, 25, 10, 5, 1};

	public USDollarCoinCalculator() {
		coinValues = constructCoinValues;
		coinNameCodePrefix = "USDollar";
	}
}
