package vision.genesis.clientapp.feature.main.wallet.my_wallets;

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
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/02/2019.
 */

public class MyWalletsFragment extends BaseFragment implements MyWalletsView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	public static MyWalletsFragment with(UUID programId) {
		MyWalletsFragment myWalletsFragment = new MyWalletsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		myWalletsFragment.setArguments(arguments);
		return myWalletsFragment;
	}

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@InjectPresenter
	public MyWalletsPresenter myWalletsPresenter;

	private MyWalletsListAdapter myWalletsListAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_my_wallets, container, false);
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
		myWalletsListAdapter = new MyWalletsListAdapter();
		recyclerView.setAdapter(myWalletsListAdapter);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			recyclerView.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void setWallets(List<WalletData> wallets) {
		if (wallets.isEmpty()) {
			recyclerView.setVisibility(View.GONE);
			return;
		}

		myWalletsListAdapter.setWallets(wallets);
		recyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void pagerShow() {
		if (myWalletsPresenter != null)
			myWalletsPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (myWalletsPresenter != null)
			myWalletsPresenter.onSwipeRefresh();
	}
}