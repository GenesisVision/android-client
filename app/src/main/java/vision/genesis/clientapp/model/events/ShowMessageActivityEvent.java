package vision.genesis.clientapp.model.events;

import vision.genesis.clientapp.feature.main.message.MessageBottomSheetDialog;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 30/03/2018.
 */
public class ShowMessageActivityEvent
{
	public int imageResourceId;

	public String title;

	public String message;

	public boolean mustRead;

	public MessageBottomSheetDialog.OnButtonClickListener listener;

	public ShowMessageActivityEvent(int imageResourceId, String title, String message, boolean mustRead, MessageBottomSheetDialog.OnButtonClickListener listener) {
		this.imageResourceId = imageResourceId;
		this.title = title;
		this.message = message;
		this.mustRead = mustRead;
		this.listener = listener;
	}
}
