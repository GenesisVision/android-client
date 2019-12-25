package vision.genesis.clientapp.feature.main.settings.social_links;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.SocialLinkViewModel;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SocialLinkView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/12/2019.
 */

public class SocialLinksActivity extends BaseSwipeBackActivity implements SocialLinksView
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), SocialLinksActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.group_content)
	public ViewGroup groupContent;

	@BindView(R.id.group_social_links)
	public LinearLayout groupSocialLinks;

	@BindView(R.id.button_save_changes)
	public PrimaryButton saveChangesButton;

	@BindView(R.id.button_progress_bar)
	public ProgressBar buttonProgressBar;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	public SocialLinksPresenter presenter;

	@OnClick(R.id.button_save_changes)
	public void onSaveChangesButtonClicked() {
		presenter.onSaveChangesButtonClicked();
	}

	@OnClick(R.id.button_back)
	public void onBackButtonClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_social_links);

		ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void showSocialLinks(List<SocialLinkViewModel> socialLinks) {
		groupSocialLinks.removeAllViews();
		for (SocialLinkViewModel model : socialLinks) {
			SocialLinkView view = new SocialLinkView(this);
			view.setData(model);
			groupSocialLinks.addView(view);
		}
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		groupContent.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showButtonProgressBar(boolean show) {
		buttonProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		saveChangesButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}