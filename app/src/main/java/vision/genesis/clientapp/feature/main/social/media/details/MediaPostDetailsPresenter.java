package vision.genesis.clientapp.feature.main.social.media.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.UUID;

import javax.inject.Inject;

import io.swagger.client.model.MediaPost;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2020.
 */

@InjectViewState
public class MediaPostDetailsPresenter extends MvpPresenter<MediaPostDetailsView>
{
	@Inject
	public SocialManager socialManager;

	private Subscription getMediaPostSubscription;

	private UUID postId;

	private MediaPost post;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getMediaPostDetails();
	}

	@Override
	public void onDestroy() {
		if (getMediaPostSubscription != null) {
			getMediaPostSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	public void setData(UUID postId) {
		this.postId = postId;
	}

	void onResume() {
		getMediaPostDetails();
	}

	private void getMediaPostDetails() {
		if (socialManager != null && postId != null) {
			if (getMediaPostSubscription != null) {
				getMediaPostSubscription.unsubscribe();
			}
			getMediaPostSubscription = socialManager.getMediaPost(postId)
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.io())
					.subscribe(this::handleGetMediaPostSuccess,
							this::handleGetMediaPostError);
		}
	}

	private void handleGetMediaPostSuccess(MediaPost response) {
		getMediaPostSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		this.post = response;

		getViewState().updateView(post);
	}

	private void handleGetMediaPostError(Throwable throwable) {
		getMediaPostSubscription.unsubscribe();
		getViewState().showProgressBar(false);

		ApiErrorResolver.resolveErrors(throwable, message -> getViewState().showSnackbarMessage(message));
	}
}
