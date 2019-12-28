package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.AssetDetails;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.SignalSubscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.model.events.ShowUnfollowTradesEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/12/2019.
 */

public class SignalProviderView extends RelativeLayout
{
	@BindView(R.id.logo)
	public ProgramLogoView logo;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.type)
	public TextView type;

	@BindView(R.id.label_type)
	public TextView labelType;

	@BindView(R.id.equivalent)
	public TextView equivalent;

	@BindView(R.id.label_equivalent)
	public TextView labelEquivalent;

	@BindView(R.id.volume)
	public TextView volume;

	@BindView(R.id.label_volume)
	public TextView labelVolume;

	@BindView(R.id.tolerance)
	public TextView tolerance;

	@BindView(R.id.label_tolerance)
	public TextView labelTolerance;

	@BindView(R.id.total_volume)
	public TextView totalVolume;

	@BindView(R.id.label_total_volume)
	public TextView labelTotalVolume;

	@BindView(R.id.total_profit)
	public TextView totalProfit;

	@BindView(R.id.label_total_profit)
	public TextView labelTotalProfit;

	@BindView(R.id.group_equivalent)
	public ViewGroup groupEquivalent;

	@BindView(R.id.group_volume)
	public ViewGroup groupVolume;

	@BindView(R.id.button_unfollow)
	public PrimaryButton buttonUnfollow;

	@BindView(R.id.delimiter)
	public View delimiter;

	private Unbinder unbinder;

	private SignalSubscription data;

	public SignalProviderView(Context context) {
		super(context);
		initView();
	}

	public SignalProviderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SignalProviderView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_unfollow)
	public void onUnfollowClicked() {
		if (data != null) {
			EventBus.getDefault().post(new ShowUnfollowTradesEvent(data.getAsset().getId(),
					data.getSubscriberInfo().getTradingAccountId(),
					data.getAsset().getTitle(),
					data.isIsExternal()));
		}
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_signal_provider, this);

		unbinder = ButterKnife.bind(this);

		setFonts();

		setOnClickListener(v -> {
			if (data != null && data.getAsset() != null) {
				AssetDetails assetDetails = data.getAsset();
				ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(assetDetails.getId(),
						assetDetails.getLogo(),
						assetDetails.getColor(),
						assetDetails.getProgramDetails() != null ? assetDetails.getProgramDetails().getLevel() : 0,
						assetDetails.getProgramDetails() != null ? assetDetails.getProgramDetails().getLevelProgress() : 0.0,
						assetDetails.getTitle(),
						"",
						"",
						false,
						false,
						AssetType.FOLLOW);
				EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
			}
		});
	}

	private void setFonts() {
		name.setTypeface(TypefaceUtil.semibold());
		type.setTypeface(TypefaceUtil.semibold());
		equivalent.setTypeface(TypefaceUtil.semibold());
		volume.setTypeface(TypefaceUtil.semibold());
		tolerance.setTypeface(TypefaceUtil.semibold());
		totalVolume.setTypeface(TypefaceUtil.semibold());
		totalProfit.setTypeface(TypefaceUtil.semibold());

		labelType.setText(labelType.getText().toString().toLowerCase());
		labelEquivalent.setText(labelEquivalent.getText().toString().toLowerCase());
		labelVolume.setText(labelVolume.getText().toString().toLowerCase());
		labelTolerance.setText(labelTolerance.getText().toString().toLowerCase());
		labelTotalVolume.setText(labelTotalVolume.getText().toString().toLowerCase());
		labelTotalProfit.setText(labelTotalProfit.getText().toString().toLowerCase());
	}

	public void removeDelimiter() {
		delimiter.setVisibility(View.INVISIBLE);
	}

	public void setData(SignalSubscription data) {
		this.data = data;

		if (data.getAsset() != null) {
			this.logo.setImage(data.getAsset().getLogo(), data.getAsset().getColor(), 50, 50);
			this.logo.hideLevel();

			this.name.setText(data.getAsset().getTitle());
		}

		buttonUnfollow.setVisibility(data.getStatus().toLowerCase().equals("active") ? View.VISIBLE : View.GONE);

		switch (data.getMode()) {
			case BYBALANCE:
				this.type.setText(getContext().getString(R.string.by_balance));
				groupEquivalent.setVisibility(View.GONE);
				groupVolume.setVisibility(View.GONE);
				break;
			case PERCENT:
				this.type.setText(getContext().getString(R.string.percentage));
				groupEquivalent.setVisibility(View.GONE);
				this.volume.setText(StringFormatUtil.getPercentString(data.getPercent()));
				break;
			case FIXED:
				this.type.setText(getContext().getString(R.string.fixed));
				groupVolume.setVisibility(View.GONE);
				this.equivalent.setText(StringFormatUtil.getValueString(data.getFixedVolume(), data.getFixedCurrency().getValue()));
				break;
		}
		this.tolerance.setText(StringFormatUtil.getPercentString(data.getOpenTolerancePercent()));

		this.totalVolume.setText(StringFormatUtil.formatAmount(data.getTotalVolume(), 2, 8));
		this.totalProfit.setText(StringFormatUtil.formatAmount(data.getTotalProfit(), 2, 8));
	}
}
