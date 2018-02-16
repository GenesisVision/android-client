package vision.genesis.clientapp.feature.tournament.participants.details;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ParticipantViewModel;
import ru.terrakok.cicerone.Router;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.TournamentManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

@InjectViewState
public class ParticipantDetailsPresenter extends MvpPresenter<ParticipantDetailsView>
{
	@Inject
	public Context context;

	@Inject
	public TournamentManager tournamentManager;

	@Inject
	public Router router;

	private Subscription getParticipantDetailsSubscription;

	private ParticipantViewModel participant;

	private UUID participantId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		if (participantId != null) {
			getParticipantDetails(participantId);
		}
	}

	@Override
	public void onDestroy() {
		if (getParticipantDetailsSubscription != null)
			getParticipantDetailsSubscription.unsubscribe();

		super.onDestroy();
	}

	void onBackClicked() {
		router.exit();
	}

	void setParticipantId(UUID participantId) {
		this.participantId = participantId;
	}

	void onTryAgainClicked() {
		getParticipantDetails(participantId);
	}

	private void getParticipantDetails(UUID participantId) {
		getViewState().showCannotLoad(false);
		getViewState().showLoading(true);
		getParticipantDetailsSubscription = tournamentManager.getParticipantDetails(participantId)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleGetParticipantDetailsResponse,
						this::handleGetParticipantDetailsError);
	}

	private void handleGetParticipantDetailsResponse(ParticipantViewModel participant) {
		getParticipantDetailsSubscription.unsubscribe();
		this.participant = participant;
		getViewState().setParticipant(participant);
		getViewState().showLoading(false);
		getViewState().showCannotLoad(false);
	}

	private void handleGetParticipantDetailsError(Throwable throwable) {
		getParticipantDetailsSubscription.unsubscribe();
		getViewState().showLoading(false);
		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showCannotLoad(true);
		}
	}
}
