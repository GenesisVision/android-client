package vision.genesis.clientapp.feature.main.user.followers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProfilePublicShort;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.UserShortListView;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class UserFollowersActivity extends BaseSwipeBackActivity implements UserFollowersView
{
	public static final String TYPE_FOLLOWERS = "type_followers";

	public static final String TYPE_FOLLOWING = "type_following";

	private static final String EXTRA_USER_ID = "extra_user_id";

	private static final String EXTRA_USER_NAME = "extra_user_name";

	private static final String EXTRA_TYPE = "extra_type";

	public static void startWith(Activity activity, UUID userId, String userName, String type) {
		Intent intent = new Intent(activity.getApplicationContext(), UserFollowersActivity.class);
		intent.putExtra(EXTRA_USER_ID, userId);
		intent.putExtra(EXTRA_USER_NAME, userName);
		intent.putExtra(EXTRA_TYPE, type);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.subtitle)
	public TextView subtitle;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.group_users)
	public LinearLayout usersGroup;

	@InjectPresenter
	UserFollowersPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_user_followers);

		ButterKnife.bind(this);

		initRefreshLayout();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID userId = (UUID) getIntent().getExtras().getSerializable(EXTRA_USER_ID);
			String userName = getIntent().getExtras().getString(EXTRA_USER_NAME);
			String type = getIntent().getExtras().getString(EXTRA_TYPE);

			if (userId != null && type != null) {
				presenter.setData(userId, type);

				this.title.setText(getString(type.equals(TYPE_FOLLOWING) ? R.string.following : R.string.followers));
				this.subtitle.setText(userName);

				return;
			}
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void initRefreshLayout() {
		refreshLayout.setColorSchemeColors(
				ThemeUtil.getColorByAttrId(this, R.attr.colorAccent),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
				ThemeUtil.getColorByAttrId(this, R.attr.colorTextSecondary));
		refreshLayout.setOnRefreshListener(presenter::onSwipeRefresh);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void setUsers(List<ProfilePublicShort> users) {
		usersGroup.removeAllViews();
		for (ProfilePublicShort user : users) {
			UserShortListView view = new UserShortListView(this);
			view.setUser(user);
			usersGroup.addView(view);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, subtitle);
	}
}
