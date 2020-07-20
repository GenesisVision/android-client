package vision.genesis.clientapp.feature.main.social.media;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.swagger.client.model.MediaPost;
import io.swagger.client.model.MediaPostItemsViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.events.OnMediaPostClickedEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class MediaPresenter extends MvpPresenter<MediaView>
{
	private static final int TAKE = 10;

	@Inject
	public Context context;

	@Inject
	public SocialManager socialManager;

	private Subscription getMediaSubscription;

	private int skip = 0;

	private List<MediaPost> mediaPosts = new ArrayList<>();

	private boolean isActive;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		getViewState().showProgress(true);
		getMediaPosts(true);
	}

	@Override
	public void onDestroy() {
		if (getMediaSubscription != null) {
			getMediaSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		isActive = true;
	}

	void onPause() {
		isActive = false;
	}

	void onSwipeRefresh() {
		getViewState().setRefreshing(true);
		getMediaPosts(true);
	}

	void onLastListItemVisible() {
		getViewState().showProgress(true);
		getMediaPosts(false);
	}


	private void getMediaPosts(boolean forceUpdate) {
		if (forceUpdate) {
			skip = 0;
		}

		if (getMediaSubscription != null) {
			getMediaSubscription.unsubscribe();
		}
		getMediaSubscription = socialManager.getMedia("", null, skip, TAKE)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::handleGetMediaResponse,
						this::handleGetMediaError);
	}

	private void handleGetMediaResponse(MediaPostItemsViewModel response) {
		getMediaSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		if (skip == 0) {
			mediaPosts.clear();
		}

		List<MediaPost> newMediaPosts = response.getItems();

		mediaPosts.addAll(newMediaPosts);

		if (skip == 0) {
			getViewState().setMedia(newMediaPosts);
		}
		else {
			getViewState().addMedia(newMediaPosts);
		}

		skip += newMediaPosts.size();
	}

	private void handleGetMediaError(Throwable throwable) {
		getMediaSubscription.unsubscribe();
		getViewState().showProgress(false);
		getViewState().setRefreshing(false);

		ApiErrorResolver.resolveErrors(throwable,
				message -> getViewState().showSnackbarMessage(message));
	}

	@Subscribe
	public void onEventMainThread(OnMediaPostClickedEvent event) {
		if (isActive) {
			getViewState().openMediaUrl(event.getPost().getUrl());
		}
	}
}
