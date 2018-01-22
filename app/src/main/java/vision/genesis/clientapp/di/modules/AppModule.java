package vision.genesis.clientapp.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

@Module
public class AppModule
{
	private Context appContext;

	public AppModule(@NonNull Context appContext) {
		this.appContext = appContext;
	}

	@Provides
	@Singleton
	Context provideContext() {
		return appContext;
	}
}
