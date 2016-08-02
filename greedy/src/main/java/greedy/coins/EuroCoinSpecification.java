package greedy.coins;

import java.util.HashMap;

public class EuroCoinSpecification implements CoinSpecification {
	
	HashMap<Integer,String> coins;

	@Override
	public HashMap<Integer, String> getCoinMap() {
		buildCoinMap();
		return coins;
	}
	
	private void buildCoinMap() {
		coins = new HashMap<Integer,String>();
		coins.put(200, "EuroCoin.1");
		coins.put(100, "EuroCoin.2");
		coins.put(50, "EuroCoin.3");
		coins.put(20, "EuroCoin.4");
		coins.put(10, "EuroCoin.5");
		coins.put(5, "EuroCoin.6");
		coins.put(1, "EuroCoin.7");
	}
	
}