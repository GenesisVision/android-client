package vision.genesis.clientapp.feature.main.program.trades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderModel;
import io.swagger.client.model.TradesViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.DividerItemDecoration;
import vision.genesis.clientapp.ui.ToolbarView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 4/1/18.
 */

public class TradesActivity extends BaseSwipeBackActivity implements TradesView
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	public static void startWith(Activity activity, UUID programId) {
		Intent intent = new Intent(activity.getApplicationContext(), TradesActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_trades);

		ButterKnife.bind(this);

		initToolbar();
		initRefreshLayout();
		initRecyclerView();
		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID programId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID);
			tradesPresenter.setProgramId(programId);
		}
		else {
			Timber.e("Passed empty programId to TradesActivity");
			onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		recyclerView.setAdapter(null);

		super.onDestroy();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.trades));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
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
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary),
				ContextCompat.getColor(this, R.color.colorAccent),
				ContextCompat.getColor(this, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> tradesPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		tradesListAdapter = new TradesListAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
				ContextCompat.getDrawable(this, R.drawable.list_item_divider),
				20, 20);
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