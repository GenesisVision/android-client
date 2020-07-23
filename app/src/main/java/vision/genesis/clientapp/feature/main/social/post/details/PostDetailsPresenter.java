package vision.genesis.clientapp.feature.main.social.post.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.Post;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class PostDetailsPresenter extends MvpPresenter<PostDetailsView>
{
	@Inject
	public SocialManager socialManager;

	private Subscription getPostSubscription;

	private UUID postId;

	private Post post;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getPostDetails();
	}

	@Override
	public void onDestroy() {
		if (getPostSubscription != null) {
			getPostSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	public void setData(UUID postId) {
		this.postId = postId;
	}

	private void getPostDetails() {
		if (socialManager != null && postId != null) {
			getPostSubscription = socialManager.getPost(postId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetPostSuccess,
							this::handleGetPostError);
		}
	}

	private void handleGetPostSuccess(Post response) {
		getPostSubscription.unsubscribe();
		getViewState().showProgressBar(false);
		getViewState().setRefreshing(false);

		this.post = response;

		getViewState().updateView(post);
	}

	private void handleGetPostError(Throwable throwable) {
		getPostSubscription.unsubscribe();
		getViewState().showProgressBar(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getPostDetails();
	}
}
