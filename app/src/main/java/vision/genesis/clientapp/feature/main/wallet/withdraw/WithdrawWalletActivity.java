package vision.genesis.clientapp.feature.main.wallet.withdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.AmountEditText;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 2/27/18.
 */

public class WithdrawWalletActivity extends BaseSwipeBackActivity implements WithdrawWalletView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity, WithdrawWalletActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.scrollview)
	public View scrollView;

	@BindView(R.id.edittext_amount)
	public AmountEditText amountEditText;

	@BindView(R.id.edittext_address)
	public EditText walletAddressEditText;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_withdraw)
	public View withdrawButton;

	@InjectPresenter
	WithdrawWalletPresenter withdrawWalletPresenter;

	@OnClick(R.id.button_withdraw)
	public void onWithdrawButtonClicked() {
		withdrawWalletPresenter.onWithdrawButtonClicked();
	}

	@OnClick(R.id.button_camera)
	public void onCameraButtonClicked() {
		withdrawWalletPresenter.onWithdrawButtonClicked();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_withdraw_wallet);

		ButterKnife.bind(this);

		initToolbar();

		setListeners();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.withdraw));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> withdrawWalletPresenter.onBackClicked());
	}

	private void setListeners() {
		amountEditText.setListener(withdrawWalletPresenter);
		RxTextView.textChanges(walletAddressEditText)
				.subscribe(text -> withdrawWalletPresenter.onWalletAddressChanged(text.toString()));
	}

	@Override
	public void setWalletAddress(String address) {
		walletAddressEditText.setText(address);
	}

	@Override
	public void setWithdrawButtonEnabled(boolean enabled) {
		withdrawButton.setEnabled(enabled);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		scrollView.setVisibility(!show ? View.VISIBLE : View.GONE);
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