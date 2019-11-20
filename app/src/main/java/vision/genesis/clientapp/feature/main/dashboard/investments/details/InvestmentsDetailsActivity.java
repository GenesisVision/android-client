package vision.genesis.clientapp.feature.main.dashboard.investments.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.DashboardInvestingDetails;
import io.swagger.client.model.DashboardTimeframeProfit;
import io.swagger.client.model.FundDetailsList;
import io.swagger.client.model.InvestmentEventViewModel;
import io.swagger.client.model.ProgramDetailsList;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.FundDashboardShortView;
import vision.genesis.clientapp.ui.PortfolioEventDashboardView;
import vision.genesis.clientapp.ui.ProgramDashboardShortView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/11/2019.
 */

public class InvestmentsDetailsActivity extends BaseSwipeBackActivity implements InvestmentsDetailsView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), InvestmentsDetailsActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;


	@BindView(R.id.total)
	public TextView total;

	@BindView(R.id.profit_day_value)
	public TextView profitDayValue;

	@BindView(R.id.profit_day_label)
	public TextView profitDayLabel;

	@BindView(R.id.profit_week_value)
	public TextView profitWeekValue;

	@BindView(R.id.profit_week_label)
	public TextView profitWeekLabel;

	@BindView(R.id.profit_month_value)
	public TextView profitMonthValue;

	@BindView(R.id.profit_month_label)
	public TextView profitMonthLabel;


	@BindView(R.id.group_events)
	public ViewGroup eventsGroup;

	@BindView(R.id.label_events)
	public TextView eventsLabel;

	@BindView(R.id.events)
	public LinearLayout events;


	@BindView(R.id.label_programs)
	public TextView programsLabel;

	@BindView(R.id.programs_count_background)
	public ViewGroup programsCountBackground;

	@BindView(R.id.programs_count)
	public TextView programsCount;

	@BindView(R.id.programs_progress_bar)
	public ProgressBar programsProgressBar;

	@BindView(R.id.programs_arrow)
	public View programsArrow;

	@BindView(R.id.programs)
	public LinearLayout programs;


	@BindView(R.id.label_funds)
	public TextView fundsLabel;

	@BindView(R.id.funds_count_background)
	public ViewGroup fundsCountBackground;

	@BindView(R.id.funds_count)
	public TextView fundsCount;

	@BindView(R.id.funds_progress_bar)
	public ProgressBar fundsProgressBar;

	@BindView(R.id.funds_arrow)
	public View fundsArrow;

	@BindView(R.id.funds)
	public LinearLayout funds;


	@InjectPresenter
	InvestmentsDetailsPresenter presenter;

	private CurrencyEnum baseCurrency;

	private DashboardInvestingDetails details;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_investments_details);

		ButterKnife.bind(this);

		setFonts();

		initRefreshLayout();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());

//		total.setTypeface(TypefaceUtil.semibold());

		profitDayValue.setTypeface(TypefaceUtil.semibold());
		profitWeekValue.setTypeface(TypefaceUtil.semibold());
		profitMonthValue.setTypeface(TypefaceUtil.semibold());

		profitDayLabel.setText(getString(R.string.day).toLowerCase());
		profitWeekLabel.setText(getString(R.string.week).toLowerCase());
		profitMonthLabel.setText(getString(R.string.month).toLowerCase());

		eventsLabel.setTypeface(TypefaceUtil.semibold());
		programsLabel.setTypeface(TypefaceUtil.semibold());
		fundsLabel.setTypeface(TypefaceUtil.semibold());

		programsCount.setTypeface(TypefaceUtil.semibold());
		fundsCount.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(presenter::onSwipeRefresh);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public void setInvesting(DashboardInvestingDetails details) {
		this.details = details;
		if (baseCurrency != null) {
			total.setText(StringFormatUtil.getValueString(details.getEquity(), baseCurrency.getValue()));
			setChangePercent(profitDayValue, details.getProfits().getDay());
			setChangePercent(profitWeekValue, details.getProfits().getWeek());
			setChangePercent(profitMonthValue, details.getProfits().getMonth());

			programsCount.setText(String.valueOf(details.getProgramsCount()));
			fundsCount.setText(String.valueOf(details.getFundsCount()));

			showProgramsCountMaybe();
			showFundsCountMaybe();
		}
	}

	private void showProgramsCountMaybe() {
		if (details != null && details.getProgramsCount() > 0 && programsProgressBar.getVisibility() != View.VISIBLE) {
			programsArrow.setVisibility(View.VISIBLE);
			programsCountBackground.setVisibility(View.VISIBLE);
		}
	}

	private void showFundsCountMaybe() {
		if (details != null && details.getFundsCount() > 0 && fundsProgressBar.getVisibility() != View.VISIBLE) {
			fundsArrow.setVisibility(View.VISIBLE);
			fundsCountBackground.setVisibility(View.VISIBLE);
		}
	}

	private void setChangePercent(TextView view, DashboardTimeframeProfit model) {
		view.setText(String.format(Locale.getDefault(), "%s%%",
				StringFormatUtil.formatAmount(model.getProfitPercent(), 0, 2)));
		view.setTextColor(ThemeUtil.getColorByAttrId(this,
				model.getProfitPercent() > 0
						? R.attr.colorGreen
						: model.getProfitPercent() < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	@Override
	public void setEvents(List<InvestmentEventViewModel> items) {
		eventsGroup.setVisibility(items.isEmpty() ? View.GONE : View.VISIBLE);
		events.removeAllViews();
		for (InvestmentEventViewModel event : items) {
			PortfolioEventDashboardView eventView = new PortfolioEventDashboardView(this);
			eventView.setEvent(event);
			events.addView(eventView);
		}
	}

	@Override
	public void setPrograms(List<ProgramDetailsList> items) {
		if (baseCurrency != null) {
			programs.removeAllViews();
			for (ProgramDetailsList program : items) {
				ProgramDashboardShortView programView = new ProgramDashboardShortView(this);
				programView.setData(program, baseCurrency.getValue());
				programs.addView(programView);
			}
		}
	}

	@Override
	public void setFunds(List<FundDetailsList> items) {
		if (baseCurrency != null) {
			funds.removeAllViews();
			for (FundDetailsList fund : items) {
				FundDashboardShortView fundView = new FundDashboardShortView(this);
				fundView.setData(fund, baseCurrency.getValue());
				funds.addView(fundView);
			}
		}
	}

	@Override
	public void hideProgramsProgress() {
		programsProgressBar.setVisibility(View.GONE);
		showProgramsCountMaybe();
	}

	@Override
	public void hideFundsProgress() {
		fundsProgressBar.setVisibility(View.GONE);
		showFundsCountMaybe();
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			refreshLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}


	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}
}
