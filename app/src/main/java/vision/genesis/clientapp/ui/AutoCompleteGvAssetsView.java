package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.CommonPublicAssetsViewModel;
import io.swagger.client.model.FollowDetailsListItem;
import io.swagger.client.model.FundDetailsListItem;
import io.swagger.client.model.ProgramDetailsListItem;
import io.swagger.client.model.PublicProfile;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SearchManager;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class AutoCompleteGvAssetsView extends RelativeLayout implements AutoCompleteItemView.Listener
{
	public interface Listener
	{
		void onAssetClicked(String assetTag);
	}

	private static final int TAKE = 10;

	@Inject
	public SearchManager searchManager;

	@BindView(R.id.scroll_view_autocomplete_gv_assets)
	public ScrollView scrollView;

	@BindView(R.id.group_assets)
	public LinearLayout assetsGroup;

	@BindDimen(R.dimen.padding)
	public int padding;

	@BindDimen(R.dimen.autocomplete_item_padding)
	public int autoCompleteItemPadding;

	@BindDimen(R.dimen.autocomplete_item_height)
	public int autoCompleteItemHeight;

	@BindDimen(R.dimen.autocomplete_gv_assets_view_height)
	public int autoCompleteViewHeight;

	public Subscription getAssetsSubscription;

	private Unbinder unbinder;

	private Listener listener;

	public AutoCompleteGvAssetsView(Context context) {
		super(context);
		initView();
	}

	public AutoCompleteGvAssetsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AutoCompleteGvAssetsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_autocomplete_gv_assets, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);
	}

	public void onDestroy() {
		if (getAssetsSubscription != null) {
			getAssetsSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void onMaskChanged(String mask) {
		getAssets(mask);
	}

	public void clear() {
		assetsGroup.removeAllViews();
		assetsGroup.setVisibility(View.GONE);
		scrollView.scrollTo(0, 0);
	}

	private void getAssets(String mask) {
		if (searchManager != null) {
			if (getAssetsSubscription != null) {
				getAssetsSubscription.unsubscribe();
			}
			getAssetsSubscription = searchManager.search(mask, TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onSuccess, this::onError);
		}
	}

	private void onSuccess(CommonPublicAssetsViewModel model) {
		getAssetsSubscription.unsubscribe();

		assetsGroup.removeAllViews();

		for (ProgramDetailsListItem program : model.getPrograms().getItems()) {
			AutoCompleteItemView view = new AutoCompleteItemView(getContext());
			view.setListener(this);
			view.setData(program.getLogoUrl(), program.getColor(), null, program.getUrl(), getContext().getString(R.string.program));
			assetsGroup.addView(view);
		}

		for (FundDetailsListItem fund : model.getFunds().getItems()) {
			AutoCompleteItemView view = new AutoCompleteItemView(getContext());
			view.setListener(this);
			view.setData(fund.getLogoUrl(), fund.getColor(), null, fund.getUrl(), getContext().getString(R.string.fund));
			assetsGroup.addView(view);
		}

		for (FollowDetailsListItem follow : model.getFollows().getItems()) {
			AutoCompleteItemView view = new AutoCompleteItemView(getContext());
			view.setListener(this);
			view.setData(follow.getLogoUrl(), follow.getColor(), null, follow.getUrl(), getContext().getString(R.string.follow));
			assetsGroup.addView(view);
		}

		for (PublicProfile user : model.getManagers().getItems()) {
			AutoCompleteItemView view = new AutoCompleteItemView(getContext());
			view.setListener(this);
			view.setData(null, null, user.getLogoUrl(), user.getUrl(), getContext().getString(R.string.user));
			assetsGroup.addView(view);
		}

		LayoutParams lp = (LayoutParams) this.getLayoutParams();
		int newHeight = assetsGroup.getChildCount() * autoCompleteItemHeight + 2 * autoCompleteItemPadding;
		if (newHeight > autoCompleteViewHeight) {
			newHeight = autoCompleteViewHeight;
		}
		lp.height = newHeight;
		this.setLayoutParams(lp);

		scrollView.scrollTo(0, 0);
		assetsGroup.setVisibility(assetsGroup.getChildCount() > 0 ? View.VISIBLE : View.GONE);
	}

	private void onError(Throwable throwable) {
		getAssetsSubscription.unsubscribe();
	}

	@Override
	public void onAssetClicked(String assetTag) {
		if (listener != null) {
			listener.onAssetClicked(assetTag);
		}
	}
}