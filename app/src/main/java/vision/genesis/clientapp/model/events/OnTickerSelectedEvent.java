package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/02/2021.
 */
public class OnTickerSelectedEvent
{
	private String symbol;

	public OnTickerSelectedEvent(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}
