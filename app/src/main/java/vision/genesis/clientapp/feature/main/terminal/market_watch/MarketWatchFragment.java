package vision.genesis.clientapp.feature.main.terminal.market_watch;

import static vision.genesis.clientapp.feature.main.terminal.market_watch.MarketWatchPresenter.SORTING_CHANGE;
import static vision.genesis.clientapp.feature.main.terminal.market_watch.MarketWatchPresenter.SORTING_PRICE;
import static vision.genesis.clientapp.feature.main.terminal.market_watch.MarketWatchPresenter.SORTING_SYMBOL;
import static vision.genesis.clientapp.feature.main.terminal.market_watch.MarketWatchPresenter.SORTING_VOLUME;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.Unbinder;
import io.swagger.client.model.ExchangeAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.main.terminal.market_watch.list.TickersListFragment;
import vision.genesis.clientapp.feature.main.terminal.select_account.SelectAccountBottomSheetFragment;
import vision.genesis.clientapp.feature.main.trading_account.create.CreateAccountActivity;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.model.terminal.MarketWatchTickerModel;
import vision.genesis.clientapp.ui.CustomTabView;
import vision.genesis.clientapp.utils.TabLayoutUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class MarketWatchFragment extends BaseFragment implements MarketWatchView, MarketWatchPagerAdapter.OnPageVisibilityChanged
{
	@BindView(R.id.appBarLayout)
	public AppBarLayout appBarLayout;

	@BindView(R.id.edittext_search)
	public EditText searchEditText;

	@BindView(R.id.button_clear)
	public View clearButton;

	@BindView(R.id.icon_search)
	public View searchIcon;

	@BindView(R.id.tab_layout)
	public TabLayout tabLayout;

	@BindView(R.id.view_pager_market_watch)
	public ViewPager viewPager;

	@BindView(R.id.text_symbol)
	public TextView textSymbol;

	@BindView(R.id.text_volume)
	public TextView textVolume;

	@BindView(R.id.text_price)
	public TextView textPrice;

	@BindView(R.id.text_change)
	public TextView textChange;

	@BindView(R.id.sort_symbol)
	public ImageView sortSymbol;

	@BindView(R.id.sort_volume)
	public ImageView sortVolume;

	@BindView(R.id.sort_price)
	public ImageView sortPrice;

	@BindView(R.id.sort_change)
	public ImageView sortChange;

	@BindView(R.id.search_fragment)
	public FrameLayout searchFragmentContainer;

	private TickersListFragment searchFragment;

	@InjectPresenter
	MarketWatchPresenter presenter;

	private TabLayout.OnTabSelectedListener tabSelectedListener;

	private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

	private MarketWatchPagerAdapter pagerAdapter;

	private Unbinder unbinder;

	@OnEditorAction(R.id.edittext_search)
	protected boolean onSearchEditorAction(int actionId) {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			presenter.onTickersMaskChanged(searchEditText.getText().toString());
			hideSoftKeyboard();
		}
		return false;
	}

	@OnClick(R.id.button_clear)
	public void onClearClicked() {
		this.searchEditText.setText("");
		hideSoftKeyboard();
	}

	@OnClick(R.id.group_symbol)
	public void onSortSymbolClicked() {
		presenter.onSortSymbolClicked();
	}

	@OnClick(R.id.group_volume)
	public void onSortVolumeClicked() {
		presenter.onSortVolumeClicked();
	}

	@OnClick(R.id.group_price)
	public void onSortPriceClicked() {
		presenter.onSortPriceClicked();
	}

	@OnClick(R.id.group_change)
	public void onSortChangeClicked() {
		presenter.onSortChangeClicked();
	}

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
		setTextListener();
		initSearchFragment();
	}

	private void initSearchFragment() {
		searchFragment = new TickersListFragment();
		requireActivity().getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
				.add(R.id.search_fragment, searchFragment)
				.commit();
	}

	private void setTextListener() {
		RxTextView.textChanges(searchEditText)
				.subscribe(charSequence -> presenter.onTickersMaskChanged(charSequence.toString()));
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
		viewPager.setOffscreenPageLimit(5);

		tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
		viewPager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
	}

	@Override
	public void showProgress(boolean show) {
		if (!show) {
			viewPager.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPager);
	}

	@Override
	public void updateTickers(ArrayList<MarketWatchTickerModel> favsTickers,
	                          ArrayList<MarketWatchTickerModel> btcTickers,
	                          ArrayList<MarketWatchTickerModel> bnbTickers,
	                          ArrayList<MarketWatchTickerModel> altsTickers,
	                          ArrayList<MarketWatchTickerModel> fiatsTickers) {
		if (pagerAdapter != null) {
			pagerAdapter.setFavoriteTickers(favsTickers);
			pagerAdapter.setBtcTickers(btcTickers);
			pagerAdapter.setBnbTickers(bnbTickers);
			pagerAdapter.setAltsTickers(altsTickers);
			pagerAdapter.setFiatsTickers(fiatsTickers);
		}
		if (searchFragment != null && searchFragmentContainer.getVisibility() == View.VISIBLE) {
			ArrayList<MarketWatchTickerModel> searchList = new ArrayList<>();
			searchList.addAll(btcTickers);
			searchList.addAll(bnbTickers);
			searchList.addAll(altsTickers);
			searchList.addAll(fiatsTickers);
			searchFragment.setTickers(searchList);
		}
	}

	@Override
	public void updateSorting(String currentSorting, int sortingDirection) {
		resetSortingHeader();

		switch (currentSorting) {
			default:
			case SORTING_SYMBOL:
				selectSorting(textSymbol, sortSymbol, sortingDirection);
				break;
			case SORTING_VOLUME:
				selectSorting(textVolume, sortVolume, sortingDirection);
				break;
			case SORTING_PRICE:
				selectSorting(textPrice, sortPrice, sortingDirection);
				break;
			case SORTING_CHANGE:
				selectSorting(textChange, sortChange, sortingDirection);
				break;
		}
	}

	@Override
	public void showSelectAccount(ArrayList<ExchangeAsset> accounts) {
		SelectAccountBottomSheetFragment fragment = SelectAccountBottomSheetFragment.with(accounts);
		fragment.setListener(presenter);
		fragment.show(getChildFragmentManager(), fragment.getTag());
	}

	@Override
	public void showCreateAccount(CreateAccountModel model) {
		if (getActivity() != null) {
			CreateAccountActivity.startWith(getActivity(), model);
		}
	}

	private void selectSorting(TextView text, ImageView icon, int sortingDirection) {
		Drawable newIcon = sortingDirection < 0
				? AppCompatResources.getDrawable(getContext(), R.drawable.icon_sorting_high_to_low)
				: AppCompatResources.getDrawable(getContext(), R.drawable.icon_sorting_low_to_high);

		icon.setImageDrawable(newIcon);
		icon.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));

		text.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary));
		text.setTypeface(TypefaceUtil.medium());
	}

	private void resetSortingHeader() {
		this.textSymbol.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		this.textVolume.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		this.textPrice.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		this.textChange.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));

		this.textSymbol.setTypeface(TypefaceUtil.regular());
		this.textVolume.setTypeface(TypefaceUtil.regular());
		this.textPrice.setTypeface(TypefaceUtil.regular());
		this.textChange.setTypeface(TypefaceUtil.regular());

		this.sortSymbol.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.icon_sorting_low_to_high));
		this.sortVolume.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.icon_sorting_low_to_high));
		this.sortPrice.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.icon_sorting_low_to_high));
		this.sortChange.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.icon_sorting_low_to_high));

		this.sortSymbol.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		this.sortVolume.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		this.sortPrice.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
		this.sortChange.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary));
	}

	@Override
	public void showSearchFragment(boolean show) {
		this.clearButton.setVisibility(show ? View.VISIBLE : View.GONE);
		this.searchIcon.setVisibility(!show ? View.VISIBLE : View.GONE);
		this.searchFragmentContainer.setVisibility(show ? View.VISIBLE : View.GONE);
		this.tabLayout.setVisibility(!show ? View.VISIBLE : View.GONE);
		if (!show && searchFragment != null) {
			searchFragment.setTickers(new ArrayList<>());
		}
	}

	@Override
	public void setButtonCreateAccount() {
		pagerAdapter.setFavoriteButtonCreateAccount();
	}

	@Override
	public void showLoginActivity() {
		if (getActivity() != null) {
			LoginActivity.startFrom(getActivity());
		}
	}

	@Override
	public void showFavoriteTickersProgress() {
		pagerAdapter.showFavoriteTickersProgress();
	}

	@Override
	public void showNewAccountProcessingDialog() {
		if (getContext() != null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			builder.setMessage(getString(R.string.your_trading_account_is_being_processing));
			builder.setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> dialogInterface.cancel());

			AlertDialog dialog = builder.create();
			dialog.show();

			dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
		}
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.clearFocus();
		if (imm != null) {
			imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
		}
	}

	@Override
	public void pagerShow() {

	}

	@Override
	public void pagerHide() {

	}
}