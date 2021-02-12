package vision.genesis.clientapp.feature.main.terminal.market_trades;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.terminal.binance_api.TradeModel;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */
public class MarketTradeItemView extends RelativeLayout
{
	@BindView(R.id.time)
	public TextView time;

	@BindView(R.id.price)
	public TextView price;

	@BindView(R.id.amount)
	public TextView amount;

	private Unbinder unbinder;

	public MarketTradeItemView(Context context) {
		super(context);
		initView();
	}

	public MarketTradeItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MarketTradeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_item_market_trade, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setTrade(TradeModel trade, boolean isPriceDecreased) {
		this.time.setText(DateTimeUtil.formatTradeTime(trade.getTime()));
		this.price.setText(StringFormatUtil.formatAmount(trade.getPrice()));
		this.amount.setText(StringFormatUtil.formatAmount(trade.getQuantity()));

		this.price.setTextColor(ThemeUtil.getColorByAttrId(getContext(), isPriceDecreased ? R.attr.colorRed : R.attr.colorGreen));
	}
}
