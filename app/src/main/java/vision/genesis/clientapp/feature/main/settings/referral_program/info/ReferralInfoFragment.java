package vision.genesis.clientapp.feature.main.settings.referral_program.info;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.PartnershipDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.settings.referral_program.ReferralProgramPagerAdapter;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/12/2019.
 */

public class ReferralInfoFragment extends BaseFragment implements ReferralInfoView, ReferralProgramPagerAdapter.OnPageVisibilityChanged
{
	@BindView(R.id.label_ref)
	public TextView labelRef;

	@BindView(R.id.ref_progress_bar)
	public ProgressBar refProgressBar;

	@BindView(R.id.group_ref_info)
	public ViewGroup groupRefInfo;

	@BindView(R.id.ref_url)
	public TextView refUrlTextView;

	@BindView(R.id.button_share)
	public PrimaryButton shareButton;


	@BindView(R.id.label_details)
	public TextView labelDetails;

	@BindView(R.id.details_progress_bar)
	public ProgressBar detailsProgressBar;

	@BindView(R.id.group_details_info)
	public ViewGroup groupDetailsInfo;

	@BindView(R.id.level_1)
	public TextView level1;

	@BindView(R.id.level_2)
	public TextView level2;

	@BindView(R.id.total_rewards)
	public TextView totalRewards;

	@InjectPresenter
	public ReferralInfoPresenter presenter;

	private String refUrl;

	@OnClick(R.id.button_share)
	public void onShareClicked() {
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share));
		intent.putExtra(android.content.Intent.EXTRA_TEXT, refUrl);
		startActivity(Intent.createChooser(intent, getString(R.string.share)));
	}

	@OnClick(R.id.button_copy)
	public void onCopyClicked() {
		ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Activity.CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText("referral link", refUrl);
		if (clipboardManager != null) {
			clipboardManager.setPrimaryClip(clipData);
			Toast.makeText(getContext(), getContext().getString(R.string.address_copied), Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getContext(), getContext().getString(R.string.cannot_copy), Toast.LENGTH_SHORT).show();
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_referral_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		setFonts();

		shareButton.setEmpty();
	}

	private void setFonts() {
		labelRef.setTypeface(TypefaceUtil.semibold());
		labelDetails.setTypeface(TypefaceUtil.semibold());
		level1.setTypeface(TypefaceUtil.semibold());
		level2.setTypeface(TypefaceUtil.semibold());
		totalRewards.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
		this.refUrlTextView.setText(refUrl);
	}

	@Override
	public void setDetails(PartnershipDetails details, String baseCurrency) {
		this.level1.setText(String.valueOf(details.getTotalReferralsL1()));
		this.level2.setText(String.valueOf(details.getTotalReferralsL2()));
		this.totalRewards.setText(StringFormatUtil.getValueString(details.getTotalAmount(), baseCurrency));
	}

	@Override
	public void showRefProgress(boolean show) {
		if (refProgressBar != null) {
			refProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
			if (!show) {
				groupRefInfo.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void showDetailsProgress(boolean show) {
		if (detailsProgressBar != null) {
			detailsProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
			if (!show) {
				groupDetailsInfo.setVisibility(View.VISIBLE);
			}
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, labelRef);
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

	public void onSwipeRefresh() {
		if (presenter != null) {
			presenter.onSwipeRefresh();
		}
	}
}