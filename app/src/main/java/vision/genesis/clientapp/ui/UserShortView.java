package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.UserDetailsList;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2020.
 */

public class UserShortView extends RelativeLayout
{
	@BindView(R.id.avatar_view)
	public AvatarView avatarView;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.followers)
	public TextView followers;

	@BindView(R.id.investors)
	public TextView investors;

	private Unbinder unbinder;

	private UserDetailsList user;

	public UserShortView(Context context) {
		super(context);
		initView();
	}

	public UserShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public UserShortView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_user_short, this);

		unbinder = ButterKnife.bind(this);

		setOnClickListener(view -> {
			if (user != null) {
				UserDetailsModel userModel = new UserDetailsModel(
						user.getUserId(),
						user.getLogoUrl(),
						user.getUsername(),
						user.getRegDate());
				EventBus.getDefault().post(new ShowUserDetailsEvent(userModel));
			}
		});
	}

	public void setUser(UserDetailsList user) {
		this.user = user;

		this.avatarView.setImage(user.getLogoUrl(), 50, 50);
		this.name.setText(user.getUsername());
		this.followers.setText(String.format(Locale.getDefault(), "%d %s",
				user.getFollowersCount(),
				getContext().getResources().getQuantityString(R.plurals.followers, user.getFollowersCount())));
		this.investors.setText(String.format(Locale.getDefault(), "%d %s",
				user.getInvestorsCount(),
				getContext().getResources().getQuantityString(R.plurals.investors, user.getInvestorsCount())));

	}
}
