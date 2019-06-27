package vision.genesis.clientapp.feature.main.copytrading.unfollow_trades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

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
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/06/2019.
 */

public class UnfollowTradesActivity extends BaseSwipeBackActivity implements UnfollowTradesView
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	private static final String EXTRA_PROGRAM_NAME = "extra_program_name";

	public static void startWith(Activity activity, UUID programId, String programName) {
		Intent intent = new Intent(activity.getApplicationContext(), UnfollowTradesActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		intent.putExtra(EXTRA_PROGRAM_NAME, programName);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.type_description)
	public TextView typeDescription;

	@BindView(R.id.button)
	public PrimaryButton button;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	UnfollowTradesPresenter unfollowTradesPresenter;

	private ArrayList<String> typeOptions;

	private Integer selectedTypePosition;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_type)
	public void onTypeClicked() {
		SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
				getString(R.string.select_unsubscribe_type), typeOptions, selectedTypePosition);
		fragment.setListener(unfollowTradesPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@OnClick(R.id.button)
	public void onButtonClicked() {
		unfollowTradesPresenter.onButtonClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_unfollow_trades);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			UUID programId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID);
			String programName = getIntent().getExtras().getString(EXTRA_PROGRAM_NAME);
			if (programId != null) {
				unfollowTradesPresenter.setProgramId(programId);

				setProgramName(programName);
				setFonts();

				return;
			}
		}
		Timber.e("Passed empty programId to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setProgramName(String accountCurrency) {
		this.programName.setText(accountCurrency);
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
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
	public void setTypeDescription(String typeDescription) {
		this.typeDescription.setText(typeDescription);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		button.setVisibility(!show ? View.VISIBLE : View.GONE);
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
		overridePendingTransition(R.anim.hold, R.anim.slide_to_bottom);
	}
}