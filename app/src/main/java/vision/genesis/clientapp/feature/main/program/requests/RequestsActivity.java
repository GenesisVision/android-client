package vision.genesis.clientapp.feature.main.program.requests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.InvestmentProgramRequest;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 3/1/18.
 */

public class RequestsActivity extends BaseSwipeBackActivity implements RequestsView
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	public static void startWith(Activity activity, UUID programId) {
		Intent intent = new Intent(activity, RequestsActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	RequestsPresenter requestsPresenter;

	private RequestsAdapter requestsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_requests);

		ButterKnife.bind(this);

		initToolbar();
		initRefreshLayout();
		initRecyclerView();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID programId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID);
			requestsPresenter.setProgramId(programId);
		}
		else {
			Timber.e("Passed empty program to ProgramDetailsActivity");
			onBackPressed();
		}
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.program_requests));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary),
				ContextCompat.getColor(this, R.color.colorAccent),
				ContextCompat.getColor(this, R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> requestsPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		requestsAdapter = new RequestsAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
		dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_item_divider));
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setAdapter(requestsAdapter);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setRequests(List<InvestmentProgramRequest> requests) {
		requestsAdapter.setRequests(requests);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}