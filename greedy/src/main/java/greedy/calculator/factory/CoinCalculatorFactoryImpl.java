package greedy.calculator.factory;

import java.util.Currency;

import greedy.calculator.CoinCalculator;
import greedy.calculator.EuroCoinCalculator;
import greedy.calculator.USDollarCoinCalculator;

public class CoinCalculatorFactoryImpl implements CoinCalculatorFactory {
	
	Currency USDollar;
	Currency Euro;
	
	public CoinCalculatorFactoryImpl() {
		USDollar = Currency.getInstance("USD");
		Euro = Currency.getInstance("EUR");
	}
	
	public CoinCalculator getCoinCalculator(Currency currency) {
		if (currency.equals(USDollar)) {
			return new USDollarCoinCalculator();
		}
		if (currency.equals(Euro)) {
			return new EuroCoinCalculator();
		}
		return null;
	}

}
