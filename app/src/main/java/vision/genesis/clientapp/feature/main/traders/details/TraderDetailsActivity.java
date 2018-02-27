package vision.genesis.clientapp.feature.main.traders.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentProgram;
import io.swagger.client.model.InvestmentProgramDetails;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.program_invest.InvestDialog;
import vision.genesis.clientapp.feature.main.program_withdraw.WithdrawProgramActivity;
import vision.genesis.clientapp.model.ProgramWithdrawalRequest;
import vision.genesis.clientapp.ui.ManagerAvatarView;
import vision.genesis.clientapp.ui.ProfitChartView;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class TraderDetailsActivity extends BaseSwipeBackActivity implements TraderDetailsView
{
	private static String EXTRA_PROGRAM = "extra_program";

	public static void startWith(Activity activity, InvestmentProgram program) {
		Intent intent = new Intent(activity, TraderDetailsActivity.class);
		intent.putExtra(EXTRA_PROGRAM, program.getId());
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.avatar)
	public ManagerAvatarView avatar;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.text_description)
	public TextView description;

	@BindView(R.id.chart)
	public ProfitChartView chart;

	@BindView(R.id.text_deposit_text)
	public TextView depositText;

	@BindView(R.id.text_trades_text)
	public TextView tradesText;

	@BindView(R.id.text_period_text)
	public TextView periodText;

	@BindView(R.id.text_profit_text)
	public TextView profitText;

	@BindView(R.id.text_min_amount)
	public TextView minAmountText;

	@BindView(R.id.text_max_amount)
	public TextView maxAmountText;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@InjectPresenter
	TraderDetailsPresenter traderDetailsPresenter;

	private InvestmentProgramDetails programDetails;

	private InvestDialog investDialog;

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		traderDetailsPresenter.onInvestClicked();
	}

	@OnClick(R.id.button_withdraw)
	public void onWithdrawClicked() {
		ProgramWithdrawalRequest withdrawalRequest = new ProgramWithdrawalRequest();
		withdrawalRequest.programId = programDetails.getId();
		withdrawalRequest.programName = programDetails.getTitle();
		WithdrawProgramActivity.startWith(this, withdrawalRequest);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_trader_details);

		ButterKnife.bind(this);

		initToolbar();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID programId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM);
			traderDetailsPresenter.setProgramId(programId);
		}
		else {
			Timber.e("Passed empty program to TraderDetailsActivity");
			onBackPressed();
		}
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.trader_details));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, this::onBackPressed);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setProgram(InvestmentProgramDetails programDetails) {
		avatar.setImageUrl(programDetails.getLogo());
		avatar.setLevel(String.valueOf(programDetails.getLevel()));

		managerName.setText(programDetails.getManager().getUsername());
		description.setText(programDetails.getDescription());

//		chart.setData(program.chartData);

		tradesText.setText(String.valueOf(programDetails.getTradesCount()));
		periodText.setText(String.valueOf(programDetails.getPeriodDuration()));
		profitText.setText(String.format(Locale.getDefault(), "%.2f%%", programDetails.getProfitAvg()));


		DecimalFormat df = new DecimalFormat("0.####");
		df.setRoundingMode(RoundingMode.DOWN);
	}

	@Override
	public void showInvestDialog() {
		if (investDialog != null)
			investDialog.cancel();
		investDialog = new InvestDialog(this, true, null);
		investDialog.setProgram(programDetails);
		investDialog.show();
	}

	@Override
	public void showInvestWithdrawButtons(boolean show) {
		buttonsGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
