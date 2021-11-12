package vision.genesis.clientapp.feature.main.program.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
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
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.program.reports.details.ProgramReportsDetailsBottomSheetFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2020.
 */

public class ProgramReportsFragment extends BaseFragment implements ProgramReportsView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	private static final String EXTRA_PROGRAM_NAME = "extra_program_name";

	private static final String EXTRA_PROGRAM_CURRENCY = "extra_program_currency";

	private static final String EXTRA_PROGRAM_PERIOD_DURATION_DAYS = "extra_program_period_duration_days";

	public static ProgramReportsFragment with(UUID programId, String programName, String programCurrency, Integer periodDurationDays) {
		ProgramReportsFragment programReportsFragment = new ProgramReportsFragment();
		Bundle arguments = new Bundle(4);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		arguments.putString(EXTRA_PROGRAM_NAME, programName);
		arguments.putString(EXTRA_PROGRAM_CURRENCY, programCurrency);
		arguments.putInt(EXTRA_PROGRAM_PERIOD_DURATION_DAYS, periodDurationDays);
		programReportsFragment.setArguments(arguments);
		return programReportsFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.interval)
	public TextView interval;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_no_data)
	public View groupNoData;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public ProgramReportsPresenter presenter;

	private ProgramReportsAdapter programReportsAdapter;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private String programCurrency;

	private int periodDurationDays;

	private ArrayList<String> intervalOptions;

	private Integer selectedIntervalPosition = -1;

	@OnClick(R.id.group_interval)
	public void onIntervalClicked() {
		if (getActivity() != null && intervalOptions != null && intervalOptions.size() > 0) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.select_interval), intervalOptions, selectedIntervalPosition);
			fragment.setListener((position, text) -> presenter.onIntervalOptionSelected(position, text));
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

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
		return inflater.inflate(R.layout.fragment_program_reports, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		if (getArguments() != null) {
			presenter.setData((UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID), getArguments().getString(EXTRA_PROGRAM_NAME));
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

		programReportsAdapter = new ProgramReportsAdapter(programCurrency, periodDurationDays);
		recyclerView.setAdapter(programReportsAdapter);

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
	public void setIntervalOptions(ArrayList<String> intervalOptions) {
		this.intervalOptions = intervalOptions;
	}

	@Override
	public void setInterval(String interval, Integer position) {
		this.interval.setText(interval);
		this.selectedIntervalPosition = position;
	}

	@Override
	public void setData(List<ProgramPeriodViewModel> periods) {
		if (periods.isEmpty()) {
			groupNoData.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		programReportsAdapter.setData(periods);
		groupNoData.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addData(List<ProgramPeriodViewModel> periods) {
		programReportsAdapter.addData(periods);
	}

	@Override
	public void showReportDetails(ProgramPeriodViewModel period) {
		if (getActivity() != null) {
			ProgramReportsDetailsBottomSheetFragment bottomSheetFragment = new ProgramReportsDetailsBottomSheetFragment();
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
}