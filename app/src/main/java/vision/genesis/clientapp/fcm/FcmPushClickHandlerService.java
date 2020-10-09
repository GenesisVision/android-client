package vision.genesis.clientapp.fcm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import vision.genesis.clientapp.feature.main.MainActivity;
import vision.genesis.clientapp.feature.splashscreen.SplashScreenActivity;
import vision.genesis.clientapp.model.events.HandlePushEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2020.
 */
public class FcmPushClickHandlerService extends Service
{
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null && intent.getExtras() != null) {
			if (MainActivity.isActive) {
				EventBus.getDefault().post(new HandlePushEvent(intent.getExtras()));
			}
			else {
				Intent splashScreenIntent = new Intent(this, SplashScreenActivity.class);
				splashScreenIntent.putExtras(intent.getExtras());
				getApplicationContext().startActivity(splashScreenIntent);
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}
}
