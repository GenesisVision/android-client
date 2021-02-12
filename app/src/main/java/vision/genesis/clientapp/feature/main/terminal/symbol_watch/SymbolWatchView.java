package vision.genesis.clientapp.feature.main.terminal.symbol_watch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/02/2021.
 */

public class SymbolWatchView extends RelativeLayout
{
	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.root)
	public ViewGroup root;

	@BindView(R.id.icon_favorite)
	public ImageView iconFavorite;

	@BindView(R.id.base_asset)
	public TextView baseAsset;

	@BindView(R.id.quote_asset)
	public TextView quoteAsset;

	@BindView(R.id.price)
	public TextView price;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.high)
	public TextView high;

	@BindView(R.id.low)
	public TextView low;

	@BindView(R.id.label_volume_base)
	public TextView volumeBaseLabel;

	@BindView(R.id.volume_base)
	public TextView volumeBase;

	@BindView(R.id.label_volume_quote)
	public TextView volumeQuoteLabel;

	@BindView(R.id.volume_quote)
	public TextView volumeQuote;

	public Subscription tickerUpdateSubscription;

	private Unbinder unbinder;

	private String symbol = "";

	private String baseAssetString = "";

	private String quoteAssetString = "";

	private int colorTextPrimary;

	private int colorGreen;

	private int colorRed;

	private double previousPrice = 0.0;

	public SymbolWatchView(Context context) {
		super(context);
		initView();
	}

	public SymbolWatchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SymbolWatchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_symbol_watch, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		colorTextPrimary = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary);
		colorGreen = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen);
		colorRed = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed);

		onSymbolChanged();
	}

	private void onSymbolChanged() {
		getTickerData();
		updateLabels();
		subscribeToTickerUpdates();
	}

	private void getTickerData() {
		if (symbol != null && terminalManager != null) {
			TickerModel tickerData = terminalManager.getTickerData(symbol);
			if (tickerData != null) {
				updateView(tickerData);
			}
		}
	}

	public void onDestroy() {
		if (tickerUpdateSubscription != null) {
			tickerUpdateSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void onResume() {
		if (tickerUpdateSubscription == null || tickerUpdateSubscription.isUnsubscribed()) {
			subscribeToTickerUpdates();
		}
	}

	private void updateLabels() {
		if (symbol != null && terminalManager != null) {
			Pair<String, String> baseQuoteAssets = terminalManager.getBaseQuoteAssets(symbol);
			if (baseQuoteAssets != null) {
				baseAssetString = baseQuoteAssets.first;
				quoteAssetString = baseQuoteAssets.second;
			}
			this.baseAsset.setText(baseAssetString);
			this.quoteAsset.setText(String.format("/%s", quoteAssetString));
			this.volumeBaseLabel.setText(String.format(getContext().getString(R.string.header_template_24h_volume), baseAssetString));
			this.volumeQuoteLabel.setText(String.format(getContext().getString(R.string.header_template_24h_volume), quoteAssetString));
		}
	}

	private void subscribeToTickerUpdates() {
		if (terminalManager != null && symbol != null) {
			if (tickerUpdateSubscription != null) {
				tickerUpdateSubscription.unsubscribe();
			}
			tickerUpdateSubscription = terminalManager.getTickerSubject(symbol)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::updateView, this::onError);
		}
	}

	private void updateView(TickerModel ticker) {
		this.root.setVisibility(ViewGroup.VISIBLE);

		if (ticker.getLastPrice() != null) {
			price.setText(StringFormatUtil.formatAmount(ticker.getLastPrice()));

			int color = colorTextPrimary;
			if (previousPrice != 0.0) {
				if (ticker.getLastPrice() > previousPrice) {
					color = colorGreen;
				}
				else if (ticker.getLastPrice() < previousPrice) {
					color = colorRed;
				}
			}
			this.price.setTextColor(color);

			previousPrice = ticker.getLastPrice();
		}

		if (ticker.getPriceChange() != null) {
			change.setText(String.format("%s%s (%s%s)",
					ticker.getPriceChange() > 0 ? getContext().getString(R.string.arrow_up) : getContext().getString(R.string.arrow_down), StringFormatUtil.formatCurrencyAmount(Math.abs(ticker.getPriceChange()), quoteAssetString),
					ticker.getPriceChange() > 0 ? "+" : "", StringFormatUtil.getPercentString(ticker.getPriceChangePercent())));
			int color = colorTextPrimary;
			if (ticker.getPriceChange() > 0) {
				color = colorGreen;
			}
			else if (ticker.getPriceChange() < 0) {
				color = colorRed;

			}
			this.change.setTextColor(color);
		}

		high.setText(StringFormatUtil.formatAmount(ticker.getHighPrice(), 2, 8));
		low.setText(StringFormatUtil.formatAmount(ticker.getLowPrice(), 2, 8));

		volumeBase.setText(StringFormatUtil.formatAmount(ticker.getTotalTradedBaseAssetVolume(), 2, 8));
		volumeQuote.setText(StringFormatUtil.formatAmount(ticker.getTotalTradedQuoteAssetVolume(), 2, 8));
	}

	private void onError(Throwable throwable) {

	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
		onSymbolChanged();
	}
}
