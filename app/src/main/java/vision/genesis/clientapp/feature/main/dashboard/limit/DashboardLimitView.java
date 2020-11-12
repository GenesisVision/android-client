package vision.genesis.clientapp.feature.main.dashboard.limit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.LimitWithoutKyc;
import io.swagger.client.model.UserVerificationStatus;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.DashboardManager;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.model.events.OnStartKycClickedEvent;
import vision.genesis.clientapp.model.events.ShowVerificationInfoActivityEvent;
import vision.genesis.clientapp.ui.PrimaryButton;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/11/2020.
 */

public class DashboardLimitView extends RelativeLayout
{
	@Inject
	public SettingsManager settingsManager;

	@Inject
	public DashboardManager dashboardManager;

	@BindView(R.id.spent)
	public TextView spent;

	@BindView(R.id.limit)
	public TextView limit;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.button_remove_limit)
	public PrimaryButton removeLimitButton;

	private Unbinder unbinder;

	private UserVerificationStatus verificationStatus;

	public DashboardLimitView(Context context) {
		super(context);
		initView();
	}

	public DashboardLimitView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public DashboardLimitView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.tooltip)
	public void onTooltipClicked() {
		if (verificationStatus != null) {
			EventBus.getDefault().post(new ShowVerificationInfoActivityEvent(verificationStatus));
		}
	}

	@OnClick(R.id.button_remove_limit)
	public void onRemoveLimitClicked() {
		EventBus.getDefault().post(new OnStartKycClickedEvent());
	}

	private void initView() {
		inflate(getContext(), R.layout.view_dashboard_limit, this);

		unbinder = ButterKnife.bind(this);
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void setData(LimitWithoutKyc limit) {
		if (limit != null) {
			this.spent.setText(StringFormatUtil.getValueString(limit.getInvested(), limit.getCurrency().getValue()));
			this.limit.setText(StringFormatUtil.getValueString(limit.getLimit(), limit.getCurrency().getValue()));

			this.progressBar.setMax(limit.getLimit().intValue());
			this.progressBar.setProgress(limit.getInvested().intValue());
		}
	}

	public void setButtonStatus(UserVerificationStatus verificationStatus) {
		this.verificationStatus = verificationStatus;
		switch (verificationStatus) {
			case NOTVERIFIED:
			case REJECTED:
				this.removeLimitButton.setVisibility(View.VISIBLE);
				this.removeLimitButton.setEnabled(true);
				this.removeLimitButton.setText(getContext().getString(R.string.remove_limit));
				break;
			case UNDERREVIEW:
				this.removeLimitButton.setVisibility(View.VISIBLE);
				this.removeLimitButton.setEnabled(false);
				this.removeLimitButton.setText(getContext().getString(R.string.under_review));
				break;
			case VERIFIED:
				this.removeLimitButton.setVisibility(View.GONE);
				break;
		}
	}
}
