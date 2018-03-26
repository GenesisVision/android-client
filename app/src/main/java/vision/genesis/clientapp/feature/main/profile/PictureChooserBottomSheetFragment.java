package vision.genesis.clientapp.feature.main.profile;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnPictureChooserCameraClickedEvent;
import vision.genesis.clientapp.model.events.OnPictureChooserGalleryClickedEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 26/03/2018.
 */

public class PictureChooserBottomSheetFragment extends BottomSheetDialogFragment
{
	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);
		View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet_picture_chooser, null);
		dialog.setContentView(contentView);

		contentView.findViewById(R.id.camera).setOnClickListener(v -> {
			EventBus.getDefault().post(new OnPictureChooserCameraClickedEvent());
			this.dismiss();
		});
		contentView.findViewById(R.id.gallery).setOnClickListener(v -> {
			EventBus.getDefault().post(new OnPictureChooserGalleryClickedEvent());
			this.dismiss();
		});
	}
}
