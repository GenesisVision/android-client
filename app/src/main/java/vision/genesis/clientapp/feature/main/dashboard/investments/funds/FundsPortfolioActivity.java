package vision.genesis.clientapp.feature.main.dashboard.investments.funds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.FundInvestingDetailsList;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.FundDashboardShortView;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

public class FundsPortfolioActivity extends BaseSwipeBackActivity implements FundsPortfolioView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), FundsPortfolioActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_funds)
	public ViewGroup funds;

	@BindView(R.id.group_funds_empty)
	public ViewGroup fundsEmptyGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	FundsPortfolioPresenter presenter;

	private CurrencyEnum baseCurrency;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_funds_portfolio);

		ButterKnife.bind(this);

		initRefreshLayout();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> presenter.onSwipeRefresh());
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onBackPressed() {
		finishActivity();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public void setFunds(List<FundInvestingDetailsList> items, Integer total) {
		if (baseCurrency != null) {
			funds.removeAllViews();
			int index = 0;
			for (FundInvestingDetailsList fund : items) {
				FundDashboardShortView fundView = new FundDashboardShortView(this);
				fundView.setData(fund, baseCurrency.getValue());
				funds.addView(fundView);
				if (index == items.size() - 1) {
					fundView.removeDelimiter();
				}
				index++;
			}
//			fundsCount.setText(String.valueOf(size));
			funds.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			fundsEmptyGroup.setVisibility(items.size() > 0 ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
