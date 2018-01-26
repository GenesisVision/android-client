package vision.genesis.clientapp.feature.main.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class WalletFragment extends BaseFragment implements WalletView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.group_balance)
	public ViewGroup balanceGroup;

	@BindView(R.id.balance)
	public TextView balance;

	@BindView(R.id.balance_progress)
	public ProgressBar balanceProgress;

	@InjectPresenter
	WalletPresenter walletPresenter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_wallet, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	@Override
	public void onResume() {
		super.onResume();

		walletPresenter.onResume();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.wallet));
	}

	@Override
	public void setBalance(double balance) {
		DecimalFormat df = new DecimalFormat("0.####");
		df.setRoundingMode(RoundingMode.DOWN);
		this.balance.setText(df.format(balance));
	}

	@Override
	public void showBalanceProgress() {
		balanceProgress.setVisibility(View.VISIBLE);
		balanceGroup.setVisibility(View.GONE);
	}

	@Override
	public void hideBalanceProgress() {
		balanceProgress.setVisibility(View.GONE);
		balanceGroup.setVisibility(View.VISIBLE);
	}
}
