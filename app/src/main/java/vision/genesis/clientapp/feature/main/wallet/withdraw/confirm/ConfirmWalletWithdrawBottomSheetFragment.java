package vision.genesis.clientapp.feature.main.wallet.withdraw.confirm;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.two_factor.check.CheckTfaActivity;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.WithdrawalRequest;
import vision.genesis.clientapp.model.api.Error;
import vision.genesis.clientapp.model.api.ErrorResponse;
import vision.genesis.clientapp.model.events.OnCheckTfaConfirmClickedEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaErrorEvent;
import vision.genesis.clientapp.model.events.OnCheckTfaSuccessEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.net.ErrorResponseConverter;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/10/2018.
 */

public class ConfirmWalletWithdrawBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnConfirmWalletWithdrawListener
	{
		void onWithdrawSucceeded();
	}

	@Inject
	public AuthManager authManager;

	@Inject
	public WalletManager walletManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.withdrawal_amount)
	public TextView withdrawalAmount;

	@BindView(R.id.address)
	public TextView address;

	@BindView(R.id.estimated_amount)
	public TextView estimatedAmount;

	@BindView(R.id.fee_amount)
	public TextView feeAmount;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_cancel)
	public PrimaryButton cancelButton;

	private OnConfirmWalletWithdrawListener listener;

	private WithdrawalRequest withdrawalRequest;

	private Subscription userSubscription;

	private Subscription withdrawSubscription;

	private boolean tfaEnabled;

	private Boolean twoFactorEnabled = false;

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		if (twoFactorEnabled) {
			if (getActivity() != null)
				CheckTfaActivity.startWith(getActivity(), "");
		}
		else {
			sendWithdrawRequest();
		}
	}

	@OnClick(R.id.button_cancel)
	public void onCancelClicked() {
		this.dismiss();
	}

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);

		getDialog().setOnShowListener(dialog1 -> {
			BottomSheetDialog d = (BottomSheetDialog) dialog1;
			View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			if (bottomSheetInternal != null)
				BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
		});

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_confirm_wallet_withdraw, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		setFonts();

		cancelButton.setEmpty();
		cancelButton.setTextColorByAttrId(R.attr.colorCard);

		updateView();

		subscribeToUser();
	}

	@Override
	public void onDestroyView() {
		if (userSubscription != null)
			userSubscription.unsubscribe();
		if (withdrawSubscription != null)
			withdrawSubscription.unsubscribe();

		EventBus.getDefault().unregister(this);

		super.onDestroyView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	public void setListener(OnConfirmWalletWithdrawListener listener) {
		this.listener = listener;
	}

	public void setData(WithdrawalRequest withdrawalRequest) {
		this.withdrawalRequest = withdrawalRequest;

		if (title != null)
			updateView();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (withdrawalRequest != null) {
			withdrawalAmount.setText(withdrawalRequest.getAmountText());
			address.setText(withdrawalRequest.getAddress());
			estimatedAmount.setText(withdrawalRequest.getEstimatedAmountText());
			feeAmount.setText(withdrawalRequest.getFeeAmountText());
		}
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated);
	}

	private void userUpdated(User user) {
		this.twoFactorEnabled = user.getTwoFactorStatus();
	}

	private void sendWithdrawRequest() {
		if (withdrawalRequest != null && walletManager != null) {
			showProgress(true);
			withdrawSubscription = walletManager.withdraw(withdrawalRequest)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWithdrawSuccess,
							this::handleWithdrawError);
		}
	}

	private void handleWithdrawSuccess(Void response) {
		withdrawSubscription.unsubscribe();

		if (tfaEnabled)
			EventBus.getDefault().post(new OnCheckTfaSuccessEvent());

		if (listener != null) {
			listener.onWithdrawSucceeded();
			this.dismiss();
		}
	}

	private void handleWithdrawError(Throwable throwable) {
		withdrawSubscription.unsubscribe();
		showProgress(false);

		if (ApiErrorResolver.isNetworkError(throwable)) {
			if (tfaEnabled)
				EventBus.getDefault().post(new OnCheckTfaErrorEvent(getContext().getResources().getString(R.string.network_error)));
			else
				showToast(getContext().getResources().getString(R.string.network_error));
		}
		else {
			ErrorResponse response = ErrorResponseConverter.createFromThrowable(throwable);
			if (response != null) {
				for (Error error : response.errors) {
					if (error.property == null || error.property.isEmpty()) {
						if (tfaEnabled)
							EventBus.getDefault().post(new OnCheckTfaErrorEvent(error.message));
						else
							showToast(error.message);
					}
				}
			}
			tfaEnabled = false;
		}
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		buttonsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}


	@Subscribe
	public void onEventMainThread(OnCheckTfaConfirmClickedEvent event) {
		tfaEnabled = true;
		withdrawalRequest.setTfaCode(event.getCode());
		sendWithdrawRequest();
	}
}
