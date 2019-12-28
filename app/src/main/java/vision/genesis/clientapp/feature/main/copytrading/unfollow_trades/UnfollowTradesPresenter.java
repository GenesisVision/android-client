package vision.genesis.clientapp.feature.main.copytrading.unfollow_trades;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.DetachFromExternalSignalProvider;
import io.swagger.client.model.DetachFromSignalProvider;
import io.swagger.client.model.SignalDetachMode;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.FollowsManager;
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
	public FollowsManager followsManager;

	private Subscription signalSubscription;

	private UUID followId;

	private UUID tradingAccountId;

	private SignalDetachMode unsubscribeType = SignalDetachMode.NONE;

	private Boolean isExternal;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initTypeOptions();
	}

	@Override
	public void onDestroy() {
		if (signalSubscription != null) {
			signalSubscription.unsubscribe();
		}

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

	void setData(UUID followId, UUID tradingAccountId, Boolean isExternal) {
		this.followId = followId;
		this.tradingAccountId = tradingAccountId;
		this.isExternal = isExternal;
	}

	void onButtonClicked() {
		performRequest();
	}

	private void performRequest() {
		if (followsManager != null && followId != null) {
			if (signalSubscription != null) {
				signalSubscription.unsubscribe();
			}
			getViewState().showProgress(true);
			DetachFromSignalProvider model = new DetachFromSignalProvider();
			model.setMode(unsubscribeType);
			model.setTradingAccountId(tradingAccountId);

			DetachFromExternalSignalProvider extModel = new DetachFromExternalSignalProvider();
			extModel.setTradingAccountId(tradingAccountId);

			signalSubscription = (isExternal
					? followsManager.unsubscribeFromExternalFollow(followId, extModel)
					: followsManager.unsubscribeFromFollow(followId, model))
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
				unsubscribeType = SignalDetachMode.NONE;
				typeDescription = context.getString(R.string.type_description_unsubscribe_manual);
				break;
			case 1:
				unsubscribeType = SignalDetachMode.PROVIDERCLOSEONLY;
				typeDescription = context.getString(R.string.type_description_unsubscribe_close_only);
				break;
			case 2:
				unsubscribeType = SignalDetachMode.CLOSEALLIMMEDIATELY;
				typeDescription = context.getString(R.string.type_description_unsubscribe_close_immediately);
				break;
		}
		getViewState().setType(text, position);
		getViewState().setTypeDescription(typeDescription);
	}
}
