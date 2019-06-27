package vision.genesis.clientapp.feature.main.copytrading.unfollow_trades;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.DetachFromSignalProvider;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.SignalsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/06/2019.
 */

@InjectViewState
public class UnfollowTradesPresenter extends MvpPresenter<UnfollowTradesView> implements
		SelectOptionBottomSheetFragment.OnOptionSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public SignalsManager signalsManager;

	private Subscription signalSubscription;

	private UUID programId;

	private DetachFromSignalProvider.ModeEnum unsubscribeType = DetachFromSignalProvider.ModeEnum.NONE;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initTypeOptions();
	}

	@Override
	public void onDestroy() {
		if (signalSubscription != null)
			signalSubscription.unsubscribe();

		super.onDestroy();
	}

	private void initTypeOptions() {
		ArrayList<String> typeOptions = new ArrayList<>();
		typeOptions.add(context.getString(R.string.manual_closing));
		typeOptions.add(context.getString(R.string.close_only));
		typeOptions.add(context.getString(R.string.close_all_immediately));
		getViewState().setTypeOptions(typeOptions);
		onOptionSelected(0, typeOptions.get(0));
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
	}

	void onButtonClicked() {
		performRequest();
	}

	private void performRequest() {
		if (signalsManager != null && programId != null) {
			if (signalSubscription != null)
				signalSubscription.unsubscribe();
			getViewState().showProgress(true);
			signalSubscription = signalsManager.unsubscribeFromProgram(programId, unsubscribeType)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleSubscriptionSuccess,
							this::handleSubscriptionError);
		}
	}

	private void handleSubscriptionSuccess(Void response) {
		signalSubscription.unsubscribe();
		getViewState().finishActivity();
	}

	private void handleSubscriptionError(Throwable throwable) {
		signalSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onOptionSelected(Integer position, String text) {
		String typeDescription = "";
		switch (position) {
			case 0:
				unsubscribeType = DetachFromSignalProvider.ModeEnum.NONE;
				typeDescription = context.getString(R.string.type_description_unsubscribe_manual);
				break;
			case 1:
				unsubscribeType = DetachFromSignalProvider.ModeEnum.PROVIDERCLOSEONLY;
				typeDescription = context.getString(R.string.type_description_unsubscribe_close_only);
				break;
			case 2:
				unsubscribeType = DetachFromSignalProvider.ModeEnum.CLOSEALLIMMEDIATELY;
				typeDescription = context.getString(R.string.type_description_unsubscribe_close_immediately);
				break;
		}
		getViewState().setType(text, position);
		getViewState().setTypeDescription(typeDescription);
	}
}
