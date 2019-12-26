package vision.genesis.clientapp.feature.main.settings.referral_program.info;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.PartnershipDetails;
import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.PartnershipManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

@InjectViewState
public class ReferralInfoPresenter extends MvpPresenter<ReferralInfoView>
{
	@Inject
	public ProfileManager profileManager;

	@Inject
	public SettingsManager settingsManager;

	@Inject
	public PartnershipManager partnershipManager;

	private Subscription profileSubscription;

	private Subscription baseCurrencySubscription;

	private Subscription getDetailsSubscription;

	private CurrencyEnum baseCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().showRefProgress(true);
		getViewState().showDetailsProgress(true);

		getProfileInfo();
		subscribeToBaseCurrency();
	}

	@Override
	public void onDestroy() {
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (getDetailsSubscription != null) {
			getDetailsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onShow() {
		getProfileInfo();
		getDetails();
	}

	void onSwipeRefresh() {
		getViewState().showRefProgress(true);
		getViewState().showDetailsProgress(true);
		getProfileInfo();
		getDetails();
	}

	private void getProfileInfo() {
		if (profileManager != null) {
			profileSubscription = profileManager.getProfileFull(true)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetProfileSuccess,
							this::handleGetProfileError);
		}
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		profileSubscription.unsubscribe();
		getViewState().showRefProgress(false);

		getViewState().setRefUrl(profile.getRefUrl());
	}

	private void handleGetProfileError(Throwable throwable) {
		profileSubscription.unsubscribe();
		getViewState().showRefProgress(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToBaseCurrency() {
		if (settingsManager != null) {
			baseCurrencySubscription = settingsManager.getBaseCurrency()
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::baseCurrencyChangedHandler);
		}
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getDetails();
	}

	private void getDetails() {
		if (partnershipManager != null && baseCurrency != null) {
			getDetailsSubscription = partnershipManager.getDetails(baseCurrency.getValue())
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetDetailsSuccess,
							this::handleGetDetailsError);
		}
	}

	private void handleGetDetailsSuccess(PartnershipDetails response) {
		getDetailsSubscription.unsubscribe();
		getViewState().showDetailsProgress(false);

		getViewState().setDetails(response, baseCurrency.getValue());
	}

	private void handleGetDetailsError(Throwable throwable) {
		getDetailsSubscription.unsubscribe();
		getViewState().showDetailsProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}
}
