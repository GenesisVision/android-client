package vision.genesis.clientapp.feature.main.program.withdraw.confirm;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/10/2018.
 */

public class ConfirmProgramWithdrawBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnConfirmProgramWithdrawListener
	{
		void onWithdrawSucceeded();
	}

	@Inject
	public ProgramsManager programsManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.period_text)
	public TextView periodText;

	@BindView(R.id.program_logo)
	public ProgramLogoView programLogo;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.amount_to_withdraw)
	public TextView amountToWithdraw;

	@BindView(R.id.payout_date)
	public TextView payoutDate;

	@BindView(R.id.remaining_investment)
	public TextView remainingInvestment;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_cancel)
	public PrimaryButton cancelButton;

	private OnConfirmProgramWithdrawListener listener;

	private ProgramRequest programRequest;

	private Subscription withdrawSubscription;

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		sendWithdrawRequest();
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
			View bottomSheetInternal = d.findViewById(android.support.design.R.id.design_bottom_sheet);
			if (bottomSheetInternal != null)
				BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
		});

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_confirm_program_withdraw, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		setFonts();

		programLogo.setLevelBackground(R.attr.colorTextPrimary);
		cancelButton.setEmpty();
		cancelButton.setTextColorByAttrId(R.attr.colorCard);

		updateView();
	}

	@Override
	public void onDestroyView() {
		if (withdrawSubscription != null)
			withdrawSubscription.unsubscribe();

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

	public void setListener(OnConfirmProgramWithdrawListener listener) {
		this.listener = listener;
	}

	public void setData(ProgramRequest programRequest) {
		this.programRequest = programRequest;

		if (title != null)
			updateView();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		managerName.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (programRequest != null) {
			periodText.setText(programRequest.getPeriodEndsText());

			programLogo.setImage(programRequest.getProgramLogo(), programRequest.getProgramColor(), 50, 50);
			programLogo.setLevel(programRequest.getLevel());

			programName.setText(programRequest.getProgramName());
			managerName.setText(programRequest.getManagerName());

			amountToWithdraw.setText(programRequest.getAmountTopText());
			payoutDate.setText(programRequest.getInfoMiddleText());
			remainingInvestment.setText(programRequest.getAmountBottomText());
		}
	}

	private void sendWithdrawRequest() {
		if (programRequest != null && programsManager != null) {
			showProgress(true);
			withdrawSubscription = programsManager.withdraw(programRequest)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleWithdrawSuccess,
							this::handleWithdrawError);
		}
	}

	private void handleWithdrawSuccess(Void response) {
		withdrawSubscription.unsubscribe();

		if (listener != null) {
			listener.onWithdrawSucceeded();
			this.dismiss();
		}
	}

	private void handleWithdrawError(Throwable throwable) {
		withdrawSubscription.unsubscribe();
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
