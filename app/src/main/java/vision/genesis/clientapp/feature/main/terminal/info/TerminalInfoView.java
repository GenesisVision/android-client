package vision.genesis.clientapp.feature.main.terminal.info;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.AssetInfo;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SettingsManager;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.ui.SocialLinksView;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */

public class TerminalInfoView extends RelativeLayout
{
	@Inject
	public SettingsManager settingsManager;

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.group_info)
	public ViewGroup infoGroup;

	@BindView(R.id.group_no_info)
	public ViewGroup noInfoGroup;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.description)
	public TextView description;

	@BindView(R.id.social_links)
	public SocialLinksView socialLinks;

	public Subscription getInfoSubscription;

	private Unbinder unbinder;

	private String symbol = "";

	private String baseAssetName;

	public TerminalInfoView(Context context) {
		super(context);
		initView();
	}

	public TerminalInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TerminalInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_terminal_info, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		getBaseAssetName();
	}

	public void onDestroy() {
		if (getInfoSubscription != null) {
			getInfoSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void getBaseAssetName() {
		if (symbol != null && terminalManager != null) {
			Pair<String, String> baseQuoteAssets = terminalManager.getBaseQuoteAssets(symbol);
			if (baseQuoteAssets != null) {
				baseAssetName = baseQuoteAssets.first;
				getInfo();
			}
		}
	}

	private void getInfo() {
		if (baseAssetName != null && !baseAssetName.isEmpty() && settingsManager != null) {
			if (getInfoSubscription != null) {
				getInfoSubscription.unsubscribe();
			}
			getInfoSubscription = settingsManager.getAssetInfo(baseAssetName)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetInfoSuccess,
							this::handleGetInfoError);
		}
	}

	private void handleGetInfoSuccess(AssetInfo response) {
		getInfoSubscription.unsubscribe();
		this.infoGroup.setVisibility(View.VISIBLE);
		this.noInfoGroup.setVisibility(View.GONE);

		this.name.setText(String.format("%s | %s", response.getName(), response.getSymbol()));
		this.description.setText(response.getDescription());
		this.socialLinks.setData(response.getSocialLinks());
	}

	private void handleGetInfoError(Throwable throwable) {
		getInfoSubscription.unsubscribe();
		this.infoGroup.setVisibility(View.GONE);
		this.noInfoGroup.setVisibility(View.VISIBLE);
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
		getBaseAssetName();
	}
}
