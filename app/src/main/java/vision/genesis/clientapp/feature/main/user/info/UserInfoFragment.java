package vision.genesis.clientapp.feature.main.user.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PublicProfile;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.feature.main.user.followers.UserFollowersActivity;
import vision.genesis.clientapp.ui.SocialLinksView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class UserInfoFragment extends BaseFragment implements UserInfoView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_USER_ID = "extra_user_id";

	public static UserInfoFragment with(UUID userId) {
		UserInfoFragment userInfoFragment = new UserInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_USER_ID, userId);
		userInfoFragment.setArguments(arguments);
		return userInfoFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.social_links)
	public SocialLinksView socialLinks;

	@BindView(R.id.followers)
	public TextView followers;

	@BindView(R.id.following)
	public TextView following;

	@BindView(R.id.about)
	public TextView about;

	@BindView(R.id.group_about)
	public ViewGroup aboutGroup;

	@InjectPresenter
	public UserInfoPresenter presenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private UUID userId;

	private PublicProfile userDetails;

	private Unbinder unbinder;

	@OnClick(R.id.group_followers)
	public void onFollowersClicked() {
		if (getActivity() != null && userDetails != null) {
			UserFollowersActivity.startWith(getActivity(), userId, userDetails.getUsername(), UserFollowersActivity.TYPE_FOLLOWERS);
		}
	}

	@OnClick(R.id.group_following)
	public void onFollowingClicked() {
		if (getActivity() != null && userDetails != null) {
			UserFollowersActivity.startWith(getActivity(), userId, userDetails.getUsername(), UserFollowersActivity.TYPE_FOLLOWING);
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		userId = (UUID) getArguments().getSerializable(EXTRA_USER_ID);
		presenter.setUserId(userId);
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
	public void setUserDetails(PublicProfile userDetails) {
		this.userDetails = userDetails;

		scrollView.setVisibility(View.VISIBLE);

		socialLinks.setData(userDetails.getSocialLinks());
		followers.setText(StringFormatUtil.formatAmount(userDetails.getFollowers(), 0, 0));
		following.setText(StringFormatUtil.formatAmount(userDetails.getFollowing(), 0, 0));
		about.setText(userDetails.getAbout());
		aboutGroup.setVisibility(!userDetails.getAbout().isEmpty() ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void pagerShow() {
		if (presenter != null) {
			presenter.onShow();
		}
	}

	@Override
	public void pagerHide() {
	}
}