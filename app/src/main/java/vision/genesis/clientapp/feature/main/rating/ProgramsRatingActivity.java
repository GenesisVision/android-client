package vision.genesis.clientapp.feature.main.rating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.AssetFacet;
import io.swagger.client.model.LevelInfo;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.about_levels.AboutLevelsActivity;
import vision.genesis.clientapp.model.FacetModel;
import vision.genesis.clientapp.ui.LevelView;
import vision.genesis.clientapp.ui.NonSwipeableViewPager;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2019.
 */

public class ProgramsRatingActivity extends BaseSwipeBackActivity implements ProgramsRatingView
{
	private static final String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, AssetFacet facet) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramsRatingActivity.class);
		FacetModel model = new FacetModel(facet.getId(), facet.getTitle(), facet.getTimeframe().toString(), facet.getSorting());
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.slide_from_right, R.anim.hold);
	}

	@BindView(R.id.scrollview)
	public HorizontalScrollView scrollview;

	@BindView(R.id.group_levels)
	public LinearLayout levelsGroup;

	@BindView(R.id.view_pager_programs_rating)
	public NonSwipeableViewPager viewPager;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@InjectPresenter
	ProgramsRatingPresenter presenter;

	private List<LevelView> levelViews = new ArrayList<>();

	private LevelView selectedLevel;

	private FacetModel facetModel;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		finishActivity();
	}

	@OnClick(R.id.button_info)
	public void onInfoClicked() {
		AboutLevelsActivity.startWith(this, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_programs_rating);

		ButterKnife.bind(this);

		viewPager.setAllowSwipe(false);

		if (getIntent().getExtras() != null) {
			facetModel = Objects.requireNonNull(getIntent().getExtras().getParcelable(EXTRA_MODEL));
			presenter.setData(facetModel);

			return;
		}
		Timber.e("Passed empty model to %s", getClass().getSimpleName());
		onBackPressed();
	}

	@Override
	public void setData(List<LevelInfo> levelData) {
		initLevels(levelData);
		initViewPager(levelData);
	}

	@Override
	public void showLevel(Integer level) {
		if (selectedLevel != null) {
			if (selectedLevel.getLevel() == level) {
				return;
			}
			selectedLevel.setSelected(false);
		}
		viewPager.setCurrentItem(level);
		selectedLevel = levelViews.get(level - 1);
		selectedLevel.setSelected(true);
	}

	@Override
	public void showAllLevels() {
		if (selectedLevel != null) {
			selectedLevel.setSelected(false);
			selectedLevel = null;
		}
		viewPager.setCurrentItem(0);
	}

	@Override
	public void showProgress(boolean show) {
		this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		if (!show) {
			this.scrollview.setVisibility(View.VISIBLE);
			this.viewPager.setVisibility(View.VISIBLE);
		}
	}

	private void initLevels(List<LevelInfo> levelData) {
		levelsGroup.removeAllViews();
		levelViews.clear();

		for (LevelInfo info : levelData) {
			LevelView view = new LevelView(this);
			view.setLevel(info.getLevel(), getLevelColor(info.getLevel()));
			levelsGroup.addView(view);
			levelViews.add(view);
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
			lp.setMargins(TypedValueFormatter.toDp(20), 0, 0, 0);
			view.setLayoutParams(lp);
			view.setOnClickListener(view1 -> presenter.onLevelClicked(info.getLevel()));
		}
	}

	private int getLevelColor(Integer level) {
		int colorResId = R.color.level1;
		switch (level) {
			case 1:
				colorResId = R.color.level1;
				break;
			case 2:
				colorResId = R.color.level2;
				break;
			case 3:
				colorResId = R.color.level3;
				break;
			case 4:
				colorResId = R.color.level4;
				break;
			case 5:
				colorResId = R.color.level5;
				break;
			case 6:
				colorResId = R.color.level6;
				break;
			case 7:
				colorResId = R.color.level7;
				break;
		}
		return colorResId;
	}

	private void initViewPager(List<LevelInfo> levelData) {
		ProgramsRatingPagerAdapter pagerAdapter = new ProgramsRatingPagerAdapter(getSupportFragmentManager(), levelData, facetModel);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOffscreenPageLimit(2);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, viewPager);
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