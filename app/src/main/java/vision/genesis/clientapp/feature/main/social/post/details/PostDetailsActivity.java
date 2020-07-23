package vision.genesis.clientapp.feature.main.social.post.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.Post;
import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.SocialCommentView;
import vision.genesis.clientapp.ui.SocialPostView;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class PostDetailsActivity extends BaseSwipeBackActivity implements PostDetailsView
{
	private static final String EXTRA_POST_ID = "extra_post_id";

	private static final String EXTRA_POST = "extra_post";

	private static final String EXTRA_SHOW_COMMENTS = "extra_show_comments";

	public static void startWith(Activity activity, UUID postId, Post post, boolean showComments) {
		Intent intent = new Intent(activity.getApplicationContext(), PostDetailsActivity.class);
		intent.putExtra(EXTRA_POST_ID, postId);
		intent.putExtra(EXTRA_POST, post);
		intent.putExtra(EXTRA_SHOW_COMMENTS, showComments);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.scrollview)
	public NestedScrollView scrollview;

	@BindView(R.id.post_view)
	public SocialPostView postView;

	@BindView(R.id.comments_section)
	public ViewGroup commentsSection;

	@BindView(R.id.comments)
	public TextView comments;

	@BindView(R.id.group_comments)
	public LinearLayout commentsGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;


	@InjectPresenter
	PostDetailsPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_post_details);

		ButterKnife.bind(this);

		initRefreshLayout();

		if (getIntent().getExtras() != null) {
			UUID postId = (UUID) getIntent().getExtras().getSerializable(EXTRA_POST_ID);
			Post post = getIntent().getExtras().getParcelable(EXTRA_POST);
			boolean showComments = getIntent().getExtras().getBoolean(EXTRA_SHOW_COMMENTS, false);
			if (postId != null) {
				if (post != null) {
					updateView(post);
					if (showComments) {
						scrollview.post(() -> scrollview.scrollTo(0, commentsSection.getTop()));
					}
				}
				presenter.setData(postId);
				postView.setDetailsMode(true);
				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorAccent),
				ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorMedium));
		refreshLayout.setOnRefreshListener(() -> presenter.onSwipeRefresh());
	}

	@Override
	public void updateView(Post post) {
		postView.setPost(post);

		if (post.getComments() != null && !post.getComments().isEmpty()) {
			this.commentsSection.setVisibility(View.VISIBLE);
			this.comments.setText(String.format(Locale.getDefault(), "%d %s",
					post.getComments().size(),
					GenesisVisionApplication.INSTANCE.getResources().getQuantityString(R.plurals.comments, post.getComments().size())));

			this.commentsGroup.removeAllViews();
			for (Post comment : post.getComments()) {
				SocialCommentView view = new SocialCommentView(this);
				view.setComment(comment);
				commentsGroup.addView(view);
			}
		}
		else {
			this.commentsSection.setVisibility(View.GONE);
		}
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, postView);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}