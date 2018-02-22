package vision.genesis.clientapp.feature.main.program_withdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.ProgramWithdrawalRequest;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 2/21/18.
 */

public class WithdrawProgramActivity extends BaseSwipeBackActivity implements WithdrawProgramView
{
	private static final String EXTRA_REQUEST = "extra_request";

	public static void startWith(Activity activity, ProgramWithdrawalRequest request) {
		Intent intent = new Intent(activity, WithdrawProgramActivity.class);
		intent.putExtra(EXTRA_REQUEST, request);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.text_available_funds)
	public TextView availableFundsText;

	@BindView(R.id.edittext_amount)
	public EditText amountEdittext;

	@BindView(R.id.button_withdraw)
	public View withdrawButton;

	@InjectPresenter
	WithdrawProgramPresenter withdrawProgramPresenter;

	private ProgramWithdrawalRequest withdrawalRequest;

	@OnClick(R.id.button_withdraw)
	public void onApplyClicked() {
		withdrawProgramPresenter.onWithdrawClicked();
	}

	@OnClick(R.id.button_withdraw_all)
	public void onClearClicked() {
		withdrawProgramPresenter.onWithdrawAllClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_withdraw);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			withdrawalRequest = getIntent().getExtras().getParcelable(EXTRA_REQUEST);

			initToolbar();
		}
		else {
			Timber.e("Passed empty request to WithdrawProgramActivity");
			onBackPressed();
		}

	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.withdraw_from_program));
		toolbar.setSubtitle(withdrawalRequest.programName);
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> withdrawProgramPresenter.onBackClicked());
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
}
