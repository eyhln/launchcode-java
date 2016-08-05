package greedy.calculator;

public class EuroCoinCalculator extends CoinCalculator {

	int[] constructCoinValues = {200, 100, 50, 20, 10, 5, 2, 1};

	public EuroCoinCalculator() {
		coinValues = constructCoinValues;
		coinNameCodePrefix = "Euro";
	}
}

