package vision.genesis.clientapp.feature.main.program.level;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.about_levels.AboutLevelsActivity;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/04/2019.
 */

public class ProgramLevelBottomSheetDialog extends BottomSheetDialogFragment
{
	@Inject
	public SettingsManager settingsManager;

	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.group_level_up)
	public ViewGroup levelUpGroup;

	@BindView(R.id.level_up)
	public TextView levelUp;

	@BindView(R.id.invest_limit)
	public TextView investLimit;

	private Integer programLevel;

	private Boolean canLevelUp;

	private String currency;

	private Double totalAvailableInvestment;

	@OnClick(R.id.button)
	public void onButtonClicked() {
		if (getActivity() != null) {
			AboutLevelsActivity.startWith(getActivity(), currency);
			this.dismiss();
		}
	}

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_program_level, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		GenesisVisionApplication.getComponent().inject(this);

		setFonts();

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

	public void setData(Integer level, Boolean canLevelUp, String currency, Double totalAvailableInvestment) {
		this.programLevel = level;
		this.canLevelUp = canLevelUp;
		this.currency = currency;
		this.totalAvailableInvestment = totalAvailableInvestment;

		updateView();
	}

	private void setFonts() {
		level.setTypeface(TypefaceUtil.semibold());
		levelUp.setTypeface(TypefaceUtil.semibold());
		investLimit.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (this.level != null) {
			this.level.setText(String.format(Locale.getDefault(), "%s %d", getString(R.string.genesis_level), programLevel));
			levelUpGroup.setVisibility(canLevelUp ? View.VISIBLE : View.GONE);
			if (totalAvailableInvestment != null) {
				investLimit.setText(StringFormatUtil.getValueString(totalAvailableInvestment, currency));
			}
		}
	}
}
