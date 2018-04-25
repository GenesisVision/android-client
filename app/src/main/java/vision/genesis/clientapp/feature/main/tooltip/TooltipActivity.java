package vision.genesis.clientapp.feature.main.tooltip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.TooltipModel;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class TooltipActivity extends MvpAppCompatActivity
{
	private static final float PADDING = 8;

	private static String EXTRA_TOOLTIP_MODEL = "extra_tooltip_model";

	public static void startWith(Activity activity, TooltipModel tooltipModel) {
		Intent intent = new Intent(activity.getApplicationContext(), TooltipActivity.class);
		intent.putExtra(EXTRA_TOOLTIP_MODEL, tooltipModel);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.hold, R.anim.hold);
	}

	@BindView(R.id.root)
	public View root;

	@BindView(R.id.group_tooltip)
	public ViewGroup tooltipGroup;

	@BindView(R.id.text_tooltip)
	public TextView tooltipText;

	private TooltipModel model;

	@OnClick(R.id.root)
	public void onRootClicked() {
		finishActivity();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_tooltip);

		ButterKnife.bind(this);

		if (getIntent().getExtras() != null && !getIntent().getExtras().isEmpty()) {
			model = getIntent().getExtras().getParcelable(EXTRA_TOOLTIP_MODEL);
			if (model != null) {
				tooltipText.setText(model.tooltipText);
			}
			else {
				onBackPressed();
			}
		}
		else {
			Timber.e("Passed empty model to TooltipActivity");
			onBackPressed();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		new Handler().postDelayed(this::showTooltip, 100);
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	private void showTooltip() {
		if (model == null) {
			finishActivity();
			return;
		}

		float newY = model.bottomY;
		if (newY + tooltipText.getHeight() > root.getHeight())
			newY = model.topY - PADDING - tooltipText.getHeight();
		tooltipGroup.setY(newY);
		tooltipGroup.invalidate();

		showTooltipWithAnimation();
	}

	private void showTooltipWithAnimation() {
		Animation tooltipAnimation = AnimationUtils.loadAnimation(this, R.anim.tooltip_fade_in);
		tooltipAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		tooltipText.startAnimation(tooltipAnimation);
		tooltipText.setVisibility(View.VISIBLE);
	}

	public void finishActivity() {
		finish();
		overridePendingTransition(R.anim.hold, R.anim.fragment_fade_out);
	}
}
