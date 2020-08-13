package vision.genesis.clientapp.feature.main.social.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.Post;
import io.swagger.client.model.PostItemsViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.social.post.PostsListAdapter;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.model.SocialPostType;
import vision.genesis.clientapp.model.events.SetPostDeletedEvent;
import vision.genesis.clientapp.model.events.ShowFeedActivityEvent;
import vision.genesis.clientapp.ui.SocialPostView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialLiveView extends RelativeLayout implements SocialPostView.Listener
{
	public interface Listener
	{
		void onShowSocialPostActions(Post post, SocialPostType type, boolean isOwnPost, SocialPostActionsBottomSheetFragment.Listener listener);

		void onPostEditClicked(Post post);
	}

	public static final String TYPE_LIVE = "type_live";

	public static final String TYPE_HOT = "type_hot";

	public static final String TYPE_FEED = "type_feed";

	private static final int POSTS_TAKE = 1;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SocialManager socialManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.recycler_view_live)
	public RecyclerView recyclerView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.padding)
	public int padding;

	public Subscription getPostsSubscription;

	private Subscription getProfileSubscription;

	private Unbinder unbinder;

	private PostsListAdapter adapter;

	private String type;

	private PostsFilter filter;

	private Listener listener;

	private ProfileFullViewModel profile;

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
		EventBus.getDefault().post(new ShowFeedActivityEvent(type));
	}

	@OnClick(R.id.button_more)
	public void onMoreClicked() {
		EventBus.getDefault().post(new ShowFeedActivityEvent(type));
	}

	private void initView() {
		inflate(getContext(), R.layout.view_social_live, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getProfileInfo();
		getPosts();
	}

	public void onDestroy() {
		if (getProfileSubscription != null) {
			getProfileSubscription.unsubscribe();
		}
		if (getPostsSubscription != null) {
			getPostsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void setData(String type, PostsFilter filter, Listener listener) {
		this.type = type;
		this.filter = filter;
		this.listener = listener;
		updateTitle();
		initRecyclerView();
		getPosts();
	}

	private void updateTitle() {
		if (type != null) {
			if (type.equals(TYPE_LIVE)) {
				this.title.setText(getContext().getString(R.string.live));
			}
			else if (type.equals(TYPE_HOT)) {
				this.title.setText(getContext().getString(R.string.hot));
			}
			else if (type.equals(TYPE_FEED)) {
				this.title.setText(getContext().getString(R.string.feed));
			}
		}
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		adapter = new PostsListAdapter(this, filter.getIsOwnFeed());
		adapter.setHasStableIds(true);
		recyclerView.setAdapter(adapter);
	}

	public void update() {
		getPosts();
	}

	private void getProfileInfo() {
		getProfileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		this.profile = profile;
	}

	private void handleGetProfileError(Throwable throwable) {
	}

	private void getPosts() {
		if (socialManager != null && filter != null) {
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

	@Override
	public void onPostMenuButtonClicked(Post post, SocialPostActionsBottomSheetFragment.Listener listener) {
		boolean isOwnPost = false;
		if (profile != null) {
			isOwnPost = profile.getId().toString().equals(post.getAuthor().getId().toString());
		}
		if (this.listener != null) {
			this.listener.onShowSocialPostActions(post, SocialPostType.POST, isOwnPost, listener);
		}
	}

	@Override
	public void onPostEditClicked(Post post) {
		listener.onPostEditClicked(post);
	}

	@Override
	public void onPostDeleted(Post post) {

	}

	@Subscribe
	public void onEventMainThread(SetPostDeletedEvent event) {
		adapter.setPostDeleted(event.getPost(), event.isDeleted());
	}
}