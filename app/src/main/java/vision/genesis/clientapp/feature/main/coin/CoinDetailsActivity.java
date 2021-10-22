package vision.genesis.clientapp.feature.main.coin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetInfo;
import io.swagger.client.model.CoinsAsset;
import io.swagger.client.model.Currency;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.coin.buy_sell.BuySellCoinsActivity;
import vision.genesis.clientapp.feature.main.terminal.tradingview_chart.ChartView;
import vision.genesis.clientapp.model.BuySellCoinsModel;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.ui.SocialLinksView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 08/10/2021.
 */

public class CoinDetailsActivity extends BaseSwipeBackActivity implements CoinDetailsView
{
	private static final String EXTRA_SYMBOL = "extra_symbol";

	public static void startWith(Activity activity, String symbol) {
		Intent intent = new Intent(activity, CoinDetailsActivity.class);
		intent.putExtra(EXTRA_SYMBOL, symbol);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.scrollview)
	public ScrollView scrollview;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_info)
	public ViewGroup infoGroup;

	@BindView(R.id.group_no_info)
	public ViewGroup noInfoGroup;

	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.label_chart)
	public TextView chartLabel;

	public ChartView chartView;


	@BindView(R.id.group_your_investment)
	public ViewGroup yourInvestmentGroup;

	@BindView(R.id.amount)
	public TextView amount;

	@BindView(R.id.label_amount)
	public TextView amountLabel;

	@BindView(R.id.total)
	public TextView total;

	@BindView(R.id.label_total)
	public TextView totalLabel;

	@BindView(R.id.price)
	public TextView price;

	@BindView(R.id.label_price)
	public TextView priceLabel;

	@BindView(R.id.avg_price)
	public TextView avgPrice;

	@BindView(R.id.label_avg_price)
	public TextView avgPriceLabel;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.label_change)
	public TextView changeLabel;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.label_profit)
	public TextView profitLabel;


	@BindView(R.id.button_buy)
	public PrimaryButton buyButton;

	@BindView(R.id.button_sell)
	public PrimaryButton sellButton;

	@BindView(R.id.button_buy_big)
	public PrimaryButton bigBuyButton;


	@BindView(R.id.description)
	public TextView description;

	@BindView(R.id.social_links)
	public SocialLinksView socialLinks;

	@InjectPresenter
	CoinDetailsPresenter presenter;

	private CoinsAsset coin = null;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_buy)
	public void onBuyClicked() {
		onBuyCoinClicked();
	}

	@OnClick(R.id.button_buy_big)
	public void onBigBuyClicked() {
		onBuyCoinClicked();
	}

	private void onBuyCoinClicked() {
		if (coin != null) {
			BuySellCoinsModel model = BuySellCoinsModel.createFrom(coin);
			model.setTransferDirection(BuySellCoinsModel.Direction.BUY);
			BuySellCoinsActivity.startWith(this, model);
		}
	}

	@OnClick(R.id.button_sell)
	public void onSellClicked() {
		if (coin != null) {
			BuySellCoinsModel model = BuySellCoinsModel.createFrom(coin);
			model.setTransferDirection(BuySellCoinsModel.Direction.SELL);
			BuySellCoinsActivity.startWith(this, model);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_coin_details);

		ButterKnife.bind(this);

		setLabels();

		buyButton.setGreen();
		sellButton.setRed();
		bigBuyButton.setGreen();

		chartView = (ChartView) findViewById(R.id.view_chart);

		if (getIntent().getExtras() != null) {
			String symbol = getIntent().getExtras().getString(EXTRA_SYMBOL, null);
			presenter.setData(symbol);

			return;
		}

		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		finishActivity();
	}

	@Override
	protected void onResume() {
		super.onResume();
		presenter.onResume();
	}

	private void setLabels() {
		amountLabel.setText(amountLabel.getText().toString().toLowerCase());
		totalLabel.setText(totalLabel.getText().toString().toLowerCase());
		priceLabel.setText(priceLabel.getText().toString().toLowerCase());
		avgPriceLabel.setText(avgPriceLabel.getText().toString().toLowerCase());
		changeLabel.setText(changeLabel.getText().toString().toLowerCase());
		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}

	@Override
	public void setAssetInfo(AssetInfo info) {
		this.infoGroup.setVisibility(View.VISIBLE);
		this.noInfoGroup.setVisibility(View.GONE);

		this.logo.setImageURI(info.getLogoUrl());
		this.name.setText(String.format("%s | %s", info.getName(), info.getSymbol()));
		this.description.setText(info.getDescription());
		this.socialLinks.setData(info.getSocialLinks());
		String symbol = info.getChartSymbol().replace("BINANCE:", "");
		this.chartLabel.setText(getString(R.string.chart).concat(" ").concat(symbol));
		this.chartView.setSymbol(symbol);
	}

	@Override
	public void setInvestment(CoinsAsset coin) {
		this.coin = coin;

		if (coin == null) {
			this.yourInvestmentGroup.setVisibility(View.GONE);
			this.bigBuyButton.setVisibility(View.VISIBLE);
			return;
		}

		this.yourInvestmentGroup.setVisibility(View.VISIBLE);
		this.bigBuyButton.setVisibility(View.GONE);

		this.amount.setText(StringFormatUtil.getValueString(coin.getAmount(), coin.getAsset()));

		this.total.setText(StringFormatUtil.getValueString(coin.getTotal(), Currency.USD.getValue()));
		this.price.setText(StringFormatUtil.getValueString(coin.getPrice(), Currency.USD.getValue()));
		this.avgPrice.setText(StringFormatUtil.getValueString(coin.getAveragePrice(), Currency.USD.getValue()));

		updateProfitText(coin.getProfitCurrent());
		updateChangeText(coin.getChange24Percent());
	}

	@Override
	public void setCoinInfo(CoinsAsset coin) {
		this.coin = coin;
	}

	private void updateProfitText(double profit) {
		this.profit.setText(StringFormatUtil.getValueString(Math.abs(profit), Currency.USD.getValue()));
		this.profit.setTextColor(ThemeUtil.getColorByAttrId(this,
				profit > 0
						? R.attr.colorGreen
						: profit < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	private void updateChangeText(double change) {
		String sign = change > 0 ? "+" : "";
		this.change.setText(String.format(Locale.getDefault(),
				"%s %s",
				sign,
				StringFormatUtil.getPercentString(change)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(this,
				change > 0
						? R.attr.colorGreen
						: change < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	@Override
	public void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? ViewGroup.VISIBLE : View.GONE);
		this.scrollview.setVisibility(!show ? ViewGroup.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, scrollview);
	}
}
