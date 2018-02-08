package vision.genesis.clientapp.feature.tournament.participants.details;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.ParticipantViewModel;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

interface ParticipantDetailsView extends MvpView
{
	void setParticipant(ParticipantViewModel participant);

	void showLoading(boolean show);

	void showCannotLoad(boolean show);
}
