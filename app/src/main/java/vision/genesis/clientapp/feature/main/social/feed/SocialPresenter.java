package vision.genesis.clientapp.feature.main.social.feed;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class SocialPresenter extends MvpPresenter<SocialView>
{
	@Inject
	public Context context;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription baseCurrencySubscription;

	private Subscription publicProfileSubscription;

	private CurrencyEnum baseCurrency;

	private boolean showEvents = true;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getShowEvents();
//		getProfileInfo();
//		subscribeToBaseCurrency();
	}

	private void getShowEvents() {
		showEvents = settingsManager.getShowEvents();
		getViewState().initViewPager(showEvents);
		getViewState().setShowEventsChecked(showEvents);
	}

	@Override
	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (publicProfileSubscription != null) {
			publicProfileSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void onResume() {
//		getProfileInfo();
	}

	void onShowEventsCheckChanged(boolean checked) {
		showEvents = checked;
		settingsManager.saveShowEvents(checked);
		getViewState().setShowEventsChecked(showEvents);
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

//	private void getProfileInfo() {
//		profileSubscription = profileManager.getProfileFull(true)
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(this::handleGetProfileSuccess,
//						this::handleGetProfileError);
//	}
//
//	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
//		getViewState().showProgress(false);
//	}
//
//	private void handleGetProfileError(Throwable throwable) {
//		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
//	}

//	private void subscribeToBaseCurrency() {
//		baseCurrencySubscription = settingsManager.getBaseCurrency()
//				.subscribeOn(Schedulers.newThread())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(this::baseCurrencyChangedHandler);
//	}
//
//	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
//		this.baseCurrency = baseCurrency;
//	}
//
//	@Override
//	public void onCurrencyChanged(CurrencyEnum currency) {
//		settingsManager.saveBaseCurrency(currency);

//	}
}
