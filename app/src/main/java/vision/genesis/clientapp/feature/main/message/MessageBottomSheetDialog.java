package vision.genesis.clientapp.feature.main.message;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

import androidx.appcompat.content.res.AppCompatResources;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/10/2018.
 */

public class MessageBottomSheetDialog extends BottomSheetDialogFragment
{
	public interface OnButtonClickListener
	{
		void onMessageButtonClicked();
	}

	@BindView(R.id.image)
	public ImageView image;

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.message)
	public TextView message;

	@BindView(R.id.button)
	public PrimaryButton button;

	private boolean mustRead = false;

	private OnButtonClickListener listener;

	private int imageResourceId;

	private String titleText;

	private String messageText;

	@OnClick(R.id.button)
	public void onButtonClicked() {
		this.dismiss();
	}

	@SuppressLint("RestrictedApi")
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_message, null);

		dialog.setContentView(contentView);

		ButterKnife.bind(this, contentView);

		setFonts();

		updateView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getDialog().getWindow() != null) {
			getDialog().getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
			getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_slide_animation;
		}
	}

	public void setData(int imageResourceId, String title, String message, boolean mustRead, OnButtonClickListener listener) {
		this.imageResourceId = imageResourceId;
		this.titleText = title;
		this.messageText = message;
		this.mustRead = mustRead;
		this.listener = listener;
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.semibold());
	}

	private void updateView() {
		if (this.image != null) {
			if (imageResourceId > 0)
				this.image.setImageDrawable(AppCompatResources.getDrawable(Objects.requireNonNull(getContext()), imageResourceId));
			this.title.setText(titleText);
			this.message.setText(messageText);

			setCancelable(!mustRead);
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		if (listener != null)
			listener.onMessageButtonClicked();
		super.onCancel(dialog);
	}

	@Override
	public void dismiss() {
		if (listener != null)
			listener.onMessageButtonClicked();
		super.dismiss();
	}
}
