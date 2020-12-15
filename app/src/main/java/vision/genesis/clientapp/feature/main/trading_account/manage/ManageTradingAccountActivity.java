package vision.genesis.clientapp.feature.main.trading_account.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.PrivateTradingAccountType;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.trading_account.change_broker.ChangeBrokerActivity;
import vision.genesis.clientapp.feature.main.trading_account.change_password.ChangeTradingAccountPasswordActivity;
import vision.genesis.clientapp.model.TradingAccountDetailsModel;
import vision.genesis.clientapp.ui.AccountAgeView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public class ManageTradingAccountActivity extends BaseSwipeBackActivity implements ManageTradingAccountView
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startFrom(Activity activity, TradingAccountDetailsModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), ManageTradingAccountActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.label_broker)
	public TextView labelBroker;

	@BindView(R.id.label_currency)
	public TextView labelCurrency;

	@BindView(R.id.label_leverage)
	public TextView labelLeverage;


	@BindView(R.id.broker_logo)
	public SimpleDraweeView brokerLogo;

	@BindView(R.id.group_currency)
	public ViewGroup currencyGroup;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.group_leverage)
	public ViewGroup leverageGroup;

	@BindView(R.id.leverage)
	public TextView leverage;


	@BindView(R.id.group_new)
	public ViewGroup groupNew;

	@BindView(R.id.new_broker_logo)
	public SimpleDraweeView newBrokerLogo;

	@BindView(R.id.new_currency)
	public TextView newCurrency;

	@BindView(R.id.new_leverage)
	public TextView newLeverage;


	@BindView(R.id.button_change)
	public PrimaryButton buttonChange;


	@BindView(R.id.group_migration)
	public ViewGroup groupMigration;

	@BindView(R.id.migration_info)
	public TextView migrationInfo;

	@BindView(R.id.warning_info)
	public TextView warningInfo;

	@BindView(R.id.button_cancel_migration)
	public PrimaryButton buttonCancelMigration;

	@BindView(R.id.migration_progress_bar)
	public ProgressBar migrationProgressBar;


	@BindView(R.id.creation_date)
	public TextView creationDate;

	@BindView(R.id.label_creation_date)
	public TextView labelCreationDate;

	@BindView(R.id.age_period)
	public AccountAgeView agePeriod;

	@BindView(R.id.label_age)
	public TextView labelAge;

	@BindView(R.id.button_change_password)
	public ViewGroup changePasswordButton;

	@BindView(R.id.button_close_account)
	public PrimaryButton closeAccountButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;


	@InjectPresenter
	ManageTradingAccountPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@OnClick(R.id.button_change)
	public void onChangeClicked() {
		presenter.onChangeClicked();
	}

	@OnClick(R.id.button_cancel_migration)
	public void onCancelMigrationClicked() {
		presenter.onCancelMigrationClicked();
	}

	@OnClick(R.id.button_change_password)
	public void onChangePasswordClicked() {
		presenter.onChangePasswordClicked();
	}


	@OnClick(R.id.button_close_account)
	public void onCloseAccountClicked() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(getString(R.string.close_trading_account));
		builder.setMessage(getString(R.string.close_account_alert_message));
		builder.setPositiveButton(getString(R.string.confirm), (dialog, which) -> {
			presenter.onCloseAccountClicked();
			dialog.dismiss();
		});
		builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss());
		builder.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_manage_trading_account);

		ButterKnife.bind(this);

		setFonts();

		closeAccountButton.setRed();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			TradingAccountDetailsModel model = getIntent().getExtras().getParcelable(EXTRA_MODEL);

			if (model != null) {
				updateView(model);

				presenter.setData(model);
			}
			return;
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());

		currency.setTypeface(TypefaceUtil.semibold());
		leverage.setTypeface(TypefaceUtil.semibold());

		newCurrency.setTypeface(TypefaceUtil.semibold());
		newLeverage.setTypeface(TypefaceUtil.semibold());

		creationDate.setTypeface(TypefaceUtil.semibold());

		labelBroker.setText(labelBroker.getText().toString().toLowerCase());
		labelCurrency.setText(labelCurrency.getText().toString().toLowerCase());
		labelLeverage.setText(labelLeverage.getText().toString().toLowerCase());
		labelCreationDate.setText(labelCreationDate.getText().toString().toLowerCase());
		labelAge.setText(labelAge.getText().toString().toLowerCase());
	}

	@Override
	public void updateView(TradingAccountDetailsModel model) {
		this.brokerLogo.setImageURI(ImageUtils.getImageUri(model.getBrokerLogo()));
		if (model.getCurrency() != null) {
			this.currency.setText(model.getCurrency());
			this.currencyGroup.setVisibility(View.VISIBLE);
		}
		else {
			this.currencyGroup.setVisibility(View.GONE);
		}
		this.leverageGroup.setVisibility(model.getType().equals(PrivateTradingAccountType.EXCHANGEACCOUNT) ? View.GONE : View.VISIBLE);
		this.leverage.setText(String.format(Locale.getDefault(), "1:%d", model.getLeverage()));
		this.creationDate.setText(DateTimeUtil.formatEventDateTime(model.getCreationDate()));
		this.agePeriod.setCreationDate(model.getCreationDate());


		if (model.getMigration() != null) {
			this.newBrokerLogo.setImageURI(ImageUtils.getImageUri(model.getMigration().getNewBroker().getLogoUrl()));
			this.newCurrency.setText(model.getCurrency());
			this.newLeverage.setText(String.format(Locale.getDefault(), "1:%d", model.getMigration().getNewLeverage()));
			this.migrationInfo.setText(String.format(Locale.getDefault(),
					getString(R.string.template_trading_account_migration_started),
					DateTimeUtil.formatEventDateTime(model.getMigration().getDateCreate()),
					model.getBrokerName(),
					model.getMigration().getNewBroker().getName()));

			if (model.getBrokerName().toLowerCase().equals("genesis markets")
					&& model.getMigration().getNewBroker().getName().toLowerCase().equals("huobi")) {
				warningInfo.setVisibility(View.VISIBLE);
				warningInfo.setText(getString(R.string.warning_info_switch_gm_to_huobi));
			}
			else {
				warningInfo.setVisibility(View.GONE);
			}

			groupNew.setVisibility(View.VISIBLE);
			groupMigration.setVisibility(View.VISIBLE);
			buttonChange.setVisibility(View.GONE);
		}
		else {
			groupNew.setVisibility(View.GONE);
			groupMigration.setVisibility(View.GONE);
			buttonChange.setVisibility(model.isCanChangeBroker() ? View.VISIBLE : View.GONE);
		}

		this.changePasswordButton.setVisibility(model.isCanChangePassword() ? View.VISIBLE : View.GONE);
		this.closeAccountButton.setVisibility(model.isCanClose() ? View.VISIBLE : View.GONE);

//		if (model.getAssetId().equals(model.getAccountId())) {
//			closeAccountButton.setVisibility(View.VISIBLE);
//		}
//		else {
//			closeAccountButton.setVisibility(View.GONE);
//		}
	}

	@Override
	public void showChangeBrokerActivity(TradingAccountDetailsModel model) {
		ChangeBrokerActivity.startFrom(this, model);
	}

	@Override
	public void showChangePasswordActivity(TradingAccountDetailsModel model) {
		ChangeTradingAccountPasswordActivity.startFrom(this, model.getAccountId());
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void showCancelProgress(boolean show) {
		migrationProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		buttonCancelMigration.setVisibility(!show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

}
