package vision.genesis.clientapp.feature.main.program.invest.confirm;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/10/2018.
 */

public class ConfirmProgramInvestBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface OnConfirmProgramInvestListener
	{
		void onInvestSucceeded();
	}

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

	@BindView(R.id.amount_to_invest)
	public TextView amountToInvest;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.amount_due)
	public TextView amountDue;

	@BindView(R.id.button_cancel)
	public PrimaryButton cancelButton;

	private OnConfirmProgramInvestListener listener;

	private ProgramRequest programRequest;

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		if (listener != null) {
			listener.onInvestSucceeded();
			this.dismiss();
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
			View bottomSheetInternal = d.findViewById(android.support.design.R.id.design_bottom_sheet);
			if (bottomSheetInternal != null)
				BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
		});

		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_confirm_program_invest, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		programLogo.setLevelBackground(R.attr.colorTextPrimary);
		cancelButton.setEmpty();
		cancelButton.setTextColorByAttrId(R.attr.colorCard);

		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	public void setListener(OnConfirmProgramInvestListener listener) {
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

			programLogo.setImage(programRequest.getProgramLogo(), 50, 50);
			programLogo.setLevel(programRequest.getLevel());

			programName.setText(programRequest.getProgramName());
			managerName.setText(programRequest.getManagerName());

			amountToInvest.setText(programRequest.getAmountToInvestText());
			entryFee.setText(programRequest.getEntryFeeText());
			amountDue.setText(programRequest.getAmountDueText());
		}
	}
}
