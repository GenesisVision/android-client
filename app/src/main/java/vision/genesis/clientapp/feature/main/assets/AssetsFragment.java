package vision.genesis.clientapp.feature.main.assets;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

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

	private TabLayout.Tab favoritesTab;

	private TabLayout.Tab programsTab;

	private TabLayout.Tab fundsTab;

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
	}

	@Override
	public void onDestroyView() {
		if (pagerAdapter != null)
			pagerAdapter.destroy();

		if (tabSelectedListener != null)
			tabLayout.removeOnTabSelectedListener(tabSelectedListener);

		if (tabLayoutOnPageChangeListener != null)
			viewPager.removeOnPageChangeListener(tabLayoutOnPageChangeListener);

		if (viewPager != null)
			viewPager.clearOnPageChangeListeners();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void initTabs() {
		favoritesTab = tabLayout.newTab().setCustomView(getFavoritesTabView()).setTag("favorites");
		programsTab = tabLayout.newTab().setCustomView(getProgramsTabView()).setTag("programs");
		fundsTab = tabLayout.newTab().setCustomView(getFundsTabView()).setTag("funds");

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

//		addPage(favoritesTab, false);
		addPage(programsTab, true);
		addPage(fundsTab, false);

//		tabLayout.post(this::setTabsIndicator);
	}

//	private void setTabsIndicator() {
//		for (int i = 0; i < tabLayout.getTabCount(); i++) {
//			TabLayout.Tab tab = tabLayout.getTabAt(i);
//			if (tab != null) {
//				View customView = tab.getCustomView();
//				if (customView != null) {
//					View targetViewToApplyMargin = (View) customView.getParent();
//					ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) targetViewToApplyMargin.getLayoutParams();
//
//					layoutParams.rightMargin = 200;
//					targetViewToApplyMargin.setLayoutParams(layoutParams);
//				}
//			}
//		}
//
//		Class<?> tabLayoutClass = tabLayout.getClass();
//		Field tabStrip = null;
//		try {
//			tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		}
//
//		tabStrip.setAccessible(true);
//		LinearLayout llTab = null;
//		try {
//			llTab = (LinearLayout) tabStrip.get(tabLayout);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//
//		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, Resources.getSystem().getDisplayMetrics());
//		int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, Resources.getSystem().getDisplayMetrics());
//
//		ViewGroup.LayoutParams lp = llTab.getLayoutParams();
//		lp.width = width;
//		llTab.setLayoutParams(lp);
//		for (int i = 0; i < llTab.getChildCount(); i++) {
//			View child = llTab.getChildAt(i);
//			child.setPadding(0, 0, 0, 0);
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//			params.leftMargin = left;
//			params.rightMargin = right;
//			child.setLayoutParams(params);
//			child.invalidate();
//		}
//	}

	private View getFavoritesTabView() {
		CustomTabView view = new CustomTabView(getContext());
		view.setData(R.drawable.ic_star_border_black_24dp, 0);
		return view;
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

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION)
			return;

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 16);
		if (pagerAdapter != null)
			pagerAdapter.notifyDataSetChanged();
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
		if (currentFragment != null && currentFragment instanceof AssetsPagerAdapter.OnPageVisibilityChanged)
			((AssetsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
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
//		if (platformInfo.isIsTournamentActive() && !isTournamentAlreadyAdded) {
//			addPage(tournamentTab, false);
//			pagerAdapter.setTournamentData(platformInfo.getTournamentCurrentRound(), platformInfo.getTournamentTotalRounds());
//			isTournamentAlreadyAdded = true;
//		}
		pagerAdapter.setProgramsFacets(platformInfo.getProgramsFacets());
		pagerAdapter.setFundsFacets(platformInfo.getFundsFacets());
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
