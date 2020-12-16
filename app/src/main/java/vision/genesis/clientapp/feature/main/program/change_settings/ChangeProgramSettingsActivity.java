package vision.genesis.clientapp.feature.main.program.change_settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ProgramUpdate;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.program.create.settings.ProgramSettingsFragment;
import vision.genesis.clientapp.model.ProgramSettingsModel;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 10/12/2019.
 */

public class ChangeProgramSettingsActivity extends BaseSwipeBackActivity implements ChangeProgramSettingsView
{
	private static final String EXTRA_ASSET_ID = "extra_asset_id";

	private static final String EXTRA_CURRENCY = "extra_currency";

	private static final String EXTRA_MODEL = "extra_model";

	private static final String EXTRA_IS_EXCHANGE = "extra_is_exchange";

	public static void startFrom(Activity activity, UUID assetId, String currency, ProgramUpdate model, Boolean isExchange) {
		Intent intent = new Intent(activity.getApplicationContext(), ChangeProgramSettingsActivity.class);
		intent.putExtra(EXTRA_ASSET_ID, assetId);
		intent.putExtra(EXTRA_CURRENCY, currency);
		intent.putExtra(EXTRA_MODEL, model);
		intent.putExtra(EXTRA_IS_EXCHANGE, isExchange);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar_group)
	public ViewGroup progressBarGroup;

	@InjectPresenter
	ChangeProgramSettingsPresenter presenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_program_settings);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID assetId = (UUID) getIntent().getExtras().getSerializable(EXTRA_ASSET_ID);
			String currency = getIntent().getExtras().getString(EXTRA_CURRENCY);
			ProgramUpdate model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			Boolean isExchange = getIntent().getExtras().getBoolean(EXTRA_IS_EXCHANGE);
			if (model != null) {
				presenter.setData(assetId, model);

				if (savedInstanceState == null) {
					getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.content, ProgramSettingsFragment.with(new ProgramSettingsModel(false, "", "", false,
									getString(R.string.update), currency,
									null, model.getStopOutLevel(), model.getInvestmentLimit(),
									model.getEntryFee(), model.getSuccessFee(), model.getTradesDelay(),
									isExchange, false, model.isIsProcessingRealTime(), model.getHourProcessing())))
							.disallowAddToBackStack()
							.commit();
				}

				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void showProgress(boolean show) {
		progressBarGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.slide_to_right);
	}
}