package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTime;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProfileFullViewModel;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.ProfileManager;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.OnAddNewPostClickedEvent;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class AddNewPostView extends RelativeLayout
{
	@Inject
	public ProfileManager profileManager;

	@BindView(R.id.avatar)
	public AvatarView avatar;

	private Subscription profileSubscription;

	private Unbinder unbinder;

	private ProfileFullViewModel profile;

	private UUID userId;

	public AddNewPostView(Context context) {
		super(context);
		initView();
	}

	public AddNewPostView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AddNewPostView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.avatar)
	public void onAvatarClicked() {
		if (profile != null) {
			UserDetailsModel model = new UserDetailsModel(
					profile.getId(),
					profile.getLogoUrl(),
					profile.getUserName(),
					DateTime.now());
			EventBus.getDefault().post(new ShowUserDetailsEvent(model));
		}
	}

	public void onDestroy() {
		if (profileSubscription != null) {
			profileSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_add_new_post, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		setOnClickListener(v -> EventBus.getDefault().post(new OnAddNewPostClickedEvent(userId)));

		getProfileInfo();
	}

	private void getProfileInfo() {
		profileSubscription = profileManager.getProfileFull(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::handleGetProfileSuccess,
						this::handleGetProfileError);
	}

	private void handleGetProfileSuccess(ProfileFullViewModel profile) {
		this.profile = profile;
		avatar.setImage(profile.getLogoUrl(), 50, 50);
	}

	private void handleGetProfileError(Throwable throwable) {
		throwable.printStackTrace();
	}

	public void setData(UUID userId) {
		this.userId = userId;
	}
}
