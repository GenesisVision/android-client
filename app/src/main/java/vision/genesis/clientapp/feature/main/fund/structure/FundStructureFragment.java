package vision.genesis.clientapp.feature.main.fund.structure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.FundAssetInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.fund.FundDetailsPagerAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

public class FundStructureFragment extends BaseFragment implements FundStructureView, FundDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_FUND_ID = "extra_fund_id";

	public static FundStructureFragment with(UUID fundID) {
		FundStructureFragment fundStructureFragment = new FundStructureFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_FUND_ID, fundID);
		fundStructureFragment.setArguments(arguments);
		return fundStructureFragment;
	}

	@BindView(R.id.header)
	public ViewGroup header;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	public FundStructurePresenter fundStructurePresenter;

	private Unbinder unbinder;

	private FundStructureAdapter fundStructureAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fund_structure, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		fundStructurePresenter.setFundId((UUID) getArguments().getSerializable(EXTRA_FUND_ID));

		setFonts();
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

	private void setFonts() {
	}


	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		fundStructureAdapter = new FundStructureAdapter();
		recyclerView.setAdapter(fundStructureAdapter);
	}

	@Override
	public void setAssets(List<FundAssetInfo> assets) {
		fundStructureAdapter.setAssets(assets);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			header.setVisibility(View.VISIBLE);
			recyclerView.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void pagerShow() {
		if (fundStructurePresenter != null)
			fundStructurePresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	public void onSwipeRefresh() {
		if (fundStructurePresenter != null)
			fundStructurePresenter.onSwipeRefresh();
	}
}