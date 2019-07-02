package vision.genesis.clientapp.feature.main.copytrading.open_trades;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderSignalModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.investor.DashboardPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class CopytradingOpenTradesFragment extends BaseFragment implements CopytradingOpenTradesView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	public static CopytradingOpenTradesFragment with() {
		CopytradingOpenTradesFragment copytradingOpenTradesFragment = new CopytradingOpenTradesFragment();
		Bundle arguments = new Bundle(1);
		copytradingOpenTradesFragment.setArguments(arguments);
		return copytradingOpenTradesFragment;
	}

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@InjectPresenter
	public CopytradingOpenTradesPresenter copytradingOpenTradesPresenter;

	private CopytradingOpenTradesAdapter copytradingOpenTradesAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dashboard_open_trades, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			emptyGroup.setNestedScrollingEnabled(true);
		}

		initRecyclerView();
	}

	@Override
	public void onResume() {
		super.onResume();

		copytradingOpenTradesPresenter.onShow();
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		copytradingOpenTradesAdapter = new CopytradingOpenTradesAdapter();
		copytradingOpenTradesAdapter.setHasStableIds(true);
		recyclerView.setAdapter(copytradingOpenTradesAdapter);
	}

	@Override
	public void setOpenTrades(List<OrderSignalModel> trades) {
		copytradingOpenTradesAdapter.setTrades(trades);

		showEmpty(trades.size() == 0);
	}

	@Override
	public void showEmpty(boolean show) {
		if (emptyGroup != null) {
			emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void showProgressBar(boolean show) {
		if (progressBar != null) {
			progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (copytradingOpenTradesPresenter != null)
			copytradingOpenTradesPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}