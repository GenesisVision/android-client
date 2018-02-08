package vision.genesis.clientapp.model.events;

import io.swagger.client.model.ParticipantViewModel;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

public class OnParticipantItemListClicked
{
	public ParticipantViewModel participant;

	public OnParticipantItemListClicked(ParticipantViewModel participant) {
		this.participant = participant;
	}
}
