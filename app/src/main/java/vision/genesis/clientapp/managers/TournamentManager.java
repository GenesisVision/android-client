package vision.genesis.clientapp.managers;

import io.swagger.client.api.TournamentApi;
import io.swagger.client.model.ParticipantsFilter;
import io.swagger.client.model.ParticipantsSummaryViewModel;
import io.swagger.client.model.ParticipantsViewModel;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

public class TournamentManager
{
	public BehaviorSubject<ParticipantsFilter> filterSubject = BehaviorSubject.create();

	private TournamentApi tournamentApi;

	public TournamentManager(TournamentApi tournamentApi) {
		this.tournamentApi = tournamentApi;

		filterSubject.onNext(new ParticipantsFilter());
	}

	public ParticipantsFilter getFilter() {
		return filterSubject.getValue();
	}

	public void setFilter(ParticipantsFilter filter) {
		filterSubject.onNext(filter);
	}

	public Observable<ParticipantsViewModel> getParticipantsList(ParticipantsFilter filter) {
		return tournamentApi.apiTournamentParticipantsPost(filter);
	}

	public Observable<ParticipantsSummaryViewModel> getParticipantsCount() {
		return tournamentApi.apiTournamentParticipantsCountGet();
	}
}