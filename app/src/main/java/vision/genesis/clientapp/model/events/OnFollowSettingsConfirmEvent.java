package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/12/2019.
 */
public class OnFollowSettingsConfirmEvent
{
	private final double volumeFee;

	private final double successFee;

	public OnFollowSettingsConfirmEvent(double volumeFee, double successFee) {
		this.volumeFee = volumeFee;
		this.successFee = successFee;
	}

	public double getVolumeFee() {
		return volumeFee;
	}

	public double getSuccessFee() {
		return successFee;
	}
}
