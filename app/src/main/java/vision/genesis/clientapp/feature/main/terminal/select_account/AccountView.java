package vision.genesis.clientapp.feature.main.terminal.select_account;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.ExchangeAsset;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/02/2021.
 */

public class AccountView extends RelativeLayout
{
	@BindView(R.id.logo)
	public ProgramLogoView logo;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.balance)
	public TextView balance;

	public AccountView(Context context) {
		super(context);
		initView();
	}

	public AccountView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AccountView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_account, this);

		ButterKnife.bind(this);
	}

	public void setData(ExchangeAsset account) {
		if (account.getAsset() != null) {
			this.logo.setImage(account.getAsset().getLogoUrl(), account.getAsset().getColor(), 50, 50);
			this.logo.hideLevel();
			this.name.setText(account.getAsset().getTitle());
		}
		else {
			this.logo.setVisibility(View.GONE);
			this.name.setText(account.getTitle());
		}
		this.balance.setText(StringFormatUtil.getValueString(account.getBalance(), account.getCurrency().getValue()));
	}
}