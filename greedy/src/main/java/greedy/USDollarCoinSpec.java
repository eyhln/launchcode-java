package greedy;

import java.util.HashMap;
import java.util.Locale;

public class USDollarCoinSpec implements CoinSpecification {
	
	Locale locale;
	HashMap<Integer,String> coins;
	
	public USDollarCoinSpec(Locale locale) {
		this.locale = locale;
	}

	@Override
	public HashMap<Integer, String> getCoinMap() {
		buildCoinMap();
		return coins;
	}
	
	private void buildCoinMap() {
		coins = new HashMap<Integer,String>();
		coins.put(1, "pennies");
		coins.put(5, "nickels");
		coins.put(10, "dimes");
		coins.put(25, "quarters");
		coins.put(100, "dollar coins");
	}
	
}
