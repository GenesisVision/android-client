package vision.genesis.clientapp.feature.main.program.info.follow;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.FollowDetailsFull;
import io.swagger.client.model.ProfilePublic;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.auth.login.LoginActivity;
import vision.genesis.clientapp.feature.main.copytrading.create_account.CreateCopytradingAccountActivity;
import vision.genesis.clientapp.feature.main.copytrading.subscription_settings.SubscriptionSettingsActivity;
import vision.genesis.clientapp.feature.main.copytrading.unfollow_trades.UnfollowTradesActivity;
import vision.genesis.clientapp.feature.main.manager.ManagerDetailsActivity;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.model.ManagerDetailsModel;
import vision.genesis.clientapp.model.SubscriptionSettingsModel;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SocialLinksView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/11/2018.
 */

public class FollowInfoFragment extends BaseFragment implements FollowInfoView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_DETAILS = "extra_details";

	public static FollowInfoFragment with(FollowDetailsFull details) {
		FollowInfoFragment followInfoFragment = new FollowInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_DETAILS, details);
		followInfoFragment.setArguments(arguments);
		return followInfoFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_subscription_buttons)
	public ViewGroup subscriptionButtonsGroup;

	@BindView(R.id.manager_avatar)
	public AvatarView managerAvatar;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.manager_date)
	public TextView managerDate;

	@BindView(R.id.social_links)
	public SocialLinksView socialLinks;

	@BindView(R.id.strategy)
	public TextView strategy;

	@BindView(R.id.strategy_shadow)
	public View strategyShadow;

	@BindView(R.id.group_subscription)
	public ViewGroup subscriptionGroup;

	@BindView(R.id.label_subscription)
	public TextView labelSubscription;

	@BindView(R.id.subscription_success_fee)
	public TextView subscriptionSuccessFee;

	@BindView(R.id.label_subscription_success_fee)
	public TextView subscriptionSuccessFeeLabel;

	@BindView(R.id.subscription_volume_fee)
	public TextView subscriptionVolumeFee;

	@BindView(R.id.label_subscription_volume_fee)
	public TextView subscriptionVolumeFeeLabel;

	@BindView(R.id.button_follow_trades)
	public PrimaryButton followTradesButton;

	@BindView(R.id.button_edit_subscription)
	public PrimaryButton editSubscriptionButton;

	@BindView(R.id.button_unfollow_trades)
	public PrimaryButton unfollowTradesButton;

	@InjectPresenter
	public FollowInfoPresenter presenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private UUID programId;

	private FollowDetailsFull followDetails;

	private Unbinder unbinder;

	@OnClick(R.id.group_manager)
	public void onManagerClicked() {
		if (getActivity() != null) {
			ProfilePublic manager = followDetails.getOwner();
			ManagerDetailsModel model = new ManagerDetailsModel(
					manager.getId(),
					manager.getAvatar(),
					manager.getUsername(),
					manager.getRegistrationDate());
			ManagerDetailsActivity.startWith(getActivity(), model);
		}
	}

	@OnClick(R.id.strategy)
	public void onStrategyClicked() {
		if (strategy.getHeight() == strategyMaxHeight) {
			expandStrategy();
		}
		else if (strategy.getHeight() > strategyMaxHeight) {
			collapseStrategy();
		}
	}

	private void expandStrategy() {
		ValueAnimator animator = ValueAnimator.ofInt(strategy.getMaxHeight(), 10000);
		animator.addUpdateListener(animation -> strategy.setMaxHeight((int) animator.getAnimatedValue()));
		animator.setDuration(400);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		strategyShadow.setVisibility(View.INVISIBLE);
	}

	private void collapseStrategy() {
		ValueAnimator animator = ValueAnimator.ofInt(strategy.getHeight(), strategyMaxHeight);
		animator.addUpdateListener(animation -> {
			strategy.setHeight((int) animator.getAnimatedValue());
			if (!animation.isRunning()) {
				strategy.setMaxHeight(strategyMaxHeight);
			}
		});
		animator.setDuration(200);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		strategyShadow.setVisibility(View.VISIBLE);
	}

	@OnClick(R.id.button_follow_trades)
	public void onFollowTradesClicked() {
		presenter.onShowSubscriptionSettingsClicked(false);
	}

	@OnClick(R.id.button_edit_subscription)
	public void onEditSubscriptionClicked() {
		presenter.onShowSubscriptionSettingsClicked(true);
	}

	@OnClick(R.id.button_unfollow_trades)
	public void onUnfollowTradesClicked() {
		presenter.onUnfollowTradesClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_follow_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		if (getArguments() != null) {
			followDetails = getArguments().getParcelable(EXTRA_DETAILS);
			if (followDetails != null) {
				presenter.setFollowDetails(followDetails);
				setFollowDetails(followDetails);

				setFonts();

				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
		labelSubscription.setTypeface(TypefaceUtil.semibold());
		subscriptionSuccessFee.setTypeface(TypefaceUtil.semibold());
		subscriptionVolumeFee.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setFollowDetails(FollowDetailsFull followDetails) {
		this.followDetails = followDetails;

		scrollView.setVisibility(View.VISIBLE);

		updateFollowInfo(followDetails);
		updateSubscription(followDetails);
	}

	private void updateFollowInfo(FollowDetailsFull programDetails) {
		managerAvatar.setImage(programDetails.getOwner().getAvatar(), 100, 100);
		managerName.setText(programDetails.getOwner().getUsername());
		managerDate.setText(DateTimeUtil.formatShortDate(programDetails.getOwner().getRegistrationDate()));

		socialLinks.setData(programDetails.getOwner().getSocialLinks());

		strategy.setText(programDetails.getDescription());
		new Handler().postDelayed(() -> {
			if (strategyShadow != null && strategy != null) {
				strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
			}
		}, 300);
	}

	private void updateSubscription(FollowDetailsFull followDetails) {
		if (followDetails != null && followDetails.getSignalSettings() != null && followDetails.getSignalSettings().isIsActive()) {
			subscriptionGroup.setVisibility(View.VISIBLE);

			subscriptionSuccessFee.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(followDetails.getSignalSettings().getSignalSuccessFee(), 0, 2)));
			subscriptionVolumeFee.setText(String.format(Locale.getDefault(), "%s%%",
					StringFormatUtil.formatAmount(followDetails.getSignalSettings().getSignalVolumeFee(), 0, 2)));

			boolean hasSubscription = followDetails.getPersonalDetails() != null
					&& followDetails.getPersonalDetails().getSignalSubscriptions() != null
					&& !followDetails.getPersonalDetails().getSignalSubscriptions().isEmpty()
					&& followDetails.getPersonalDetails().getSignalSubscriptions().get(0) != null
					&& followDetails.getPersonalDetails().getSignalSubscriptions().get(0).isHasActiveSubscription();

			followTradesButton.setVisibility(!hasSubscription ? View.VISIBLE : View.GONE);
			editSubscriptionButton.setVisibility(hasSubscription ? View.VISIBLE : View.GONE);
			unfollowTradesButton.setVisibility(hasSubscription ? View.VISIBLE : View.GONE);
		}
		else {
			subscriptionGroup.setVisibility(View.GONE);
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSubscriptionButtons(boolean show) {
		subscriptionButtonsGroup.setVisibility(show ? View.VISIBLE : View.GONE);
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

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, scrollView);
	}

	@Override
	public void showLoginActivity() {
		if (getActivity() != null) {
			LoginActivity.startFrom(getActivity());
		}
	}

	@Override
	public void showCreateCopytradingAccountActivity(SubscriptionSettingsModel model) {
		if (getActivity() != null) {
			CreateCopytradingAccountActivity.startWith(getActivity(), model);
		}
	}

	@Override
	public void showSubscriptionSettings(SubscriptionSettingsModel model, boolean isEdit) {
		if (getActivity() != null) {
			SubscriptionSettingsActivity.startWith(getActivity(), model, isEdit);
		}
	}

	@Override
	public void showUnfollowTradesActivity(UUID programId, String programName) {
		if (getActivity() != null) {
			UnfollowTradesActivity.startWith(getActivity(), programId, programName);
		}
	}
}