package vision.genesis.clientapp.feature.main.facet.follows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetFacet;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.follows_list.FollowsListFragment;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.model.FacetModel;
import vision.genesis.clientapp.model.SortingEnum;
import vision.genesis.clientapp.model.filter.ProgramsFilter;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/12/2019.
 */

public class FollowsFacetActivity extends BaseSwipeBackActivity implements MvpView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, AssetFacet facet) {
		Intent intent = new Intent(activity.getApplicationContext(), FollowsFacetActivity.class);
		FacetModel model = new FacetModel(facet.getId(), facet.getTitle(), facet.getTimeframe().toString(), facet.getSorting());
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_facet);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			FacetModel model = Objects.requireNonNull(getIntent().getExtras().getParcelable(EXTRA_MODEL));

			this.title.setText(model.getTitle());

			setFonts();

			if (savedInstanceState == null) {
				ProgramsFilter filter = new ProgramsFilter();
				filter.setFacetId(model.getId());
				filter.setDateRange(DateRange.createFromString(model.getTimeFrame()));
				filter.setSorting(SortingEnum.fromValue(model.getSorting()));
				Bundle data = new Bundle();
				data.putParcelable(FollowsListFragment.EXTRA_FILTER, filter);

				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.content, FollowsListFragment.with(FollowsListFragment.LOCATION_FACET, data))
						.disallowAddToBackStack()
						.commit();
			}
		}
		else {
			Timber.e("Passed empty model to %s", getClass().getSimpleName());
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