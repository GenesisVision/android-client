package vision.genesis.clientapp.feature.main.facet.funds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.FundFacet;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.funds_list.FundsListFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.FacetModel;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 11/04/2019.
 */

public class FundsFacetActivity extends BaseSwipeBackActivity implements FundsFacetView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, FundFacet facet) {
		Intent intent = new Intent(activity.getApplicationContext(), FundsFacetActivity.class);
		FacetModel model = new FacetModel(facet.getId(), facet.getTitle(), facet.getTimeframe().toString(), facet.getSorting().getValue());
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@InjectPresenter
	FundsFacetPresenter fundsFacetPresenter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_facet);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			FacetModel model = Objects.requireNonNull(getIntent().getExtras().getParcelable(EXTRA_MODEL));

			this.title.setText(model.getTitle());

			setFonts();

			if (savedInstanceState == null) {
				ProgramsFilter filter = new ProgramsFilter();
				filter.setFacetId(model.getId());
				filter.setDateRange(DateRange.createFromString(model.getTimeFrame()));

				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content, FundsListFragment.with(FundsListFragment.LOCATION_FACET, filter))
						.disallowAddToBackStack()
						.commit();
			}
		}
		else {
			Timber.e("Passed empty model to FundsFacetActivity");
			onBackPressed();
		}
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
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