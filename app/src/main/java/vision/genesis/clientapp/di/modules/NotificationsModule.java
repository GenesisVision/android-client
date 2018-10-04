package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.swagger.client.api.NotificationsApi;
import vision.genesis.clientapp.managers.NotificationsManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */

@Module
public class NotificationsModule
{
	@Provides
	@Singleton
	public NotificationsManager provideNotificationsManager(NotificationsApi notificationsApi) {
		return new NotificationsManager(notificationsApi);
	}
}
