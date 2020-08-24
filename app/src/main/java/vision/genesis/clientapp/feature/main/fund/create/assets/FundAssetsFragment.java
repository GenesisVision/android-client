package vision.genesis.clientapp.feature.main.fund.create.assets;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.PlatformAsset;
import timber.log.Timber;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.feature.main.fund.add_asset.AddAssetActivity;
import vision.genesis.clientapp.feature.main.fund.create.assets.share.AssetShareDialogFragment;
import vision.genesis.clientapp.model.FundAssetsModel;
import vision.genesis.clientapp.ui.CreateFundAssetView;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 14/10/2019.
 */

public class FundAssetsFragment extends BaseFragment implements FundAssetsView
{
	private static String EXTRA_MODEL = "extra_model";

	public static FundAssetsFragment with(FundAssetsModel model) {
		FundAssetsFragment fragment = new FundAssetsFragment();
		Bundle arguments = new Bundle(1);
		arguments.putParcelable(EXTRA_MODEL, model);
		fragment.setArguments(arguments);
		return fragment;
	}

	@BindView(R.id.group_step)
	public ViewGroup stepGroup;

	@BindView(R.id.step_number)
	public TextView stepNumber;

	@BindView(R.id.step_title)
	public TextView stepTitle;

	@BindView(R.id.assets_notification)
	public TextView assetsNotification;

	@BindView(R.id.long_tap_tip)
	public TextView longTapTip;

	@BindView(R.id.group_line)
	public LinearLayout lineGroup;

	@BindView(R.id.remaining_allocation)
	public View remainingAllocationLine;

	@BindView(R.id.group_remaining_share)
	public ViewGroup remainingShareGroup;

	@BindView(R.id.text_remaining_share)
	public TextView remainingShareText;

	@BindView(R.id.text_selected_asset)
	public TextView selectedAssetText;

	@BindView(R.id.flex_box)
	public FlexboxLayout flexBox;

	@BindView(R.id.button_add_asset)
	public PrimaryButton addAssetButton;

	@BindView(R.id.button_confirm)
	public PrimaryButton confirmButton;

	@InjectPresenter
	public FundAssetsPresenter presenter;

	double previousRemainingShare = 100f;

	private Unbinder unbinder;

	private List<View> lineViews = new ArrayList<>();

	private List<CreateFundAssetView> assetViews = new ArrayList<>();

	private long removeAssetAnimationDuration = 300;

	private CreateFundAssetView selectedAssetView;

	@OnClick(R.id.button_add_asset)
	public void onAddAssetClicked() {
		if (getActivity() != null) {
			AddAssetActivity.startWith(getActivity(), false);
		}
	}

	@OnClick(R.id.button_confirm)
	public void onConfirmClicked() {
		presenter.onConfirmClicked();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fund_assets, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);

		setFonts();

		addAssetButton.setEmpty();

