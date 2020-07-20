package vision.genesis.clientapp.feature.main.social.media;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.MediaPost;
import io.swagger.client.model.MediaPostItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.events.OnShowMediaActivityEvent;
import vision.genesis.clientapp.ui.MediaPostShortView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialMediaView extends RelativeLayout
{
	private static final int POSTS_TAKE = 5;

	@Inject
	public SocialManager socialManager;

	@BindView(R.id.scroll_view_media)
	public HorizontalScrollView scrollView;

	@BindView(R.id.group_media)
	public LinearLayout mediaGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.padding)
	public int padding;

	public Subscription getMediaSubscription;

	private Unbinder unbinder;

	public SocialMediaView(Context context) {
		super(context);
		initView();
	}

	public SocialMediaView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SocialMediaView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.header)
	public void onHeaderClicked() {
		showMediaActivity();
	}

	private void showMediaActivity() {
		EventBus.getDefault().post(new OnShowMediaActivityEvent());
	}

	private void initView() {
		inflate(getContext(), R.layout.view_social_media, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		getMedia();
	}

	public void onDestroy() {
		if (getMediaSubscription != null) {
			getMediaSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void update() {
		getMedia();
	}

	private void getMedia() {
		if (socialManager != null) {
			getMediaSubscription = socialManager.getMedia(null, null, 0, POSTS_TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onSuccess, this::onError);
		}
	}

	private void onSuccess(MediaPostItemsViewModel model) {
		getMediaSubscription.unsubscribe();

		mediaGroup.removeAllViews();
		int index = 0;
		for (MediaPost post : model.getItems()) {
			MediaPostShortView view = new MediaPostShortView(getContext());
			view.setPost(post);
			mediaGroup.addView(view);

			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(index == 0 ? padding : 0, 0, TypedValueFormatter.toDp(16), 0);
			view.setLayoutParams(lp);

			index++;
		}

		TextView more = new TextView(getContext());
		more.setTypeface(TypefaceUtil.semibold());
		more.setText(getContext().getString(R.string.more));
		more.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
		more.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		mediaGroup.addView(more);

		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) more.getLayoutParams();
		lp.setMargins(TypedValueFormatter.toDp(16),
				TypedValueFormatter.toDp(16),
				TypedValueFormatter.toDp(24),
				TypedValueFormatter.toDp(16));
		lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
		more.setLayoutParams(lp);
		more.setGravity(Gravity.CENTER_VERTICAL);

		more.setOnClickListener(view -> showMediaActivity());

		progressBar.setVisibility(View.GONE);
	}

	private void onError(Throwable throwable) {
		getMediaSubscription.unsubscribe();
	}

}