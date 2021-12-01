package vision.genesis.clientapp.feature.main.notifications.create;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 23/10/2018.
 */

public class CreateCustomNotificationSettingActivity extends BaseSwipeBackActivity implements CreateCustomNotificationSettingView
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	private static final String EXTRA_PROGRAM_NAME = "extra_program_name";

	public static void startWith(Activity activity, UUID programId, String programName) {
		Intent intent = new Intent(activity.getApplicationContext(), CreateCustomNotificationSettingActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		intent.putExtra(EXTRA_PROGRAM_NAME, programName);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.group_profit)
	public ViewGroup profitGroup;

	@BindView(R.id.edittext_profit)
	public EditText profit;

	@BindView(R.id.group_amount)
	public ViewGroup amountGroup;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.group_select_level)
	public ViewGroup selectLevelGroup;

	@BindView(R.id.level_1)
	public TextView level1;

	@BindView(R.id.level_2)
	public TextView level2;

	@BindView(R.id.level_3)
	public TextView level3;

	@BindView(R.id.level_4)
	public TextView level4;

	@BindView(R.id.level_5)
	public TextView level5;

	@BindView(R.id.level_6)
	public TextView level6;

	@BindView(R.id.level_7)
	public TextView level7;

	@BindView(R.id.button_create)
	public PrimaryButton createButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	CreateCustomNotificationSettingPresenter presenter;

	private ArrayList<String> typeOptions;

	private Integer selectedTypePosition;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.group_type)
	public void onTypeClicked() {
		SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
				getString(R.string.select_notification_type), typeOptions, selectedTypePosition);
		fragment.setListener(presenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.group_edittext_profit)
	public void onProfitEditTextClicked() {
		showSoftKeyboard(profit);
	}

	@OnClick(R.id.group_edittext_amount)
	public void onAmountEditTextClicked() {
		showSoftKeyboard(amount);
	}

	@OnClick(R.id.level_1)
	public void onLevel1Clicked() {
		onLevelClicked(level1, 1);
	}

	@OnClick(R.id.level_2)
	public void onLevel2Clicked() {
		onLevelClicked(level2, 2);
	}

	@OnClick(R.id.level_3)
	public void onLevel3Clicked() {
		onLevelClicked(level3, 3);
	}

	@OnClick(R.id.level_4)
	public void onLevel4Clicked() {
		onLevelClicked(level4, 4);
	}

	@OnClick(R.id.level_5)
	public void onLevel5Clicked() {
		onLevelClicked(level5, 5);
	}

	@OnClick(R.id.level_6)
	public void onLevel6Clicked() {
		onLevelClicked(level6, 6);
	}

	@OnClick(R.id.level_7)
	public void onLevel7Clicked() {
		onLevelClicked(level7, 7);
	}

	@OnClick(R.id.button_create)
	public void onCreateClicked() {
		presenter.onCreateClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_create_custom_notification_setting);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			presenter.setProgramId((UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID));
			this.programName.setText(getIntent().getExtras().getString(EXTRA_PROGRAM_NAME));

			setTextListener();

			clearLevels();
		}
		else {
			Timber.e("Passed empty programId to CreateCustomNotificationSettingActivity");
			onBackPressed();
		}
	}

	private void clearLevels() {
		level1.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorCard), PorterDuff.Mode.SRC_IN);
		level2.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorCard), PorterDuff.Mode.SRC_IN);
		level3.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorCard), PorterDuff.Mode.SRC_IN);
		level4.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorCard), PorterDuff.Mode.SRC_IN);
		level5.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorCard), PorterDuff.Mode.SRC_IN);
		level6.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorCard), PorterDuff.Mode.SRC_IN);
		level7.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorCard), PorterDuff.Mode.SRC_IN);
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> presenter.onInvestAmountChanged(charSequence.toString()));
		RxTextView.textChanges(profit)
				.subscribe(charSequence -> presenter.onProfitChanged(charSequence.toString()));
	}

	private void onLevelClicked(TextView levelView, int level) {
		clearLevels();
		levelView.getBackground().setColorFilter(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent), PorterDuff.Mode.SRC_IN);
		presenter.onLevelSelected(level);
	}

	@Override
	public void setTypeOptions(ArrayList<String> typeOptions) {
		this.typeOptions = typeOptions;
	}

	@Override
	public void setType(String type, Integer position) {
		this.type.setText(type);
		this.selectedTypePosition = position;
	}

	@Override
	public void showProfitInput() {
		profit.setText("");
		profitGroup.setVisibility(View.VISIBLE);
		amountGroup.setVisibility(View.GONE);
		selectLevelGroup.setVisibility(View.GONE);
	}

	@Override
	public void showInvestInput() {
		amount.setText("");
		profitGroup.setVisibility(View.GONE);
		amountGroup.setVisibility(View.VISIBLE);
		selectLevelGroup.setVisibility(View.GONE);
	}

	@Override
	public void showLevelInput() {
		clearLevels();
		profitGroup.setVisibility(View.GONE);
		amountGroup.setVisibility(View.GONE);
		selectLevelGroup.setVisibility(View.VISIBLE);
	}

	@Override
	public void setCreateButtonEnabled(Boolean enabled) {
		createButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(Boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		createButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	private void showSoftKeyboard(EditText editText) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		editText.requestFocus();
		if (imm != null) {
			imm.showSoftInput(editText, 0);
		}
	}
}
