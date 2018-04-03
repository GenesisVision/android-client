package vision.genesis.clientapp.feature.main.program.history;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.TransactionsFilter;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.feature.main.wallet.transactions.TransactionsFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/03/2018.
 */

public class ProgramHistoryActivity extends BaseSwipeBackActivity implements ProgramHistoryView
{
	private static final String EXTRA_PROGRAM_ID = "extra_program_id";

	public static void startWith(Activity activity, UUID programId) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramHistoryActivity.class);
		intent.putExtra(EXTRA_PROGRAM_ID, programId);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_right, R.anim.hold);
	}

	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@InjectPresenter
	ProgramHistoryPresenter programHistoryPresenter;

	private TransactionsFragment transactionsFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_program_history);

		ButterKnife.bind(this);

		initToolbar();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			UUID programId = (UUID) getIntent().getExtras().getSerializable(EXTRA_PROGRAM_ID);
			initTransactionsFragment(programId);
		}
		else {
			Timber.e("Passed empty program to ProgramHistoryActivity");
			onBackPressed();
		}
	}

	private void initTransactionsFragment(UUID programId) {
		transactionsFragment = TransactionsFragment.with(TransactionsFilter.TypeEnum.ALL, programId);

		getSupportFragmentManager()
				.beginTransaction()
				.setCustomAnimations(R.anim.hold, R.anim.hold)
				.add(R.id.content, transactionsFragment)
				.commit();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.history));
		toolbar.addLeftButton(R.drawable.back_arrow, this::onBackPressed);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	@Override
	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.activity_slide_to_right);
	}
}