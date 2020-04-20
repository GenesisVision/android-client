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
import io.swagger.client.model.SignalSubscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.ShowUnfollowTradesEvent;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 17/12/2019.
 */

public class FollowSubscriberView extends RelativeLayout
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

	@BindView(R.id.group_equivalent)
	public ViewGroup groupEquivalent;

	@BindView(R.id.group_volume)
	public ViewGroup groupVolume;

	@BindView(R.id.delimiter)
	public View delimiter;

	private Unbinder unbinder;

	private SignalSubscription data;

	public FollowSubscriberView(Context context) {
		super(context);
		initView();
	}

	public FollowSubscriberView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FollowSubscriberView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_follow_subscriber, this);

		unbinder = ButterKnife.bind(this);

		setFonts();

//		setOnClickListener(v -> {
//			if (data != null) {
//				AssetDetails assetDetails = data.getAsset();
//				if (assetDetails != null) {
//					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(assetDetails.getId(),
//							assetDetails.getLogoUrl(),
//							assetDetails.getColor(),
//							assetDetails.getProgramDetails() != null ? assetDetails.getProgramDetails().getLevel() : 0,
//							assetDetails.getProgramDetails() != null ? assetDetails.getProgramDetails().getLevelProgress() : 0.0,
//							assetDetails.getTitle(),
//							"",
//							"",
//							false,
//							false,
//							assetDetails.getAssetType());
//					EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
//				}
//				else {
//					TradingAccountDetailsModel tradingAccountDetailsModel = new TradingAccountDetailsModel(
//							data.getSubscriberInfo().getTradingAccountId(), null, null);
//					EventBus.getDefault().post(new ShowTradingAccountDetailsEvent(tradingAccountDetailsModel));
//				}
//			}
//		});
	}

	private void setFonts() {
		name.setTypeface(TypefaceUtil.semibold());
		type.setTypeface(TypefaceUtil.semibold());
		equivalent.setTypeface(TypefaceUtil.semibold());
		volume.setTypeface(TypefaceUtil.semibold());
		tolerance.setTypeface(TypefaceUtil.semibold());

		labelType.setText(labelType.getText().toString().toLowerCase());
		labelEquivalent.setText(labelEquivalent.getText().toString().toLowerCase());
		labelVolume.setText(labelVolume.getText().toString().toLowerCase());
		labelTolerance.setText(labelTolerance.getText().toString().toLowerCase());
	}

	public void removeDelimiter() {
		delimiter.setVisibility(View.INVISIBLE);
	}

	public void setData(SignalSubscription data) {
		this.data = data;

//		if (data.getAsset() != null) {
//			this.logo.setImage(data.getAsset().getLogoUrl(), data.getAsset().getColor(), 50, 50);
//			if (data.getAsset().getProgramDetails() != null) {
//				this.logo.setLevel(data.getAsset().getProgramDetails().getLevel(), data.getAsset().getProgramDetails().getLevelProgress());
//			}
//			else {
//				this.logo.hideLevel();
//			}
//
//			this.name.setText(data.getAsset().getTitle());
//		}
//		else {
		this.logo.setVisibility(View.GONE);
		this.name.setText(data.getSubscriberInfo().getTradingAccountLogin());
//		}

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
	}
}
