package vision.genesis.clientapp.model.events;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/09/2018.
 */
public class ShowBottomNavigationEvent
{
	private Boolean animate;

	public ShowBottomNavigationEvent(Boolean animate) {
		this.animate = animate;
	}

	public Boolean getAnimate() {
		return animate;
	}
}
