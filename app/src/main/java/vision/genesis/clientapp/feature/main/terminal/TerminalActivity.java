package vision.genesis.clientapp.feature.main.terminal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/01/2021.
 */

public class TerminalActivity extends BaseSwipeBackActivity implements TerminalView
{
	private static final String EXTRA_SYMBOL = "extra_currency";

	public static void startWith(Activity activity, String currency) {
		Intent intent = new Intent(activity.getApplicationContext(), TerminalActivity.class);
		intent.putExtra(EXTRA_SYMBOL, currency);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.symbol)
	public TextView symbol;

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
	public void setSelectedSymbol(String value) {
		selectedSymbol = value;
		this.symbol.setText(value);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, symbol);
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