package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.AccountApi;
import vision.genesis.clientapp.managers.AuthManager;

/**
 * GenesisVision
 * Created by Vitaly on 1/22/18.
 */

@Module
public class AuthModule
{
	@Provides
	@Singleton
	public AuthManager provideAuthManager(AccountApi api) {
		return new AuthManager(api);
	}
}
