package vision.genesis.clientapp.feature.main.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProfileFullViewModel;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.settings.security.SecurityActivity;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

public class SettingsFragment extends BaseFragment implements SettingsView
{
	@BindView(R.id.avatar)
	public AvatarView avatar;

	@BindView(R.id.profile_name)
	public TextView profileName;

	@BindView(R.id.profile_email)
	public TextView profileEmail;

	@BindView(R.id.verification_status)
	public TextView verificationStatus;

	@BindView(R.id.verification_status_background)
	public View verificationStatusBackground;

	@BindView(R.id.version)
	public TextView version;

	@InjectPresenter
	SettingsPresenter settingsPresenter;

	private Unbinder unbinder;

	@OnClick(R.id.security)
	public void onSecurityClicked() {
		if (getActivity() != null)
			SecurityActivity.startFrom(getActivity());
	}

	@OnClick(R.id.terms_conditions)
	public void onTermsConditionsClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.terms_and_conditions_address)));
		startActivity(browserIntent);
	}

	@OnClick(R.id.privacy_policy)
	public void onPrivacyPolicyClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_policy_address)));
		startActivity(browserIntent);
	}

	@OnClick(R.id.contact_us)
	public void onContactUsClicked() {
		showFeedbackDialog();
	}

	@OnClick(R.id.logout)
	public void onLogoutClicked() {
		showLogoutDialog();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_settings, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		version.setText(String.format(Locale.getDefault(), "Version %s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));

		setFonts();
	}

	private void setFonts() {
		profileName.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onResume() {
		super.onResume();

		settingsPresenter.onResume();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void showFeedbackDialog() {
		CharSequence options[] = new CharSequence[]{getString(R.string.visit_feedback_website), getString(R.string.send_email)};

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.feedback_dialog_title));
		builder.setItems(options, (dialog, optionId) -> {
			if (optionId == 0)
				openFeedbackSite();
			else
				sendFeedbackEmail();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	private void openFeedbackSite() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.feedback_web_address)));
		startActivity(browserIntent);
	}

	private void sendFeedbackEmail() {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("message/rfc822");  //set the email recipient
		String recipient = getString(R.string.feedback_email_address);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Android feedback");
		try {
			emailIntent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), "\n\nversion %s (%d)\n%s %s\n%s %s",
					BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE,
					Build.MANUFACTURER, Build.MODEL,
					Build.VERSION.RELEASE, Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		startActivity(Intent.createChooser(emailIntent, "Send email using..."));
	}

	private void showLogoutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.are_you_sure_logout));
		builder.setPositiveButton(getString(R.string.log_out), (dialog, which) -> {
			settingsPresenter.onLogoutClicked();
			dialog.dismiss();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	@Override
	public void onShow() {
		settingsPresenter.onResume();
	}

	@Override
	public void updateProfile(ProfileFullViewModel profile) {
		avatar.setImage(profile.getAvatar(), 200, 200);

		if (profile.getUserName() != null && !profile.getUserName().isEmpty()) {
			profileName.setText(profile.getUserName());
		}
		else if (profile.getFirstName() != null && !profile.getFirstName().isEmpty()
				&& profile.getLastName() != null && !profile.getLastName().isEmpty()) {
			profileName.setText(String.format(Locale.getDefault(), "%s %s", profile.getFirstName(), profile.getLastName()));
		}
		profileEmail.setText(profile.getEmail());

		switch (profile.getVerificationStatus()) {
			case NOTVERIFIED:
				verificationStatus.setText(getString(R.string.not_verified));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				break;
			case VERIFIED:
				verificationStatus.setText(getString(R.string.verified));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
				break;
			case UNDERREVIEW:
				verificationStatus.setText(getString(R.string.under_review));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
				break;
			case REJECTED:
				verificationStatus.setText(getString(R.string.rejected));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				break;
		}
	}

	@Override
	public void showDialogMessage(String message) {
		showMessageDialog(message);
	}

//	@Override
//	public void changeThemeWithAnim(String newTheme) {
//		ValueAnimator backgroundColorAnimation = ThemeUtil.getColorAnimator(getActivity(), ((ColorDrawable) root.getBackground()).getColor(), R.attr.colorBackground);
//		backgroundColorAnimation.addUpdateListener(animator -> root.setBackgroundColor((int) animator.getAnimatedValue()));
//		backgroundColorAnimation.start();
//
//		ValueAnimator cardColorAnimation = ThemeUtil.getColorAnimator(getActivity(), profileCard.getCardBackgroundColor().getDefaultColor(), R.attr.colorCard);
//		cardColorAnimation.addUpdateListener(animator -> {
//			profileCard.setCardBackgroundColor((int) animator.getAnimatedValue());
//			changePasswordCard.setCardBackgroundColor((int) animator.getAnimatedValue());
//			securityCard.setCardBackgroundColor((int) animator.getAnimatedValue());
//			darkThemeCard.setCardBackgroundColor((int) animator.getAnimatedValue());
//			feedbackCard.setCardBackgroundColor((int) animator.getAnimatedValue());
//			logoutCard.setCardBackgroundColor((int) animator.getAnimatedValue());
//		});
//		cardColorAnimation.start();
//
//		ValueAnimator primaryTextColorAnimation = ThemeUtil.getColorAnimator(getActivity(), profileName.getCurrentTextColor(), R.attr.colorTextPrimary);
//		primaryTextColorAnimation.addUpdateListener(animator -> {
//			profileName.setTextColor((int) animator.getAnimatedValue());
//			profileArrow.setColorFilter((int) animator.getAnimatedValue());
//
//			changePasswordIcon.setColorFilter((int) animator.getAnimatedValue());
//			changePasswordText.setTextColor((int) animator.getAnimatedValue());
//			changePasswordArrow.setColorFilter((int) animator.getAnimatedValue());
//
//			twoFactor.setColor((int) animator.getAnimatedValue());
//			pinCode.setColor((int) animator.getAnimatedValue());
//			fingerprint.setColor((int) animator.getAnimatedValue());
//			darkTheme.setColor((int) animator.getAnimatedValue());
//
//			feedbackIcon.setColorFilter((int) animator.getAnimatedValue());
//			feedbackText.setTextColor((int) animator.getAnimatedValue());
//		});
//		primaryTextColorAnimation.start();
//
//		ValueAnimator delimiterColorAnimation = ThemeUtil.getColorAnimator(getActivity(), ((ColorDrawable) fingerprintDelimiter.getBackground()).getColor(), R.attr.colorDelimiter);
//		delimiterColorAnimation.addUpdateListener(animator -> {
//			pinCodeDelimiter.setBackgroundColor((int) animator.getAnimatedValue());
//			fingerprintDelimiter.setBackgroundColor((int) animator.getAnimatedValue());
//		});
//		delimiterColorAnimation.start();
//
//		ValueAnimator secondaryTextColorAnimation = ThemeUtil.getColorAnimator(getActivity(), version.getCurrentTextColor(), R.attr.colorTextSecondary);
//		secondaryTextColorAnimation.addUpdateListener(animator -> version.setTextColor((int) animator.getAnimatedValue()));
//		secondaryTextColorAnimation.start();
//	}

}