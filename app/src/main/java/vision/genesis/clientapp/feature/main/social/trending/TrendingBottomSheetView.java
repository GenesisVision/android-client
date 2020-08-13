package vision.genesis.clientapp.feature.main.social.trending;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.SocialPostPlatformAsset;
import io.swagger.client.model.SocialSummary;
import io.swagger.client.model.SocialSummaryHashTag;
import io.swagger.client.model.SocialSummaryStrategy;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.events.OnHashTagClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.HotTopicView;
import vision.genesis.clientapp.ui.SocialTagView;
import vision.genesis.clientapp.ui.TopAssetView;
import vision.genesis.clientapp.ui.TopStrategyView;
import vision.genesis.clientapp.utils.Constants;
import vision.genesis.clientapp.utils.TypedValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/12/2018.
 */

public class TrendingBottomSheetView extends RelativeLayout implements SocialTagView.Listener
{
	public interface Listener
	{
		void onTagsChanged(List<String> hashTags, List<UUID> contentIds);
	}

	@Inject
	public SocialManager socialManager;

	@BindView(R.id.hashtags_flex_box)
	public FlexboxLayout flexBox;

	@BindView(R.id.group_hot_topics)
	public LinearLayout hotTopicsGroup;

	@BindView(R.id.group_top_strategies)
	public LinearLayout topStrategiesGroup;

	@BindView(R.id.group_trending_assets)
	public LinearLayout trendingAssetsGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.padding)
	public int padding;

	private Listener listener;

	private Subscription getDataSubscription;

	private Unbinder unbinder;

	private List<String> hashTags = new ArrayList<>();

	private List<UUID> contentIds = new ArrayList<>();

	public TrendingBottomSheetView(Context context) {
		super(context);
		initView();
	}

	public TrendingBottomSheetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TrendingBottomSheetView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void initView() {
		inflate(getContext(), R.layout.view_bottomsheet_trending, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getData();
	}

	public void onDestroy() {
		if (getDataSubscription != null) {
			getDataSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private void getData() {
		if (socialManager != null) {
			showProgress(true);
			getDataSubscription = socialManager.getSummary()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetDataSuccess,
							this::handleGetDataError);
		}
	}

	private void handleGetDataSuccess(SocialSummary response) {
		getDataSubscription.unsubscribe();
		showProgress(false);

		int index = 0;
		hotTopicsGroup.removeAllViews();
		for (SocialSummaryHashTag hotTopic : response.getHotTopics()) {
			HotTopicView view = new HotTopicView(getContext());
			view.setHotTopic(hotTopic);
			view.setOnClickListener(view1 -> handleHotTopicClick(hotTopic));
			hotTopicsGroup.addView(view);

			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(index == 0 ? padding : 0, 0, TypedValueFormatter.toDp(16), 0);
			view.setLayoutParams(lp);

			index++;
		}

		index = 0;
		topStrategiesGroup.removeAllViews();
		for (SocialSummaryStrategy topStrategy : response.getTopStrategies()) {
			TopStrategyView view = new TopStrategyView(getContext());
			view.setTopStrategy(topStrategy);
			view.setOnClickListener(view1 -> handleTopStrategyClick(topStrategy));
			topStrategiesGroup.addView(view);

			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(index == 0 ? padding : 0, 0, TypedValueFormatter.toDp(16), 0);
			view.setLayoutParams(lp);

			index++;
		}

		index = 0;
		trendingAssetsGroup.removeAllViews();
		for (SocialPostPlatformAsset topAsset : response.getTopAssets()) {
			TopAssetView view = new TopAssetView(getContext());
			view.setTopAsset(topAsset);
			view.setOnClickListener(view1 -> handleTopAssetClick(topAsset));
			trendingAssetsGroup.addView(view);

			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(index == 0 ? padding : 0, 0, TypedValueFormatter.toDp(16), 0);
			view.setLayoutParams(lp);

			index++;
		}
	}

	private void handleHotTopicClick(SocialSummaryHashTag hotTopic) {
		addTag(hotTopic.getHashTag(), null);
	}

	private void handleTopStrategyClick(SocialSummaryStrategy topStrategy) {
		addTag(topStrategy.getTitle(), topStrategy.getId());
	}

	private void handleTopAssetClick(SocialPostPlatformAsset topAsset) {
		addTag("#".concat(topAsset.getAsset()), null);
	}

	private void addTag(String hashTag, UUID contentId) {
		for (String tag : hashTags) {
			if (tag.equals(hashTag)) {
				showToast(getContext().getString(R.string.error_hashtag_added));
				return;
			}
		}

		if (hashTags.size() >= Constants.MAX_SOCIAL_TAGS_FILTER) {
			showToast(getContext().getString(R.string.error_too_many_hashtags_added));
			return;
		}

		SocialTagView view = new SocialTagView(getContext());
		int[] colors = getResources().getIntArray(R.array.assetsColors);
		view.setTag(hashTag, colors[(new Random()).nextInt(colors.length)], contentId);
		view.setListener(this);
//		flexBox.addView(view, flexBox.getChildCount() - 1);
		flexBox.addView(view);
		FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
		lp.setMargins(0, 0, TypedValueFormatter.toDp(10), TypedValueFormatter.toDp(4));
		view.setLayoutParams(lp);

		updatePeekHeight();

		if (contentId != null) {
			contentIds.add(contentId);
		}
		else {
			hashTags.add(hashTag);
		}

		if (listener != null) {
			listener.onTagsChanged(hashTags, contentIds);
		}
	}

	private void handleGetDataError(Throwable throwable) {
		getDataSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onRemoveTagClicked(String hashTag, UUID contentId) {
		for (int i = 0; i < flexBox.getChildCount(); i++) {
			if (((SocialTagView) flexBox.getChildAt(i)).getHashTag().equals(hashTag)) {
				flexBox.removeViewAt(i);
				break;
			}
		}

		updatePeekHeight();
		hashTags.remove(hashTag);
		contentIds.remove(contentId);

		if (listener != null) {
			listener.onTagsChanged(hashTags, contentIds);
		}
	}

	private void updatePeekHeight() {
		BottomSheetBehavior bottomsheetBehavior = BottomSheetBehavior.from(this);
		flexBox.post(() -> bottomsheetBehavior.setPeekHeight(TypedValueFormatter.toDp(60) + flexBox.getHeight()));
	}

	public void clearTags() {
		flexBox.removeAllViews();

		updatePeekHeight();
		hashTags.clear();
		contentIds.clear();

		if (listener != null) {
			listener.onTagsChanged(hashTags, contentIds);
		}
	}

	@Subscribe
	public void onEventMainThread(OnHashTagClickedEvent event) {
		addTag(event.getHashTag(), null);
	}
}
