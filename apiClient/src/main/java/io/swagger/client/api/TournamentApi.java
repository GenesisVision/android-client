package io.swagger.client.api;

import java.util.UUID;

import io.swagger.client.model.NewParticipant;
import io.swagger.client.model.ParticipantViewModel;
import io.swagger.client.model.ParticipantsFilter;
import io.swagger.client.model.ParticipantsSummaryViewModel;
import io.swagger.client.model.ParticipantsViewModel;
import io.swagger.client.model.TradesFilter;
import io.swagger.client.model.TradesViewModel;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface TournamentApi
{
	/**
	 * Participant info
	 *
	 * @param participantId (required)
	 * @return Call&lt;ParticipantViewModel&gt;
	 */
	@GET("api/tournament/participant")
	Observable<ParticipantViewModel> apiTournamentParticipantGet(
			@retrofit2.http.Query("participantId") UUID participantId
	);

	/**
	 * Participant trades history
	 *
	 * @param filter (optional)
	 * @return Call&lt;TradesViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/tournament/participant/trades")
	Observable<TradesViewModel> apiTournamentParticipantTradesPost(
			@retrofit2.http.Body TradesFilter filter
	);

	/**
	 * Participants summary
	 *
	 * @return Call&lt;ParticipantsSummaryViewModel&gt;
	 */
	@GET("api/tournament/participants/count")
	Observable<ParticipantsSummaryViewModel> apiTournamentParticipantsCountGet();


	/**
	 * Participants list
	 *
	 * @param filter (optional)
	 * @return Call&lt;ParticipantsViewModel&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/tournament/participants")
	Observable<ParticipantsViewModel> apiTournamentParticipantsPost(
			@retrofit2.http.Body ParticipantsFilter filter
	);

	/**
	 * Registration for the tournament
	 *
	 * @param model (optional)
	 * @return Call&lt;Void&gt;
	 */
	@Headers({
			"Content-Type:application/json"
	})
	@POST("api/tournament/register")
	Observable<Void> apiTournamentRegisterPost(
			@retrofit2.http.Body NewParticipant model
	);

}
