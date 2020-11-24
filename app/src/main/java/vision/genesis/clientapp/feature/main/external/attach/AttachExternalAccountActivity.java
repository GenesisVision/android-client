package vision.genesis.clientapp.feature.main.external.attach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.Broker;
import io.swagger.client.model.BrokerDetails;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 27/12/2019.
 */

public class AttachExternalAccountActivity extends BaseSwipeBackActivity implements AttachExternalAccountView
{
	public static final String EXTRA_BROKER = "extra_broker";

	public static void startWith(Activity activity, BrokerDetails selectedBroker) {
		Intent intent = new Intent(activity.getApplicationContext(), AttachExternalAccountActivity.class);
		intent.putExtra(EXTRA_BROKER, selectedBroker);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.attach_external_account_view_pager)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	AttachExternalAccountPresenter presenter;

	private AttachExternalAccountPagerAdapter adapter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attach_external_account);

		ButterKnife.bind(this);

		viewPager.setAllowSwipe(false);

		if (getIntent().getExtras() != null) {
			BrokerDetails selectedBroker = getIntent().getExtras().getParcelable(EXTRA_BROKER);
			if (selectedBroker != null) {
				presenter.setData(selectedBroker);
			}

			initViewPager(selectedBroker);

			return;
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void initViewPager(BrokerDetails selectedBroker) {
		this.adapter = new AttachExternalAccountPagerAdapter(getSupportFragmentManager(), selectedBroker);
		viewPager.setAdapter(adapter);
		viewPager.setEnabled(false);
		viewPager.setOffscreenPageLimit(3);
	}

	@Override
	public void onBackPressed() {
		switch (viewPager.getCurrentItem()) {
			case 0:
				finishActivity();
				break;
			case 1:
				viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
				break;
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showNextStep() {
		viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
	}

	@Override
	public void showApiKey(Broker selectedBroker) {
		if (adapter != null) {
			viewPager.setCurrentItem(1);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPager);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}