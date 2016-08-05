package greedy.coins;

public abstract class CoinSpecification {
	
	int[] coinValuesInCents;
	String coinNameCodePrefix;
	String[] coinNameCodes;
	
	public int[] getCoinValues() {
		return coinValuesInCents;
	}
	
	public String[] getCoinNameCodes() {
		return coinNameCodes;
	}
	
	String[] createNameCodes() {
		String[] nameCodes = new String[coinValuesInCents.length];
		for (int i = 0; i < nameCodes.length; i++) {
			nameCodes[i] = coinNameCodePrefix + "." + coinValuesInCents[i];
		}
		return nameCodes;
	}
}
