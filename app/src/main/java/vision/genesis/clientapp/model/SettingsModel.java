package vision.genesis.clientapp.model;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */
public class SettingsModel
{
	private boolean pinCodeEnabled;

	private boolean twoFactorEnabled;

	private boolean fingerprintEnabled;

	public boolean isPinCodeEnabled() {
		return pinCodeEnabled;
	}

	public void setPinCodeEnabled(boolean pinCodeEnabled) {
		this.pinCodeEnabled = pinCodeEnabled;
	}

	public boolean isTwoFactorEnabled() {
		return twoFactorEnabled;
	}

	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}

	public boolean isFingerprintEnabled() {
		return fingerprintEnabled;
	}

	public void setFingerprintEnabled(boolean fingerprintEnabled) {
		this.fingerprintEnabled = fingerprintEnabled;
	}
}
