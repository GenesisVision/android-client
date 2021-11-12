package vision.genesis.clientapp.feature.main.program.trades;

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

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.OrderSignalModel;
import io.swagger.client.model.TradesDelay;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

public class ProgramTradesFragment extends BaseFragment implements ProgramTradesView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	private static final String EXTRA_PROGRAM_NAME = "extra_program_name";

	public static ProgramTradesFragment with(UUID programId, String programName) {
		ProgramTradesFragment programTradesFragment = new ProgramTradesFragment();
		Bundle arguments = new Bundle(2);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		arguments.putString(EXTRA_PROGRAM_NAME, programName);
		programTradesFragment.setArguments(arguments);
		return programTradesFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.group_delay)
	public ViewGroup delayGroup;

	@BindView(R.id.text_delay)
	public TextView delayText;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_no_trades)
	public View groupNoTransactions;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

//	@BindDimen(R.dimen.program_details_padding_left)
//	public int paddingLeft;

	@InjectPresenter
	public ProgramTradesPresenter presenter;

	private TradesListAdapter tradesListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	private TradesDelay tradesDelay;

	@OnClick(R.id.delay_tooltip)
	public void onDelayTooltipClicked() {
		if (getActivity() != null && tradesDelay != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info,
					getString(R.string.trades_delay).toUpperCase(),
					String.format(Locale.getDefault(), getString(R.string.template_manager_trades_delay), StringFormatUtil.getTradesDelayString(tradesDelay)),
					false, null);
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
		return inflater.inflate(R.layout.fragment_trades, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRecyclerView();
		setFonts();

		if (getArguments() != null) {
			presenter.setData((UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID), getArguments().getString(EXTRA_PROGRAM_NAME));
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

		tradesListAdapter = new TradesListAdapter();
		sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getContext(), R.layout.list_item_trades_date_section, R.id.text, tradesListAdapter);
		recyclerView.setAdapter(sectionedAdapter);

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
	public void setTradesDelay(TradesDelay tradesDelay) {
		this.tradesDelay = tradesDelay;
		if (tradesDelay == null || tradesDelay.equals(TradesDelay.NONE)) {
			delayGroup.setVisibility(View.GONE);
		}
		else {
			delayGroup.setVisibility(View.VISIBLE);
			delayText.setText(String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.getTradesDelayString(tradesDelay),
					getString(R.string.delay)));
		}
	}

	@Override
	public void setTrades(List<OrderSignalModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		if (trades.isEmpty()) {
			groupNoTransactions.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		sectionedAdapter.setSections(sections);
		tradesListAdapter.setTrades(trades);
		groupNoTransactions.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addTrades(List<OrderSignalModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		sectionedAdapter.setSections(sections);
		tradesListAdapter.addTrades(trades);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void showTradeDetails(OrderSignalModel trade, Boolean showSwaps, Boolean showTickets) {
		if (getActivity() != null) {
			TradeDetailsDialog dialog = new TradeDetailsDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(trade, showSwaps, showTickets);
		}
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