package greedy.coins;

public class USDollarCoinSpecification extends CoinSpecification  {
	
	int[] coinValuesInCents = {100, 25, 10, 5, 1};
	
	public USDollarCoinSpecification() {
		coinValues = coinValuesInCents;
		coinNameCodePrefix = "USDollar";
		coinNameCodes = super.createNameCodes();
	}
	
}

