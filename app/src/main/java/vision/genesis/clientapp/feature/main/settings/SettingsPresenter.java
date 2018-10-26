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
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView>
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

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getProfileInfo();
	}

	@Override
	public void onDestroy() {
		if (profileSubscription != null)
			profileSubscription.unsubscribe();

		super.onDestroy();
	}

	void onResume() {
		getProfileInfo();
	}

	private void getProfileInfo() {
		profileSubscription = profileManager.getProfileFull()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);

	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		getViewState().updateProfile(profile);
	}

	private void handleGetProfileError(Throwable throwable) {

	}

	public void onLogoutClicked() {
		authManager.logout();
	}

//	public void onDarkThemeClicked(boolean checked) {
//		String newTheme = checked ? ThemeUtil.THEME_LIGHT : ThemeUtil.THEME_DARK;
//		settingsManager.setTheme(newTheme);
//		EventBus.getDefault().post(new OnThemeChangedEvent());
//		getViewState().changeThemeWithAnim(newTheme);
//	}
}
