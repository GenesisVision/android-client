package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.TournamentApi;
import vision.genesis.clientapp.managers.TournamentManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/24/18.
 */

@Module
public class TournamentModule
{
	@Provides
	@Singleton
	public TournamentManager provideTournamentManager(TournamentApi tournamentApi) {
		return new TournamentManager(tournamentApi);
	}
}
