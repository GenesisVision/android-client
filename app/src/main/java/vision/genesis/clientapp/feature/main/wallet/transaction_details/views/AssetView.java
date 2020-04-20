package vision.genesis.clientapp.feature.main.wallet.transaction_details.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.AssetDetails;
import io.swagger.client.model.AssetType;
import io.swagger.client.model.ProgramAssetDetails;
import io.swagger.client.model.TransactionAssetDetails;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.FundDetailsModel;
import vision.genesis.clientapp.model.ProgramDetailsModel;
import vision.genesis.clientapp.model.events.ShowFundDetailsEvent;
import vision.genesis.clientapp.model.events.ShowProgramDetailsEvent;
import vision.genesis.clientapp.ui.ProgramLogoView;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */
public class AssetView extends RelativeLayout
{
	@BindView(R.id.label)
	public TextView label;

	@BindView(R.id.program_logo)
	public ProgramLogoView programLogo;

	@BindView(R.id.asset_name)
	public TextView assetName;

	@BindView(R.id.owner_name)
	public TextView ownerName;

	private UUID assetId;

	private String logo;

	private String color;

	private String title;

	private AssetType assetType;

	private ProgramAssetDetails programDetails;

	public AssetView(Context context) {
		super(context);
		initView();
	}

	public AssetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AssetView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_transaction_program, this);

		ButterKnife.bind(this);

		ownerName.setTypeface(TypefaceUtil.semibold());

		setOnClickListener(view -> {
			if (assetId != null) {
				if (assetType.equals(AssetType.PROGRAM) || assetType.equals(AssetType.FOLLOW)) {
					ProgramDetailsModel programDetailsModel = new ProgramDetailsModel(assetId,
							logo,
							color,
							0,
							0.0,
							title,
							"",
							null,
							false,
							false,
							assetType);
					EventBus.getDefault().post(new ShowProgramDetailsEvent(programDetailsModel));
				}
				else if (assetType.equals(AssetType.FUND)) {
					FundDetailsModel fundDetailsModel = new FundDetailsModel(assetId,
							logo,
							color,
							title,
							"",
							false,
							false);
					EventBus.getDefault().post(new ShowFundDetailsEvent(fundDetailsModel));
				}
			}
		});
	}

	public void setData(TransactionAssetDetails asset) {
		this.assetId = asset.getId();
		this.logo = asset.getLogoUrl();
		this.color = asset.getColor();
		this.title = asset.getTitle();
		this.assetType = asset.getAssetType();
		this.programDetails = asset.getProgramDetails();

		updateView();
	}

	public void setData(AssetDetails asset) {
		this.assetId = asset.getId();
		this.logo = asset.getLogoUrl();
		this.color = asset.getColor();
		this.title = asset.getTitle();
		this.assetType = asset.getAssetType();
		this.programDetails = asset.getProgramDetails();

		updateView();
	}

	private void updateView() {
		this.label.setText(assetType.getValue());
		this.programLogo.setImage(logo, color, 50, 50);

		if (programDetails != null) {
			this.programLogo.setLevel(programDetails.getLevel(), programDetails.getLevelProgress());
		}
		else {
			this.programLogo.hideLevel();
			ViewGroup.LayoutParams lp = this.programLogo.getLayoutParams();
			lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics());
			this.programLogo.setLayoutParams(lp);
		}
		this.assetName.setText(title);
		this.ownerName.setVisibility(View.GONE);
	}
}
