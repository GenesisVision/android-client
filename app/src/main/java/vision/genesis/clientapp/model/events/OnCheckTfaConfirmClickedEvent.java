package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 06/06/2018.
 */
public class OnCheckTfaConfirmClickedEvent
{
	private String code;

	private boolean useRecoveryCode;

	public OnCheckTfaConfirmClickedEvent(String code, boolean useRecoveryCode) {
		this.code = code;
		this.useRecoveryCode = useRecoveryCode;
	}

	public String getCode() {
		return code;
	}

	public boolean isUseRecoveryCode() {
		return useRecoveryCode;
	}
}
