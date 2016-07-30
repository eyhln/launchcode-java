package greedy;

import java.util.HashMap;

public class USDollarCoinSpec implements CoinSpecification {
	
	HashMap<Integer,String> coins;

	@Override
	public HashMap<Integer, String> getCoinMap() {
		buildCoinMap();
		return coins;
	}
	
	private void buildCoinMap() {
		coins = new HashMap<Integer,String>();
		coins.put(100, "coin.1");
		coins.put(25, "coin.2");
		coins.put(10, "coin.3");
		coins.put(5, "coin.4");
		coins.put(1, "coin.5");
	}
	
}
