package vision.genesis.clientapp.feature.common.facet;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.swagger.client.model.AssetFacet;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.events.OnFundFacetClickedEvent;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/04/2019.
 */
public class FundFacetView extends RelativeLayout
{
	@BindView(R.id.image)
	public SimpleDraweeView image;

	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.description)
	public TextView description;

	private AssetFacet facet;

	public FundFacetView(Context context) {
		super(context);
		initView();
	}

	public FundFacetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FundFacetView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_facet, this);

		ButterKnife.bind(this);

		name.setTypeface(TypefaceUtil.semibold());

		this.setOnClickListener(view -> EventBus.getDefault().post(new OnFundFacetClickedEvent(facet)));
	}

	public void setData(AssetFacet facet) {
		this.facet = facet;
		this.image.setImageURI(ImageUtils.getImageUri(facet.getLogo()));
		this.name.setText(facet.getTitle());
		this.description.setText(facet.getDescription());
	}
}
