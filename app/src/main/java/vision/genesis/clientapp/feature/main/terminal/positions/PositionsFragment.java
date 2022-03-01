package vision.genesis.clientapp.feature.main.terminal.positions;

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
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.BinanceRawFuturesPosition;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.terminal.positions.change_margin.ChangeMarginBottomSheetFragment;
import vision.genesis.clientapp.feature.main.terminal.positions.close.ClosePositionBottomSheetFragment;
import vision.genesis.clientapp.feature.main.terminal.positions.tpsl.TpSlBottomSheetFragment;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

public class PositionsFragment extends BaseFragment implements PositionsView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	private static final String EXTRA_SYMBOL = "extra_symbol";

	public static PositionsFragment with(UUID accountId, String symbol) {
		PositionsFragment positionsFragment = new PositionsFragment();
		Bundle arguments = new Bundle(2);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
		arguments.putString(EXTRA_SYMBOL, symbol);
		positionsFragment.setArguments(arguments);
		return positionsFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_no_positions)
	public View groupNoPositions;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	public PositionsPresenter presenter;

	private PositionsListAdapter adapter;

	private Unbinder unbinder;

	private UUID accountId;

	private String symbol;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_positions, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRecyclerView();

		if (getArguments() != null) {
			accountId = (UUID) getArguments().getSerializable(EXTRA_ACCOUNT_ID);
			symbol = getArguments().getString(EXTRA_SYMBOL);
			if (accountId != null) {
				presenter.setData(accountId, symbol);
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
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

		adapter = new PositionsListAdapter(presenter);
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showChangePositionMargin(BinanceRawFuturesPosition position) {
		if (getActivity() != null) {
			ChangeMarginBottomSheetFragment fragment = ChangeMarginBottomSheetFragment.with(accountId, symbol, position);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	public void showTpSl(BinanceRawFuturesPosition position) {
		if (getActivity() != null) {
			TpSlBottomSheetFragment fragment = TpSlBottomSheetFragment.with(accountId, symbol, position);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	public void showClosePosition(BinanceRawFuturesPosition position) {
		if (getActivity() != null) {
			ClosePositionBottomSheetFragment fragment = ClosePositionBottomSheetFragment.with(accountId, symbol, position);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	public void setPositions(List<BinanceRawFuturesPosition> positions) {
		adapter.setPositions(positions);
		if (positions.isEmpty()) {
			groupNoPositions.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.GONE);
			return;
		}

		groupNoPositions.setVisibility(View.GONE);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}


	@Override
	public void pagerShow() {
		if (presenter != null) {
			presenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (presenter != null) {
			presenter.onSwipeRefresh();
		}
	}
}