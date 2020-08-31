package vision.genesis.clientapp.feature.main.social;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.swagger.client.model.Post;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.feature.main.social.feed.SocialLiveView;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.managers.AuthManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.SocialPostType;
import vision.genesis.clientapp.model.User;
import vision.genesis.clientapp.model.events.OnMediaPostClickedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

@InjectViewState
public class SocialMainPresenter extends MvpPresenter<SocialMainView> implements SocialLiveView.Listener
{
	@Inject
	public Context context;

	@Inject
	public AuthManager authManager;

	@Inject
	public SettingsManager settingsManager;

	private Subscription userSubscription;


	private boolean isActive;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		subscribeToUser();
	}

	@Override
	public void onDestroy() {
		if (userSubscription != null) {
			userSubscription.unsubscribe();
		}

		EventBus.getDefault().unregister(this);

		super.onDestroy();
	}

	void onResume() {
		isActive = true;
		updateAll();
	}

	void onPause() {
		isActive = false;
	}

	private void updateAll() {
		getViewState().updateMedia();
		getViewState().updateLive();
		getViewState().updateHot();
		getViewState().updateFeed();
		getViewState().updateUsers();
	}

	private void subscribeToUser() {
		userSubscription = authManager.userSubject
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.newThread())
				.subscribe(this::userUpdated, this::handleUserError);
	}

	private void userUpdated(User user) {
		getViewState().showAddNewPostButton(user != null);
	}

	private void handleUserError(Throwable throwable) {
		getViewState().showAddNewPostButton(false);
	}

	@Subscribe
	public void onEventMainThread(OnMediaPostClickedEvent event) {
		if (isActive) {
			getViewState().openMediaUrl(event.getPost().getUrl());
		}
	}

	@Override
	public void onShowSocialPostActions(Post post, SocialPostType type, boolean isOwnPost, SocialPostActionsBottomSheetFragment.Listener listener) {
		getViewState().showSocialPostActions(post, type, isOwnPost, listener);

	}

	@Override
	public void onPostEditClicked(Post post) {
		getViewState().showEditPost(post);
	}
}
