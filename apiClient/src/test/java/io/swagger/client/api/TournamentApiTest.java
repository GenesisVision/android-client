package io.swagger.client.api;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import io.swagger.client.ApiClient;
import io.swagger.client.model.NewParticipant;
import io.swagger.client.model.ParticipantsFilter;
import io.swagger.client.model.TradesFilter;

/**
 * API tests for TournamentApi
 */
public class TournamentApiTest
{

	private TournamentApi api;

	@Before
	public void setup() {
		api = new ApiClient().createService(TournamentApi.class);
	}

	/**
	 * Participant info
	 */
	@Test
	public void apiTournamentParticipantGetTest() {
		UUID participantId = null;
		// ParticipantViewModel response = api.apiTournamentParticipantGet(participantId);

		// TODO: test validations
	}

	/**
	 * Participant trades history
	 */
	@Test
	public void apiTournamentParticipantTradesPostTest() {
		TradesFilter filter = null;
		// TradesViewModel response = api.apiTournamentParticipantTradesPost(filter);

		// TODO: test validations
	}

	/**
	 * Participants summary
	 */
	@Test
	public void apiTournamentParticipantsCountGetTest() {
		// ParticipantsSummaryViewModel response = api.apiTournamentParticipantsCountGet();

		// TODO: test validations
	}

	/**
	 * Participants list
	 */
	@Test
	public void apiTournamentParticipantsPostTest() {
		ParticipantsFilter filter = null;
		// ParticipantsViewModel response = api.apiTournamentParticipantsPost(filter);

		// TODO: test validations
	}

	/**
	 * Registration for the tournament
	 */
	@Test
	public void apiTournamentRegisterPostTest() {
		NewParticipant model = null;
		// Void response = api.apiTournamentRegisterPost(model);

		// TODO: test validations
	}
}
