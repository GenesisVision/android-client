package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.CoinsAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowCoinDetailsEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/10/2021.
 */

public class CoinDashboardShortView extends RelativeLayout
{
	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.amount)
	public TextView amount;

	@BindView(R.id.price)
	public TextView price;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.delimiter)
	public View delimiter;

	private Unbinder unbinder;

	private CoinsAsset coin;

	public CoinDashboardShortView(Context context) {
		super(context);
		initView();
	}

	public CoinDashboardShortView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CoinDashboardShortView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_coin_dashboard_short, this);

		unbinder = ButterKnife.bind(this);

		setOnClickListener(v -> {
			if (coin != null) {
				EventBus.getDefault().post(new ShowCoinDetailsEvent(coin.getAsset()));
			}
		});
	}

	public void removeDelimiter() {
		delimiter.setVisibility(View.INVISIBLE);
	}

	public void setData(CoinsAsset coin) {
		this.coin = coin;

		this.logo.setImageURI(coin.getLogoUrl());
		this.name.setText(coin.getName());
		this.amount.setText(StringFormatUtil.getValueString(coin.getAmount(), coin.getAsset()));

		this.price.setText(String.format(Locale.getDefault(), getContext().getString(R.string.asset_price_template),
				String.format(Locale.getDefault(), "$ %s", StringFormatUtil.formatAmount(coin.getPrice(), 0, 8)),
				String.format(Locale.getDefault(), "$ %s", StringFormatUtil.formatAmount(coin.getAveragePrice(), 0, 8))));

		updateProfitText(coin.getTotal(), coin.getProfitCurrent());
		updateChangeText(coin.getChange24Percent());
	}

	private void updateProfitText(double value, double profit) {
		String sign = profit > 0 ? "+" : profit < 0 ? "-" : "";
		this.profit.setText(String.format(Locale.getDefault(), "%s (%s %s)",
				String.format(Locale.getDefault(), "$ %s", StringFormatUtil.formatAmount(value, 0, 8)),
				sign,
				String.format(Locale.getDefault(), "$ %s", StringFormatUtil.formatAmount(Math.abs(profit), 0, 8))));
		this.profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				profit > 0
						? R.attr.colorGreen
						: profit < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	private void updateChangeText(double change) {
		String sign = change > 0 ? "+" : "";
		this.change.setText(String.format(Locale.getDefault(),
				getContext().getString(R.string.asset_change_24_template),
				sign,
				StringFormatUtil.formatAmount(change, 2, 2)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				change > 0
						? R.attr.colorGreen
						: change < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}
}
