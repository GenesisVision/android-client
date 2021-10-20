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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetInfo;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.terminal.tradingview_chart.ChartView;
import vision.genesis.clientapp.ui.SocialLinksView;
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

	public ChartView chartView;

	@BindView(R.id.description)
	public TextView description;

	@BindView(R.id.social_links)
	public SocialLinksView socialLinks;

	@InjectPresenter
	CoinDetailsPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_coin_details);

		ButterKnife.bind(this);

		chartView = (ChartView) findViewById(R.id.view_chart);

		if (getIntent().getExtras() != null) {
			String symbol = getIntent().getExtras().getString(EXTRA_SYMBOL, null);
			presenter.setData(symbol);

			return;
		}

		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		finishActivity();
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
		this.chartView.setSymbol(symbol);
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
