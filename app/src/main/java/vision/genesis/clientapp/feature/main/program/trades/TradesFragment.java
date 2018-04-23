package vision.genesis.clientapp.feature.main.program.trades;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

public class TradesFragment extends BaseFragment implements TradesView
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	public static TradesFragment with(UUID programId) {
		TradesFragment tradesFragment = new TradesFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		tradesFragment.setArguments(arguments);
		return tradesFragment;
	}

	@BindView(R.id.header)
	public ViewGroup header;

	@BindView(R.id.group_no_trades)
	public View groupNoTransactions;

	@BindView(R.id.label_whoops)
	public TextView whoopsLabel;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.header_date_open)
	public TextView dateOpen;

	@BindView(R.id.header_date_close)
	public TextView dateClose;

	@BindView(R.id.header_symbol)
	public TextView symbol;

	@BindView(R.id.header_price_open)
	public TextView priceOpen;

	@BindView(R.id.header_price_close)
	public TextView priceClose;

	@BindView(R.id.header_volume)
	public TextView volume;

	@BindView(R.id.header_profit)
	public TextView profit;

	@BindView(R.id.header_direction)
	public TextView direction;

	@InjectPresenter
	public TradesPresenter tradesPresenter;

	private TradesListAdapter tradesListAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_trades, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRefreshLayout();
		initRecyclerView();
		setFonts();

		if (getArguments() != null) {
			UUID programId = (UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID);
			tradesPresenter.setProgramId(programId);

			setFonts();

			initRefreshLayout();
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
		whoopsLabel.setTypeface(TypefaceUtil.bold());
		dateOpen.setTypeface(TypefaceUtil.bold());
		dateClose.setTypeface(TypefaceUtil.bold());
		symbol.setTypeface(TypefaceUtil.bold());
		priceOpen.setTypeface(TypefaceUtil.bold());
		priceClose.setTypeface(TypefaceUtil.bold());
		volume.setTypeface(TypefaceUtil.bold());
		profit.setTypeface(TypefaceUtil.bold());
		direction.setTypeface(TypefaceUtil.bold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimary),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> tradesPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		tradesListAdapter = new TradesListAdapter();
//		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
//				ContextCompat.getDrawable(getContext(), R.drawable.list_item_divider),
//				20, 20);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
				ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, R.drawable.list_item_divider));
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setAdapter(tradesListAdapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
				int totalItemCount = layoutManager.getItemCount();
				int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

				boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
				if (totalItemCount > 0 && endHasBeenReached) {
					tradesPresenter.onLastListItemVisible();
				}
			}
		});
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setTrades(List<OrderModel> trades, TradesViewModel.TradeServerTypeEnum tradeServerType) {
		if (trades.isEmpty()) {
			groupNoTransactions.setVisibility(View.VISIBLE);
			return;
		}

		tradesListAdapter.setTrades(trades, tradeServerType);
		header.setVisibility(View.VISIBLE);
	}

	@Override
	public void addTrades(List<OrderModel> trades) {
		tradesListAdapter.addTrades(trades);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void setTradeServerType(TradesViewModel.TradeServerTypeEnum tradeServerType) {
		switch (tradeServerType) {
			case METATRADER4:
				dateClose.setVisibility(View.VISIBLE);
				priceClose.setVisibility(View.VISIBLE);

				dateOpen.setText(String.format(Locale.getDefault(), "%s %s", getString(R.string.date), getString(R.string.open)));
				priceOpen.setText(String.format(Locale.getDefault(), "%s %s", getString(R.string.price), getString(R.string.open)));
				break;
			default:
				dateClose.setVisibility(View.GONE);
				priceClose.setVisibility(View.GONE);

				dateOpen.setText(getString(R.string.date));
				priceOpen.setText(getString(R.string.price));
				break;
		}
	}
}