package greedy.coins;

import java.util.HashMap;

public class USDollarCoinSpecification implements CoinSpecification {
	
	HashMap<Integer,String> coins;

	@Override
	public HashMap<Integer, String> getCoinMap() {
		buildCoinMap();
		return coins;
	}
	
	private void buildCoinMap() {
		coins = new HashMap<Integer,String>();
		coins.put(100, "USDollarCoin.1");
		coins.put(25, "USDollarCoin.2");
		coins.put(10, "USDollarCoin.3");
		coins.put(5, "USDollarCoin.4");
		coins.put(1, "USDollarCoin.5");
	}
	
}

