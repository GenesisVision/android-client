package vision.genesis.clientapp.feature.main.traders.filter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.bottom_navigation.RouterProvider;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 1/26/18.
 */

public class TradersFiltersFragment extends BaseFragment implements TradersFiltersView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@InjectPresenter
	TradersFiltersPresenter tradersFiltersPresenter;

	@ProvidePresenter
	public TradersFiltersPresenter provideTraderFiltersPresenter() {
		return new TradersFiltersPresenter(((RouterProvider) getParentFragment()).getRouter());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_traders_filters, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.filters));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> tradersFiltersPresenter.onBackClicked());
	}

	@Override
	public boolean onBackPressed() {
		tradersFiltersPresenter.onBackClicked();
		return true;
	}
}
