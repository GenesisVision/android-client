package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.SignalSubscription;
import vision.genesis.clientapp.R;
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

	@BindView(R.id.group_equivalent)
	public ViewGroup groupEquivalent;

	@BindView(R.id.group_volume)
	public ViewGroup groupVolume;

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

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_signal_provider, this);

		unbinder = ButterKnife.bind(this);

		name.setTypeface(TypefaceUtil.semibold());

		setOnClickListener(v -> {
			if (data != null) {
//				ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(program.getId(),
//						program.getLogo(),
//						program.getColor(),
//						program.getLevel(),
//						program.getLevelProgress(),
//						program.getTitle(),
//						program.getOwner().getUsername(),
//						program.getCurrency().getValue(),
//						program.getPersonalDetails() != null ?
//								program.getPersonalDetails().isIsFavorite()
//								: false,
////TODO:
////							program.getPersonalDetails() != null ?
////									program.getPersonalDetails().isHasNotifications()
////									: false);
//						false,
//						AssetType.PROGRAM);
//				EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
			}
		});
	}

	public void removeDelimiter() {
		delimiter.setVisibility(View.INVISIBLE);
	}

	public void setData(SignalSubscription data) {
		this.data = data;

		this.logo.setImage(data.getAsset().getLogo(), data.getAsset().getColor(), 50, 50);
		if (data.getAsset().getProgramDetails() != null) {
			this.logo.setLevel(data.getAsset().getProgramDetails().getLevel(), data.getAsset().getProgramDetails().getLevelProgress());
		}
		else {
			this.logo.hideLevel();
		}
		this.name.setText(data.getSubscriberInfo().getTradingAccountLogin());

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
