package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.SocialSummaryStrategy;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/08/2020.
 */

public class TopStrategyView extends RelativeLayout
{
	@BindView(R.id.asset_logo)
	public ProgramLogoView assetLogo;

	@BindView(R.id.asset_name)
	public TextView assetName;

	@BindView(R.id.asset_type)
	public TextView assetType;

	@BindView(R.id.profit)
	public TextView profit;

	@BindView(R.id.investors)
	public TextView investors;

	private Unbinder unbinder;

	private SocialSummaryStrategy strategy;

	public TopStrategyView(Context context) {
		super(context);
		initView();
	}

	public TopStrategyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TopStrategyView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_top_strategy, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setTopStrategy(SocialSummaryStrategy strategy) {
		this.strategy = strategy;

		this.assetLogo.hideLevel();
		this.assetLogo.setImage(strategy.getLogoUrl(), strategy.getColor(), 50, 50);

		this.assetName.setText(strategy.getTitle());
		this.assetType.setText(strategy.getAssetType().toString());

		if (strategy.getProfitPercent() != null) {
			this.profit.setText(StringFormatUtil.getPercentString(strategy.getProfitPercent()));
		}
		this.investors.setText(String.valueOf(strategy.getInvestorsCount()));

	}
}
