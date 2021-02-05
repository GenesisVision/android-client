package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 02/02/2021.
 */
public class OnTickerSelectedEvent
{
	private MarketWatchTickerModel ticker;

	public OnTickerSelectedEvent(MarketWatchTickerModel ticker) {
		this.ticker = ticker;
	}

	public MarketWatchTickerModel getTicker() {
		return ticker;
	}
}
