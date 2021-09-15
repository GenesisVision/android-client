package vision.genesis.clientapp.managers;

import com.sumsub.sns.core.data.listener.TokenExpirationHandler;

import java.io.IOException;
import java.util.Objects;

import io.swagger.client.api.ProfileApi;
import io.swagger.client.model.ExternalKycAccessToken;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import vision.genesis.clientapp.net.api.KycApi;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/11/2020.
 */
public class KycVerificationManager
{
	private final ProfileApi profileApi;

	private final KycApi kycApi;

	private BehaviorSubject<ExternalKycAccessToken> kycModelSubject = BehaviorSubject.create();

	private Subscription getTokenSubscription;

	public KycVerificationManager(ProfileApi profileApi, KycApi kycApi) {
		this.profileApi = profileApi;
		this.kycApi = kycApi;
	}

	public Observable<ExternalKycAccessToken> getKycVerificationData() {
		kycModelSubject = BehaviorSubject.create();
		getTokenSubscription = profileApi.getMobileVerificationToken()
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.io())
				.subscribe(model -> {
							getTokenSubscription.unsubscribe();
							kycModelSubject.onNext(model);
						},
						error -> {
							getTokenSubscription.unsubscribe();
							kycModelSubject.onError(error);
						});
		return kycModelSubject;
	}

	public String getToken() {
		try {
			return Objects.requireNonNull(kycApi.getMobileVerificationToken().execute().body()).getAccessToken();
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	public TokenExpirationHandler getTokenExpirationHandler() {
		return this::getToken;
	}
}