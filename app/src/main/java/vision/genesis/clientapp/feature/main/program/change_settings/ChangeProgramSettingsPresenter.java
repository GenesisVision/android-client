package vision.genesis.clientapp.feature.main.program.change_settings;

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
import vision.genesis.clientapp.model.events.OnProgramSettingsChangedEvent;
import vision.genesis.clientapp.model.events.OnProgramSettingsConfirmEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

@InjectViewState
public class ChangeProgramSettingsPresenter extends MvpPresenter<ChangeProgramSettingsView>
{
	@Inject
	public Context context;

	@Inject
	public AssetsManager assetsManager;

	private Subscription updatePublicInfoSubscription;

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
		if (updatePublicInfoSubscription != null) {
			updatePublicInfoSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(UUID assetId, ProgramUpdate model) {
		this.assetId = assetId;
		this.model = model;
	}

	private void updateProgramSettings() {
		getViewState().showProgress(true);

		if (assetsManager != null) {
			updatePublicInfoSubscription = assetsManager.updateProgramSettings(assetId, model)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleUpdateSuccess,
							this::handleUpdateError);
		}
	}

	private void handleUpdateSuccess(Void response) {
		updatePublicInfoSubscription.unsubscribe();

		EventBus.getDefault().post(new OnProgramSettingsChangedEvent(model));
		getViewState().finishActivity();
	}

	private void handleUpdateError(Throwable throwable) {
		updatePublicInfoSubscription.unsubscribe();
		getViewState().showProgress(false);
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnProgramSettingsConfirmEvent event) {
		model.setInvestmentLimit(event.getModel().getInvestmentLimit());
		model.setTradesDelay(event.getModel().getTradesDelay());
		model.setStopOutLevel(event.getModel().getStopOutLevel());
		model.setEntryFee(event.getModel().getEntryFee());
		model.setSuccessFee(event.getModel().getSuccessFee());

		updateProgramSettings();
	}
}