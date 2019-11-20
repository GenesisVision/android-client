package vision.genesis.clientapp.feature.main.fund.add_asset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.PlatformAsset;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/10/2019.
 */

public class AddAssetActivity extends BaseSwipeBackActivity implements AddAssetView
{
	private static final String EXTRA_NEED_FINISH = "extra_need_finish";

	public static void startWith(Activity activity, boolean finishAfterAssetSelected) {
		Intent intent = new Intent(activity.getApplicationContext(), AddAssetActivity.class);
		intent.putExtra(EXTRA_NEED_FINISH, finishAfterAssetSelected);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.edittext_search)
	public EditText searchEditText;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.text_no_assets)
	public TextView noAssetsText;

	@InjectPresenter
	AddAssetPresenter presenter;

	private AddAssetListAdapter adapter;

	@OnClick(R.id.button_back)
	public void onBackClicked() {
		onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(ThemeUtil.getCurrentThemeResource());
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_asset);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null) {
			presenter.setNeedFinish(getIntent().getExtras().getBoolean(EXTRA_NEED_FINISH, true));

			setFonts();
			initRecyclerView();
			setTextListener();

			return;
		}
		Timber.e("Passed empty data to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setNestedScrollingEnabled(false);

		adapter = new AddAssetListAdapter();
		recyclerView.setAdapter(adapter);
	}

	private void setTextListener() {
		RxTextView.textChanges(searchEditText)
				.subscribe(charSequence -> presenter.onMaskChanged(charSequence.toString()));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void setAssets(List<PlatformAsset> assets) {
		noAssetsText.setVisibility(assets.isEmpty() ? View.VISIBLE : View.GONE);
		recyclerView.setVisibility(assets.isEmpty() ? View.GONE : View.VISIBLE);
		adapter.setAssets(assets);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
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
}
