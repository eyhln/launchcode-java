package greedy.calculator.factory;

import java.util.Currency;

import greedy.calculator.CoinCalculator;

public interface CoinCalculatorFactory {

	public CoinCalculator getCoinCalculator(Currency currency);
	
}
