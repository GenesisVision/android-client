package vision.genesis.clientapp.feature.main.program.period_history;

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
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProgramPeriodViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.program.period_history.details.PeriodDetailsBottomSheetFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/08/2019.
 */

public class PeriodHistoryFragment extends BaseFragment implements PeriodHistoryView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	private static final String EXTRA_PROGRAM_CURRENCY = "extra_program_currency";

	private static final String EXTRA_PROGRAM_PERIOD_DURATION_DAYS = "extra_program_period_duration_days";

	public static PeriodHistoryFragment with(UUID programId, String programCurrency, Integer periodDurationDays) {
		PeriodHistoryFragment periodHistoryFragment = new PeriodHistoryFragment();
		Bundle arguments = new Bundle(2);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		arguments.putString(EXTRA_PROGRAM_CURRENCY, programCurrency);
		arguments.putInt(EXTRA_PROGRAM_PERIOD_DURATION_DAYS, periodDurationDays);
		periodHistoryFragment.setArguments(arguments);
		return periodHistoryFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_no_periods)
	public View groupNoTransactions;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public PeriodHistoryPresenter periodHistoryPresenter;

	private PeriodHistoryAdapter periodHistoryAdapter;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private String programCurrency;

	private int periodDurationDays;

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		if (getActivity() != null) {
			DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setDateRange(dateRange);
			bottomSheetDialog.setListener(periodHistoryPresenter);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_period_history, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			periodHistoryPresenter.setProgramId((UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID));
			programCurrency = getArguments().getString(EXTRA_PROGRAM_CURRENCY);
			periodDurationDays = getArguments().getInt(EXTRA_PROGRAM_PERIOD_DURATION_DAYS);

			initRecyclerView();
		}
		else {
			Timber.e("Passed empty programId to TradesFragment");
			onBackPressed();
		}
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		periodHistoryAdapter = new PeriodHistoryAdapter(programCurrency, periodDurationDays);
		recyclerView.setAdapter(periodHistoryAdapter);

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
				int totalItemCount = layoutManager.getItemCount();
				int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

				boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
				if (totalItemCount > 0 && endHasBeenReached) {
					periodHistoryPresenter.onLastListItemVisible();
				}
			}
		});
	}

	@Override
	public void setPeriods(List<ProgramPeriodViewModel> periods) {
		if (periods.isEmpty()) {
			groupNoTransactions.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		periodHistoryAdapter.setPeriod(periods);
		groupNoTransactions.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addPeriods(List<ProgramPeriodViewModel> periods) {
		periodHistoryAdapter.addPeriod(periods);
	}

	@Override
	public void showPeriodDetails(ProgramPeriodViewModel period) {
		if (getActivity() != null) {
			PeriodDetailsBottomSheetFragment bottomSheetFragment = new PeriodDetailsBottomSheetFragment();
			bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
			bottomSheetFragment.setData(period, programCurrency);
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			dateRangeView.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (periodHistoryPresenter != null) {
			periodHistoryPresenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (periodHistoryPresenter != null) {
			periodHistoryPresenter.onSwipeRefresh();
		}
	}

	public void onOffsetChanged(int verticalOffset) {
		if (dateRangeView != null && root.getHeight() != 0) {
			dateRangeView.setY(root.getHeight() - verticalOffset - dateRangeView.getHeight() - dateRangeMarginBottom);
		}
	}
}