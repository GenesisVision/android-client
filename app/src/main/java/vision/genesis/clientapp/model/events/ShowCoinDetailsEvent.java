package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2021.
 */
public class ShowCoinDetailsEvent
{
	private String symbol;

	public ShowCoinDetailsEvent(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}
