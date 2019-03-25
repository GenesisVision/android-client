package vision.genesis.clientapp.feature.main.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import io.swagger.client.model.SearchViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 07/05/2018.
 */

public class SearchActivity extends MvpAppCompatActivity implements SearchView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), SearchActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.fragment_fade_in, R.anim.hold);
	}

	@BindView(R.id.background_black)
	public View backgroundBlack;

	@BindView(R.id.background)
	public View background;

	@BindView(R.id.edittext_search)
	public EditText searchEditText;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_results)
	public ViewGroup resultsGroup;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_search)
	public ViewPager viewPager;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SearchPresenter searchPresenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab programsTab;

	private TabLayout.Tab fundsTab;

	private TabLayout.Tab managersTab;

	private SearchPagerAdapter pagerAdapter;

	@OnClick(R.id.background_black)
	public void onBackgroundBlackClicked() {
		if (background.getVisibility() == View.VISIBLE)
			hideSoftKeyboard();
		else
			onBackPressed();
	}

	@OnClick(R.id.icon_close)
	public void onCloseClicked() {
		onBackPressed();
	}

	@OnFocusChange(R.id.edittext_search)
	void onSearchFocusChange(View view, boolean hasFocus) {
		if (hasFocus)
			showBackgroundBlack();
		else
			hideBackgroundBlack();
	}

	@OnEditorAction(R.id.edittext_search)
	protected boolean onSearchEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			searchPresenter.onSearchClicked(searchEditText.getText().toString());
			background.setVisibility(View.VISIBLE);
			hideSoftKeyboard();
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_search);

		ButterKnife.bind(this);

		initTabs();
		initViewPager();

		showSoftKeyboard();

		setTextListener();
	}

	private void setTextListener() {
		RxTextView.textChanges(searchEditText)
				.subscribe(charSequence -> searchPresenter.onMaskChanged(charSequence.toString()));
	}

	private void initTabs() {
		programsTab = tabLayout.newTab().setCustomView(getTabView(R.string.programs)).setTag("programs");
		fundsTab = tabLayout.newTab().setCustomView(getTabView(R.string.funds)).setTag("funds");
		managersTab = tabLayout.newTab().setCustomView(getTabView(R.string.managers)).setTag("managers");

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

		addPage(programsTab, true);
		addPage(fundsTab, false);
		addPage(managersTab, false);
	}

	private View getTabView(int textResId) {
		CustomTabView view = new CustomTabView(this);
		view.setData(0, textResId);
		return view;
	}

	private void addPage(TabLayout.Tab tab, boolean selected) {
		if (tab.getPosition() != TabLayout.Tab.INVALID_POSITION)
			return;

		tabLayout.addTab(tab, selected);
		TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 20, 10);
		if (pagerAdapter != null)
			pagerAdapter.notifyDataSetChanged();
	}

	private void initViewPager() {
		pagerAdapter = new SearchPagerAdapter(getSupportFragmentManager(), tabLayout);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
	}

	private void showBackgroundBlack() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.fragment_fade_in);
		animation.setInterpolator(new LinearInterpolator());
		backgroundBlack.startAnimation(animation);
		backgroundBlack.setVisibility(View.VISIBLE);
	}

	private void hideBackgroundBlack() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.tooltip_fade_out);
		animation.setInterpolator(new LinearInterpolator());
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				backgroundBlack.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		backgroundBlack.startAnimation(animation);
	}

	@Override
	public void sendSearchResults(SearchViewModel results) {
		resultsGroup.setVisibility(View.VISIBLE);
		if (pagerAdapter != null) {
			pagerAdapter.sendSearchResults(results);
			((CustomTabView) programsTab.getCustomView()).setCount(results.getPrograms().getTotal());
			((CustomTabView) fundsTab.getCustomView()).setCount(results.getFunds().getTotal());
			((CustomTabView) managersTab.getCustomView()).setCount(results.getManagers().getTotal());
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		Snackbar snack = Snackbar.make(background, message, Snackbar.LENGTH_LONG);
		((TextView) snack.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
		snack.show();
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.fragment_fade_out);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.requestFocus();
		if (imm != null) {
			imm.showSoftInput(searchEditText, 0);
		}
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
		}
	}
}
