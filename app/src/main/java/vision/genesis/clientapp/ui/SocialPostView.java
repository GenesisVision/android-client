package vision.genesis.clientapp.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.stfalcon.imageviewer.StfalconImageViewer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.swagger.client.model.ImageQuality;
import io.swagger.client.model.Post;
import io.swagger.client.model.PostImage;
import io.swagger.client.model.PostImageResize;
import io.swagger.client.model.PostTag;
import io.swagger.client.model.SocialPostTagType;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.PostTagMatch;
import vision.genesis.clientapp.model.events.OnHashTagClickedEvent;
import vision.genesis.clientapp.model.events.OnPostImageClickedEvent;
import vision.genesis.clientapp.model.events.OnPostTagClickedEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialPostView extends RelativeLayout
{
	private final static int MAX_IMAGES = 7;

	@Inject
	public SocialManager socialManager;

	@BindView(R.id.author_logo)
	public AvatarView authorLogo;

	@BindView(R.id.author_name)
	public TextView authorName;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.view_event)
	public PostEventView eventView;

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.flex_box)
	public FlexboxLayout flexbox;

//	@BindView(R.id.images_grid)
//	public AsymmetricGridView imagesGrid;

	@BindView(R.id.scrollview)
	public HorizontalScrollView scrollview;

	@BindView(R.id.group_post_tags)
	public LinearLayout postTagsGroup;

	@BindView(R.id.likes_count)
	public TextView likesCount;

	@BindView(R.id.reposts_count)
	public TextView repostsCount;

	@BindView(R.id.comments_count)
	public TextView commentsCount;

	@BindView(R.id.views_count)
	public TextView viewsCount;

	@BindView(R.id.icon_like)
	public ImageView likeIcon;

	@BindDimen(R.dimen.padding)
	public int padding;

	public Subscription setLikeSubscription;

	private Unbinder unbinder;

	private Post post;

	private ArrayList<PostImageView> imageViews = new ArrayList<>();

	public SocialPostView(Context context) {
		super(context);
		initView();
	}

	public SocialPostView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SocialPostView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (setLikeSubscription != null) {
			setLikeSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}

		EventBus.getDefault().unregister(this);
	}

	private void initView() {
		inflate(getContext(), R.layout.view_social_post, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		EventBus.getDefault().register(this);

		setOnClickListener(v -> {
			if (post != null) {

			}
		});
	}

	@OnClick(R.id.button_like)
	public void onLikeClicked() {
		if (post != null && post.getPersonalDetails() != null) {
			post.getPersonalDetails().setIsLiked(!post.getPersonalDetails().isIsLiked());
			post.setLikesCount(post.getPersonalDetails().isIsLiked()
					? post.getLikesCount() + 1
					: post.getLikesCount() - 1);
			updateData(post);
			setLike();
		}
	}

	private void setLike() {
		if (socialManager != null && post != null) {
			setLikeSubscription = (post.getPersonalDetails().isIsLiked()
					? socialManager.likePost(post.getId())
					: socialManager.unlikePost(post.getId()))
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
					}, throwable -> {
					});
		}
	}

	@OnClick(R.id.button_repost)
	public void onRepostClicked() {

	}

	@OnClick(R.id.button_comments)
	public void onCommentsClicked() {

	}

	public void setPost(Post post) {
		this.post = post;
		updateData(post);
	}

	private void updateData(Post post) {
		this.authorLogo.setImage(post.getAuthor().getLogoUrl(), 50, 50);
		this.authorName.setText(post.getAuthor().getUsername());
		this.date.setText(DateTimeUtil.formatEventDateTime(post.getDate()));

		this.text.setVisibility(View.VISIBLE);

		parseText(post.getText());
		setImages(post);
		setPostTags(post.getTags());

		this.likesCount.setText(String.valueOf(post.getLikesCount()));
		this.repostsCount.setText(String.valueOf(post.getRePostsCount()));
		this.commentsCount.setText(String.valueOf(post.getComments().size()));
		this.viewsCount.setText(String.valueOf(post.getImpressionsCount()));

		this.likesCount.setVisibility(post.getLikesCount() > 0 ? View.VISIBLE : View.GONE);
		this.repostsCount.setVisibility(post.getRePostsCount() > 0 ? View.VISIBLE : View.GONE);
		this.commentsCount.setVisibility(post.getComments().size() > 0 ? View.VISIBLE : View.GONE);

		this.likeIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), post.getPersonalDetails().isIsLiked() ? R.drawable.icon_like_full : R.drawable.icon_like));
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			likeIcon.setImageTintList(post.getPersonalDetails().isIsLiked()
					? ColorStateList.valueOf(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent))
					: ColorStateList.valueOf(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary)));
			this.likesCount.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
					post.getPersonalDetails().isIsLiked() ? R.attr.colorAccent : R.attr.colorTextSecondary));
		}
	}

	private void parseText(String text) {
		Matcher postTagsMatcher = Pattern.compile("@tag-\\d+").matcher(text);
		int start;
		int end;
		String tag;
		int tagNumber;
		ArrayList<PostTagMatch> postTagsMatches = new ArrayList<>();
		ArrayList<PostTagMatch> hashTagsMatches = new ArrayList<>();
		while (postTagsMatcher.find()) {
			start = postTagsMatcher.start();
			end = postTagsMatcher.end();
			tag = text.substring(start, end);
			try {
				tagNumber = Integer.parseInt(tag.replace("@tag-", ""));
			} catch (NumberFormatException e) {
				tagNumber = -1;
			}
			for (PostTag postTag : post.getTags()) {
				if (postTag.getType().equals(SocialPostTagType.EVENT)) {
					this.eventView.setVisibility(View.VISIBLE);
					this.text.setVisibility(View.GONE);
					this.eventView.setPostTag(postTag);
				}
				else {
					this.eventView.setVisibility(View.GONE);
				}
				if (postTag.getNumber().equals(tagNumber)) {
					postTagsMatches.add(new PostTagMatch(tag, getTagReplaceText(postTag), start, end, postTag, null));
					break;
				}
			}
		}
		for (PostTagMatch match : postTagsMatches) {
			text = text.replace(match.getTagString(), match.getReplaceString());
		}
		Matcher hashTagsMatcher = Pattern.compile("#[a-zA-Z0-9_]+").matcher(text);
		while (hashTagsMatcher.find()) {
			start = hashTagsMatcher.start();
			end = hashTagsMatcher.end();
			tag = text.substring(start, end);
			hashTagsMatches.add(new PostTagMatch(tag, "", start, end, null, tag));

		}

		this.text.setText(getSpannableString(text, postTagsMatches, hashTagsMatches));
		this.text.setMovementMethod(LinkMovementMethod.getInstance());
	}

	private SpannableString getSpannableString(String text, ArrayList<PostTagMatch> postTagsMatches, ArrayList<PostTagMatch> hashTagsMatches) {
		SpannableString spannable = new SpannableString(text);

		int shift = 0;
		for (PostTagMatch match : postTagsMatches) {
			spannable.setSpan(new ClickableSpan()
			                  {
				                  @Override
				                  public void onClick(View view) {
					                  if (match.getPostTag() != null) {
						                  EventBus.getDefault().post(new OnPostTagClickedEvent(match.getPostTag()));
					                  }
				                  }

				                  @Override
				                  public void updateDrawState(TextPaint ds) {
					                  super.updateDrawState(ds);
					                  int linkColor = ContextCompat.getColor(getContext(), R.color.accent);
					                  ds.setColor(linkColor);
					                  ds.setUnderlineText(false);
				                  }
			                  },
					match.getStart() + shift,
					match.getEnd() + shift + match.getReplaceString().length() - match.getTagString().length(),
					Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

			if (match.getPostTag() != null) {
				shift += match.getReplaceString().length() - match.getTagString().length();
			}
		}
		for (PostTagMatch match : hashTagsMatches) {
			spannable.setSpan(new ClickableSpan()
			                  {
				                  @Override
				                  public void onClick(View view) {
					                  if (match.getPostTag() != null) {
						                  EventBus.getDefault().post(new OnHashTagClickedEvent(match.getHashTag()));
					                  }
				                  }

				                  @Override
				                  public void updateDrawState(TextPaint ds) {
					                  super.updateDrawState(ds);
					                  int linkColor = ContextCompat.getColor(getContext(), R.color.accent);
					                  ds.setColor(linkColor);
					                  ds.setUnderlineText(false);
				                  }
			                  },
					match.getStart(),
					match.getEnd(),
					Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		}
		return spannable;
	}

	private String getTagReplaceText(PostTag postTag) {
		switch (postTag.getType()) {
			case EVENT:
				return "";
			case URL:
				return postTag.getLink() != null ? postTag.getLink().getTitle() : postTag.getTitle();
			case PROGRAM:
			case FUND:
			case FOLLOW:
			case ASSET:
			case USER:
			case POST:
			case UNDEFINED:
			default:
				return postTag.getTitle();
		}
	}

	private void setImages(Post post) {
		if (post.getImages() != null && !post.getImages().isEmpty()) {
			int pos = 0;
			int col = 1;
			int row = 1;
			int newWidth = 100;

			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);

			flexbox.removeAllViews();
			ImageQuality requiredQuality = post.getImages().size() <= 3 ? ImageQuality.ORIGINAL : ImageQuality.LOW;
//			List<PostImageModel> items = new ArrayList<>();
			for (PostImage image : post.getImages()) {
				String logoUrl = image.getResizes().get(image.getResizes().size() - 1).getLogoUrl();
				for (PostImageResize resize : image.getResizes()) {
					if (resize.getQuality().equals(requiredQuality)) {
						logoUrl = resize.getLogoUrl();
						break;
					}
				}
				int maxImagesToAdd = post.getImages().size() < MAX_IMAGES ? post.getImages().size() : MAX_IMAGES;
				if (post.getImages().size() > 1 && pos == Math.round(maxImagesToAdd / 2)) {

					newWidth = (int) ((size.x - 10 * (Math.round(maxImagesToAdd / 2) - 1)) / pos);
					setNewSize(row, newWidth);
					col = 1;
					row++;
				}

				if (pos == maxImagesToAdd - 1) {
//					items.add(new PostImageModel(logoUrl, pos, post.getImages().size() - pos - 1, col, row));
					int count = post.getImages().size() - pos;
					if (count == 1) {
						count = 0;
					}
					addNewPostImageView(logoUrl, pos, count, col, row, post.getId());
					newWidth = (int) ((size.x - 10 * (maxImagesToAdd - Math.round(maxImagesToAdd / 2) - 1)) / (maxImagesToAdd - Math.round(maxImagesToAdd / 2)));
					setNewSize(row, newWidth);
					break;
				}
//				items.add(new PostImageModel(logoUrl, pos, 0, col, row));
				addNewPostImageView(logoUrl, pos, 0, col, row, post.getId());

				col++;
				pos++;
			}
			this.flexbox.setVisibility(View.VISIBLE);


//			this.imagesGrid.setVisibility(View.VISIBLE);
//			imagesGrid.setRequestedColumnCount(2);
//			imagesGrid.setAllowReordering(true);
//			imagesGrid.setRequestedHorizontalSpacing(TypedValueFormatter.toDp(8));
//			imagesGrid.setAdapter(new AsymmetricGridViewAdapter(getContext(), imagesGrid, new PostImagesListAdapter(getContext(), items)));
		}
		else {
			this.flexbox.setVisibility(View.GONE);
//			this.imagesGrid.setVisibility(View.GONE);
		}
	}

	private void setNewSize(int row, int newWidth) {
		for (PostImageView imageView : imageViews) {
			if (imageView.getRow() == row) {
				imageView.resize(newWidth);
			}
		}
	}

	private void addNewPostImageView(String logoUrl, int pos, int count, int col, int row, UUID postId) {
		PostImageView imageView = new PostImageView(getContext());
		imageView.setData(logoUrl, pos, count, col, row, postId);

		imageViews.add(imageView);
		flexbox.addView(imageView);

		FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) imageView.getLayoutParams();
		lp.setFlexGrow(2f);
		imageView.setLayoutParams(lp);
	}

	private void setPostTags(List<PostTag> tags) {
		postTagsGroup.removeAllViews();
		int index = 0;
		for (PostTag tag : tags) {
			if (needDisplayPostTag(tag)) {
				PostTagView view = new PostTagView(getContext());
				view.setPostTag(tag);
				postTagsGroup.addView(view);

				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
				lp.setMargins(index == 0 ? padding : 0, 0, TypedValueFormatter.toDp(16), 0);
				view.setLayoutParams(lp);

				index++;
			}
		}
		scrollview.setVisibility(postTagsGroup.getChildCount() > 0 ? View.VISIBLE : View.GONE);
	}

	private boolean needDisplayPostTag(PostTag tag) {
		return tag.getType().equals(SocialPostTagType.PROGRAM)
				|| tag.getType().equals(SocialPostTagType.FUND)
				|| tag.getType().equals(SocialPostTagType.FOLLOW)
				|| tag.getType().equals(SocialPostTagType.ASSET);
	}


	@Subscribe
	public void onEventMainThread(OnPostImageClickedEvent event) {
		if (post != null && post.getId().equals(event.getPostId())) {
			ArrayList<String> images = new ArrayList<>();

			for (PostImage image : post.getImages()) {
				for (PostImageResize resize : image.getResizes()) {
					if (resize.getQuality().equals(ImageQuality.ORIGINAL)) {
						images.add(resize.getLogoUrl());
						break;
					}
				}
			}

			ImageViewerOverlayView overlayView = new ImageViewerOverlayView(getContext());
			overlayView.setImagesCount(images.size());

			StfalconImageViewer imageViewer = new StfalconImageViewer.Builder<>(getContext(), images, (imageView, image) -> Glide.with(getContext()).load(image).into(imageView))
					.withStartPosition(event.getPosition())
					.withHiddenStatusBar(false)
					.withTransitionFrom(event.getImageView())
					.withOverlayView(overlayView)
					.withImageChangeListener(overlayView)
					.show();

			overlayView.setImageViewer(imageViewer);
		}
	}
}
