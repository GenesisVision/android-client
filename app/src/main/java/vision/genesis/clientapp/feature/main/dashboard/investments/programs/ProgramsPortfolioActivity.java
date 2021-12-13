package vision.genesis.clientapp.feature.main.dashboard.investments.programs;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProgramInvestingDetailsList;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.ProgramDashboardShortView;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2020.
 */

public class ProgramsPortfolioActivity extends BaseSwipeBackActivity implements ProgramsPortfolioView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramsPortfolioActivity.class);
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

	@BindView(R.id.order_by)
	public TextView orderBy;

	@BindView(R.id.group_programs)
	public ViewGroup programs;

	@BindView(R.id.group_programs_empty)
	public ViewGroup programsEmptyGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	ProgramsPortfolioPresenter presenter;

	private CurrencyEnum baseCurrency;

	private ArrayList<String> orderByOptions;

	private Integer selectedOrderByPosition = -1;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.group_order_by)
	public void onTypeClicked() {
		if (orderByOptions != null && orderByOptions.size() > 0) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.select_type), orderByOptions, selectedOrderByPosition);
			fragment.setListener((position, text) -> presenter.onOrderByOptionSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_programs_portfolio);

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
	public void setOrderByOptions(ArrayList<String> orderByOptions) {
		this.orderByOptions = orderByOptions;
	}

	@Override
	public void setOrderBy(String orderBy, Integer position) {
		this.orderBy.setText(orderBy);
		this.selectedOrderByPosition = position;
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public void setPrograms(List<ProgramInvestingDetailsList> items, Integer total) {
		if (baseCurrency != null) {
			programs.removeAllViews();
			int index = 0;
			for (ProgramInvestingDetailsList program : items) {
				ProgramDashboardShortView programView = new ProgramDashboardShortView(this);
				programView.setData(program, baseCurrency.getValue());
				programs.addView(programView);
				if (index == items.size() - 1) {
					programView.removeDelimiter();
				}
				index++;
			}
//			programsCount.setText(String.valueOf(size));
			programs.setVisibility(items.size() > 0 ? View.VISIBLE : View.GONE);
			programsEmptyGroup.setVisibility(items.size() > 0 ? View.GONE : View.VISIBLE);
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
