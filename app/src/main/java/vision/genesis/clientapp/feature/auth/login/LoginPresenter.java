package vision.genesis.clientapp.feature.auth.login;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import javax.inject.Inject;

import io.swagger.client.model.ErrorViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnCheckTfaConfirmClickedEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaErrorEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaSuccessEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription loginSubscription;

	private String email;

	private String password;

	private boolean tfaEnabled;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		if (loginSubscription != null)
			loginSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onSignUpClicked() {
		getViewState().showRegistrationActivity();
	}

	void onSignInClicked(String email, String password) {
		this.email = email;
		this.password = password;
		login(null, false);
	}

	private void login(String tfaCode, boolean useRecoveryCode) {
		getViewState().clearErrors();
		getViewState().showProgress();
		loginSubscription = authManager.login(email, password, tfaCode, useRecoveryCode)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::onLoginResponse,
						this::onLoginError);
	}

	private void onLoginResponse(String response) {
		loginSubscription.unsubscribe();

		getViewState().finishActivity(false);
		if (tfaEnabled)
			EventBus.getDefault().post(new OnCheckTfaSuccessEvent());
	}

	private void onLoginError(Throwable throwable) {
		loginSubscription.unsubscribe();

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (tfaEnabled)
				EventBus.getDefault().post(new OnCheckTfaErrorEvent(context.getResources().getString(R.string.network_error)));
			else
				getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
			getViewState().hideProgress();
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				if (response.code.equals(ErrorViewModel.CodeEnum.REQUIRESTWOFACTOR.toString())) {
					String action = String.format(Locale.getDefault(), "%s %s", context.getString(R.string.sign_in_as), email);
					getViewState().startCheckTfaActivity(action);
				}
				else {
					for (Error error : response.errors) {
						if (error.property == null || error.property.isEmpty()) {
							if (tfaEnabled)
								EventBus.getDefault().post(new OnCheckTfaErrorEvent(error.message));
							else
								getViewState().showSnackbarMessage(error.message);
						}
						else {
							switch (error.property.toLowerCase()) {
								case "email":
									getViewState().setEmailError(error.message);
									break;
								case "password":
									getViewState().setPasswordError(error.message);
									break;
								default:
									getViewState().showSnackbarMessage(error.message);
									break;
							}
						}
					}
				}
			}
			getViewState().hideProgress();
			tfaEnabled = false;
		}
	}

	@Subscribe
	public void onEventMainThread(OnCheckTfaConfirmClickedEvent event) {
		tfaEnabled = true;
		login(event.getCode(), event.isUseRecoveryCode());
	}
}
