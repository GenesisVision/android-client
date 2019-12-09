package vision.genesis.clientapp.feature.main.follow.edit;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.CreateSignalProvider;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.events.OnFollowSettingsConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/12/2019.
 */

@InjectViewState
public class EditFollowSettingsPresenter extends MvpPresenter<EditFollowSettingsView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription updatePublicInfoSubscription;

	private CreateSignalProvider model;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

	}

	@Override
	public void onDestroy() {
		if (updatePublicInfoSubscription != null) {
			updatePublicInfoSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(CreateSignalProvider model) {
		this.model = model;
	}

	private void updateSettings() {
		getViewState().showProgress(true);

		if (assetsManager != null) {
			updatePublicInfoSubscription = assetsManager.updateSignalProviderSettings(model)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleUpdateSuccess,
							this::handleUpdateError);
		}
	}

	private void handleUpdateSuccess(Void response) {
		updatePublicInfoSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleUpdateError(Throwable throwable) {
		updatePublicInfoSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnFollowSettingsConfirmEvent event) {
		model.setVolumeFee(event.getVolumeFee());
		model.setSuccessFee(event.getSuccessFee());

		updateSettings();
	}
}