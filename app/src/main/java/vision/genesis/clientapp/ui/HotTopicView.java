package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.swagger.client.model.SocialSummaryHashTag;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.StringFormatUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/08/2020.
 */

public class HotTopicView extends RelativeLayout
{
	@BindView(R.id.name)
	public TextView name;

	@BindView(R.id.view)
	public TextView view;

	@BindView(R.id.discuss)
	public TextView discuss;

	private Unbinder unbinder;

	private SocialSummaryHashTag topic;

	public HotTopicView(Context context) {
		super(context);
		initView();
	}

	public HotTopicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public HotTopicView(Context context, AttributeSet attrs, int defStyleAttr) {
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
		inflate(getContext(), R.layout.view_hot_topic, this);

		unbinder = ButterKnife.bind(this);
	}

	public void setHotTopic(SocialSummaryHashTag topic) {
		this.topic = topic;

		this.name.setText(topic.getHashTag());
		this.view.setText(StringFormatUtil.formatAmount(topic.getImpressionsCount(), 0, 0));
		this.discuss.setText(StringFormatUtil.formatAmount(topic.getDiscussCount(), 0, 0));
	}
}
