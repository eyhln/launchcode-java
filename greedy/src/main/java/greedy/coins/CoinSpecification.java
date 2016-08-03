package greedy.coins;

public class CoinSpecification {
	
	int[] coinValues;
	String coinNameCodePrefix;
	String[] coinNameCodes;
	
	public int[] getCoinValues() {
		return coinValues;
	}
	
	public String[] getCoinNameCodes() {
		return coinNameCodes;
	}
	
	String[] createNameCodes() {
		String[] nameCodes = new String[coinValues.length];
		for (int i = 0; i < nameCodes.length; i++) {
			nameCodes[i] = coinNameCodePrefix + "." + coinValues[i];
		}
		return nameCodes;
	}
}
