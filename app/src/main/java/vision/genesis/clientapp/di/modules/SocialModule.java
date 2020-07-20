package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.SocialApi;
import vision.genesis.clientapp.managers.SocialManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@Module
public class SocialModule
{
	@Provides
	@Singleton
	public SocialManager provideSocialManager(SocialApi socialApi) {
		return new SocialManager(socialApi);
	}
}
