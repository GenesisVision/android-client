package vision.genesis.clientapp.feature.main.terminal.market_watch.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class TickersListFragment extends BaseFragment
{
	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	private TickersListAdapter tickersListAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tickers_list, container, false);
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

		tickersListAdapter = new TickersListAdapter();
		recyclerView.setAdapter(tickersListAdapter);
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	public void setTickers(List<MarketWatchTickerModel> tickers) {
		showProgress(false);
		tickersListAdapter.setTickers(tickers);
	}
}