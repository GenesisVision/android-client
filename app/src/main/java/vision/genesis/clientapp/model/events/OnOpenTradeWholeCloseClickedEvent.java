package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2019.
 */
public class OnOpenTradeWholeCloseClickedEvent
{
	private UUID tradeId;

	private String symbol;

	private Double volume;

	public OnOpenTradeWholeCloseClickedEvent(UUID tradeId, String symbol, Double volume) {
		this.tradeId = tradeId;
		this.symbol = symbol;
		this.volume = volume;
	}

	public UUID getTradeId() {
		return tradeId;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getVolume() {
		return volume;
	}
}
