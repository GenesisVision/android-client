package vision.genesis.clientapp.feature.two_factor.setup;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.RecoveryCode;
import io.swagger.client.model.RecoveryCodesViewModel;
import io.swagger.client.model.TwoFactorAuthenticator;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.SetupTfaConfirmButtonClickedEvent;
import vision.genesis.clientapp.model.events.SetupTfaFinishedEvent;
import vision.genesis.clientapp.model.events.SetupTfaNextButtonClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/05/2018.
 */

@InjectViewState
public class SetupTfaPresenter extends MvpPresenter<SetupTfaView>
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	private Subscription createTfaKeySubscription;

	private Subscription confirmTfaKeySubscription;

	private String sharedKey;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		createTfaKey();
	}

	@Override
	public void onDestroy() {
		if (createTfaKeySubscription != null) {
			createTfaKeySubscription.unsubscribe();
		}
		if (confirmTfaKeySubscription != null) {
			confirmTfaKeySubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	private void createTfaKey() {
		if (createTfaKeySubscription != null) {
			createTfaKeySubscription.unsubscribe();
		}
		createTfaKeySubscription = authManager.createTfaKey()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleCreateTfaKeySuccess,
						this::handleCreateTfaKeyError);
	}

	private void handleCreateTfaKeySuccess(TwoFactorAuthenticator response) {
		createTfaKeySubscription.unsubscribe();
		this.sharedKey = response.getSharedKey();
		getViewState().setKey(sharedKey, response.getAuthenticatorUri());
	}

	private void handleCreateTfaKeyError(Throwable throwable) {
		createTfaKeySubscription.unsubscribe();
		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbar(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null && response.errors != null && response.errors.get(0) != null) {
				getViewState().showSnackbar(response.errors.get(0).message);
			}
		}
	}

	private void confirmTfa(String password, String code) {
		getViewState().showProgress(true);
		if (confirmTfaKeySubscription != null) {
			confirmTfaKeySubscription.unsubscribe();
		}
		confirmTfaKeySubscription = authManager.confirmTfa(sharedKey, password, code)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(this::handleConfirmTfaKeySuccess,
						this::handleConfirmTfaKeyError);
	}

	private void handleConfirmTfaKeySuccess(RecoveryCodesViewModel response) {
		confirmTfaKeySubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().hideKeyboard();

		authManager.saveNewToken(response.getAuthToken());
		authManager.setTwoFactorStatus(true);
		getViewState().onConfirmSuccess(getCodesStringList(response.getCodes()));
	}

	private ArrayList<String> getCodesStringList(List<RecoveryCode> codes) {
		ArrayList<String> codesStrings = new ArrayList<>();
		for (RecoveryCode code : codes) {
			String codeString = code.getCode();
			int length = codeString.length();
			codesStrings.add(codeString.substring(0, length / 2)
					.concat(" ")
					.concat(codeString.substring(length / 2, length)));
		}
		return codesStrings;
	}

	private void handleConfirmTfaKeyError(Throwable throwable) {
		createTfaKeySubscription.unsubscribe();
		getViewState().showProgress(false);
		if (ApiErrorResolver.isNetworkError(throwable)) {
			getViewState().showSnackbar(context.getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null && response.errors != null && response.errors.get(0) != null) {
				getViewState().showSnackbar(response.errors.get(0).message);
			}
		}
	}

	@Subscribe
	public void onEventMainThread(SetupTfaNextButtonClickedEvent event) {
		getViewState().showNextStep();
	}

	@Subscribe
	public void onEventMainThread(SetupTfaConfirmButtonClickedEvent event) {
		confirmTfa(event.getPassword(), event.getCode());
	}

	@Subscribe
	public void onEventMainThread(SetupTfaFinishedEvent event) {
		getViewState().finishActivity();
	}
}