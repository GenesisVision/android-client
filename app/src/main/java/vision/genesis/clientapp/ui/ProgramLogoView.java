package vision.genesis.clientapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.ImageUtils;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/08/2018.
 */

public class ProgramLogoView extends RelativeLayout
{
	@BindView(R.id.image)
	public SimpleDraweeView image;

	@BindView(R.id.level)
	public TextView level;

	@BindView(R.id.level_background)
	public ImageView levelBackground;

	@BindView(R.id.level_color)
	public ImageView levelColor;

	@BindView(R.id.level_progress_bar)
	public ProgressBar levelProgress;

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
		inflate(getContext(), R.layout.view_program_logo, this);

		ButterKnife.bind(this);

		level.setTypeface(TypefaceUtil.semibold());
	}

	public void setImage(String imageId, String programColorHex, int width, int height) {
		if (imageId == null || imageId.isEmpty()) {
			GenericDraweeHierarchy hierarchy = image.getHierarchy();
			hierarchy.setBackgroundImage(new ColorDrawable(Color.parseColor(programColorHex)));
			image.setHierarchy(hierarchy);
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

	public void setLevel(int level, Double levelProgress) {
		this.level.setText(String.valueOf(level));

		if (level > 0) {
			int[] levelColors = getResources().getIntArray(R.array.levelColors);
			if (level > levelColors.length) {
				level = levelColors.length;
			}
			levelColor.setColorFilter(levelColors[level - 1]);

			this.levelProgress.setProgress(levelProgress.intValue());
		}
	}

	public void setLevelBackground(int colorResId) {
		levelBackground.setColorFilter(ThemeUtil.getColorByAttrId(getContext(), colorResId));
	}

	public void hideLevel() {
		groupLevel.setVisibility(View.GONE);
		LayoutParams lp = (LayoutParams) image.getLayoutParams();
		lp.setMargins(0, 0, 0, 0);
		lp.setMarginEnd(0);
		image.setLayoutParams(lp);
	}
}
