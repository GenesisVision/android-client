package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */
public class SetupTfaConfirmButtonClickedEvent
{
	private String password;

	private String code;

	public SetupTfaConfirmButtonClickedEvent(String password, String code) {

		this.password = password;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getPassword() {
		return password;
	}
}
