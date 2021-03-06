package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.MultiWalletTransactionStatus;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class StatusView extends RelativeLayout
{
	@BindView(R.id.status)
	public TextView status;

	@BindView(R.id.icon_status)
	public ImageView statusIcon;

	public StatusView(Context context) {
		super(context);
		initView();
	}

	public StatusView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_status, this);

		ButterKnife.bind(this);
	}

	public void setData(MultiWalletTransactionStatus status) {
		switch (status) {
			case DONE:
				this.status.setText(getContext().getString(R.string.status_done));
				this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
						R.drawable.icon_status_done));
				break;
			case PENDING:
				this.status.setText(getContext().getString(R.string.status_pending));
				this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
						R.drawable.icon_status_pending));
				break;
			case CANCELED:
				this.status.setText(getContext().getString(R.string.status_canceled));
				this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
						R.drawable.icon_status_canceled));
				break;
			case ERROR:
				this.status.setText(getContext().getString(R.string.status_error));
				this.statusIcon.setImageDrawable(AppCompatResources.getDrawable(GenesisVisionApplication.INSTANCE,
						R.drawable.icon_status_canceled));
				break;
		}
	}
}