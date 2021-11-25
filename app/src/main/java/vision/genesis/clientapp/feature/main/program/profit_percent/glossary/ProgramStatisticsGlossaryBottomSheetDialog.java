package vision.genesis.clientapp.feature.main.program.profit_percent.glossary;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/11/2021.
 */

public class ProgramStatisticsGlossaryBottomSheetDialog extends BottomSheetDialogFragment
{
	@BindView(R.id.group_investors)
	public ViewGroup investorsGroup;

	@BindView(R.id.group_subscribers)
	public ViewGroup subscribersGroup;

	private boolean isFollow;

	private boolean isProgram;

	public ProgramStatisticsGlossaryBottomSheetDialog(boolean isProgram, boolean isFollow) {
		this.isProgram = isProgram;
		this.isFollow = isFollow;
	}

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		this.dismiss();
	}

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_program_statistics_glossary, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		this.investorsGroup.setVisibility(isProgram ? View.VISIBLE : View.GONE);
		this.subscribersGroup.setVisibility(isFollow ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}
}
