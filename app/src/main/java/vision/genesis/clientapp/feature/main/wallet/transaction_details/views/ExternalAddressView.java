package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 04/03/2019.
 */
public class ExternalAddressView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	@BindView(R.id.address)
	public TextView address;

	@BindView(R.id.copy)
	public TextView copy;

	public ExternalAddressView(Context context) {
		super(context);
		initView();
	}

	public ExternalAddressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ExternalAddressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_copy)
	public void onCopyClicked() {
		ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Activity.CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText("address", address.getText());
		if (clipboardManager != null) {
			clipboardManager.setPrimaryClip(clipData);
			Toast.makeText(getContext(), getContext().getString(R.string.address_copied), Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getContext(), getContext().getString(R.string.cannot_copy), Toast.LENGTH_SHORT).show();
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_external_address, this);

		ButterKnife.bind(this);

		copy.setTypeface(TypefaceUtil.semibold());
	}

	public void setData(String label, String address) {
		this.label.setText(label);
		this.address.setText(address);
	}
}