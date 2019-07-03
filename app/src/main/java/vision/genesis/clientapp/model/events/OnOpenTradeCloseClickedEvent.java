package vision.genesis.clientapp.model.events;

import java.util.UUID;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/07/2019.
 */
public class OnOpenTradeCloseClickedEvent
{
	private UUID programId;

	private String symbol;

	private Double volume;

	public OnOpenTradeCloseClickedEvent(UUID programId, String symbol, Double volume) {
		this.programId = programId;
		this.symbol = symbol;
		this.volume = volume;
	}

	public UUID getProgramId() {
		return programId;
	}

	public String getSymbol() {
		return symbol;
	}

	public Double getVolume() {
		return volume;
	}
}
