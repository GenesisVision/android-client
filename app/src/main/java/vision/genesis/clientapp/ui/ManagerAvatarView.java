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
import vision.genesis.clientapp.R;

/**
 * GenesisVision
 * Created by Vitaly on 1/29/18.
 */

public class ManagerAvatarView extends RelativeLayout
{
	@BindView(R.id.image)
	public SimpleDraweeView image;

	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.group_level)
	public ViewGroup groupLevel;

	public ManagerAvatarView(Context context) {
		super(context);
		initView();
	}

	public ManagerAvatarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ManagerAvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_manager_avatar, this);

		ButterKnife.bind(this);
	}

	public void setImageUrl(String url) {
		image.setImageURI(url);
	}

	public void setLevel(String level) {
		this.level.setText(level);
	}

	public void hideLevel() {
		groupLevel.setVisibility(View.GONE);
	}
}
