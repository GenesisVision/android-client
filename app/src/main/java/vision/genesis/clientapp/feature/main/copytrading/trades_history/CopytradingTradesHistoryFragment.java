package vision.genesis.clientapp.feature.main.copytrading.trades_history;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.OrderSignalModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.copytrading.commissions.CommissionsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.DashboardPagerAdapter;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.ui.DateRangeView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class CopytradingTradesHistoryFragment extends BaseFragment implements CopytradingTradesHistoryView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_MODEL = "extra_model";

	public static CopytradingTradesHistoryFragment with(TradingAccountDetailsModel model) {
		CopytradingTradesHistoryFragment copytradingTradesHistoryFragment = new CopytradingTradesHistoryFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_MODEL, model);
		copytradingTradesHistoryFragment.setArguments(arguments);
		return copytradingTradesHistoryFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@BindDimen(R.dimen.filters_margin_bottom)
	public int filtersMarginBottom;

	@BindDimen(R.dimen.filters_margin_top)
	public int filtersMarginTop;

	@InjectPresenter
	public CopytradingTradesHistoryPresenter presenter;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private CopytradingTradesHistoryAdapter copytradingTradesHistoryAdapter;

	private TradingAccountDetailsModel model;

	@OnClick(R.id.button_export)
	public void onExportClicked() {
		presenter.onExportClicked();
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(presenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_copytrading_trades_history, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			emptyGroup.setNestedScrollingEnabled(true);
		}

		if (getArguments() != null) {
			model = getArguments().getParcelable(EXTRA_MODEL);
			presenter.setData(model);

			initRecyclerView();
		}
		else {
			Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
			onBackPressed();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		presenter.onHide();
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onShow();
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		copytradingTradesHistoryAdapter = new CopytradingTradesHistoryAdapter();
		copytradingTradesHistoryAdapter.setHasStableIds(true);
		recyclerView.setAdapter(copytradingTradesHistoryAdapter);

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
				int totalItemCount = layoutManager.getItemCount();
				int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

				boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
				if (totalItemCount > 0 && endHasBeenReached) {
					presenter.onLastListItemVisible();
				}
			}
		});
	}

	@Override
	public void setTrades(List<OrderSignalModel> trades) {
		copytradingTradesHistoryAdapter.setTrades(trades);

		showEmpty(trades.size() == 0);
	}

	@Override
	public void addTrades(List<OrderSignalModel> newTrades) {
		copytradingTradesHistoryAdapter.addTrades(newTrades);
	}

	@Override
	public void showEmpty(boolean show) {
		if (emptyGroup != null) {
			emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void showCommissions(OrderSignalModel trade) {
		if (getActivity() != null) {
			CommissionsBottomSheetFragment fragment = new CommissionsBottomSheetFragment();
			fragment.setData(trade);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	public void showProgress(boolean show) {
		if (progressBar != null) {
			progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
			if (!show) {
				dateRangeView.setVisibility(View.VISIBLE);
				recyclerView.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void pagerShow() {
		if (presenter != null) {
			presenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (presenter != null) {
			presenter.onSwipeRefresh();
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		if (dateRangeView != null && root.getHeight() != 0) {
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
		}
	}

	public void onDashboardOffsetChanged(int verticalOffset) {
		if (dateRangeView != null) {
			float newY = root.getHeight() - verticalOffset - dateRangeView.getHeight() - filtersMarginBottom;
			if (newY < filtersMarginTop) {
				newY = filtersMarginTop;
			}
			dateRangeView.setY(newY);
		}
	}
}