package vision.genesis.clientapp.feature.main.social;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.Post;
import io.swagger.client.model.UserFeedMode;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.social.feed.SocialLiveView;
import vision.genesis.clientapp.feature.main.social.media.SocialMediaView;
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.feature.main.social.post.create.CreatePostActivity;
import vision.genesis.clientapp.feature.main.social.users.SocialUsersView;
import vision.genesis.clientapp.model.PostsFilter;
import vision.genesis.clientapp.model.SocialPostType;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialMainFragment extends BaseFragment implements SocialMainView
{
	@BindView(R.id.scrollview)
	public NestedScrollView scrollview;

	@BindView(R.id.live_view)
	public SocialLiveView liveView;

	@BindView(R.id.hot_view)
	public SocialLiveView hotView;

	@BindView(R.id.feed_view)
	public SocialLiveView feedView;

	@BindView(R.id.media_view)
	public SocialMediaView mediaView;

	@BindView(R.id.users_view)
	public SocialUsersView usersView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SocialMainPresenter presenter;

	private Unbinder unbinder;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_social_main, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		initViews();
	}

	private void initViews() {
		PostsFilter liveFilter = new PostsFilter();
		liveFilter.setShowOnlyUserPosts(true);
		liveView.setData(SocialLiveView.TYPE_LIVE, liveFilter, presenter);

		PostsFilter hotFilter = new PostsFilter();
		hotFilter.setShowTop(true);
		hotFilter.setShowOnlyUserPosts(true);
		hotView.setData(SocialLiveView.TYPE_HOT, hotFilter, presenter);

		PostsFilter feedFilter = new PostsFilter();
		feedFilter.setUserMode(UserFeedMode.FRIENDSPOSTS);
		feedFilter.setShowOnlyUserPosts(true);
		feedView.setData(SocialLiveView.TYPE_FEED, feedFilter, presenter);
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	@Override
	public void onShow() {
		super.onShow();

		presenter.onResume();
	}

	@Override
	public void onResume() {
		super.onResume();

		presenter.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();

		presenter.onPause();
	}

	@Override
	public void updateMedia() {
		if (mediaView != null) {
			mediaView.update();
		}
	}

	@Override
	public void updateLive() {
		if (liveView != null) {
			liveView.update();
		}
	}

	@Override
	public void updateHot() {
		if (hotView != null) {
			hotView.update();
		}
	}

	@Override
	public void updateFeed() {
		if (feedView != null) {
			feedView.update();
		}
	}

	@Override
	public void updateUsers() {
		if (usersView != null) {
			usersView.update();
		}
	}

	@Override
	public void openMediaUrl(String url) {
		if (getActivity() != null) {
			try {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(browserIntent);
			} catch (ActivityNotFoundException e) {
				Snackbar.make(scrollview, getString(R.string.error_cannot_open_link), Snackbar.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void showSocialPostActions(Post post, SocialPostType type, boolean isOwnPost, SocialPostActionsBottomSheetFragment.Listener listener) {
		if (getActivity() != null) {
			SocialPostActionsBottomSheetFragment bottomSheetDialog = new SocialPostActionsBottomSheetFragment();
			bottomSheetDialog.show(getActivity().getSupportFragmentManager(), bottomSheetDialog.getTag());
			bottomSheetDialog.setData(post, type, isOwnPost);
			bottomSheetDialog.setListener(listener);
		}
	}

	@Override
	public void showEditPost(Post post) {
		if (getActivity() != null) {
			CreatePostActivity.startWith(getActivity(), null, post);
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			scrollview.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, scrollview);
	}
}