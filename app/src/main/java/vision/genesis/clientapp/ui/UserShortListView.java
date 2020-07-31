package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProfilePublicShort;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class UserShortListView extends RelativeLayout
{
	@Inject
	public SocialManager socialManager;

	@BindView(R.id.avatar)
	public AvatarView avatar;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.group_buttons)
	public ViewGroup buttonsGroup;

	@BindView(R.id.button_follow)
	public PrimaryButton followButton;

	@BindView(R.id.button_unfollow)
	public PrimaryButton unfollowButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	private Unbinder unbinder;

	private ProfilePublicShort user;

	private Subscription followSubscription;

	public UserShortListView(Context context) {
		super(context);
		initView();
	}

	public UserShortListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public UserShortListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_follow)
	public void onFollowClicked() {
		changeFollowStatus();
	}

	@OnClick(R.id.button_unfollow)
	public void onUnfollowClicked() {
		changeFollowStatus();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		if (followSubscription != null) {
			followSubscription.unsubscribe();
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_user_short_list, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		unfollowButton.setEmpty();

		setOnClickListener(v -> {
			if (user != null) {
				UserDetailsModel model = new UserDetailsModel(
						user.getId(),
						user.getLogoUrl(),
						user.getUsername(),
						DateTime.now());
				EventBus.getDefault().post(new ShowUserDetailsEvent(model));
			}
		});
	}

	public void setUser(ProfilePublicShort user) {
		this.user = user;
		updateView();
	}

	private void updateView() {
		avatar.setImage(user.getLogoUrl(), 50, 50);
		name.setText(user.getUsername());

		if (user.getPersonalDetails().isAllowFollow()) {
			buttonsGroup.setVisibility(View.VISIBLE);

			followButton.setVisibility(!user.getPersonalDetails().isIsFollow() ? View.VISIBLE : View.GONE);
			unfollowButton.setVisibility(user.getPersonalDetails().isIsFollow() ? View.VISIBLE : View.GONE);
		}
		else {
			buttonsGroup.setVisibility(View.GONE);
		}
	}

	private void changeFollowStatus() {
		if (socialManager != null && user != null) {
			if (followSubscription != null) {
				followSubscription.unsubscribe();
			}

			showProgress(true);
			followSubscription = (!user.getPersonalDetails().isIsFollow()
					? socialManager.follow(user.getId())
					: socialManager.unfollow(user.getId()))
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeOn(Schedulers.newThread())
					.subscribe(this::handleFollowResponse,
							this::handleFollowError);
		}
	}

	private void handleFollowResponse(Void response) {
		followSubscription.unsubscribe();
		showProgress(false);

		this.user.getPersonalDetails().setIsFollow(!user.getPersonalDetails().isIsFollow());
		updateView();
	}

	private void handleFollowError(Throwable throwable) {
		followSubscription.unsubscribe();
		showProgress(false);

		ApiErrorResolver.resolveErrors(throwable, this::showSnackbarMessage);
	}

	private void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		buttonsGroup.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	private void showSnackbarMessage(String message) {
		Snackbar.make(avatar, message, Snackbar.LENGTH_LONG).show();
	}
}
