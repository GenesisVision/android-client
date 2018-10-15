package vision.genesis.clientapp.feature.main.manager.info;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ManagerProfileDetails;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/10/2018.
 */

public class ManagerInfoFragment extends BaseFragment implements ManagerInfoView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_MANAGER_ID = "extra_manager_id";

	public static ManagerInfoFragment with(UUID managerId) {
		ManagerInfoFragment managerInfoFragment = new ManagerInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_MANAGER_ID, managerId);
		managerInfoFragment.setArguments(arguments);
		return managerInfoFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.assets_type)
	public TextView assetsType;

	@BindView(R.id.about)
	public TextView about;

	@BindView(R.id.about_shadow)
	public View aboutShadow;

	@InjectPresenter
	public ManagerInfoPresenter managerInfoPresenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private UUID managerId;

	private ManagerProfileDetails managerDetails;

	private Unbinder unbinder;

	@OnClick(R.id.about)
	public void onAboutClicked() {
		if (about.getHeight() == strategyMaxHeight) {
			expandAbout();
		}
		else if (about.getHeight() > strategyMaxHeight) {
			collapseAbout();
		}
	}

	private void expandAbout() {
		ValueAnimator animator = ValueAnimator.ofInt(about.getMaxHeight(), 10000);
		animator.addUpdateListener(animation -> about.setMaxHeight((int) animator.getAnimatedValue()));
		animator.setDuration(400);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		aboutShadow.setVisibility(View.INVISIBLE);
	}

	private void collapseAbout() {
		ValueAnimator animator = ValueAnimator.ofInt(about.getHeight(), strategyMaxHeight);
		animator.addUpdateListener(animation -> {
			about.setHeight((int) animator.getAnimatedValue());
			if (!animation.isRunning())
				about.setMaxHeight(strategyMaxHeight);
		});
		animator.setDuration(200);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		aboutShadow.setVisibility(View.VISIBLE);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_manager_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		managerId = (UUID) getArguments().getSerializable(EXTRA_MANAGER_ID);
		managerInfoPresenter.setManagerId(managerId);

		setFonts();
	}

	@Override
	public void onDestroyView() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		super.onDestroyView();
	}

	private void setFonts() {
	}

	@Override
	public void setManagerDetails(ManagerProfileDetails managerDetails) {
		this.managerDetails = managerDetails;

		scrollView.setVisibility(View.VISIBLE);

		String assetsString = "";
		int index = 0;
		List<Integer> spanIndexes = new ArrayList<>();
		for (String asset : managerDetails.getManagerProfile().getAssets()) {
			assetsString = assetsString.concat(asset);
			if (index < managerDetails.getManagerProfile().getAssets().size() - 1) {
				assetsString = assetsString.concat("  |  ");
				spanIndexes.add(assetsString.length() - 3);
			}
			index++;
		}
		Spannable spannableString = new SpannableString(assetsString);
		for (Integer spanIndex : spanIndexes) {
			spannableString.setSpan(new ForegroundColorSpan(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary)), spanIndex, spanIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		assetsType.setText(spannableString);

		about.setText(managerDetails.getManagerProfile().getAbout());
		new Handler().postDelayed(() -> {
			if (aboutShadow != null && about != null)
				aboutShadow.setVisibility(about.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
		}, 300);

	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void pagerShow() {
		if (managerInfoPresenter != null)
			managerInfoPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}
}