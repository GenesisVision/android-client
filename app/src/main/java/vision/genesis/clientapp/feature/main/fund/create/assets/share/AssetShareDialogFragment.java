package vision.genesis.clientapp.feature.main.fund.create.assets.share;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.PlatformAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/10/2019.
 */

public class AssetShareDialogFragment extends AppCompatDialogFragment
{
	public interface Listener
	{
		void onAddAsset(PlatformAsset asset, double share);
	}

	@BindView(R.id.icon)
	public SimpleDraweeView icon;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.ticker)
	public TextView ticker;

	@BindView(R.id.free_space)
	public TextView freeSpace;

	@BindView(R.id.share)
	public EditText share;

	@BindView(R.id.button_minus)
	public ImageView minusButton;

	@BindView(R.id.button_plus)
	public ImageView plusButton;

	@BindView(R.id.button_add)
	public PrimaryButton addButton;

	private Listener listener;

	private PlatformAsset asset;

	private double origFreeSpaceValue;

	private double currentFreeSpaceValue;

	private double origShareValue;

	private double currentShareValue;

	@OnClick(R.id.group_share)
	public void onShareGroupClicked() {
		if (getContext() != null) {
			share.requestFocus();
			InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			Objects.requireNonNull(imm).showSoftInput(share, InputMethodManager.SHOW_IMPLICIT);
		}
	}

	@OnClick(R.id.button_minus)
	public void onMinusClicked() {
		setShareText(currentShareValue - 1);
	}

	@OnClick(R.id.button_plus)
	public void onPlusClicked() {
		setShareText(currentShareValue + 1);
	}

	@OnClick(R.id.button_add)
	public void onAddClicked() {
		if (listener != null && currentShareValue >= asset.getMandatoryFundPercent()) {
			listener.onAddAsset(asset, currentShareValue);
			this.dismiss();
		}
	}

	@OnClick(R.id.button_cancel)
	public void onCancelClicked() {
		this.dismiss();
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_dialog_asset_share, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		updateView();

		setTextListener();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		}
	}

	public void setData(PlatformAsset asset, double share, double freeSpace) {
		this.asset = asset;
		this.origShareValue = share;
		this.currentShareValue = origShareValue;
		this.origFreeSpaceValue = freeSpace;
		this.currentFreeSpaceValue = origFreeSpaceValue;

		if (icon != null) {
			updateView();
		}
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private void updateView() {
		if (asset != null && this.icon != null) {
			this.icon.setImageURI(ImageUtils.getImageUri(asset.getLogoUrl()));
			this.name.setText(asset.getName());
			this.ticker.setText(asset.getAsset());

			setFreeSpaceText(origFreeSpaceValue);
			if (currentShareValue > 0) {
				this.addButton.setText(getString(R.string.update));
			}
			setShareText(origShareValue);

			updateButtons();
		}
	}

	private void setTextListener() {
		RxTextView.textChanges(share)
				.subscribe(charSequence -> onShareChanged(charSequence.toString()));
	}

	private void setFreeSpaceText(double freeSpaceValue) {
		this.freeSpace.setText(StringFormatUtil.formatAmount(freeSpaceValue, 0, 4).concat("%"));
	}

	private void setShareText(double shareValue) {
		String shareText = StringFormatUtil.formatAmount(shareValue, 0, 0);
		if (shareValue == 0) {
			this.share.setText("");
		}
		else {
			this.share.setText(shareText);
			this.share.setSelection(shareText.length(), shareText.length());
		}
	}

	private void onShareChanged(String shareString) {
		try {
			currentShareValue = Double.parseDouble(shareString);
		} catch (NumberFormatException e) {
			currentShareValue = 0;
		}
		if (currentShareValue > 99) {
			setShareText(99);
			return;
		}
		if (currentShareValue > origShareValue + origFreeSpaceValue) {
			setShareText(origShareValue + origFreeSpaceValue);
			return;
		}
		else if (currentShareValue < asset.getMandatoryFundPercent()) {
			setShareText(asset.getMandatoryFundPercent());
			return;
		}
		currentFreeSpaceValue = origFreeSpaceValue - (currentShareValue - origShareValue);
		setFreeSpaceText(currentFreeSpaceValue);
		updateButtons();
	}

	private void updateButtons() {
		this.addButton.setEnabled(currentShareValue > 0 && currentShareValue <= 99 && currentFreeSpaceValue >= 0 && currentShareValue != origShareValue);
		this.minusButton.setAlpha(currentShareValue > asset.getMandatoryFundPercent() ? 1f : 0.4f);
		this.minusButton.setEnabled(currentShareValue > asset.getMandatoryFundPercent());
		this.plusButton.setAlpha(currentFreeSpaceValue > 0 && currentShareValue < 99 ? 1f : 0.4f);
		this.plusButton.setEnabled(currentFreeSpaceValue > 0 && currentShareValue < 99);
	}
}
