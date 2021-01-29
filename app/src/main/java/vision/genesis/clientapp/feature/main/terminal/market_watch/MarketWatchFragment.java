package vision.genesis.clientapp.feature.main.terminal.market_watch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class MarketWatchFragment extends BaseFragment implements MarketWatchView
{
	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_market_watch)
	public ViewPager viewPager;

	@InjectPresenter
	MarketWatchPresenter presenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private MarketWatchPagerAdapter pagerAdapter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_market_watch, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initViewPager();
		initTabs();
	}

	@Override
	public void onResume() {
		super.onResume();

		presenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
		if (tabSelectedListener != null && tabLayout != null) {
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);
		}

		if (tabLayoutOnPageChangeListener != null && viewPager != null) {
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
		}
		if (pagerAdapter != null) {
			pagerAdapter.destroy();
		}
		if (viewPager != null) {
			viewPager.clearOnPageChangeListeners();
		}

		super.onDestroyView();
	}

	@Override
	public void onShow() {
		presenter.onResume();
	}

	@Override
	public void onHide() {
		presenter.onPause();
	}

	private void initTabs() {
		TabLayout.Tab favoritesTab = tabLayout.newTab().setCustomView(getTabView(R.drawable.icon_favorite, 0)).setTag("favorites");
		TabLayout.Tab btcTab = tabLayout.newTab().setCustomView(getTabView(0, R.string.btc)).setTag("btc");
		TabLayout.Tab bnbTab = tabLayout.newTab().setCustomView(getTabView(0, R.string.bnb)).setTag("bnb");
		TabLayout.Tab altsTab = tabLayout.newTab().setCustomView(getTabView(0, R.string.alts)).setTag("alts");
		TabLayout.Tab fiatsTab = tabLayout.newTab().setCustomView(getTabView(0, R.string.fiats)).setTag("fiats");

		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		tabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(false);
				}
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(CustomTabView.class)) {
					((CustomTabView) tab.getCustomView()).setSelectedState(true);
				}
			}
		};

		tabLayout.addOnTabSelectedListener(tabSelectedListener);

		addPage(favoritesTab, false);
		addPage(btcTab, true);
		addPage(bnbTab, false);
		addPage(altsTab, false);
		addPage(fiatsTab, false);
	}


	private View getTabView(int iconResId, int textResId) {
		CustomTabView view = new CustomTabView(getContext());
		view.setData(iconResId, textResId);
		return view;
	}

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION) {
			return;
		}

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
		if (pagerAdapter != null) {
			pagerAdapter.notifyDataSetChanged();
		}
	}

	public void initViewPager() {
		pagerAdapter = new MarketWatchPagerAdapter(getChildFragmentManager(), tabLayout);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
	}

	@Override
	public void updateTickers(ArrayList<MarketWatchTickerModel> tickers) {
		if (pagerAdapter != null) {
			pagerAdapter.setBtcTickers(tickers);
		}
	}

	@Override
	public void showProgress(boolean show) {
//		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			viewPager.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPager);
	}
}