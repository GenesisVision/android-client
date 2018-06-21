package vision.genesis.clientapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import timber.log.Timber;
import vision.genesis.clientapp.di.components.AppComponent;
import vision.genesis.clientapp.di.components.DaggerAppComponent;
import vision.genesis.clientapp.di.modules.AppModule;
import vision.genesis.clientapp.utils.AppLifecycleTracker;
import vision.genesis.clientapp.utils.TypefaceUtil;

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

	public static void initializeSSLContext(Context mContext) {
		try {
			SSLContext.getInstance("TLSv1.2");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			ProviderInstaller.installIfNeeded(mContext.getApplicationContext());
		} catch (GooglePlayServicesRepairableException e) {
			e.printStackTrace();
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();

		INSTANCE = this;

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}

		component = buildComponent();

		initializeSSLContext(this);

		Fresco.initialize(this);

		overrideFonts();

		registerActivityLifecycleCallbacks(new AppLifecycleTracker());
	}


	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	private AppComponent buildComponent() {
		return DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}

	private void overrideFonts() {
		TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/NeuzeitGro-Reg.ttf");
	}
}
