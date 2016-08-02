package greedy.calculator;

import java.util.HashMap;

public interface CoinCalculator {

	public HashMap<String,Integer> calculateChange(int nonNegativeAmountInCents);
}
