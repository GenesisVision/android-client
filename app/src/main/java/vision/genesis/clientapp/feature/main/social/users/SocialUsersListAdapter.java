package vision.genesis.clientapp.feature.main.social.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.Currency;
import io.swagger.client.model.UserDetailsList;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;
import vision.genesis.clientapp.net.ApiErrorResolver;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2019.
 */

public class SocialUsersListAdapter extends RecyclerView.Adapter<SocialUsersListAdapter.UserViewHolder>
{
	private List<UserDetailsList> users = new ArrayList<>();

	@NonNull
	@Override
	public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_social_user, parent, false);
		return new UserViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
		holder.setUser(users.get(position));
	}

	@Override
	public long getItemId(int position) {
		return users.get(position).hashCode();
	}

	@Override
	public int getItemCount() {
		return users.size();
	}

	public void setUsers(List<UserDetailsList> users) {
		this.users.clear();
		this.users.addAll(users);
		notifyDataSetChanged();
	}

	public void addUsers(List<UserDetailsList> users) {
		this.users.addAll(users);
		notifyDataSetChanged();
	}

	public static class UserViewHolder extends RecyclerView.ViewHolder
	{
		@Inject
		public SocialManager socialManager;

		@BindView(R.id.avatar)
		public AvatarView avatar;

		@BindView(R.id.name)
		public TextView name;

		@BindView(R.id.about)
		public TextView about;

		@BindView(R.id.age)
		public TextView age;

		@BindView(R.id.aum)
		public TextView aum;

		@BindView(R.id.followers)
		public TextView followers;

		@BindView(R.id.investors)
		public TextView investors;

		@BindView(R.id.group_buttons)
		public ViewGroup buttonsGroup;

		@BindView(R.id.button_follow)
		public PrimaryButton followButton;

		@BindView(R.id.button_unfollow)
		public PrimaryButton unfollowButton;

		@BindView(R.id.progress_bar)
		public ProgressBar progressBar;

		private UserDetailsList user;

		private Subscription followSubscription;

		UserViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);

			GenesisVisionApplication.getComponent().inject(this);

			unfollowButton.setEmpty();

			itemView.setOnClickListener(v -> {
				if (user != null) {
					UserDetailsModel model = new UserDetailsModel(
							user.getUserId(),
							user.getLogoUrl(),
							user.getUsername(),
							user.getRegDate());
					EventBus.getDefault().post(new ShowUserDetailsEvent(model));
				}
			});
		}

		@OnClick(R.id.button_follow)
		public void onFollowClicked() {
			changeFollowStatus();
		}

		@OnClick(R.id.button_unfollow)
		public void onUnfollowClicked() {
			changeFollowStatus();
		}

		void setUser(UserDetailsList user) {
			this.user = user;
			updateView();
		}

		private void updateView() {
			avatar.setImage(user.getLogoUrl(), 100, 100);
			name.setText(user.getUsername());
			about.setText(user.getAbout());

			age.setText(DateTimeUtil.formatAgeDateTime(user.getRegDate()));
			aum.setText(StringFormatUtil.getValueString(user.getAssetsUnderManagement(), Currency.USD.getValue()));
			investors.setText(String.valueOf(user.getInvestorsCount()));
			followers.setText(String.valueOf(user.getFollowersCount()));

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
						? socialManager.follow(user.getUserId())
						: socialManager.unfollow(user.getUserId()))
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
}
