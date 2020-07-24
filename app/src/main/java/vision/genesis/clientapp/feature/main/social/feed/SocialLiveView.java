package vision.genesis.clientapp.feature.main.social.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PostItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.social.post.PostsListAdapter;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.model.events.ShowFeedActivityEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialLiveView extends RelativeLayout
{
	private static final int POSTS_TAKE = 3;

	@Inject
	public SocialManager socialManager;

	@BindView(R.id.recycler_view_live)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.padding)
	public int padding;


	public Subscription getPostsSubscription;

	private Unbinder unbinder;

	private PostsListAdapter adapter;

	public SocialLiveView(Context context) {
		super(context);
		initView();
	}

	public SocialLiveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SocialLiveView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.header)
	public void onHeaderClicked() {
		EventBus.getDefault().post(new ShowFeedActivityEvent());
	}

	@OnClick(R.id.button_more)
	public void onMoreClicked() {
		EventBus.getDefault().post(new ShowFeedActivityEvent());
	}

	private void initView() {
		inflate(getContext(), R.layout.view_social_live, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		initRecyclerView();

		getData();
	}

	public void onDestroy() {
		if (getPostsSubscription != null) {
			getPostsSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		adapter = new PostsListAdapter();
		adapter.setHasStableIds(true);
		recyclerView.setAdapter(adapter);
	}

	public void update() {
		getData();
	}

	private void getData() {
		if (socialManager != null) {
			PostsFilter filter = new PostsFilter();
			filter.setShowOnlyUserPosts(true);
			getPostsSubscription = socialManager.getFeed(filter, 0, POSTS_TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onSuccess, this::onError);
		}
	}

	private void onSuccess(PostItemsViewModel model) {
		getPostsSubscription.unsubscribe();
		progressBar.setVisibility(View.GONE);

		adapter.setPosts(model.getItems());
	}

	private void onError(Throwable throwable) {
		getPostsSubscription.unsubscribe();
	}

}