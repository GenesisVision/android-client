package vision.genesis.clientapp.ui;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/29/18.
 */

public class AvatarView extends RelativeLayout
{
	@BindView(R.id.image)
	public SimpleDraweeView image;

	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.group_level)
	public ViewGroup groupLevel;

	public AvatarView(Context context) {
		super(context);
		initView();
	}

	public AvatarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_manager_avatar, this);

		ButterKnife.bind(this);

		level.setTypeface(TypefaceUtil.bold());
	}

	public void setImage(String imageId, int width, int height) {
		if (imageId == null || imageId.isEmpty()) {
			image.setImageURI("");
			return;
		}

		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(ImageUtils.getImageUri(imageId)))
				.setResizeOptions(new ResizeOptions(width, height))
				.build();

		PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
				.setOldController(image.getController())
				.setImageRequest(request)
				.build();

		image.setController(controller);
	}

	public void setLevel(int level) {
		this.level.setText(String.valueOf(level));
	}

	public void hideLevel() {
		groupLevel.setVisibility(View.GONE);
	}
}
