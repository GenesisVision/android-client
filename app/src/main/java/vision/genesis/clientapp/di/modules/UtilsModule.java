package vision.genesis.clientapp.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vision.genesis.clientapp.net.NetworkManager;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.SharedPreferencesUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/29/18.
 */

@Module
public class UtilsModule
{
	@Provides
	@Singleton
	public SharedPreferencesUtil provideSharedPreferencesUtil(Context context) {
		return new SharedPreferencesUtil(context);
	}

	@Provides
	@Singleton
	public ImageUtils provideImageUtils() {
		return new ImageUtils();
	}

	@Provides
	@Singleton
	public NetworkManager provideNetworkManager(Context context) {
		return new NetworkManager(context);
	}
}
