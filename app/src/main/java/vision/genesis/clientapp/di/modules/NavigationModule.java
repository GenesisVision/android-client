package vision.genesis.clientapp.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import vision.genesis.clientapp.navigation.LocalCiceroneHolder;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@Module
public class NavigationModule
{
	@Provides
	@Singleton
	Cicerone<Router> provideCicerone() {
		return Cicerone.create();
	}

	@Provides
	@Singleton
	public Router provideRouter(Cicerone<Router> cicerone) {
		return cicerone.getRouter();
	}

	@Provides
	@Singleton
	public NavigatorHolder provideNavigatorHolder(Cicerone<Router> cicerone) {
		return cicerone.getNavigatorHolder();
	}

	@Provides
	@Singleton
	public LocalCiceroneHolder provideLocalCiceroneHolder() {
		return new LocalCiceroneHolder();
	}
}
