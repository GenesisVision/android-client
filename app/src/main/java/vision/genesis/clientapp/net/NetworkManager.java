package vision.genesis.clientapp.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import rx.subjects.BehaviorSubject;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class NetworkManager
{
	private static final int CHECKER_INTERVAL_SECONDS = 2;

	public static BehaviorSubject<Boolean> serverAvailabilitySubject = BehaviorSubject.create();

	private static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public BehaviorSubject<Boolean> networkAvailabilitySubject = BehaviorSubject.create();

	private Context context;

	public NetworkManager(Context context) {
		this.context = context;

		ScheduledExecutorService checker = Executors.newSingleThreadScheduledExecutor();
		checker.scheduleAtFixedRate(this::performChecking, 0, CHECKER_INTERVAL_SECONDS, TimeUnit.SECONDS);
	}

	private void performChecking() {
		networkAvailabilitySubject.onNext(isNetworkAvailable((context)));
	}
}