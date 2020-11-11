package vision.genesis.clientapp.model.events;

import io.swagger.client.model.UserVerificationStatus;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/11/2020.
 */
public class ShowVerificationInfoActivityEvent
{
	private final UserVerificationStatus verificationStatus;

	public ShowVerificationInfoActivityEvent(UserVerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public UserVerificationStatus getVerificationStatus() {
		return verificationStatus;
	}
}
