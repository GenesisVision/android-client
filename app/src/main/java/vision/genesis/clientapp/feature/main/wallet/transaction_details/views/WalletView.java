package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class WalletView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	@BindView(R.id.icon)
	public SimpleDraweeView icon;

	@BindView(R.id.wallet_name)
	public TextView walletName;

	@BindView(R.id.wallet_available)
	public TextView walletAvailable;

	public WalletView(Context context) {
		super(context);
		initView();
	}

	public WalletView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public WalletView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_wallet, this);

		ButterKnife.bind(this);
	}

	public void setData(String label, String logo, String walletName) {
		this.label.setText(label);
		this.icon.setImageURI(ImageUtils.getImageUri(logo));
		this.walletName.setText(walletName);
//		this.walletAvailable.setText(String.format(Locale.getDefault(), "%s %s",
//				StringFormatUtil.formatCurrencyAmount(wallet.getAvailable(), wallet.getCurrency().getValue()),
//				wallet.getCurrency().getValue()));
//		this.walletAvailable.setText(walletAvailable);
	}
}