		if (getArguments() != null) {
			FundAssetsModel model = getArguments().getParcelable(EXTRA_MODEL);
			if (model != null) {
				updateView(model);
				presenter.setModel(model);
				return;
			}
		}
		Timber.e("Passed empty arguments to %s", getClass().getSimpleName());
		onBackPressed();
	}

	private void updateView(FundAssetsModel model) {
		stepGroup.setVisibility(model.isNeedStep() ? View.VISIBLE : View.GONE);
		stepNumber.setText(model.getStepNumber());
		stepTitle.setText(model.getStepTitle());

		assetsNotification.setText(assetsNotification.getText().toString().concat("\n").concat(model.getReallocationinfo()));

		confirmButton.setText(model.getButtonText());
		confirmButton.setEnabled(false);
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
		stepNumber.setTypeface(TypefaceUtil.semibold());
		stepTitle.setTypeface(TypefaceUtil.semibold());
	}

	@Override
	public void updateNotification(List<PlatformAsset> mandatoryAssets) {
		for (PlatformAsset asset : mandatoryAssets) {
			assetsNotification.setText(assetsNotification.getText().toString().concat("\n").concat(
					String.format(Locale.getDefault(),
							getString(R.string.template_create_fund_asset_mandatory),
							StringFormatUtil.formatAmount(asset.getMandatoryFundPercent(), 0, 4),
							asset.getAsset())));
		}
	}

	@Override
	public void addAsset(PlatformAsset asset, double share) {
		addAssetLine(asset, share);
		addAssetView(asset, share);
	}

	@Override
	public void updateAsset(int index, double newShare) {
		updateAssetLine(index, newShare);
		if (newShare == 0) {
			removeAssetView(index);
		}
		else {
			updateAssetView(index, newShare);
		}
	}

	private void updateAssetLine(int index, double newShare) {
		View lineView = lineViews.get(index);
		LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) lineView.getLayoutParams();
		LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) remainingAllocationLine.getLayoutParams();
		float originalShare = lp1.weight;
		float originalRemainingShare = lp2.weight;

		ValueAnimator animator = ValueAnimator.ofFloat(lp1.weight, (float) newShare);
		animator.addUpdateListener(animation -> {
			if (remainingAllocationLine != null) {
				lp1.weight = (float) animator.getAnimatedValue();
				lineView.setLayoutParams(lp1);
				lp2.weight = originalRemainingShare + originalShare - (float) animator.getAnimatedValue();
				remainingAllocationLine.setLayoutParams(lp2);
			}
		});
		if (newShare == 0) {
			animator.addListener(new AnimatorListenerAdapter()
			{
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					lineGroup.removeViewAt(index);
					lineViews.remove(index);
				}
			});
		}
		animator.setDuration(removeAssetAnimationDuration);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();
	}

	private void updateAssetView(int index, double newShare) {
		assetViews.get(index).updateShareWithAnim(newShare);
	}

	@Override
	public void selectAsset(PlatformAsset asset) {
		int selectedPosition = -1;
		int index = 0;
		for (CreateFundAssetView assetView : assetViews) {
			assetView.select(assetView.getAsset().equals(asset));
			if (assetView.getAsset().equals(asset)) {
				selectedPosition = index;
				selectedAssetView = assetView;
			}
			index++;
		}
		index = 0;
		for (View lineView : lineViews) {
			animateAlpha(lineView, index == selectedPosition ? 1f : 0.2f, 100);
			if (index == selectedPosition) {
				updateSelectedAssetTextPosition(lineView);
			}
			index++;
		}
	}

	private void animateAlpha(View view, float targetAlpha, int duration) {
		ValueAnimator animator = ValueAnimator.ofFloat(view.getAlpha(), targetAlpha);
		animator.addUpdateListener(animation -> view.setAlpha((float) animator.getAnimatedValue()));
		animator.setDuration(duration);
		animator.setInterpolator(new AccelerateInterpolator());
		animator.start();
	}

	private void updateSelectedAssetTextPosition(View selectedLineView) {
		ValueAnimator fadeOutAnim = ValueAnimator.ofFloat(selectedAssetText.getAlpha(), 0f);
		fadeOutAnim.addUpdateListener(animation -> selectedAssetText.setAlpha((float) fadeOutAnim.getAnimatedValue()));
		fadeOutAnim.setDuration(75);
		fadeOutAnim.setInterpolator(new AccelerateInterpolator());

		ValueAnimator fadeInAnim = ValueAnimator.ofFloat(selectedAssetText.getAlpha(), 1f);
		fadeInAnim.addUpdateListener(animation -> selectedAssetText.setAlpha((float) fadeInAnim.getAnimatedValue()));
		fadeInAnim.setDuration(75);
		fadeInAnim.setInterpolator(new AccelerateInterpolator());
		fadeOutAnim.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				selectedAssetText.setText(String.format(Locale.getDefault(), "%s %s%%",
						selectedAssetView.getAsset().getName(),
						StringFormatUtil.formatAmount(selectedAssetView.getShare(), 0, 4)));
				new Handler().postDelayed(() -> {
					float x = selectedLineView.getX() + (float) selectedLineView.getWidth() / 2 - (float) selectedAssetText.getWidth() / 2;

					if (x < 0) {
						x = 0;
					}
					if (x > lineGroup.getWidth() - selectedAssetText.getWidth()) {
						x = lineGroup.getWidth() - selectedAssetText.getWidth();
					}
					selectedAssetText.setX(x);
					fadeInAnim.start();
				}, 50);
			}
		});
		fadeOutAnim.start();
	}

	@Override
	public void deselectAssets() {
		for (CreateFundAssetView assetView : assetViews) {
			assetView.select(false);
		}
		for (View lineView : lineViews) {
			lineView.setAlpha(1f);
		}

		animateAlpha(selectedAssetText, 0f, 100);
	}

	private void addAssetLine(PlatformAsset asset, double share) {
		View view = new View(getContext());
		view.setBackgroundColor(Color.parseColor(asset.getColor()));
		lineGroup.addView(view);
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
		lp.weight = (float) share;
		lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
		lp.width = 0;
		view.setLayoutParams(lp);

		lineViews.add(view);
	}

	private void addAssetView(PlatformAsset asset, double share) {
		CreateFundAssetView assetView = new CreateFundAssetView(getContext());
		assetView.setListener(presenter);
		assetView.setAsset(asset, share);
		assetViews.add(assetView);
		flexBox.addView(assetView);
		FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) assetView.getLayoutParams();
		lp.setMargins(0, TypedValueFormatter.toDp(20), 0, 0);
		assetView.setLayoutParams(lp);

		updateLongTapTipVisibility();
	}

	@Override
	public void updateRemainingShare(double remainingShare) {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) remainingAllocationLine.getLayoutParams();
		lp.weight = (float) remainingShare;
		remainingAllocationLine.setLayoutParams(lp);

		lineGroup.removeView(remainingAllocationLine);
		lineGroup.addView(remainingAllocationLine);

		remainingShareGroup.setVisibility(remainingShare > 0 ? View.VISIBLE : View.INVISIBLE);
		updateRemainingShareValue(remainingShare);
		updateRemainingShareGroupPosition(remainingShare);
		previousRemainingShare = remainingShare;
	}

	private void updateRemainingShareValue(double remainingShare) {
		ValueAnimator animator = ValueAnimator.ofFloat((float) previousRemainingShare, (float) remainingShare);
		animator.addUpdateListener(animation -> {
			if (remainingShareText != null) {
				remainingShareText.setText(String.format(Locale.getDefault(), "+%s%%",
						StringFormatUtil.formatAmount((float) animator.getAnimatedValue(), 0, 0)));
			}
		});
		animator.setDuration(removeAssetAnimationDuration);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();
	}

	private void updateRemainingShareGroupPosition(double remainingShare) {
		float x = (float) (((float) lineGroup.getWidth()) / 100 * (100 - remainingShare) - (float) remainingShareGroup.getWidth() / 2);

		if (x < 0) {
			x = 0;
		}
		if (x > lineGroup.getWidth() - remainingShareGroup.getWidth()) {
			x = lineGroup.getWidth() - remainingShareGroup.getWidth();
		}

		ValueAnimator animator = ValueAnimator.ofFloat(remainingShareGroup.getX(), x);
		animator.addUpdateListener(animation -> {
			if (remainingShareText != null) {
				remainingShareGroup.setX((float) animator.getAnimatedValue());
			}
		});
		animator.setDuration(removeAssetAnimationDuration);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();
	}

	@Override
	public void removeAsset(int index) {
		updateAssetLine(index, 0);
		removeAssetView(index);
	}

	@Override
	public void showSelectAssetShareDialog(PlatformAsset asset, double share, double remainingShare) {
		if (getActivity() != null) {
			AssetShareDialogFragment dialog = new AssetShareDialogFragment();
			dialog.show(getActivity().getSupportFragmentManager(), dialog.getTag());
			dialog.setData(asset, share, remainingShare);
			dialog.setListener(presenter);
		}
	}

	private void removeAssetView(int index) {
		flexBox.removeViewAt(index);
		assetViews.remove(index);
		updateLongTapTipVisibility();
	}

	private void updateLongTapTipVisibility() {
		longTapTip.setVisibility(flexBox.getFlexItemCount() > 0 ? View.VISIBLE : View.GONE);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, stepNumber);
	}

	@Override
	public void setAddAssetButtonEnabled(boolean enabled) {
		addAssetButton.setEnabled(enabled);
	}

	@Override
	public void setConfirmButtonEnabled(boolean enabled) {
		confirmButton.setEnabled(enabled);
	}
}