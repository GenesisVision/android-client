package vision.genesis.clientapp.feature.main.trading_account.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.Broker;
import io.swagger.client.model.ExchangeInfo;
import io.swagger.client.model.NewTradingAccountRequest;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.trading_account.two_factor.SetupAccountTfaActivity;
import vision.genesis.clientapp.model.CreateAccountModel;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 20/11/2019.
 */

public class CreateAccountActivity extends BaseSwipeBackActivity implements CreateAccountView
{
	public static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, CreateAccountModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), CreateAccountActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.create_account_view_pager)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	CreateAccountPresenter presenter;

	private CreateAccountPagerAdapter adapter;

	private boolean isExchange = false;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);

		ButterKnife.bind(this);

		viewPager.setAllowSwipe(false);

		if (getIntent().getExtras() != null) {
			CreateAccountModel model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				presenter.setData(model);
			}

			initViewPager(model);

			return;
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void initViewPager(CreateAccountModel model) {
		this.adapter = new CreateAccountPagerAdapter(getSupportFragmentManager(), model);
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
			case 2:
				viewPager.setCurrentItem(viewPager.getCurrentItem() - 2);
				break;
			case 3:
				viewPager.setCurrentItem(isExchange
						? viewPager.getCurrentItem() - 1
						: viewPager.getCurrentItem() - 2);
				break;
		}
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSetup2Fa(UUID id) {
		SetupAccountTfaActivity.startWith(this, id);
	}

	@Override
	public void setRequestObjectToFragments(NewTradingAccountRequest request) {
		if (adapter != null) {
			adapter.setRequest(request);
		}
	}

	@Override
	public void showExchangeAccountDeposit(Map<String, Double> minDepositAmount, String currency) {
		if (adapter != null) {
			adapter.setDepositStepNumber("03");
			adapter.setMinDepositAmount(minDepositAmount, currency);
			viewPager.setCurrentItem(3);
		}
	}

	@Override
	public void showBrokerAccountDeposit(Map<String, Double> minDepositAmountInfo, String currency) {
		if (adapter != null) {
			adapter.setMinDepositAmount(minDepositAmountInfo, currency);
			viewPager.setCurrentItem(3);
		}
	}

	@Override
	public void showBrokerSettings(Broker selectedBroker) {
		if (adapter != null) {
			this.isExchange = false;
			adapter.setDepositStepNumber("03");
			adapter.setSelectedBroker(selectedBroker);
			viewPager.setCurrentItem(1);
		}
	}

	@Override
	public void showExchangeSettings(ExchangeInfo selectedExchange) {
		if (adapter != null) {
			this.isExchange = true;
			adapter.setDepositStepNumber("03");
			adapter.setSelectedExchange(selectedExchange);
			viewPager.setCurrentItem(2);
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