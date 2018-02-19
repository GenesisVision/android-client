package vision.genesis.clientapp.feature.main.traders.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.InvestmentProgram;
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
		intent.putExtra(EXTRA_PROGRAM, program);
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

	@InjectPresenter
	TraderDetailsPresenter traderDetailsPresenter;

	private InvestmentProgram program;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_trader_details);

		ButterKnife.bind(this);

		initToolbar();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			program = getIntent().getExtras().getParcelable(EXTRA_PROGRAM);
			setData();
		}
		else {
			Timber.e("Passed empty program to TraderDetailsFragment");
			onBackPressed();
		}
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.trader_details));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, this::onBackPressed);
	}

	private void setData() {
		avatar.setImageUrl(program.logo);
		avatar.setLevel(program.getRating());

		managerName.setText(program.managerName);
		description.setText(program.description);

		chart.setData(program.chartData);

		tradesText.setText(String.valueOf(program.ordersCount));
		periodText.setText(String.valueOf(program.period));
		profitText.setText(String.format(Locale.getDefault(), "%.2f%%", program.totalProfit));


		DecimalFormat df = new DecimalFormat("0.####");
		df.setRoundingMode(RoundingMode.DOWN);
		minAmountText.setText(program.investMinAmount != null
				? df.format(program.investMinAmount)
				: "-");
		maxAmountText.setText(program.investMaxAmount != null
				? df.format(program.investMaxAmount)
				: "-");
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
