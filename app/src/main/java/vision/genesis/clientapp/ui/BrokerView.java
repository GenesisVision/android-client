package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.Broker;
import io.swagger.client.model.ProgramTag;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/11/2019.
 */

public class BrokerView extends RelativeLayout
{
	@BindView(R.id.check)
	public ImageView check;

	@BindView(R.id.logo)
	public SimpleDraweeView logo;

	@BindView(R.id.group_tags)
	public LinearLayout groupTags;

	protected Unbinder unbinder;

	public BrokerView(Context context) {
		super(context);
		initView();
	}

	public BrokerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public BrokerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	protected void initView() {
		inflate(getContext(), R.layout.view_broker, this);

		unbinder = ButterKnife.bind(this);
	}

	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	public void setData(Broker broker) {
		this.logo.setImageURI(ImageUtils.getImageUri(broker.getLogo()));

		groupTags.removeAllViews();
		int position = 0;
		ProgramTag emptyTag = new ProgramTag();
		emptyTag.setName("...");
		emptyTag.setColor("#00bdaf");
		for (ProgramTag tag : broker.getTags()) {
			if (position > 0) {
				addTag(createTagView(emptyTag), groupTags);
				break;
			}
			addTag(createTagView(tag), groupTags);
			position++;
		}
	}

	private TagView createTagView(ProgramTag tag) {
		TagView view = new TagView(getContext());
		view.setTag(tag);
		return view;
	}

	private void addTag(TagView tagView, LinearLayout tagsGroup) {
		tagsGroup.addView(tagView);
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tagView.getLayoutParams();
		lp.setMargins(0,
				0,
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
						GenesisVisionApplication.INSTANCE.getResources().getDisplayMetrics()),
				0);
		tagView.setLayoutParams(lp);
	}

	public void select(boolean select) {
		check.setVisibility(select ? View.VISIBLE : View.GONE);
	}
}
