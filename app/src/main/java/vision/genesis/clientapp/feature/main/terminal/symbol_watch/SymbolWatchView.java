package vision.genesis.clientapp.feature.main.terminal.symbol_watch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

	@BindView(R.id.line)
	public ViewGroup line;

	@BindView(R.id.arrow)
	public View arrow;

	@BindView(R.id.line_fill)
	public View fillLine;

	@BindView(R.id.line_empty)
	public View emptyLine;

	public Subscription getFavoritesSubscription;

	public Subscription tickerUpdateSubscription;

	private List<String> favoriteTickers;

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

	@OnClick(R.id.icon_favorite)
	public void onFavoriteClicked() {
		changeSymbolIsFavorite();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_symbol_watch, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		colorTextPrimary = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary);
		colorGreen = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen);
		colorRed = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed);

		onSymbolChanged();
		subscribeToFavoriteTickers();
	}

	private void subscribeToFavoriteTickers() {
		if (terminalManager != null) {
			if (getFavoritesSubscription != null) {
				getFavoritesSubscription.unsubscribe();
			}
			getFavoritesSubscription = terminalManager.getFavoriteTickers()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetFavoriteTickersSuccess,
							this::handleGetFavoriteTickersError);
		}
	}

	private void handleGetFavoriteTickersSuccess(List<String> response) {
		this.favoriteTickers = response;

		checkSymbolIsFavorite();
		iconFavorite.setEnabled(favoriteTickers != null);
	}

	private void checkSymbolIsFavorite() {
		if (favoriteTickers == null) {
			iconFavorite.setVisibility(View.GONE);
			return;
		}

		iconFavorite.setVisibility(View.VISIBLE);
		if (symbol != null) {
			iconFavorite.setImageDrawable(ContextCompat.getDrawable(getContext(),
					isSymbolFavorite() ? R.drawable.icon_favorite_fill : R.drawable.icon_favorite));
		}
	}

	private boolean isSymbolFavorite() {
		if (favoriteTickers == null || symbol == null) {
			return false;
		}
		return favoriteTickers.contains(symbol);
	}

	private void handleGetFavoriteTickersError(Throwable throwable) {
		getFavoritesSubscription.unsubscribe();
	}

	private void changeSymbolIsFavorite() {
		if (terminalManager != null) {
			iconFavorite.setEnabled(false);
			if (!isSymbolFavorite()) {
				terminalManager.addFavoriteTicker(symbol);
			}
			else {
				terminalManager.removeFavoriteTicker(symbol);
			}
		}
	}

	private void onSymbolChanged() {
		getTickerData();
		updateLabels();
		subscribeToTickerUpdates();
		checkSymbolIsFavorite();
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
		if (getFavoritesSubscription != null) {
			getFavoritesSubscription.unsubscribe();
		}
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

			updateHighLow(ticker);
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

		volumeBase.setText(StringFormatUtil.formatAmount(ticker.getTotalTradedBaseAssetVolume(), 2, 8));
		volumeQuote.setText(StringFormatUtil.formatAmount(ticker.getTotalTradedQuoteAssetVolume(), 2, 8));
	}

	private void updateHighLow(TickerModel ticker) {
		double fill = (ticker.getLastPrice() - ticker.getLowPrice()) / (ticker.getHighPrice() - ticker.getLowPrice());

		LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) fillLine.getLayoutParams();
		LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) emptyLine.getLayoutParams();
		lp1.weight = (float) fill;
		lp2.weight = 1 - (float) fill;
		fillLine.setLayoutParams(lp1);
		emptyLine.setLayoutParams(lp2);

		line.post(() -> {
			if (line != null) {
				arrow.setX((float) (line.getWidth() * fill));
			}
		});

		high.setText(StringFormatUtil.formatAmount(ticker.getHighPrice(), 2, 8));
		low.setText(StringFormatUtil.formatAmount(ticker.getLowPrice(), 2, 8));
	}

	private void onError(Throwable throwable) {

	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
		onSymbolChanged();
	}
}
