package vision.genesis.clientapp.feature.main.copytrading.open_trades;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.OrderSignalModel;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.dashboard.old.investor.DashboardPagerAdapter;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class CopytradingOpenTradesFragment extends BaseFragment implements CopytradingOpenTradesView, DashboardPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_ACCOUNT_ID = "extra_account_id";

	public static CopytradingOpenTradesFragment with(UUID accountId) {
		CopytradingOpenTradesFragment copytradingOpenTradesFragment = new CopytradingOpenTradesFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_ACCOUNT_ID, accountId);
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
		return inflater.inflate(R.layout.fragment_copytrading_open_trades, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			emptyGroup.setNestedScrollingEnabled(true);
		}

		if (getArguments() != null) {
			UUID accountId = (UUID) getArguments().getSerializable(EXTRA_ACCOUNT_ID);
			if (accountId != null) {
				copytradingOpenTradesPresenter.setData(accountId);
				initRecyclerView();
				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();

	}

	@Override
	public void onPause() {
		super.onPause();

		copytradingOpenTradesPresenter.onHide();
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
	public void removeOpenTrade(int position, boolean isListEmpty) {
		copytradingOpenTradesAdapter.deleteTrade(position);
		showEmpty(isListEmpty);
	}

	@Override
	public void askCloseTrade(UUID tradeId, String symbol, Double volume) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle(getString(R.string.closing_the_trade));
		builder.setMessage(String.format(Locale.getDefault(), getString(R.string.close_trade_template),
				symbol,
				StringFormatUtil.formatAmount(volume)));
		builder.setPositiveButton(getString(R.string.close_trade), (dialogInterface, i) -> copytradingOpenTradesPresenter.closeTrade(tradeId));
		builder.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.cancel());

		AlertDialog dialog = builder.create();
		dialog.show();

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
		dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
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
		if (copytradingOpenTradesPresenter != null) {
			copytradingOpenTradesPresenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}
}