package vision.genesis.clientapp.feature.main.wallet.deposit;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 2/26/18.
 */

public class DepositWalletActivity extends BaseSwipeBackActivity implements DepositWalletView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity, DepositWalletActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.scrollview)
	public View scrollView;

	@BindView(R.id.text_wallet_address)
	public TextView walletAddressText;

	@BindView(R.id.image_qrcode)
	public ImageView qrCodeImage;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	DepositWalletPresenter depositWalletPresenter;

	private String walletAddress = "";

	@OnClick(R.id.button_copy)
	public void onCopyClicked() {
		ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText("address", walletAddress);
		if (clipboardManager != null) {
			clipboardManager.setPrimaryClip(clipData);
			Toast.makeText(this, getString(R.string.address_copied), Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(this, getString(R.string.cannot_copy), Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_deposit_wallet);

		ButterKnife.bind(this);

		initToolbar();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.deposit));
		toolbar.addLeftButton(R.drawable.ic_chevron_left_black_24dp, () -> depositWalletPresenter.onBackClicked());
	}

	@Override
	public void setWalletAddress(String address) {
		this.walletAddress = address;
		walletAddressText.setText(address);
	}

	@Override
	public void setWalletQrCode(Bitmap image) {
		qrCodeImage.setImageBitmap(image);
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