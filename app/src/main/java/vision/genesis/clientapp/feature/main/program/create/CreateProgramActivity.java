package vision.genesis.clientapp.feature.main.program.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.Broker;
import io.swagger.client.model.Currency;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

public class CreateProgramActivity extends BaseSwipeBackActivity implements CreateProgramView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startFrom(Activity activity, CreateProgramModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), CreateProgramActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.create_program_view_pager)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	CreateProgramPresenter presenter;

	private CreateProgramPagerAdapter adapter;

	private Boolean isExchangeProgram = false;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_program);

		ButterKnife.bind(this);

		setFonts();
		viewPager.setAllowSwipe(false);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			CreateProgramModel model = getIntent().getExtras().getParcelable(EXTRA_MODEL);

			presenter.setData(model);
			return;
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onBackPressed() {
		if (viewPager.getCurrentItem() == 0) {
			finishActivity();
		}
		else if (viewPager.getCurrentItem() == 2) {
			viewPager.setCurrentItem(!isExchangeProgram ? 1 : 0);
		}
		else {
			viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
		}
	}

	@Override
	public void initViewPager(Boolean needBrokerSelect, Boolean needPublicInfo, Boolean needDeposit, CreateProgramModel model) {
		this.adapter = new CreateProgramPagerAdapter(getSupportFragmentManager(), needBrokerSelect, needPublicInfo, needDeposit, model);
		viewPager.setAdapter(adapter);
		viewPager.setEnabled(false);
		viewPager.setOffscreenPageLimit(5);
	}

	@Override
	public void setIsExchangeProgram(Boolean isExchangeProgram) {
		if (adapter != null) {
			this.isExchangeProgram = isExchangeProgram;
			adapter.setIsExchangeProgram(isExchangeProgram);
		}
	}

	@Override
	public void setMinDeposit(Map<String, Double> minDepositInfo, Currency accountCurrency) {
		if (adapter != null) {
			adapter.setMinDeposit(minDepositInfo, accountCurrency);
		}
	}

	@Override
	public void showAccountSettings(Broker selectedBroker) {
		if (adapter != null) {
			adapter.setSelectedBroker(selectedBroker);
			viewPager.setCurrentItem(adapter.getAccountSettingsPosition());
		}
	}

	@Override
	public void showPublicInfo() {
		if (adapter != null) {
			viewPager.setCurrentItem(adapter.getPublicInfoPosition());
		}
	}

	@Override
	public void showSettings() {
		viewPager.setCurrentItem(adapter.getSettingsPosition());
	}

	@Override
	public void showDeposit() {
		viewPager.setCurrentItem(adapter.getDepositPosition());
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
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