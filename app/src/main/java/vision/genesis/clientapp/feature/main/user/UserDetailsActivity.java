package vision.genesis.clientapp.feature.main.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.PublicProfile;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.common.DetailsTabView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class UserDetailsActivity extends BaseSwipeBackActivity implements UserDetailsView, ViewPager.OnPageChangeListener
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, UserDetailsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), UserDetailsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ViewGroup toolbar;

	@BindView(R.id.swipe_refresh)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.toolbar_manager_avatar)
	public AvatarView toolbarUserAvatar;

	@BindView(R.id.toolbar_manager_name)
	public TextView toolbarUserName;

	@BindView(R.id.group_toolbar_buttons)
	public ViewGroup toolbarButtons;

	@BindView(R.id.button_notifications)
	public ImageView notificationsButton;

	@BindView(R.id.button_favorite)
	public ImageView favoriteButton;

	@BindView(R.id.manager_avatar)
	public SimpleDraweeView programLogo;

	@BindView(R.id.manager_name)
	public TextView userName;

	@BindView(R.id.manager_date)
	public TextView managerDate;

	@BindView(R.id.group_follow_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.button_follow)
	public PrimaryButton followButton;

	@BindView(R.id.button_unfollow)
	public PrimaryButton unfollowButton;

	@BindView(R.id.progress_bar_follow)
	public ProgressBar followProgressBar;

	@BindView(R.id.app_bar_layout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.collapsing_toolbar_screen)
	public View collapsingToolbarScreen;

	@BindView(R.id.group_tabs)
	public ViewGroup tabsGroup;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_program_details)
	public ViewPager viewPager;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.progress_bar_no_internet)
	public ProgressBar noInternetProgressBar;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@InjectPresenter
	UserDetailsPresenter presenter;

	private PublicProfile userDetails;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private TabLayout.Tab infoTab;

	private TabLayout.Tab feedTab;

	private TabLayout.Tab programsTab;

	private TabLayout.Tab fundsTab;

	private TabLayout.Tab followsTab;

	private UserDetailsPagerAdapter pagerAdapter;

	private Fragment currentFragment;

	private UserDetailsModel model;

	private boolean isPagerDragging;

	private int verticalOffset = 0;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_follow)
	public void onFollowClicked() {
		presenter.onFollowClicked();
	}

	@OnClick(R.id.button_unfollow)
	public void onUnfollowClicked() {
		presenter.onUnfollowClicked();
	}

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		presenter.onTryAgainClicked();
	}

	@OnClick(R.id.button_favorite)
	public void onFavoriteClicked() {
		if (userDetails != null) {
//			programDetails.isFavorite(!programDetails.isIsFavorite());
//			setFavoriteButtonImage(programDetails.isIsFavorite());
//			programInfoPresenter.onFavoriteButtonClicked(programDetails.isIsFavorite());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_user_details);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);

			initRefreshLayout();
			updateHeader();
			unfollowButton.setEmpty();

			presenter.setUserId(model.getUserId());

			setAnimations();
		}
		else {
			Timber.e("Passed empty program to ManagerDetailsActivity");
			onBackPressed();
		}
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(() -> {
			presenter.onSwipeRefresh();
			if (pagerAdapter != null) {
				pagerAdapter.sendSwipeRefresh();
			}
		});
	}

	private void setAnimations() {
		appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
			this.verticalOffset = verticalOffset;
			double toolbarStartOffset = Math.abs((float) appBarLayout.getTotalScrollRange() * 0.15);
			float alphaPercent = ((float) Math.abs(verticalOffset) / ((float) appBarLayout.getTotalScrollRange() - 100));
			collapsingToolbarScreen.setAlpha(alphaPercent);

			float toolbarAlphaPercent = 1;
			if (Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() - toolbarStartOffset) {
				toolbarAlphaPercent = (float) ((appBarLayout.getTotalScrollRange() - Math.abs(verticalOffset)) / toolbarStartOffset);
			}
			toolbarUserAvatar.setAlpha(1 - toolbarAlphaPercent);
			toolbarUserName.setAlpha(1 - toolbarAlphaPercent);

			updateRefreshLayoutEnabled();

			if (pagerAdapter != null) {
				pagerAdapter.onOffsetChanged(appBarLayout.getHeight() + verticalOffset - tabLayout.getHeight() - toolbar.getHeight());
			}
		});
	}

	private void updateRefreshLayoutEnabled() {
		refreshLayout.setEnabled(verticalOffset == 0 && !isPagerDragging);
	}

	@Override
	protected void onDestroy() {
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

		super.onDestroy();
	}

	private void setFonts() {
		toolbarUserName.setTypeface(TypefaceUtil.semibold());
		userName.setTypeface(TypefaceUtil.semibold());
	}

	private void updateHeader() {
		programLogo.setImageURI(ImageUtils.getImageUri(model.getAvatar()));
		toolbarUserAvatar.setImage(model.getAvatar(), 50, 50);

		userName.setText(model.getName());
		toolbarUserName.setText(model.getName());

		if (model.getRegistrationDate() != null) {
			managerDate.setText(String.format(Locale.getDefault(),
					getString(R.string.manager_member_sicne_template),
					DateTimeUtil.formatShortDate(model.getRegistrationDate())));
		}
	}

	private void initTabs() {
		infoTab = tabLayout.newTab().setCustomView(getTabView(R.string.info)).setTag("info");
		feedTab = tabLayout.newTab().setCustomView(getTabView(R.string.feed)).setTag("feed");
		programsTab = tabLayout.newTab().setCustomView(getTabView(R.string.programs)).setTag("programs");
		fundsTab = tabLayout.newTab().setCustomView(getTabView(R.string.funds)).setTag("funds");
		followsTab = tabLayout.newTab().setCustomView(getTabView(R.string.follow)).setTag("follows");

		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		tabSelectedListener = new TabLayout.OnTabSelectedListener()
		{
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(DetailsTabView.class)) {
					((DetailsTabView) tab.getCustomView()).setSelectedState(true);
				}
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(DetailsTabView.class)) {
					((DetailsTabView) tab.getCustomView()).setSelectedState(false);
				}
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				if (tab.getCustomView() != null && tab.getCustomView().getClass().equals(DetailsTabView.class)) {
					((DetailsTabView) tab.getCustomView()).setSelectedState(true);
				}
			}
		};

		tabLayout.addOnTabSelectedListener(tabSelectedListener);

		addPage(infoTab, true);
		addPage(feedTab, false);
		addPage(programsTab, false);
		addPage(fundsTab, false);
		addPage(followsTab, false);
	}

	private View getTabView(int textResId) {
		DetailsTabView view = new DetailsTabView(this);
		view.setData(textResId);
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

	@Override
	protected void onResume() {
		super.onResume();
		presenter.onResume();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void initViewPager(UUID userId, boolean isOwnDetails) {
		if (pagerAdapter == null) {
			pagerAdapter = new UserDetailsPagerAdapter(getSupportFragmentManager(), tabLayout, userId, isOwnDetails);
			viewPager.setAdapter(pagerAdapter);
			viewPager.setOffscreenPageLimit(4);

			tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
			viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
			viewPager.addOnPageChangeListener(this);
			initTabs();
		}
	}

	@Override
	public void setUserDetails(PublicProfile userDetails) {
		this.userDetails = userDetails;

		model.update(userDetails);
		updateHeader();

		if (userDetails.getPersonalDetails().isAllowFollow()) {
			buttonsGroup.setVisibility(View.VISIBLE);

			followButton.setVisibility(!userDetails.getPersonalDetails().isIsFollow() ? View.VISIBLE : View.GONE);
			unfollowButton.setVisibility(userDetails.getPersonalDetails().isIsFollow() ? View.VISIBLE : View.GONE);
		}
		else {
			buttonsGroup.setVisibility(View.GONE);
		}

		tabsGroup.setVisibility(View.VISIBLE);
		viewPager.setVisibility(View.VISIBLE);

		pagerAdapter.sendUpdate();
	}

	private void setFavoriteButtonImage(boolean isFavorite) {
		favoriteButton.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(), isFavorite
				? R.drawable.icon_favorite_fill
				: R.drawable.icon_favorite));
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		if (currentFragment != null && currentFragment instanceof UserDetailsPagerAdapter.OnPageVisibilityChanged) {
			((UserDetailsPagerAdapter.OnPageVisibilityChanged) currentFragment).pagerHide();
		}
		currentFragment = pagerAdapter.getItem(position);
		if (pagerAdapter.getItem(position) instanceof UserDetailsPagerAdapter.OnPageVisibilityChanged) {
			((UserDetailsPagerAdapter.OnPageVisibilityChanged) pagerAdapter.getItem(position)).pagerShow();
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		isPagerDragging = state == ViewPager.SCROLL_STATE_DRAGGING;
		updateRefreshLayoutEnabled();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void showToolbarButtons(boolean show) {
		toolbarButtons.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
	}

	@Override
	public void showNoInternet(boolean show) {
//		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbarUserAvatar);
	}

	@Override
	public void showNoInternetProgress(boolean show) {
		noInternetProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showFollowProgress(boolean show) {
		followProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		buttonsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

//	@Override
//	public void setRefreshing(boolean refreshing) {
//		if (refreshLayout != null)
//			refreshLayout.setRefreshing(refreshing);
//	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setProgramsCount(Integer programsCount) {
		((DetailsTabView) programsTab.getCustomView()).setCount(programsCount);
	}

	@Override
	public void setFundsCount(Integer fundsCount) {
		((DetailsTabView) fundsTab.getCustomView()).setCount(fundsCount);
	}

	@Override
	public void setFollowsCount(Integer followsCount) {
		((DetailsTabView) followsTab.getCustomView()).setCount(followsCount);
	}
}
