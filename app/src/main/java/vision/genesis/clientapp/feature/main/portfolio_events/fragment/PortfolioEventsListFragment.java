package vision.genesis.clientapp.feature.main.portfolio_events.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.DashboardPortfolioEvent;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/08/2018.
 */

public class PortfolioEventsListFragment extends BaseFragment implements PortfolioEventsListView
{
	private static final String EXTRA_PRODUCT_ID = "extra_product_id";

	public static PortfolioEventsListFragment with(UUID productId) {
		PortfolioEventsListFragment portfolioEventsListFragment = new PortfolioEventsListFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PRODUCT_ID, productId);
		portfolioEventsListFragment.setArguments(arguments);
		return portfolioEventsListFragment;
	}

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	public PortfolioEventsListPresenter portfolioEventsListPresenter;

	private PortfolioEventsListAdapter portfolioEventsListAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_portfolio_events, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRefreshLayout();
		initRecyclerView();

		if (getArguments() != null) {
			portfolioEventsListPresenter.setProductId((UUID) getArguments().getSerializable(EXTRA_PRODUCT_ID));
		}
		else {
			Timber.e("Passed empty productId to PortfolioEventsFragment");
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		portfolioEventsListPresenter.onShow();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}


	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed),
				ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen));
		refreshLayout.setOnRefreshListener(() -> portfolioEventsListPresenter.onSwipeRefresh());
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		portfolioEventsListAdapter = new PortfolioEventsListAdapter();
		recyclerView.setAdapter(portfolioEventsListAdapter);
	}

	@Override
	public void setEvents(List<DashboardPortfolioEvent> events) {

	}

	@Override
	public void setRefreshing(boolean show) {

	}
}