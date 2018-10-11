package vision.genesis.clientapp.feature.main.wallet.deposit;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jakewharton.rxbinding.widget.RxTextView;

import net.glxn.qrgen.android.QRCode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.WalletInfo;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.option.SelectOptionBottomSheetFragment;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/26/18.
 */

public class DepositWalletActivity extends BaseSwipeBackActivity implements DepositWalletView
{
	public static void startWith(Activity activity) {
		Intent intent = new Intent(activity.getApplicationContext(), DepositWalletActivity.class);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.wallet)
	public TextView wallet;

	@BindView(R.id.edittext_amount)
	public EditText amount;

	@BindView(R.id.base_currency_amount)
	public TextView baseCurrencyAmount;

	@BindView(R.id.address)
	public TextView address;

	@BindView(R.id.image_qr_code)
	public ImageView qrCodeImage;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	DepositWalletPresenter depositWalletPresenter;

	private String walletAddress = "";

	private ArrayList<String> walletsOptions;

	private Integer selectedWalletsPosition = 0;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_wallet)
	public void onWalletClicked() {
		SelectOptionBottomSheetFragment fragment = SelectOptionBottomSheetFragment.with(
				getString(R.string.select_wallet_currency), walletsOptions, selectedWalletsPosition);
		fragment.setListener(depositWalletPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());
	}

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
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_deposit_wallet);

		ButterKnife.bind(this);

		setFonts();

		setTextListener();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void setTextListener() {
		RxTextView.textChanges(amount)
				.subscribe(charSequence -> depositWalletPresenter.onAmountChanged(charSequence.toString()));
	}

	@Override
	public void setWalletsOptions(ArrayList<String> walletsOptions) {
		this.walletsOptions = walletsOptions;
	}

	@Override
	public void setWalletInfo(WalletInfo wallet, String walletName, Integer position) {
		this.wallet.setText(walletName);
		this.selectedWalletsPosition = position;
		this.walletAddress = wallet.getAddress();
		this.address.setText(wallet.getAddress());
		qrCodeImage.setImageBitmap(QRCode.from(wallet.getAddress())
				.withColor(ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
						ThemeUtil.getColorByAttrId(this, R.attr.colorBackground))
				.withSize(1000, 1000)
				.withErrorCorrection(ErrorCorrectionLevel.H)
				.bitmap());
	}

	@Override
	public void setAmountBase(String amountBaseString) {
		this.baseCurrencyAmount.setText(amountBaseString);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			content.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_bottom);
	}

}