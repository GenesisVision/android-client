package vision.genesis.clientapp.feature.main.notifications.create;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.NotificationSettingConditionType;
import io.swagger.client.model.NotificationType;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.managers.NotificationsManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */

@InjectViewState
public class CreateCustomNotificationSettingPresenter extends MvpPresenter<CreateCustomNotificationSettingView> implements SelectOptionBottomSheetFragment.OnOptionSelectedListener
{
	@Inject
	public Context context;

	@Inject
	public NotificationsManager notificationsManager;

	private Subscription createSubscription;

	private UUID programId;

	private NotificationSettingConditionType type;

	private Double amount;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		initTypeOptions();
	}

	private void initTypeOptions() {
		ArrayList<String> typeOptions = new ArrayList<>();
		typeOptions.add(context.getString(R.string.profit));
		typeOptions.add(context.getString(R.string.level));
		typeOptions.add(context.getString(R.string.investment_availability));
		getViewState().setTypeOptions(typeOptions);
		onOptionSelected(0, typeOptions.get(0));
	}

	@Override
	public void onDestroy() {
		if (createSubscription != null) {
			createSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;
	}

	void onInvestAmountChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		updateCreateButtonEnabled();
	}

	void onProfitChanged(String newAmount) {
		try {
			amount = Double.parseDouble(newAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}

		updateCreateButtonEnabled();
	}

	void onLevelSelected(int level) {
		this.amount = (double) level;
		updateCreateButtonEnabled();
	}

	void onCreateClicked() {
		createNotificationSetting();
	}

	private void updateCreateButtonEnabled() {
		getViewState().setCreateButtonEnabled(type != null && amount != null && amount > 0);
	}

	private void createNotificationSetting() {
		if (programId != null && type != null && amount != null) {
			if (createSubscription != null) {
				createSubscription.unsubscribe();
			}
			getViewState().showProgress(true);
			createSubscription = notificationsManager.addNotificationSetting(programId, null,
					NotificationType.PROGRAMCONDITION.getValue(), type.getValue(), amount)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleCreateCustomNotificationSuccess,
							this::handleCreateCustomNotificationError);
		}
	}

	private void handleCreateCustomNotificationSuccess(UUID resppnse) {
		createSubscription.unsubscribe();

		getViewState().finishActivity();
	}

	private void handleCreateCustomNotificationError(Throwable throwable) {
		createSubscription.unsubscribe();
		getViewState().showProgress(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Override
	public void onOptionSelected(Integer position, String text) {
		if (text.equals(context.getString(R.string.profit))) {
			this.type = NotificationSettingConditionType.PROFIT;
			getViewState().showProfitInput();
		}
		else if (text.equals(context.getString(R.string.level))) {
			this.type = NotificationSettingConditionType.LEVEL;
			getViewState().showLevelInput();
		}
		else if (text.equals(context.getString(R.string.investment_availability))) {
			this.type = NotificationSettingConditionType.AVAILABLETOINVEST;
			getViewState().showInvestInput();
		}
		getViewState().setType(text, position);
		this.amount = 0.0;
		updateCreateButtonEnabled();
	}
}
