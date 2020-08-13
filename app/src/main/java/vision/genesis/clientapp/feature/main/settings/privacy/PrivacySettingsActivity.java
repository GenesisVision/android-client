package vision.genesis.clientapp.feature.main.settings.privacy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/07/2020.
 */

public class PrivacySettingsActivity extends BaseSwipeBackActivity implements PrivacySettingsView
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), PrivacySettingsActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.group_main)
	public ViewGroup mainGroup;

	@BindView(R.id.who_can_post_to_my_wall)
	public TextView whoCanPostToMyWall;

	@BindView(R.id.who_can_view_comments_on_my_post)
	public TextView whoCanViewCommentsOnMyPost;

	@BindView(R.id.who_can_comment_on_my_posts)
	public TextView whoCanCommentOnMyPost;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.progress_bar_button)
	public ProgressBar progressBarButton;

	@BindView(R.id.button_update)
	public PrimaryButton updateButton;

	@InjectPresenter
	PrivacySettingsPresenter presenter;

	private ArrayList<String> whoCanPostToMyWallOptions;

	private ArrayList<String> whoCanViewCommentsOnMyPostOptions;

	private ArrayList<String> whoCanCommentOnMyPostOptions;

	private Integer selectedWhoCanPostToMyWallPosition = -1;

	private Integer selectedWhoCanViewCommentsOnMyPostPosition = -1;

	private Integer selectedWhoCanCommentOnMyPostPosition = -1;

	private boolean whoCanCommentOnMyPostEnabled;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_update)
	public void onUpdateClicked() {
		presenter.onUpdateClicked();
	}

	@OnClick(R.id.group_who_can_post_to_my_wall)
	public void onWhoCanPostToMyWallClicked() {
		if (whoCanPostToMyWallOptions != null && whoCanPostToMyWallOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.who_can_post_to_my_wall), whoCanPostToMyWallOptions, selectedWhoCanPostToMyWallPosition);
			fragment.setListener((position, text) -> presenter.onWhoCanPostToMyWallSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_who_can_view_comments_on_my_post)
	public void onWhoCanViewCommentsOnMyPostClicked() {
		if (whoCanViewCommentsOnMyPostOptions != null && whoCanViewCommentsOnMyPostOptions.size() > 1) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.who_can_view_comments_on_my_post), whoCanViewCommentsOnMyPostOptions, selectedWhoCanViewCommentsOnMyPostPosition);
			fragment.setListener((position, text) -> presenter.onWhoCanViewCommentsOnMyPostSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@OnClick(R.id.group_who_can_comment_on_my_posts)
	public void onWhoCanCommentOnMyPostClicked() {
		if (whoCanCommentOnMyPostOptions != null && whoCanCommentOnMyPostOptions.size() > 1 && whoCanCommentOnMyPostEnabled) {
			SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
					getString(R.string.who_can_comment_on_my_posts), whoCanCommentOnMyPostOptions, selectedWhoCanCommentOnMyPostPosition);
			fragment.setListener((position, text) -> presenter.onWhoCanCommentOnMyPostSelected(position, text));
			fragment.show(getSupportFragmentManager(), fragment.getTag());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_privacy_settings);

		ButterKnife.bind(this);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setWhoCanPostToMyWallOptions(ArrayList<String> whoCanPostToMyWallOptions) {
		this.whoCanPostToMyWallOptions = whoCanPostToMyWallOptions;
	}

	@Override
	public void setWhoCanViewCommentsOnMyPostOptions(ArrayList<String> whoCanViewCommentsOnMyPostOptions) {
		this.whoCanViewCommentsOnMyPostOptions = whoCanViewCommentsOnMyPostOptions;
	}

	@Override
	public void setWhoCanCommentOnMyPostOptions(ArrayList<String> whoCanCommentOnMyPostOptions) {
		this.whoCanCommentOnMyPostOptions = whoCanCommentOnMyPostOptions;
	}

	@Override
	public void setWhoCanPostToMyWall(String text, Integer position) {
		this.whoCanPostToMyWall.setText(text);
		this.selectedWhoCanPostToMyWallPosition = position;
	}

	@Override
	public void setWhoCanViewCommentsOnMyPost(String text, Integer position) {
		this.whoCanViewCommentsOnMyPost.setText(text);
		this.selectedWhoCanViewCommentsOnMyPostPosition = position;
	}

	@Override
	public void setWhoCanCommentOnMyPost(String text, Integer position) {
		this.whoCanCommentOnMyPost.setText(text);
		this.selectedWhoCanCommentOnMyPostPosition = position;
	}

	@Override
	public void setWhoCanCommentOnMyPostEnabled(boolean enabled) {
		this.whoCanCommentOnMyPost.setAlpha(enabled ? 1.0f : 0.4f);
		whoCanCommentOnMyPostEnabled = enabled;
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			mainGroup.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showButtonProgress(boolean show) {
		progressBarButton.setVisibility(show ? View.VISIBLE : View.GONE);
		updateButton.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, mainGroup);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}