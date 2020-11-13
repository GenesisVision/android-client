package vision.genesis.clientapp.feature.common.picture_chooser;

import android.app.Dialog;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class PictureChooserBottomSheetFragment extends BottomSheetDialogFragment
{
	public interface Listener
	{
		void onPictureChooserCameraClicked();

		void onPictureChooserGalleryClicked();
	}

	private Listener listener;

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_picture_chooser, null);
		dialog.setContentView(contentView);

		contentView.findViewById(R.id.camera).setOnClickListener(v -> {
			if (listener != null) {
				listener.onPictureChooserCameraClicked();
			}
			this.dismiss();
		});
		contentView.findViewById(R.id.gallery).setOnClickListener(v -> {
			if (listener != null) {
				listener.onPictureChooserGalleryClicked();
			}
			this.dismiss();
		});
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
