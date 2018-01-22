package vision.genesis.clientapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;
import vision.genesis.clientapp.di.components.AppComponent;
import vision.genesis.clientapp.di.components.DaggerAppComponent;
import vision.genesis.clientapp.di.modules.AppModule;

/**
 * GenesisVision
 * Created by Vitaly on 1/18/18.
 */

public class GenesisVisionApplication extends Application
{
	public static GenesisVisionApplication INSTANCE;

	private static AppComponent component;

	public static AppComponent getComponent() {
		return component;
	}

	private Cicerone<Router> cicerone;

	@Override
	public void onCreate() {
		super.onCreate();

		INSTANCE = this;

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}

		initCicerone();

		component = buildComponent();

		Fresco.initialize(this);
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	public NavigatorHolder getNavigatorHolder() {
		return cicerone.getNavigatorHolder();
	}

	public Router getRouter() {
		return cicerone.getRouter();
	}

	private AppComponent buildComponent() {
		return DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}

	private void initCicerone() {
		cicerone = Cicerone.create();
	}
}
