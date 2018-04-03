package vision.genesis.clientapp.feature.main.program.details.description;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseSwipeBackActivity;
import vision.genesis.clientapp.model.ProgramDescriptionModel;
import vision.genesis.clientapp.ui.AvatarView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 22/03/2018.
 */

public class ProgramDescriptionActivity extends BaseSwipeBackActivity implements ProgramDescriptionView
{
	private static String EXTRA_MODEL = "extra_model";

	public static void startWith(Activity activity, ProgramDescriptionModel model) {
		Intent intent = new Intent(activity.getApplicationContext(), ProgramDescriptionActivity.class);
		intent.putExtra(EXTRA_MODEL, model);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_slide_from_top, R.anim.hold);
	}

	@BindView(R.id.program_logo)
	public AvatarView programLogo;

	@BindView(R.id.program_name)
	public TextView programName;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.description)
	public TextView description;

	@BindView(R.id.program_details_label)
	public TextView programDetailsLabel;

	@InjectPresenter
	ProgramDescriptionPresenter programDescriptionPresenter;

	private ProgramDescriptionModel model;

	@OnClick(R.id.group_program_details)
	public void onProgramDetailsClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setEdgeTrackingMode(SwipeBackLayout.EDGE_BOTTOM);

		setContentView(R.layout.activity_program_description);

		ButterKnife.bind(this);

		setFonts();

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_MODEL);
			setData(model);
		}
		else {
			Timber.e("Passed empty ProgramDescriptionModel to ProgramDescriptionActivity");
			onBackPressed();
		}
	}
	private void setFonts() {
		programName.setTypeface(TypefaceUtil.bold());
		programDetailsLabel.setTypeface(TypefaceUtil.bold());
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}


	public void setData(ProgramDescriptionModel model) {
		programLogo.setImage(model.programLogo, 500, 500);
		programLogo.setLevel(model.programLevel);

		programName.setText(model.programName);
		managerName.setText(String.format(Locale.getDefault(), "%s %s", getResources().getString(R.string.by), model.managerName));

		description.setText(model.programDescription);
	}

	@Override
	public void finishActivity() {
		scrollToFinishActivity();
	}
}
