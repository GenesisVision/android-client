package vision.genesis.clientapp.feature.main.fund.invest.confirm;

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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.FundsManager;
import vision.genesis.clientapp.model.FundRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

public class ConfirmFundInvestBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnConfirmFundInvestListener
	{
		void onInvestSucceeded();
	}

	@Inject
	public FundsManager fundsManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.fund_logo)
	public ProgramLogoView fundLogo;

	@BindView(R.id.fund_name)
	public TextView fundName;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.amount_to_invest)
	public TextView amountToInvest;

	@BindView(R.id.group_commissions)
	public ViewGroup commissionsGroup;

	@BindView(R.id.fees_and_commissions)
	public TextView feesAndCommissions;

	@BindView(R.id.investment_amount)
	public TextView investmentAmount;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_cancel)
	public PrimaryButton cancelButton;

	private OnConfirmFundInvestListener listener;

	private FundRequest fundRequest;

	private Subscription investSubscription;

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		sendInvestRequest();
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
			if (bottomSheetInternal != null) {
				BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
			}
		});

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_confirm_fund_invest, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		setFonts();

		fundLogo.setLevelBackground(R.attr.colorTextPrimary);
		cancelButton.setEmpty();
		cancelButton.setTextColorByAttrId(R.attr.colorCard);

		updateView();
	}

	@Override
	public void onDestroyView() {
		if (investSubscription != null) {
			investSubscription.unsubscribe();
		}

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

	public void setListener(OnConfirmFundInvestListener listener) {
		this.listener = listener;
	}

	public void setData(FundRequest fundRequest) {
		this.fundRequest = fundRequest;

		if (title != null) {
			updateView();
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		managerName.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (fundRequest != null) {
			fundLogo.setImage(fundRequest.getFundLogo(), fundRequest.getFundColor(), 50, 50);
			fundLogo.hideLevel();

			fundName.setText(fundRequest.getFundName());
			managerName.setText(fundRequest.getManagerName());

			amountToInvest.setText(fundRequest.getAmountTopText());
			feesAndCommissions.setText(fundRequest.getInfoMiddleText());
			investmentAmount.setText(fundRequest.getAmountBottomText());

			if (fundRequest.isOwnFund()) {
				commissionsGroup.setVisibility(View.GONE);
			}
		}
	}

	private void sendInvestRequest() {
		if (fundRequest != null && fundsManager != null) {
			showProgress(true);
			investSubscription = fundsManager.invest(fundRequest)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(response -> handleInvestSuccess(),
							this::handleInvestError);
		}
	}

	private void handleInvestSuccess() {
		investSubscription.unsubscribe();

		if (listener != null) {
			listener.onInvestSucceeded();
			this.dismiss();
		}
	}

	private void handleInvestError(Throwable throwable) {
		investSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		buttonsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}
}
