package vision.genesis.clientapp.feature.main.wallet.buy_with_card;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.net.CustomWebViewClient;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/09/2020.
 */

public class BuyWithCardActivity extends BaseSwipeBackActivity implements BuyWithCardView
{
	private static final String EXTRA_PAYMENT_URL = "extra_payment_url";

	public static void startWith(Activity activity, String paymentUrl) {
		Intent intent = new Intent(activity.getApplicationContext(), BuyWithCardActivity.class);
		intent.putExtra(EXTRA_PAYMENT_URL, paymentUrl);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.web_view)
	public WebView webView;

	@InjectPresenter
	BuyWithCardPresenter presenter;

	private CustomWebViewClient webViewClient;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_buy_with_card);

		ButterKnife.bind(this);

		initWebView();

		if (getIntent().getExtras() != null) {
			String paymentUrl = getIntent().getExtras().getString(EXTRA_PAYMENT_URL);
			if (paymentUrl != null && !paymentUrl.isEmpty()) {
				setPaymentUrl(paymentUrl);

				return;
			}
		}
		Timber.e("Passed empty params to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		webViewClient = new CustomWebViewClient();
		webView.setWebViewClient(webViewClient);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
	}

	public void setPaymentUrl(String paymentUrl) {
		this.webView.loadUrl(paymentUrl);
		webViewClient.setUrlToIntercept("www.genesis.vision", presenter);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, webView);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}