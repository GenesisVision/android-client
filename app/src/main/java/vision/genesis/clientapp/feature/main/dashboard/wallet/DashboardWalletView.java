package vision.genesis.clientapp.feature.main.dashboard.wallet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.WalletData;
import io.swagger.client.model.WalletSummary;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.WalletManager;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.ui.WalletDashboardShortView;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/11/2019.
 */

public class DashboardWalletView extends RelativeLayout
{
	@Inject
	public SettingsManager settingsManager;

	@Inject
	public WalletManager walletManager;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.equity)
	public TextView equity;

	@BindView(R.id.change)
	public TextView change;

	@BindView(R.id.share_progress)
	public ProgressBar shareProgress;

	@BindView(R.id.share_percent)
	public TextView sharePercent;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.group_equity)
	public ViewGroup equityGroup;

	@BindView(R.id.group_share)
	public ViewGroup shareGroup;

	@BindView(R.id.group_wallets)
	public ViewGroup walletsGroup;

	public Subscription baseCurrencySubscription;

	public Subscription getDataSubscription;

	private Unbinder unbinder;

	private CurrencyEnum baseCurrency;

	private WalletSummary details;

	public DashboardWalletView(Context context) {
		super(context);
		initView();
	}

	public DashboardWalletView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DashboardWalletView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_dashboard_wallet, this);

		unbinder = ButterKnife.bind(this);

		setFonts();

		GenesisVisionApplication.getComponent().inject(this);

		subscribeToBaseCurrency();
	}

	public void onDestroy() {
		if (baseCurrencySubscription != null) {
			baseCurrencySubscription.unsubscribe();
		}
		if (getDataSubscription != null) {
			getDataSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		equity.setTypeface(TypefaceUtil.semibold());
		sharePercent.setTypeface(TypefaceUtil.semibold());
	}

	public void update() {
		getData();
	}

	private void subscribeToBaseCurrency() {
		baseCurrencySubscription = settingsManager.getBaseCurrency()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::baseCurrencyChangedHandler);
	}

	private void baseCurrencyChangedHandler(CurrencyEnum baseCurrency) {
		this.baseCurrency = baseCurrency;
		getData();
	}

	private void getData() {
		if (walletManager != null && baseCurrency != null) {
			getDataSubscription = walletManager.getWallets(baseCurrency.getValue(), true)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::updateView, this::onError);
		}
	}

	private void updateView(WalletSummary details) {
		getDataSubscription.unsubscribe();

		this.details = details;

		equity.setText(StringFormatUtil.getValueString(details.getGrandTotal().getAvailable(), details.getGrandTotal().getCurrency().getValue()));
//		updateChangeText();
		setWallets(details.getWallets());

		progressBar.setVisibility(View.GONE);
		equityGroup.setVisibility(View.VISIBLE);
		shareGroup.setVisibility(View.VISIBLE);
		walletsGroup.setVisibility(View.VISIBLE);
	}

	private void updateChangeText() {
		double profit = 120756.45;
		double profitPercent = 12.87;
		String sign = profit > 0 ? "+" : "";
		change.setText(String.format(Locale.getDefault(), "%s%s (%s%%)",
				sign,
				StringFormatUtil.getValueString(profit, baseCurrency.getValue()),
				StringFormatUtil.formatAmount(profitPercent, 0, 2)));
		this.change.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
				profitPercent > 0
						? R.attr.colorGreen
						: profitPercent < 0
						? R.attr.colorRed
						: R.attr.colorTextPrimary));
	}

	private void setWallets(List<WalletData> wallets) {
		walletsGroup.removeAllViews();
		for (WalletData wallet : wallets) {
			WalletDashboardShortView walletView = new WalletDashboardShortView(getContext());
			walletView.setData(wallet, baseCurrency.getValue());
			walletsGroup.addView(walletView);
		}
	}

	private void onError(Throwable throwable) {
		getDataSubscription.unsubscribe();
	}

	public void setShare(int share) {
		this.shareProgress.setProgress(share);
		this.sharePercent.setText(String.format(Locale.getDefault(), "%d%%", share));
	}
}
