package vision.genesis.clientapp.feature.main.dashboard.investments.coins.history;

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
import butterknife.Unbinder;
import io.swagger.client.model.CoinsHistoryEvent;
import io.swagger.client.model.FundHistoryEventViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.fund.reallocate_history.details.FundHistoryDetailsBottomSheetFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2021.
 */

public class CoinsHistoryFragment extends BaseFragment implements CoinsHistoryView
{
	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_no_history)
	public View groupNoHistory;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindDimen(R.dimen.date_range_margin_bottom)
	public int dateRangeMarginBottom;

	@InjectPresenter
	public CoinsHistoryPresenter presenter;

	private CoinsHistoryAdapter coinsHistoryAdapter;

	private Unbinder unbinder;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

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
		return inflater.inflate(R.layout.fragment_coins_history, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRecyclerView();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);

		coinsHistoryAdapter = new CoinsHistoryAdapter();
		recyclerView.setAdapter(coinsHistoryAdapter);

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
	public void setHistory(List<CoinsHistoryEvent> history) {
		if (history.isEmpty()) {
			groupNoHistory.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		coinsHistoryAdapter.setHistory(history);
		groupNoHistory.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addHistory(List<CoinsHistoryEvent> history) {
		coinsHistoryAdapter.addHistory(history);
	}

	@Override
	public void showEventDetails(FundHistoryEventViewModel event) {
		if (getActivity() != null) {
			FundHistoryDetailsBottomSheetFragment bottomSheetFragment = new FundHistoryDetailsBottomSheetFragment();
			bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
			bottomSheetFragment.setData(event);
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