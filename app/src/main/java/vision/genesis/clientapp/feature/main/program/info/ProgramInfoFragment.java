package vision.genesis.clientapp.feature.main.program.info;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.UUID;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ProgramDetailsFull;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.program.ProgramDetailsPagerAdapter;
import vision.genesis.clientapp.ui.PeriodLeftDetailsView;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/04/2018.
 */

public class ProgramInfoFragment extends BaseFragment implements ProgramInfoView, ProgramDetailsPagerAdapter.OnPageVisibilityChanged
{
	private static String EXTRA_PROGRAM_ID = "extra_program_id";

	public static ProgramInfoFragment with(UUID programId) {
		ProgramInfoFragment programInfoFragment = new ProgramInfoFragment();
		Bundle arguments = new Bundle(1);
		arguments.putSerializable(EXTRA_PROGRAM_ID, programId);
		programInfoFragment.setArguments(arguments);
		return programInfoFragment;
	}

	@BindView(R.id.scrollview)
	public NestedScrollView scrollView;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.manager_avatar)
	public SimpleDraweeView managerAvatar;

	@BindView(R.id.manager_name)
	public TextView managerName;

	@BindView(R.id.manager_date)
	public TextView managerDate;

	@BindView(R.id.label_strategy)
	public TextView labelStrategy;

	@BindView(R.id.strategy)
	public TextView strategy;

	@BindView(R.id.strategy_shadow)
	public View strategyShadow;

	@BindView(R.id.label_period)
	public TextView labelPeriod;

	@BindView(R.id.view_period)
	public PeriodLeftDetailsView periodView;

	@InjectPresenter
	public ProgramInfoPresenter programInfoPresenter;

	@BindDimen(R.dimen.program_info_strategy_max_height)
	public int strategyMaxHeight;

	private UUID programId;

	private ProgramDetailsFull programDetails;

	private Unbinder unbinder;

	@OnClick(R.id.group_manager)
	public void onManagerClicked() {

	}

	@OnClick(R.id.strategy)
	public void onStrategyClicked() {
		if (strategy.getHeight() == strategyMaxHeight) {
			expandStrategy();
		}
		else if (strategy.getHeight() > strategyMaxHeight) {
			collapseStrategy();
		}
	}

	private void expandStrategy() {
		ValueAnimator animator = ValueAnimator.ofInt(strategy.getMaxHeight(), 10000);
		animator.addUpdateListener(animation -> strategy.setMaxHeight((int) animator.getAnimatedValue()));
		animator.setDuration(400);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		strategyShadow.setVisibility(View.INVISIBLE);
	}

	private void collapseStrategy() {
		ValueAnimator animator = ValueAnimator.ofInt(strategy.getHeight(), strategyMaxHeight);
		animator.addUpdateListener(animation -> {
			strategy.setHeight((int) animator.getAnimatedValue());
			if (!animation.isRunning())
				strategy.setMaxHeight(strategyMaxHeight);
		});
		animator.setDuration(200);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();

		strategyShadow.setVisibility(View.VISIBLE);
	}

//	@OnClick(R.id.button_invest)
//	public void onInvestClicked() {
//		if (programDetails == null || getActivity() == null)
//			return;
//		if (programDetails.getAvailableInvestment() == 0) {
//			showInvestmentNotAvailableDialog();
//			return;
//		}
//		ProgramRequest request = new ProgramRequest();
//		request.programId = programDetails.getId();
//		request.programName = programDetails.getTitle();
//		request.programCurrency = programDetails.getCurrency().toString();
//		request.available = programDetails.getAvailableInvestment();
//		InvestProgramActivity.startWith(getActivity(), request);
//	}
//
//	@OnClick(R.id.button_withdraw)
//	public void onWithdrawClicked() {
//		if (programDetails == null || getActivity() == null)
//			return;
//		ProgramRequest request = new ProgramRequest();
//		request.programId = programDetails.getId();
//		request.programName = programDetails.getTitle();
//		request.available = programDetails.getInvestedTokens();
//		request.tokenPrice = programDetails.getToken().getInitialPrice();
//		WithdrawProgramActivity.startWith(getActivity(), request);
//	}
//
//	@OnClick(R.id.button_requests)
//	public void onRequestsClicked() {
//		if (programDetails == null || getActivity() == null)
//			return;
//		RequestsActivity.startWith(getActivity(), programDetails.getId());
//	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_program_info, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		programId = (UUID) getArguments().getSerializable(EXTRA_PROGRAM_ID);
		programInfoPresenter.setProgramId(programId);

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
	public void setProgramDetails(ProgramDetailsFull programDetails) {
		this.programDetails = programDetails;

		managerAvatar.setImageURI(ImageUtils.getImageUri(programDetails.getManager().getAvatar()));
		managerName.setText(programDetails.getManager().getUsername());
		managerDate.setText(DateTimeUtil.formatShortDate(programDetails.getManager().getRegistrationDate()));

		strategy.setText(programDetails.getDescription());
		new Handler().postDelayed(() -> strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE), 300);

		periodView.setData(programDetails.getPeriodDuration(), programDetails.getPeriodStarts(), programDetails.getPeriodEnds());

		scrollView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showInvestWithdrawButtons(boolean show) {
//		buttonsGroup.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showProgress(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	@Override
	public void pagerShow() {
		if (programInfoPresenter != null)
			programInfoPresenter.onShow();
	}

	@Override
	public void pagerHide() {
	}

	private void showInvestmentNotAvailableDialog() {
		showMessageDialog(getString(R.string.no_available_tokens_to_purchase));
	}
}