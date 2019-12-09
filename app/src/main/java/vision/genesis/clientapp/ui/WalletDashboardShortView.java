package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.WalletModel;
import vision.genesis.clientapp.model.events.ShowSpecificWalletEvent;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/11/2019.
 */

public class WalletDashboardShortView extends RelativeLayout
{
	@BindView(R.id.icon)
	public SimpleDraweeView icon;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.value_ccy)
	public TextView valueCcy;

	private WalletData wallet;

	private Unbinder unbinder;


	public WalletDashboardShortView(Context context) {
		super(context);
		initView();
	}

	public WalletDashboardShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public WalletDashboardShortView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_wallet_dashboard_short, this);

		unbinder = ButterKnife.bind(this);

		setFonts();

		setOnClickListener(v -> {
			if (wallet != null) {
				EventBus.getDefault().post(new ShowSpecificWalletEvent(WalletModel.createFrom(wallet)));
			}
		});
	}

	private void setFonts() {
		value.setTypeface(TypefaceUtil.semibold());
	}

	public void setData(WalletData wallet) {
		this.wallet = wallet;
		icon.setImageURI(ImageUtils.getImageUri(wallet.getLogo()));
		currency.setText(wallet.getTitle());
		value.setText(String.format(Locale.getDefault(), "%s",
				StringFormatUtil.getValueString(wallet.getAvailable(), wallet.getCurrency().getValue())));
//		valueCcy.setText(String.format(Locale.getDefault(), "%s",
//				StringFormatUtil.getValueString(wallet.getAvailableCcy(), wallet.getCurrencyCcy().getValue())));
	}
}
