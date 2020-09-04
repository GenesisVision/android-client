package vision.genesis.clientapp.feature.main.fund.add_asset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.PlatformAsset;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/10/2019.
 */

public class AddAssetActivity extends BaseSwipeBackActivity implements AddAssetView
{
	private static final String EXTRA_NEED_FINISH = "extra_need_finish";

	public static void startWith(Activity activity, boolean finishAfterAssetSelected) {
		Intent intent = new Intent(activity.getApplicationContext(), AddAssetActivity.class);
		intent.putExtra(EXTRA_NEED_FINISH, finishAfterAssetSelected);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.edittext_search)
	public EditText searchEditText;


	@BindView(R.id.group_results)
	public ViewGroup resultsGroup;

	@BindView(R.id.view_pager_add_asset)
	public ViewPager viewPager;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@InjectPresenter
	AddAssetPresenter presenter;

	private AddAssetPagerAdapter pagerAdapter;

	private ArrayList<TabLayout.Tab> tabs = new ArrayList<>();

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_asset);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			setTextListener();

			return;
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}


	private void setTextListener() {
		RxTextView.textChanges(searchEditText)
				.subscribe(charSequence -> presenter.onMaskChanged(charSequence.toString()));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void initTabs(ArrayList<Pair<String, List<PlatformAsset>>> assets) {
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener()
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

		boolean isFirst = true;
		for (Pair<String, List<PlatformAsset>> pair : assets) {
			TabLayout.Tab newTab = tabLayout.newTab().setCustomView(getTabView(pair.first.toUpperCase())).setTag(pair.first);
			tabs.add(newTab);
			addPage(newTab, isFirst);
			isFirst = false;
		}
	}

	private View getTabView(String text) {
		CustomTabView view = new CustomTabView(this);
		view.setText(text);
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

	private void initViewPager(ArrayList<Pair<String, List<PlatformAsset>>> assets) {
		pagerAdapter = new AddAssetPagerAdapter(getSupportFragmentManager(), tabLayout, assets);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(3);

		TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
	}

	@Override
	public void setAssets(ArrayList<Pair<String, List<PlatformAsset>>> assets) {
		if (pagerAdapter == null && assets != null && !assets.isEmpty()) {
			initTabs(assets);
			initViewPager(assets);
		}
		if (pagerAdapter != null) {
			resultsGroup.setVisibility(View.VISIBLE);
			pagerAdapter.sendSearchResults(assets);
			for (int i = 0; i < tabs.size(); i++) {
				((CustomTabView) Objects.requireNonNull(tabs.get(i).getCustomView())).setCount(assets.get(i).second.size());
			}
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPager);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
