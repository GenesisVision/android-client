package vision.genesis.clientapp.feature.main.social.post;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.Post;
import io.swagger.client.model.PostItemsViewModel;
import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.model.SocialPostType;
import vision.genesis.clientapp.model.events.OnNewPostCreatedEvent;
import vision.genesis.clientapp.model.events.OnNewPostEditedEvent;
import vision.genesis.clientapp.model.events.OnShowEventsCheckedChangedEvent;
import vision.genesis.clientapp.model.events.SetPostDeletedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.SocialPostView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class PostsListPresenter extends MvpPresenter<PostsListView> implements SocialPostView.Listener
{
	private static int TAKE = 10;

	@Inject
	public Context context;

	@Inject
	public ProfileManager profileManager;

	@Inject
	public SocialManager socialManager;

	@Inject
	public SettingsManager settingsManager;

	private boolean showEvents = true;

	private Subscription getProfileSubscription;

	private Subscription getPostsSubscription;

	private List<Post> posts = new ArrayList<>();

	private int skip = 0;

	private PostsFilter filter;

	private ProfileFullViewModel profile;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().setRefreshing(true);
		getShowEvents();
		getProfileInfo();
	}

	@Override
	public void onDestroy() {
		if (getProfileSubscription != null) {
			getProfileSubscription.unsubscribe();
		}
		if (getPostsSubscription != null) {
			getPostsSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void setData(PostsFilter filter) {
		this.filter = filter;
		getShowEvents();
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getPostsList(true);
	}

	void onLastListItemVisible() {
		getViewState().showBottomProgress(true);
		getPostsList(false);
	}

	void clearPostsList() {
		getViewState().setPosts(new ArrayList<>());
		skip = 0;
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
		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	private void getPostsList(boolean forceUpdate) {
		if (filter != null && socialManager != null) {
			if (forceUpdate) {
				skip = 0;
			}

			if (getPostsSubscription != null) {
				getPostsSubscription.unsubscribe();
			}
			getPostsSubscription = socialManager.getFeed(filter, skip, TAKE)
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetPostsSuccess,
							this::handleGetPostsError);
		}
	}

	private void handleGetPostsSuccess(PostItemsViewModel response) {
		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		getViewState().showNoInternet(false);
		getViewState().showEmptyList(false);
		getViewState().showBottomProgress(false);

		if (getPostsSubscription != null) {
			getPostsSubscription.unsubscribe();
		}

		List<Post> postsToAdd = response.getItems();

		if (postsToAdd.size() == 0) {
			if (skip == 0 && !filter.isCanAddNewPost()) {
				getViewState().showEmptyList(true);
			}
			return;
		}

		if (skip == 0) {
			posts.clear();
			getViewState().setPosts(postsToAdd);
		}
		else {
			getViewState().addPosts(postsToAdd);
		}
		posts.addAll(postsToAdd);
		skip += TAKE;
	}

	private void handleGetPostsError(Throwable error) {
		getPostsSubscription.unsubscribe();
		getViewState().showBottomProgress(false);

		getViewState().setRefreshing(false);
		getViewState().showProgressBar(false);
		if (ApiErrorResolver.isNetworkError(error)) {
			if (posts.size() == 0) {
				getViewState().showEmptyList(false);
				getViewState().showNoInternet(true);
			}
			getViewState().showSnackbarMessage(context.getResources().getString(R.string.network_error));
		}
	}

	@Subscribe
	public void onEventMainThread(SetPostDeletedEvent event) {
		getViewState().setPostDeleted(event.getPost(), event.isDeleted());
	}

	@Subscribe
	public void onEventMainThread(OnNewPostCreatedEvent event) {
		if (filter != null && filter.isCanAddNewPost()) {
			getPostsList(true);
		}
	}

	@Subscribe
	public void onEventMainThread(OnNewPostEditedEvent event) {
		if (filter != null && filter.isCanAddNewPost()) {
			getPostsList(true);
		}
	}

	@Override
	public void onPostMenuButtonClicked(Post post, SocialPostActionsBottomSheetFragment.Listener listener) {
		boolean isOwnPost = false;
		if (profile != null) {
			isOwnPost = profile.getId().toString().equals(post.getAuthor().getId().toString());
		}
		getViewState().showSocialPostActions(post, SocialPostType.POST, isOwnPost, listener);

	}

	@Override
	public void onPostEditClicked(Post post) {
		getViewState().showEditPost(post);
	}

	@Override
	public void onPostDeleted(Post post) {

	}

	private void getShowEvents() {
		if (settingsManager != null) {
			showEvents = settingsManager.getShowEvents();
			getViewState().setShowEventsChecked(showEvents);
			if (filter != null) {
				filter.setShowOnlyUserPosts(!showEvents);
				getPostsList(true);
			}
		}
	}

	void onShowEventsCheckChanged(boolean checked) {
		showEvents = checked;
		settingsManager.saveShowEvents(checked);
		getViewState().setShowEventsChecked(showEvents);
		EventBus.getDefault().post(new OnShowEventsCheckedChangedEvent(showEvents));
		if (filter != null) {
			filter.setShowOnlyUserPosts(!showEvents);
			getPostsList(true);
		}
	}
}
