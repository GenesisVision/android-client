package vision.genesis.clientapp.feature.main.wallet.copytrading_accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.CopyTradingAccountInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/06/2019.
 */

public class CopytradingAccountsFragment extends BaseFragment implements CopytradingAccountsView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	public CopytradingAccountsPresenter copytradingAccountsPresenter;

	private CopytradingAccountsListAdapter copytradingAccountsListAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_copytrading_accounts, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initRecyclerView();
		setFonts();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		copytradingAccountsListAdapter = new CopytradingAccountsListAdapter();
		recyclerView.setAdapter(copytradingAccountsListAdapter);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			recyclerView.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void setAccounts(List<CopyTradingAccountInfo> accounts) {
		if (accounts.isEmpty()) {
			recyclerView.setVisibility(View.GONE);
			return;
		}

		copytradingAccountsListAdapter.setAccounts(accounts);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (copytradingAccountsPresenter != null)
			copytradingAccountsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (copytradingAccountsPresenter != null)
			copytradingAccountsPresenter.onSwipeRefresh();
	}
}