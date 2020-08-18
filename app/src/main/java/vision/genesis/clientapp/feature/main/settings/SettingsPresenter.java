package vision.genesis.clientapp.feature.main.settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> implements SelectCurrencyFragment.OnCurrencyChangedListener
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription profileSubscription;

	private Subscription baseCurrencySubscription;

	private Subscription sendPlatformCurrencySubscription;

	private Subscription publicProfileSubscription;

	private CurrencyEnum baseCurrency;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

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
		if (sendPlatformCurrencySubscription != null) {
			sendPlatformCurrencySubscription.unsubscribe();
		}
		if (publicProfileSubscription != null) {
			publicProfileSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onResume() {
		getProfileInfo();
	}

	void onLogoutClicked() {
		authManager.logout();
	}

	void onPublicInvestorProfileCheckedChanged(boolean checked) {
		setPublicInvestorProfile(checked);
	}

	private void setPublicInvestorProfile(boolean on) {
		if (profileManager != null) {
			if (publicProfileSubscription != null) {
				publicProfileSubscription.unsubscribe();
			}
			publicProfileSubscription = profileManager.setPublicInvestorProfile(on)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handlePublicProfileSuccess,
							this::handlePublicProfileError);
		}
	}

	private void handlePublicProfileSuccess(Void response) {
		publicProfileSubscription.unsubscribe();
	}

	private void handlePublicProfileError(Throwable throwable) {
		publicProfileSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getProfileInfo() {
		profileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		getViewState().updateProfile(profile);
		getViewState().showProgress(false);
	}

	private void handleGetProfileError(Throwable throwable) {
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getViewState().setBaseCurrency(baseCurrency);
	}

	@Override
	public void onCurrencyChanged(CurrencyEnum currency) {
		settingsManager.saveBaseCurrency(currency);
		sendPlatformCurrency(currency);
	}

	private void sendPlatformCurrency(CurrencyEnum currency) {
		if (profileManager != null) {
			sendPlatformCurrencySubscription = profileManager.updatePlatformCurrency(currency.getValue())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> sendPlatformCurrencySubscription.unsubscribe(),
							throwable -> sendPlatformCurrencySubscription.unsubscribe());
		}
	}
}
