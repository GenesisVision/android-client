package vision.genesis.clientapp.feature.main.about_levels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.LevelInfo;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.common.currency.SelectCurrencyFragment;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */

public class AboutLevelsActivity extends BaseSwipeBackActivity implements AboutLevelsView
{
	private static final String EXTRA_CURRENCY = "extra_currency";

	public static void startWith(Activity activity, String currency) {
		Intent intent = new Intent(activity.getApplicationContext(), AboutLevelsActivity.class);
		intent.putExtra(EXTRA_CURRENCY, currency);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.currency)
	public TextView currency;

	@BindView(R.id.group_levels)
	public ViewGroup levelsGroup;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.levels_title)
	public TextView levelsTitle;

	@BindView(R.id.formula)
	public TextView formula;

	@BindView(R.id.point_1)
	public TextView point1;

	@BindView(R.id.title_1)
	public TextView title1;

	@BindView(R.id.point_2)
	public TextView point2;

	@BindView(R.id.title_2)
	public TextView title2;

	@BindView(R.id.point_3)
	public TextView point3;

	@BindView(R.id.title_3)
	public TextView title3;

	@BindView(R.id.notes)
	public TextView notes;

	@BindView(R.id.more)
	public TextView more;

	@BindView(R.id.level_1)
	public TextView level1;

	@BindView(R.id.level_2)
	public TextView level2;

	@BindView(R.id.level_3)
	public TextView level3;

	@BindView(R.id.level_4)
	public TextView level4;

	@BindView(R.id.level_5)
	public TextView level5;

	@BindView(R.id.level_6)
	public TextView level6;

	@BindView(R.id.level_7)
	public TextView level7;

	@BindView(R.id.limit_1)
	public TextView limit1;

	@BindView(R.id.limit_2)
	public TextView limit2;

	@BindView(R.id.limit_3)
	public TextView limit3;

	@BindView(R.id.limit_4)
	public TextView limit4;

	@BindView(R.id.limit_5)
	public TextView limit5;

	@BindView(R.id.limit_6)
	public TextView limit6;

	@BindView(R.id.limit_7)
	public TextView limit7;

	@InjectPresenter
	AboutLevelsPresenter aboutLevelsPresenter;

	private String selectedCurrency = "";

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.group_currency)
	public void onCurrencyClicked() {
		SelectCurrencyFragment fragment = SelectCurrencyFragment.with(selectedCurrency);
		fragment.setListener(aboutLevelsPresenter);
		fragment.show(getSupportFragmentManager(), fragment.getTag());

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_about_levels);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			selectedCurrency = getIntent().getExtras().getString(EXTRA_CURRENCY, "GVT");
			aboutLevelsPresenter.onCurrencyChanged(CurrencyEnum.fromValue(selectedCurrency));

			setFonts();
			setMore();
		}
		else {
			Timber.e("Passed empty model to AboutLevelsActivity");
			onBackPressed();
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
		levelsTitle.setTypeface(TypefaceUtil.semibold());
		formula.setTypeface(TypefaceUtil.bold());
		currency.setTypeface(TypefaceUtil.semibold());

		point1.setTypeface(TypefaceUtil.semibold());
		point2.setTypeface(TypefaceUtil.semibold());
		point3.setTypeface(TypefaceUtil.semibold());
		title1.setTypeface(TypefaceUtil.semibold());
		title2.setTypeface(TypefaceUtil.semibold());
		title3.setTypeface(TypefaceUtil.semibold());
		notes.setTypeface(TypefaceUtil.semibold());

		level1.setTypeface(TypefaceUtil.semibold());
		level2.setTypeface(TypefaceUtil.semibold());
		level3.setTypeface(TypefaceUtil.semibold());
		level4.setTypeface(TypefaceUtil.semibold());
		level5.setTypeface(TypefaceUtil.semibold());
		level6.setTypeface(TypefaceUtil.semibold());
		level7.setTypeface(TypefaceUtil.semibold());

		limit1.setTypeface(TypefaceUtil.semibold());
		limit2.setTypeface(TypefaceUtil.semibold());
		limit3.setTypeface(TypefaceUtil.semibold());
		limit4.setTypeface(TypefaceUtil.semibold());
		limit5.setTypeface(TypefaceUtil.semibold());
		limit6.setTypeface(TypefaceUtil.semibold());
		limit7.setTypeface(TypefaceUtil.semibold());
	}

	private void setMore() {
		String moreString = getString(R.string.about_levels_more);
		String articleString = getString(R.string.about_levels_article);
		String url = getString(R.string.about_levels_more_link);
		String completeString = moreString.concat(" ").concat(articleString);

		StringFormatUtil.setClickableSpan(this, more, completeString, articleString, url, false);
	}

	@Override
	public void setSelectedCurrency(String value) {
		selectedCurrency = value;
		this.currency.setText(value);
	}

	@Override
	public void setLevelsInfo(List<LevelInfo> levelsInfo) {
		String limitText;
		for (LevelInfo info : levelsInfo) {
			limitText = String.format(Locale.getDefault(), "%s %s",
					StringFormatUtil.formatAmount(info.getInvestmentLimit(), 0, 2), selectedCurrency);
			switch (info.getLevel()) {
				case 1:
					limit1.setText(limitText);
					break;
				case 2:
					limit2.setText(limitText);
					break;
				case 3:
					limit3.setText(limitText);
					break;
				case 4:
					limit4.setText(limitText);
					break;
				case 5:
					limit5.setText(limitText);
					break;
				case 6:
					limit6.setText(limitText);
					break;
				case 7:
					limit7.setText(limitText);
					break;
			}
		}
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
		levelsGroup.setVisibility(!show ? View.VISIBLE : View.INVISIBLE);
	}

	public void showSnackbarMessage(String message) {
		showSnackbar(message, title);
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