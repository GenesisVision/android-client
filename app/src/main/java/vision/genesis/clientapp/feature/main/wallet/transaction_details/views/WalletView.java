package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class WalletView extends RelativeLayout
{
	@BindView(R.id.first_icon)
	public SimpleDraweeView firstIcon;

	@BindView(R.id.first_name)
	public TextView firstName;

	@BindView(R.id.icon_arrow)
	public View arrowIcon;

	@BindView(R.id.second)
	public ViewGroup second;

	@BindView(R.id.second_icon)
	public SimpleDraweeView secondIcon;

	@BindView(R.id.second_name)
	public TextView secondName;

	public WalletView(Context context) {
		super(context);
		initView();
	}

	public WalletView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public WalletView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_wallet, this);

		ButterKnife.bind(this);
	}

	public void setData(String firstLogo, String firstName, String secondLogo, String secondName) {
		this.firstIcon.setImageURI(ImageUtils.getImageUri(firstLogo));
		this.firstName.setText(firstName);

		if (secondLogo != null && secondName != null) {
			this.arrowIcon.setVisibility(View.VISIBLE);
			this.second.setVisibility(View.VISIBLE);
			this.secondIcon.setImageURI(ImageUtils.getImageUri(secondLogo));
			this.secondName.setText(secondName);
		}
	}
}