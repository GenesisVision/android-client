package vision.genesis.clientapp.feature.main.wallet.deposit;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.android.QRCode;

import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 2/26/18.
 */

public class DepositWalletActivity extends BaseSwipeBackActivity implements DepositWalletView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, WalletModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), DepositWalletActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_bottom, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.content)
	public ViewGroup content;

	@BindView(R.id.wallet_icon)
	public SimpleDraweeView walletIcon;

	@BindView(R.id.wallet_name)
	public TextView walletName;

	@BindView(R.id.wallet_balance)
	public TextView walletBalance;

	@BindView(R.id.address)
	public TextView address;

	@BindView(R.id.image_qr_code)
	public ImageView qrCodeImage;

	@InjectPresenter
	DepositWalletPresenter depositWalletPresenter;

	private WalletModel model;

	@OnClick(R.id.button_close)
	public void onCloseClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_copy)
	public void onCopyClicked() {
		ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText("address", model.getAddress());
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

		if (getIntent().getExtras() != null) {
			model = Objects.requireNonNull(getIntent().getExtras().getParcelable(EXTRA_MODEL));
			updateData(model);
		}

		setFonts();
	}

	private void updateData(WalletModel model) {
		this.walletIcon.setImageURI(ImageUtils.getImageUri(model.getLogo()));
		this.walletName.setText(model.getTitle());
		this.walletBalance.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(model.getAvailable(), model.getCurrency()),
				model.getCurrency()));

		if (model.getAddress() != null) {
			this.address.setText(model.getAddress());
			this.qrCodeImage.setImageBitmap(QRCode.from(model.getAddress())
					.withColor(ThemeUtil.getColorByAttrId(this, R.attr.colorTextPrimary),
							ThemeUtil.getColorByAttrId(this, R.attr.colorCard))
					.withSize(1000, 1000)
					.withErrorCorrection(ErrorCorrectionLevel.H)
					.bitmap());
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_bottom);
	}
}