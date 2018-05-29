package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/05/2018.
 */
public class CurrencyView extends RelativeLayout
{
	@BindView(R.id.currency)
	public TextView currency;

	private Unbinder unbinder;

	public CurrencyView(Context context) {
		super(context);
		initView();
	}

	public CurrencyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CurrencyView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_currency, this);

		unbinder = ButterKnife.bind(this);

		currency.setTypeface(TypefaceUtil.bold());
	}

	public void setCurrency(String currency) {
		this.currency.setText(currency);
		int drawableResId = R.drawable.currency_background;
		switch (currency.toUpperCase()) {
			case "USD":
				drawableResId = R.drawable.currency_usd_background;
				break;
			case "EUR":
				drawableResId = R.drawable.currency_eur_background;
				break;
			case "BTC":
				drawableResId = R.drawable.currency_btc_background;
				break;
			case "ETH":
				drawableResId = R.drawable.currency_eth_background;
				break;
		}

		this.currency.setBackground(ContextCompat.getDrawable(GenesisVisionApplication.INSTANCE, drawableResId));
	}
}
