package vision.genesis.clientapp.feature.main.events;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.InvestmentEventViewModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.date_range.DateRangeBottomSheetFragment;
import vision.genesis.clientapp.feature.main.events.details.EventDetailsBottomSheetFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.ui.DateRangeView;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/08/2018.
 */

public class EventsActivity extends BaseSwipeBackActivity implements EventsView
{
	public static final String GROUP_INVESTMENT = "InvestmentHistory";

	public static final String GROUP_TRADING = "TradingHistory";

	private static final String EXTRA_GROUP = "extra_group";

	public static void startWith(Activity activity, String group) {
		Intent intent = new Intent(activity.getApplicationContext(), EventsActivity.class);
		intent.putExtra(EXTRA_GROUP, group);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.date_range)
	public DateRangeView dateRangeView;

	@BindView(R.id.group_no_events)
	public ViewGroup groupNoEvents;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	EventsPresenter presenter;

	private EventsListAdapter eventsListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

	private DateRange dateRange = DateRange.createFromEnum(DateRange.DateRangeEnum.ALL_TIME);

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.date_range)
	public void onDateRangeClicked() {
		DateRangeBottomSheetFragment bottomSheetDialog = new DateRangeBottomSheetFragment();
		bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
		bottomSheetDialog.setDateRange(dateRange);
		bottomSheetDialog.setListener(presenter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_portfolio_events);

		ButterKnife.bind(this);


		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			String eventsGroup = getIntent().getExtras().getString(EXTRA_GROUP);
			if (eventsGroup != null) {
				presenter.setData(eventsGroup);

				updateTitle(eventsGroup);

				initRefreshLayout();
				initRecyclerView();
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateTitle(String eventsGroup) {
		this.title.setText(getString(eventsGroup.equals(GROUP_INVESTMENT) ? R.string.investment_history
				: eventsGroup.equals(GROUP_TRADING) ? R.string.trading_history : R.string.events));
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (presenter != null) {
			presenter.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (presenter != null) {
			presenter.onPause();
		}
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			presenter.onSwipeRefresh();
		});
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		eventsListAdapter = new EventsListAdapter();
		sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(this, R.layout.list_item_trades_date_section, R.id.text, eventsListAdapter);
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
			refreshLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
		dateRangeView.setDateRange(dateRange);
	}

	@Override
	public void setEvents(List<InvestmentEventViewModel> events, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		if (events.isEmpty()) {
			groupNoEvents.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		sectionedAdapter.setSections(sections);
		eventsListAdapter.setEvents(events);
		groupNoEvents.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addEvents(List<InvestmentEventViewModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		sectionedAdapter.setSections(sections);
		eventsListAdapter.addEvents(trades);
	}

	@Override
	public void showEventDetails(InvestmentEventViewModel event) {
		EventDetailsBottomSheetFragment fragment = new EventDetailsBottomSheetFragment();
		fragment.setData(event);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
