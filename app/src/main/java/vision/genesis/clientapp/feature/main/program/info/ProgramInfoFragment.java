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

import java.util.Locale;
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
import vision.genesis.clientapp.feature.main.program.invest.InvestProgramActivity;
import vision.genesis.clientapp.model.ProgramRequest;
import vision.genesis.clientapp.ui.PeriodLeftDetailsView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

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

	@BindView(R.id.group_your_investment)
	public ViewGroup yourInvestmentGroup;

	@BindView(R.id.label_your_investment)
	public TextView labelYourInvestment;

	@BindView(R.id.invested)
	public TextView invested;

	@BindView(R.id.invested_label)
	public TextView investedLabel;

	@BindView(R.id.value)
	public TextView value;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.profit_label)
	public TextView profitLabel;

	@BindView(R.id.button_withdraw)
	public PrimaryButton withdrawButton;

	@BindView(R.id.label_invest_now)
	public TextView labelInvestNow;

	@BindView(R.id.available_to_invest)
	public TextView availableToInvest;

	@BindView(R.id.entry_fee)
	public TextView entryFee;

	@BindView(R.id.label_entry_fee)
	public TextView entryFeeLabel;

	@BindView(R.id.success_fee)
	public TextView successFee;

	@BindView(R.id.invest_info)
	public TextView investInfo;

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

	@OnClick(R.id.button_invest)
	public void onInvestClicked() {
		if (programDetails == null || getActivity() == null)
			return;
		if (programDetails.getAvailableInvestment() == 0) {
			showInvestmentNotAvailableDialog();
			return;
		}

		ProgramRequest request = new ProgramRequest();

		request.setProgramId(programDetails.getId());
		request.setProgramLogo(programDetails.getLogo());
		request.setLevel(programDetails.getLevel());
		request.setProgramName(programDetails.getTitle());
		request.setManagerName(programDetails.getManager().getUsername());

		InvestProgramActivity.startWith(getActivity(), request);
	}
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

		withdrawButton.setEmpty();
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
		labelYourInvestment.setTypeface(TypefaceUtil.semibold());
		invested.setTypeface(TypefaceUtil.semibold());
		value.setTypeface(TypefaceUtil.semibold());
		profit.setTypeface(TypefaceUtil.semibold());

		labelInvestNow.setTypeface(TypefaceUtil.semibold());
		availableToInvest.setTypeface(TypefaceUtil.semibold());
		entryFee.setTypeface(TypefaceUtil.semibold());
		successFee.setTypeface(TypefaceUtil.semibold());

		investedLabel.setText(investedLabel.getText().toString().toLowerCase());
		profitLabel.setText(profitLabel.getText().toString().toLowerCase());
		entryFeeLabel.setText(entryFeeLabel.getText().toString().toLowerCase());
	}

	@Override
	public void setProgramDetails(ProgramDetailsFull programDetails) {
		this.programDetails = programDetails;

		scrollView.setVisibility(View.VISIBLE);

		managerAvatar.setImageURI(ImageUtils.getImageUri(programDetails.getManager().getAvatar()));
		managerName.setText(programDetails.getManager().getUsername());
		managerDate.setText(DateTimeUtil.formatShortDate(programDetails.getManager().getRegistrationDate()));

		strategy.setText(programDetails.getDescription());
		new Handler().postDelayed(() -> {
			if (strategyShadow != null && strategy != null)
				strategyShadow.setVisibility(strategy.getHeight() < strategyMaxHeight ? View.INVISIBLE : View.VISIBLE);
		}, 300);

		periodView.setData(programDetails.getPeriodDuration(), programDetails.getPeriodStarts(), programDetails.getPeriodEnds(), true, true);

		if (programDetails.getPersonalProgramDetails().isIsInvested()) {
			yourInvestmentGroup.setVisibility(View.VISIBLE);
//		invested.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.getShortenedAmount(programDetails.getPersonalProgramDetails().getInvested()).toString()));
			invested.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(programDetails.getPersonalProgramDetails().getValue(), ProgramDetailsFull.CurrencyEnum.GVT.toString())));
			value.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.formatCurrencyAmount(programDetails.getPersonalProgramDetails().getValue(), ProgramDetailsFull.CurrencyEnum.GVT.toString())));
			profit.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(programDetails.getPersonalProgramDetails().getProfit(), 0, 4)));
			profit.setTextColor(ThemeUtil.getColorByAttrId(getContext(), programDetails.getPersonalProgramDetails().getProfit() < 0 ? R.attr.colorRed : R.attr.colorGreen));
		}
		availableToInvest.setText(String.format(Locale.getDefault(), "%s GVT", StringFormatUtil.getShortenedAmount(programDetails.getAvailableInvestment()).toString()));
		entryFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(programDetails.getEntryFee(), 0, 4)));
		successFee.setText(String.format(Locale.getDefault(), "%s%%", StringFormatUtil.formatAmount(programDetails.getSuccessFee(), 0, 4)));

		investInfo.setText(String.format(Locale.getDefault(), getString(R.string.request_info_template), DateTimeUtil.formatShortDateTime(programDetails.getPeriodEnds())));
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