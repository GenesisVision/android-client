package vision.genesis.clientapp.feature.main.copytrading.open_trade_details;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.OpenTradeModel;
import vision.genesis.clientapp.model.OpenTradeProviderModel;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/06/2019.
 */

public class OpenTradeDetailsActivity extends BaseSwipeBackActivity implements OpenTradeDetailsView
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, OpenTradeModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), OpenTradeDetailsActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.header_details)
	public TextView headerDetails;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@InjectPresenter
	public OpenTradeDetailsPresenter openTradeDetailsPresenter;

	private OpenTradeDetailsAdapter openTradeDetailsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_open_trade_details);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			OpenTradeModel model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			if (model != null) {
				setFonts();
				updateHeader(model);
				initRecyclerView();

				openTradeDetailsPresenter.setModel(model);


				return;
			}
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		headerDetails.setTypeface(TypefaceUtil.semibold());
	}

	private void updateHeader(OpenTradeModel model) {
		headerDetails.setText(String.format("%s %s %s",
				model.getDir(),
				StringFormatUtil.formatAmount(model.getVolume()),
				model.getSymbol()));
	}

	private void initRecyclerView() {
		recyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		openTradeDetailsAdapter = new OpenTradeDetailsAdapter();
		openTradeDetailsAdapter.setHasStableIds(true);
		recyclerView.setAdapter(openTradeDetailsAdapter);
	}

	@Override
	public void setOpenTrades(List<OpenTradeProviderModel> trades) {
		openTradeDetailsAdapter.setTrades(trades);
	}

	@Override
	public void removeOpenTrade(int position, boolean isListEmpty) {
		openTradeDetailsAdapter.deleteTrade(position);
		if (isListEmpty)
			finishActivity();
	}

	@Override
	public void askCloseTrade(UUID programId, String symbol, Double volume) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(String.format(Locale.getDefault(), getString(R.string.close_trade_template),
				StringFormatUtil.formatAmount(volume),
				symbol));
		builder.setPositiveButton(getString(R.string.close_order), (dialogInterface, i) -> openTradeDetailsPresenter.closeTrade(programId));
		builder.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.cancel());

		AlertDialog dialog = builder.create();
		dialog.show();

		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
		dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ThemeUtil.getColorByAttrId(this, R.attr.colorAccent));
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, recyclerView);
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}

	@Override
	public void showProgress(Boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}
}