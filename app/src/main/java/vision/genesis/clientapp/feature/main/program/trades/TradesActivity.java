package vision.genesis.clientapp.feature.main.program.trades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderModel;
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
		Intent intent = new Intent(activity, TradesActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.group_no_trades)
	public View groupNoTransactions;

	@BindView(R.id.label_whoops)
	public TextView whoopsLabel;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.header_date)
	public TextView date;

	@BindView(R.id.header_symbol)
	public TextView symbol;

	@BindView(R.id.header_price)
	public TextView price;

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

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.trades));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
	}

	private void setFonts() {
		whoopsLabel.setTypeface(TypefaceUtil.bold(this));
		date.setTypeface(TypefaceUtil.bold(this));
		symbol.setTypeface(TypefaceUtil.bold(this));
		price.setTypeface(TypefaceUtil.bold(this));
		volume.setTypeface(TypefaceUtil.bold(this));
		profit.setTypeface(TypefaceUtil.bold(this));
		direction.setTypeface(TypefaceUtil.bold(this));
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
				ContextCompat.getDrawable(this, R.drawable.divider_dot_horizontal),
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
	public void setTrades(List<OrderModel> trades) {
		tradesListAdapter.setTrades(trades);

		groupNoTransactions.setVisibility(trades.size() == 0
				? View.VISIBLE
				: View.GONE);
	}

	@Override
	public void addTrades(List<OrderModel> trades) {
		tradesListAdapter.addTrades(trades);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}
}