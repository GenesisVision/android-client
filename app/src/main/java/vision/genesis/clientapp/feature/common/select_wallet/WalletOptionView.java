package vision.genesis.clientapp.feature.common.select_wallet;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/02/2019.
 */
public class WalletOptionView extends RelativeLayout
{
	@BindView(R.id.icon)
	public SimpleDraweeView icon;

	@BindView(R.id.wallet_name)
	public TextView walletName;

	@BindView(R.id.wallet_available)
	public TextView walletAvailable;

	public WalletOptionView(Context context) {
		super(context);
		initView();
	}

	public WalletOptionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public WalletOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_wallet_option, this);

		ButterKnife.bind(this);
	}

	public void setData(WalletData wallet) {
		this.icon.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		this.walletName.setText(wallet.getTitle());
		this.walletAvailable.setText(String.format(Locale.getDefault(), "%s %s",
				StringFormatUtil.formatCurrencyAmount(wallet.getAvailable(), wallet.getCurrency().getValue()),
				wallet.getCurrency().getValue()));
	}
}
