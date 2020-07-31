package vision.genesis.clientapp.feature.main.social.users;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.UserDetailsList;
import io.swagger.client.model.UserDetailsListItemsViewModel;
import io.swagger.client.model.UsersFilterSorting;
import io.swagger.client.model.UsersFilterTimeframe;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.UsersManager;
import vision.genesis.clientapp.model.events.OnShowUsersListActivityEvent;
import vision.genesis.clientapp.ui.UserShortView;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2020.
 */

public class SocialUsersView extends RelativeLayout
{
	private static final int TAKE = 10;

	@Inject
	public UsersManager usersManager;

	@BindView(R.id.scroll_view_users)
	public HorizontalScrollView scrollView;

	@BindView(R.id.group_users)
	public LinearLayout usersGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindDimen(R.dimen.padding)
	public int padding;

	public Subscription getUsersSubscription;

	private Unbinder unbinder;

	public SocialUsersView(Context context) {
		super(context);
		initView();
	}

	public SocialUsersView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SocialUsersView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.header)
	public void onHeaderClicked() {
		showUsersListActivity();
	}

	private void showUsersListActivity() {
		EventBus.getDefault().post(new OnShowUsersListActivityEvent());
	}

	private void initView() {
		inflate(getContext(), R.layout.view_social_users, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		getUsers();
	}

	public void onDestroy() {
		if (getUsersSubscription != null) {
			getUsersSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void update() {
		getUsers();
	}

	private void getUsers() {
		if (usersManager != null) {
			getUsersSubscription = usersManager.getUsers(UsersFilterSorting.BYFOLLOWERSDESC, UsersFilterTimeframe.MONTH, null, 0, TAKE)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onSuccess, this::onError);
		}
	}

	private void onSuccess(UserDetailsListItemsViewModel model) {
		getUsersSubscription.unsubscribe();

		usersGroup.removeAllViews();
		int index = 0;
		for (UserDetailsList user : model.getItems()) {
			UserShortView view = new UserShortView(getContext());
			view.setUser(user);
			usersGroup.addView(view);

			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(index == 0 ? padding : 0, 0, TypedValueFormatter.toDp(16), 0);
			view.setLayoutParams(lp);

			index++;
		}

		TextView more = new TextView(getContext());
		more.setTypeface(TypefaceUtil.semibold());
		more.setText(getContext().getString(R.string.more));
		more.setTextColor(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent));
		more.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		usersGroup.addView(more);

		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) more.getLayoutParams();
		lp.setMargins(TypedValueFormatter.toDp(16),
				TypedValueFormatter.toDp(16),
				TypedValueFormatter.toDp(24),
				TypedValueFormatter.toDp(16));
		lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
		more.setLayoutParams(lp);
		more.setGravity(Gravity.CENTER_VERTICAL);

		more.setOnClickListener(view -> showUsersListActivity());

		progressBar.setVisibility(View.GONE);
	}

	private void onError(Throwable throwable) {
		getUsersSubscription.unsubscribe();
	}

}