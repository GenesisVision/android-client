package vision.genesis.clientapp.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import io.swagger.client.model.PushNotificationChannel;
import io.swagger.client.model.PushNotificationViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;

/**
 * GenesisVisionAndroid
 * Created on 11/11/2019.
 */

public class GvFirebaseMessagingService extends FirebaseMessagingService
{
	public static final String KEY_RESULT = "result";

	public static final String KEY_NOTIFICATION = "key_notification";

	private final NotificationManager notificationManager;

	@Inject
	public AuthManager authManager;

	public GvFirebaseMessagingService() {
		super();
		GenesisVisionApplication.getComponent().inject(this);
		notificationManager = (NotificationManager) GenesisVisionApplication.INSTANCE.getSystemService(Context.NOTIFICATION_SERVICE);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			createNotificationChannels();
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	private void createNotificationChannels() {
		createChannel(PushNotificationChannel.PLATFORM.getValue(), GenesisVisionApplication.INSTANCE.getString(R.string.notification_channel_name_platform), NotificationManager.IMPORTANCE_DEFAULT);
		createChannel(PushNotificationChannel.PROFILE.getValue(), GenesisVisionApplication.INSTANCE.getString(R.string.notification_channel_name_profile), NotificationManager.IMPORTANCE_DEFAULT);
		createChannel(PushNotificationChannel.ASSET.getValue(), GenesisVisionApplication.INSTANCE.getString(R.string.notification_channel_name_assets), NotificationManager.IMPORTANCE_DEFAULT);
		createChannel(PushNotificationChannel.USER.getValue(), GenesisVisionApplication.INSTANCE.getString(R.string.notification_channel_name_user), NotificationManager.IMPORTANCE_DEFAULT);
		createChannel(PushNotificationChannel.SOCIAL.getValue(), GenesisVisionApplication.INSTANCE.getString(R.string.notification_channel_name_social), NotificationManager.IMPORTANCE_DEFAULT);
		createChannel(PushNotificationChannel.SIGNAL.getValue(), GenesisVisionApplication.INSTANCE.getString(R.string.notification_channel_name_signals), NotificationManager.IMPORTANCE_DEFAULT);
		createChannel(PushNotificationChannel.OTHER.getValue(), GenesisVisionApplication.INSTANCE.getString(R.string.notification_channel_name_other), NotificationManager.IMPORTANCE_DEFAULT);
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	private void createChannel(String channelId, String channelName, int importanceLevel) {
		if (notificationManager != null) {
			NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importanceLevel);
			notificationChannel.enableLights(true);
			notificationChannel.setVibrationPattern(new long[]{0, 300, 100, 300});
			notificationChannel.enableVibration(true);

			notificationManager.createNotificationChannel(notificationChannel);
		}
	}

	@Override
	public void onNewToken(@NonNull String token) {
		Timber.d("FCM_TOKEN_ON_NEW_TOKEN: %s", token);
		sendFcmToken(token);
		FirebaseMessaging.getInstance().subscribeToTopic("all");
	}

	private void sendFcmToken(String token) {
		if (authManager != null) {
			authManager.updateFcmTokenMaybe(token);
		}
	}

	@Override
	public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
		super.onMessageReceived(remoteMessage);

		String payload = remoteMessage.getData().get(KEY_RESULT);
		Timber.d("FCM_PUSH: %s", payload);

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						DateTime.class,
						(JsonDeserializer<DateTime>) (json, typeOfT, context) -> ISODateTimeFormat.dateTime().parseDateTime(json.getAsString())
				)
				.create();

		Type typeToken = new TypeToken<PushNotificationViewModel>()
		{
		}.getType();

		PushNotificationViewModel data = null;
		try {
			data = gson.fromJson(payload, typeToken);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (data != null) {
			String type = data.getType();
			if (type != null) {
				Intent intent = new Intent(this, FcmPushClickHandlerService.class);
				intent.setAction(data.getId().toString());
				intent.putExtra(KEY_NOTIFICATION, data);

//				switch (type) {
//					case NotificationType.PLATFORMNEWSANDUPDATES:
//					case NotificationType.PLATFORMEMERGENCY:
//					case NotificationType.PLATFORMOTHER:
//						showNotification(data, PLATFORM_CHANNEL_ID, intent);
//						break;
//					case NotificationType.PROFILEUPDATED:
//					case NotificationType.PROFILEPWDUPDATED:
//					case NotificationType.PROFILEVERIFICATION:
//					case NotificationType.PROFILE2FA:
//					case NotificationType.PROFILESECURITY:
//						showNotification(data, PROFILE_CHANNEL_ID, intent);
//						break;
//					case NotificationType.TRADINGACCOUNTPWDUPDATED:
//					case NotificationType.TRADINGACCOUNTUPDATED:
//					case NotificationType.PROGRAMNEWSANDUPDATES:
//					case NotificationType.PROGRAMENDOFPERIOD:
//					case NotificationType.PROGRAMCONDITION:
//					case NotificationType.PROGRAMEXCEEDINVESTMENTLIMIT:
//					case NotificationType.FOLLOWNEWSANDUPDATES:
//					case NotificationType.FUNDNEWSANDUPDATES:
//					case NotificationType.FUNDENDOFPERIOD:
//					case NotificationType.FUNDREBALANCING:
//						showNotification(data, ASSETS_CHANNEL_ID, intent);
//						break;
//					case NotificationType.MANAGERNEWPROGRAM:
//					case NotificationType.MANAGERNEWFUND:
//					case NotificationType.MANAGERNEWEXTERNALSIGNALACCOUNT:
//					case NotificationType.MANAGERNEWSIGNALPROVIDER:
//						showNotification(data, MANAGER_CHANNEL_ID, intent);
//						break;
//					case NotificationType.SIGNALS:
//					case NotificationType.EXTERNALSIGNALS.get:
//						showNotification(data, SIGNALS_CHANNEL_ID, intent);
//						break;
//					default:
//						showNotification(data, OTHER_CHANNEL_ID, intent);
//						break;
//				}
				showNotification(data, data.getChannel(), intent);
			}
		}
	}

	private void showNotification(PushNotificationViewModel notification, PushNotificationChannel notificationChannel, Intent intent) {
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);

		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, notificationChannel.getValue())
				.setWhen(System.currentTimeMillis())
				.setAutoCancel(true)
				.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
				.setContentTitle(getString(R.string.app_name))
				.setContentText(notification.getText())
				.setStyle(new NotificationCompat.BigTextStyle())
				.setContentIntent(pendingIntent);

		if (notification.getImageUrl() != null && !notification.getImageUrl().isEmpty()) {
			FutureTarget target = Glide.with(this)
					.asBitmap()
					.load(notification.getImageUrl())
					.submit();
			try {
//				notificationBuilder.setLargeIcon((Bitmap) target.get());
				notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle()
						.bigPicture((Bitmap) target.get()));
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		notificationBuilder.setSmallIcon(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
				? R.mipmap.icon_investor
				: R.drawable.ic_stat_logo);

		notificationManager.notify(notification.getId().toString(), 101, notificationBuilder.build());
	}
}