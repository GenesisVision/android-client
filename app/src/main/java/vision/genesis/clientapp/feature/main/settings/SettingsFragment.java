package vision.genesis.clientapp.feature.main.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;

import com.arellomobile.mvp.presenter.InjectPresenter;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProfileFullViewModel;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;
import vision.genesis.clientapp.feature.main.settings.privacy.PrivacySettingsActivity;
import vision.genesis.clientapp.feature.main.settings.public_info.ProfilePublicInfoActivity;
import vision.genesis.clientapp.feature.main.settings.referral_program.ReferralProgramActivity;
import vision.genesis.clientapp.feature.main.settings.security.SecurityActivity;
import vision.genesis.clientapp.feature.main.settings.social_links.SocialLinksActivity;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2018.
 */

public class SettingsFragment extends BaseFragment implements SettingsView
{
	@BindView(R.id.scrollview)
	public ScrollView scrollview;

	@BindView(R.id.avatar)
	public AvatarView avatar;

	@BindView(R.id.group_avatar_empty)
	public ViewGroup groupAvatarEmpty;

	@BindView(R.id.profile_name)
	public TextView profileName;

	@BindView(R.id.profile_email)
	public TextView profileEmail;

	@BindView(R.id.verification_status)
	public TextView verificationStatus;

	@BindView(R.id.verification_status_background)
	public View verificationStatusBackground;

	@BindView(R.id.verification_arrow)
	public View verificationArrow;

	@BindView(R.id.public_investor)
	public ViewGroup publicInvestor;

	@BindView(R.id.switch_public_investor)
	public SwitchCompat switchPublicInvestor;

	@BindView(R.id.platform_currency_value)
	public TextView platformCurrencyValue;

	@BindView(R.id.version)
	public TextView version;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	SettingsPresenter presenter;

	private Unbinder unbinder;

	private CurrencyEnum baseCurrency;

	private ProfileFullViewModel profile;

	@OnClick(R.id.verification)
	public void onVerificationClicked() {
		presenter.onVerificationClicked();
	}

	@OnClick(R.id.my_profile)
	public void onMyProfileClicked() {
		if (getActivity() != null && profile != null) {
			UserDetailsModel model = new UserDetailsModel(
					profile.getId(),
					profile.getLogoUrl(),
					profile.getUserName(),
					DateTime.now());
			EventBus.getDefault().post(new ShowUserDetailsEvent(model));
		}
	}

	@OnClick(R.id.public_info)
	public void onPublicInfoClicked() {
		if (getActivity() != null) {
			ProfilePublicInfoActivity.startFrom(getActivity(), false);
		}
	}

	@OnClick(R.id.social_links)
	public void onSocialLinksClicked() {
		if (getActivity() != null) {
			SocialLinksActivity.startFrom(getActivity());
		}
	}

	@OnClick(R.id.tooltip_public_investor)
	public void onTooltipPublicInvestorClicked() {
		if (getActivity() != null) {
			MessageBottomSheetDialog dialog = new MessageBottomSheetDialog();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(R.drawable.icon_info, getString(R.string.public_investors_profile), getString(R.string.tooltip_public_investors_profile), false, null);
		}
	}

	@OnClick(R.id.privacy)
	public void onPrivacyClicked() {
		if (getActivity() != null) {
			PrivacySettingsActivity.startFrom(getActivity());
		}
	}

	@OnClick(R.id.security)
	public void onSecurityClicked() {
		if (getActivity() != null) {
			SecurityActivity.startFrom(getActivity());
		}
	}

	@OnClick(R.id.platform_currency)
	public void onPlatformCurrencyClicked() {
		if (getActivity() != null) {
			SelectCurrencyFragment fragment = SelectCurrencyFragment.with(baseCurrency.getValue());
			fragment.setListener(presenter);
			fragment.show(getActivity().getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.referral_program)
	public void onReferralProgramClicked() {
		if (getActivity() != null) {
			ReferralProgramActivity.startWith(getActivity());
		}
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

		setFonts();
		setListener();

		version.setText(String.format(Locale.getDefault(), "Version %s (%d)", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
	}

	private void setListener() {
		switchPublicInvestor.setOnCheckedChangeListener((view, checked) ->
				presenter.onPublicInvestorProfileCheckedChanged(checked));
	}

	private void setFonts() {
		profileName.setTypeface(TypefaceUtil.semibold());
		platformCurrencyValue.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onResume() {
		super.onResume();

		presenter.onResume();
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
			if (optionId == 0) {
				openFeedbackSite();
			}
			else {
				sendFeedbackEmail();
			}
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
			presenter.onLogoutClicked();
			dialog.dismiss();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	@Override
	public void onShow() {
		presenter.onResume();
	}

	@Override
	public void updateProfile(ProfileFullViewModel profile) {
		this.profile = profile;
		avatar.setImage(profile.getLogoUrl(), 200, 200);
		groupAvatarEmpty.setVisibility(profile.getLogoUrl() != null && !profile.getLogoUrl().isEmpty() ? View.GONE : View.VISIBLE);

		if (profile.getUserName() != null && !profile.getUserName().isEmpty()) {
			profileName.setText(profile.getUserName());
			profileName.setVisibility(View.VISIBLE);
		}
		else if (profile.getFirstName() != null && !profile.getFirstName().isEmpty()
				&& profile.getLastName() != null && !profile.getLastName().isEmpty()) {
			profileName.setText(String.format(Locale.getDefault(), "%s %s", profile.getFirstName(), profile.getLastName()));
			profileName.setVisibility(View.VISIBLE);
		}
		else {
			profileName.setText("");
			profileName.setVisibility(View.GONE);
		}

		profileEmail.setText(profile.getEmail());

		switch (profile.getVerificationStatus()) {
			case NOTVERIFIED:
				verificationStatus.setText(getString(R.string.not_verified));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				verificationArrow.setVisibility(View.VISIBLE);
				break;
			case VERIFIED:
				verificationStatus.setText(getString(R.string.verified));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
				verificationArrow.setVisibility(View.GONE);
				break;
			case UNDERREVIEW:
				verificationStatus.setText(getString(R.string.under_review));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorPending));
				verificationArrow.setVisibility(View.VISIBLE);
				break;
			case REJECTED:
				verificationStatus.setText(getString(R.string.rejected));
				verificationStatus.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				verificationStatusBackground.setBackgroundColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed));
				verificationArrow.setVisibility(View.GONE);
				break;
		}

		switchPublicInvestor.setChecked(profile.isIsPublicInvestor());
	}

	@Override
	public void setBaseCurrency(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		platformCurrencyValue.setText(baseCurrency.getValue());
	}

	@Override
	public void showDialogMessage(String message) {
		showMessageDialog(message);
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