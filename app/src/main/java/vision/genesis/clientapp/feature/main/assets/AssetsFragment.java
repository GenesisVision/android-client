package vision.genesis.clientapp.feature.main.assets;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PlatformInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.search.SearchActivity;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/05/2018.
 */

public class AssetsFragment extends BaseFragment implements AssetsView, ViewPager.OnPageChangeListener
{
	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_assets)
	public ViewPager viewPager;

	@InjectPresenter
	AssetsPresenter assetsPresenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab programsTab;

	private TabLayout.Tab fundsTab;

	private TabLayout.Tab followsTab;

	private AssetsPagerAdapter pagerAdapter;

	private Fragment currentFragment;

	private Unbinder unbinder;

	@OnClick(R.id.searchbar)
	public void onSearchbarClicked() {
		if (getActivity() != null) {
			appBarLayout.setExpanded(true, true);
			SearchActivity.startWith(getActivity());
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), ThemeUtil.getCurrentThemeResource());
		LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
		return localInflater.inflate(R.layout.fragment_assets, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initViewPager();
		initTabs();

		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) ->
				pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight()));
	}

	@Override
	public void onDestroyView() {
		if (pagerAdapter != null) {
			pagerAdapter.destroy();
		}

		if (tabSelectedListener != null) {
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);
		}

		if (tabLayoutOnPageChangeListener != null) {
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
		}

		if (viewPager != null) {
			viewPager.clearOnPageChangeListeners();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initTabs() {
		programsTab = tabLayout.newTab().setCustomView(getProgramsTabView()).setTag("programs");
		fundsTab = tabLayout.newTab().setCustomView(getFundsTabView()).setTag("funds");
		followsTab = tabLayout.newTab().setCustomView(getFollowsTabView()).setTag("follows");

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

		addPage(programsTab, true);
		addPage(fundsTab, false);
		addPage(followsTab, false);
	}

	private View getProgramsTabView() {
		CustomTabView view = new CustomTabView(getContext());
		view.setData(0, R.string.programs);
		return view;
	}

	private View getFundsTabView() {
		CustomTabView view = new CustomTabView(getContext());
		view.setData(0, R.string.funds);
		return view;
	}

	private View getFollowsTabView() {
		CustomTabView view = new CustomTabView(getContext());
		view.setData(0, R.string.follows);
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

	private void initViewPager() {
		pagerAdapter = new AssetsPagerAdapter(getChildFragmentManager(), tabLayout);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
		viewPager.addOnPageChangeListener(this);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof AssetsPagerAdapter.OnPageVisibilityChanged) {
			((AssetsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		}
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof AssetsPagerAdapter.OnPageVisibilityChanged) {
			((AssetsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPager);
	}

	@Override
	public void showProgress(boolean show) {
//		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onPlatformInfoUpdated(PlatformInfo platformInfo) {
		pagerAdapter.setProgramsFacets(platformInfo.getAssetInfo().getProgramInfo().getFacets());
		pagerAdapter.setFundsFacets(platformInfo.getAssetInfo().getFundInfo().getFacets());
	}

	@Override
	public void showPrograms() {
		viewPager.setCurrentItem(0);
	}

	@Override
	public void showFunds() {
		viewPager.setCurrentItem(1);
	}
}
