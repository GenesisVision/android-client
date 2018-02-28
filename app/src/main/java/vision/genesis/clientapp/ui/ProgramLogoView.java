package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 1/29/18.
 */

public class ProgramLogoView extends RelativeLayout
{
	@BindView(R.id.image)
	public SimpleDraweeView image;

	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.group_level)
	public ViewGroup groupLevel;

	public ProgramLogoView(Context context) {
		super(context);
		initView();
	}

	public ProgramLogoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ProgramLogoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_manager_avatar, this);

		ButterKnife.bind(this);
	}

	public void setImageUrl(String url) {
		String baseUrl = BuildConfig.FLAVOR.equals("tournament")
				? BuildConfig.TOURNAMENT_API_ADDRESS
				: BuildConfig.API_ADDRESS;
		image.setImageURI(baseUrl + "/api/files/get?filename=" + url);
	}

	public void setLevel(String level) {
		this.level.setText(level);
	}

	public void hideLevel() {
		groupLevel.setVisibility(View.GONE);
	}
}
