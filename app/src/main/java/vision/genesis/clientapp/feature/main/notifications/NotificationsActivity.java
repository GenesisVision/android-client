package vision.genesis.clientapp.feature.main.notifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.NotificationViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.fund.FundDetailsActivity;
import vision.genesis.clientapp.feature.main.notifications.settings.NotificationsSettingsActivity;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsActivity;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 03/10/2018.
 */

public class NotificationsActivity extends BaseSwipeBackActivity implements NotificationsView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), NotificationsActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_notifications)
	public View groupNoNotifications;

	@InjectPresenter
	NotificationsPresenter notificationsPresenter;

	private NotificationsListAdapter notificationsListAdapter;

	private SimpleSectionedRecyclerViewAdapter sectionedAdapter;

	@OnClick(R.id.settings)
	public void onSettingsClicked() {
		NotificationsSettingsActivity.startWith(this);
	}

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_notifications);

		ButterKnife.bind(this);

		setFonts();

		initRecyclerView();
		initRefreshLayout();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		notificationsListAdapter = new NotificationsListAdapter();
		sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(this, R.layout.list_item_trades_date_section, R.id.text, notificationsListAdapter);
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
					notificationsPresenter.onLastListItemVisible();
				}
			}
		});
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(notificationsPresenter::onSwipeRefresh);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setNotifications(List<NotificationViewModel> notifications, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		if (notifications.isEmpty()) {
			groupNoNotifications.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		sectionedAdapter.setSections(sections);
		notificationsListAdapter.setNotifications(notifications);
		groupNoNotifications.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void addNotifications(List<NotificationViewModel> notifications, List<SimpleSectionedRecyclerViewAdapter.Section> sections) {
		sectionedAdapter.setSections(sections);
		notificationsListAdapter.addNotifications(notifications);
	}

	@Override
	public void showProgramDetails(ProgramDetailsModel programDetailsModel) {
		ProgramDetailsActivity.startWith(this, programDetailsModel);
	}

	@Override
	public void showFundDetails(FundDetailsModel fundDetailsModel) {
		FundDetailsActivity.startWith(this, fundDetailsModel);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}
}
