package vision.genesis.clientapp.feature.main.social.post;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.Post;
import io.swagger.client.model.PostItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class PostsListPresenter extends MvpPresenter<PostsListView>
{
	private static int TAKE = 10;

	@Inject
	public Context context;

	@Inject
	public SocialManager socialManager;

	private Subscription getPostsSubscription;

	private List<Post> posts = new ArrayList<>();

	private int skip = 0;

	private PostsFilter filter;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().setRefreshing(true);
		getPostsList(true);
	}

	@Override
	public void onDestroy() {
		if (getPostsSubscription != null) {
			getPostsSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	void setData(PostsFilter filter) {
		this.filter = filter;
		getPostsList(true);
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getPostsList(true);
	}

	void onLastListItemVisible() {
		getViewState().showBottomProgress(true);
		getPostsList(false);
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
			if (skip == 0) {
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
}
