package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class AutoCompleteItemView extends RelativeLayout
{
	public interface Listener
	{
		void onAssetClicked(String assetTag);
	}

	@BindView(R.id.avatar)
	public AvatarView avatar;

	@BindView(R.id.logo)
	public ProgramLogoView logo;

	@BindView(R.id.asset_name)
	public TextView assetName;

	@BindView(R.id.asset_type)
	public TextView assetType;

	private Unbinder unbinder;

	private String tag;

	private Listener listener;

	public AutoCompleteItemView(Context context) {
		super(context);
		initView();
	}

	public AutoCompleteItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AutoCompleteItemView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_autocomplete_item, this);

		unbinder = ButterKnife.bind(this);

		setOnClickListener(v -> {
			if (listener != null && tag != null) {
				listener.onAssetClicked(tag);
			}
		});
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public void setData(String logo, String logoColor, String avatar, String name, String type) {
		this.tag = StringFormatUtil.getAssetSocialTag(name, type);
		this.assetName.setText(name);
		this.assetType.setText(type);

		if (logo != null) {
			this.logo.setVisibility(View.VISIBLE);
			this.avatar.setVisibility(View.GONE);
			this.logo.hideLevel();
			this.logo.setImage(logo, logoColor, 50, 50);
		}
		else {
			this.logo.setVisibility(View.GONE);
			this.avatar.setVisibility(View.VISIBLE);
			this.avatar.setImage(avatar, 50, 50);
		}
	}
}
