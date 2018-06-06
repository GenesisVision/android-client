package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/06/2018.
 */
public class OnCheckTfaConfirmClickedEvent
{
	private String code;

	public OnCheckTfaConfirmClickedEvent(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
