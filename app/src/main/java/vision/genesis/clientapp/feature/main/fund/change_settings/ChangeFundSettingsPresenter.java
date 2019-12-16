package vision.genesis.clientapp.feature.main.fund.change_settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.ProgramUpdate;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.AssetsManager;
import vision.genesis.clientapp.model.events.OnFundFeesConfirmEvent;
import vision.genesis.clientapp.model.events.OnFundSettingsChangedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/12/2019.
 */

@InjectViewState
public class ChangeFundSettingsPresenter extends MvpPresenter<ChangeFundSettingsView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription updateSettingsSubscription;

	private ProgramUpdate model;

	private UUID assetId;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

	}

	@Override
	public void onDestroy() {
		if (updateSettingsSubscription != null) {
			updateSettingsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID assetId, ProgramUpdate model) {
		this.assetId = assetId;
		this.model = model;
	}

	private void updateFundSettings() {
		getViewState().showProgress(true);

		if (assetsManager != null) {
			updateSettingsSubscription = assetsManager.updateFundSettings(assetId, model)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleUpdateSuccess,
							this::handleUpdateError);
		}
	}

	private void handleUpdateSuccess(Void response) {
		updateSettingsSubscription.unsubscribe();

		EventBus.getDefault().post(new OnFundSettingsChangedEvent(model));
		getViewState().finishActivity();
	}

	private void handleUpdateError(Throwable throwable) {
		updateSettingsSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnFundFeesConfirmEvent event) {
		model.setEntryFee(event.getModel().getEntryFee());
		model.setExitFee(event.getModel().getExitFee());

		updateFundSettings();
	}
}