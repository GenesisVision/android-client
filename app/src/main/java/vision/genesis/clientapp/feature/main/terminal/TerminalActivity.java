package vision.genesis.clientapp.feature.main.terminal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.terminal.symbol_watch.SymbolWatchView;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class TerminalActivity extends BaseSwipeBackActivity implements TerminalView
{
	private static final String EXTRA_SYMBOL = "extra_symbol";

	public static void startWith(Activity activity, String symbol) {
		Intent intent = new Intent(activity.getApplicationContext(), TerminalActivity.class);
		intent.putExtra(EXTRA_SYMBOL, symbol);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.scrollview)
	public ScrollView scrollView;

	@BindView(R.id.view_symbol_watch)
	public SymbolWatchView symbolWatchView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	TerminalPresenter terminalPresenter;

	private String selectedSymbol = "";

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_terminal);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			selectedSymbol = getIntent().getExtras().getString(EXTRA_SYMBOL, null);
			terminalPresenter.onSymbolChanged(selectedSymbol);

			return;
		}

		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		finishActivity();
	}

	@Override
	protected void onDestroy() {
		if (symbolWatchView != null) {
			symbolWatchView.onDestroy();
		}

		super.onDestroy();
	}

	@Override
	protected void onResume() {
		if (symbolWatchView != null) {
			symbolWatchView.onResume();
		}
		super.onResume();
	}

	@Override
	public void setSelectedSymbol(String symbol) {
		selectedSymbol = symbol;
		this.symbolWatchView.setSymbol(symbol);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		if (!show) {
			scrollView.setVisibility(View.VISIBLE);
		}
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, progressBar);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}