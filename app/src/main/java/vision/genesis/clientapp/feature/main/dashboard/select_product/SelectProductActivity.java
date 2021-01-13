package vision.genesis.clientapp.feature.main.dashboard.select_product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.external.attach.AttachExternalAccountActivity;
import vision.genesis.clientapp.feature.main.fund.create.CreateFundActivity;
import vision.genesis.clientapp.feature.main.fund.self_managed.create.CreateSelfManagedFundActivity;
import vision.genesis.clientapp.feature.main.program.create.CreateProgramActivity;
import vision.genesis.clientapp.feature.main.trading_account.create.CreateAccountActivity;
import vision.genesis.clientapp.model.CreateProgramModel;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/01/2021.
 */

public class SelectProductActivity extends BaseSwipeBackActivity
{
	public static void startFrom(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), SelectProductActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_fund)
	public void onFundClicked() {
		CreateFundActivity.startFrom(this);
		finishActivity();
	}

	@OnClick(R.id.button_self_managed_fund)
	public void onSelfManagedFundClicked() {
		CreateSelfManagedFundActivity.startFrom(this);
		finishActivity();
	}

	@OnClick(R.id.button_program)
	public void onProgramClicked() {
		CreateProgramActivity.startFrom(this, new CreateProgramModel());
		finishActivity();
	}

	@OnClick(R.id.button_trading_account)
	public void onTradingAccountClicked() {
		CreateAccountActivity.startWith(this, null);
		finishActivity();
	}

	@OnClick(R.id.button_attach_external_account)
	public void onAttachExternalAccountClicked() {
		AttachExternalAccountActivity.startWith(this, null);
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_select_product);

		ButterKnife.bind(this);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}
