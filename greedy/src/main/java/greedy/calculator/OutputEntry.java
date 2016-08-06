package greedy.calculator;

public class OutputEntry {
	
	String coinCode;
	int numberOfCoins;
	
	public OutputEntry(String coinCode, int numberOfCoins) {
		this.coinCode = coinCode;
		this.numberOfCoins = numberOfCoins;
	}
	
	public String getCoinCode() {
		return coinCode;
	}
	
	public int getNumberOfCoins() {
		return numberOfCoins;
	}
	
	@Override
	public String toString() {
		String entry = coinCode + ": " + numberOfCoins;
		return entry;
	}

}
