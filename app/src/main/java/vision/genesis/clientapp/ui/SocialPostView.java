package vision.genesis.clientapp.ui;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.stfalcon.imageviewer.StfalconImageViewer;

import org.greenrobot.eventbus.EventBus;

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
import vision.genesis.clientapp.feature.main.social.post.actions.SocialPostActionsBottomSheetFragment;
import vision.genesis.clientapp.managers.SocialManager;
import vision.genesis.clientapp.model.PostTagMatch;
import vision.genesis.clientapp.model.SocialPostType;
import vision.genesis.clientapp.model.UserDetailsModel;
import vision.genesis.clientapp.model.events.OnHashTagClickedEvent;
import vision.genesis.clientapp.model.events.OnShowRepostEvent;
import vision.genesis.clientapp.model.events.SetPostDeletedEvent;
import vision.genesis.clientapp.model.events.ShowReportPostEvent;
import vision.genesis.clientapp.model.events.ShowUserDetailsEvent;
import vision.genesis.clientapp.utils.DateTimeUtil;
import vision.genesis.clientapp.utils.PostTagClickHandler;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;
import vision.genesis.clientapp.utils.TypedValueFormatter;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 13/06/2020.
 */

public class SocialPostView extends RelativeLayout implements PostImageView.PostImageClickListener, SocialPostActionsBottomSheetFragment.Listener
{
	public interface Listener
	{
		void onPostMenuButtonClicked(Post post, SocialPostActionsBottomSheetFragment.Listener listener);

		void onPostEditClicked(Post post);

		void onPostDeleted(Post post);
	}

	private final static int MAX_IMAGES = 7;

	private static final int MAX_TEXT_LINES = 7;

	@Inject
	public SocialManager socialManager;

	@BindView(R.id.group_main)
	public ViewGroup mainGroup;

	@BindView(R.id.group_deleted)
	public ViewGroup deletedGroup;

	@BindView(R.id.author_logo)
	public AvatarView authorLogo;

	@BindView(R.id.author_name)
	public TextView authorName;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.pin)
	public ImageView pin;

	@BindView(R.id.button_menu)
	public ImageView menuButton;

	@BindView(R.id.view_event)
	public PostEventView eventView;

	@BindView(R.id.group_text)
	public ViewGroup textGroup;

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.button_expand)
	public ViewGroup expandButton;

	@BindView(R.id.flex_box)
	public FlexboxLayout flexbox;

	@BindView(R.id.scrollview)
	public HorizontalScrollView scrollview;

	@BindView(R.id.group_post_tags)
	public LinearLayout postTagsGroup;

	@BindView(R.id.group_repost)
	public ViewGroup repostGroup;

	@BindView(R.id.frame_repost)
	public FrameLayout repostFrame;

	@BindView(R.id.bottom)
	public ViewGroup bottom;

	@BindView(R.id.button_comments)
	public LinearLayout commentsButton;

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

	public Subscription pinSubscription;

	public Subscription unpinSubscription;

	public Subscription deleteSubscription;

	private Unbinder unbinder;

	private Post post;

	private Listener listener;

	private ArrayList<PostImageView> imageViews = new ArrayList<>();

	private boolean isDetailsMode = false;

	private boolean isRepostMode = false;

	private boolean blockTextClick = false;

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
		if (pinSubscription != null) {
			pinSubscription.unsubscribe();
		}
		if (unpinSubscription != null) {
			unpinSubscription.unsubscribe();
		}
		if (deleteSubscription != null) {
			deleteSubscription.unsubscribe();
		}

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_social_post, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);
	}

	@OnClick(R.id.group_main)
	public void onPostClicked() {
		showPostDetails(false);
	}

	@OnClick(R.id.text)
	public void onTextClicked() {
		if (!blockTextClick) {
			showPostDetails(false);
		}
		blockTextClick = false;
	}

	@OnClick(R.id.group_author)
	public void onAuthorClicked() {
		if (post != null) {
			UserDetailsModel model = new UserDetailsModel(
					post.getAuthor().getId(),
					post.getAuthor().getLogoUrl(),
					post.getAuthor().getUsername(),
					post.getAuthor().getRegistrationDate());
			EventBus.getDefault().post(new ShowUserDetailsEvent(model));
		}
	}

	@OnClick(R.id.button_expand)
	public void onExpandClicked() {
		this.expandButton.setVisibility(View.GONE);
		ValueAnimator animator = ValueAnimator.ofInt(text.getHeight(), text.getLineCount() * text.getLineHeight());
		animator.addUpdateListener(animation -> text.setHeight((int) animator.getAnimatedValue()));
		animator.setDuration(300);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();
	}

	@OnClick(R.id.button_undo_delete)
	public void onUndoDeleteClicked() {
		if (post != null && socialManager != null) {
			setLikeSubscription = socialManager.revertDelete(post.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
						post.setIsDeleted(false);
						EventBus.getDefault().post(new SetPostDeletedEvent(post, false));
						updateData(post);
					}, throwable -> {
					});
		}
	}

	@OnClick(R.id.button_menu)
	public void onMenuClicked() {
		if (post != null && listener != null) {
			listener.onPostMenuButtonClicked(post, this);
		}
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
		if (post != null) {
			EventBus.getDefault().post(new OnShowRepostEvent(post));
		}
	}

	@OnClick(R.id.button_comments)
	public void onCommentsClicked() {
		showPostDetails(true);
	}

	public void setDetailsMode(boolean isDetailsMode) {
		this.isDetailsMode = isDetailsMode;
		this.menuButton.setVisibility(isDetailsMode ? View.GONE : View.VISIBLE);
		this.commentsButton.setVisibility(isDetailsMode ? View.GONE : View.VISIBLE);
		this.text.setMaxLines(isDetailsMode ? Integer.MAX_VALUE : MAX_TEXT_LINES);
	}

	public void setRepostMode(boolean isRepostMode) {
		this.isRepostMode = isRepostMode;
		this.menuButton.setVisibility(isRepostMode ? View.GONE : View.VISIBLE);
		this.bottom.setVisibility(isRepostMode ? View.GONE : View.VISIBLE);
	}

	public void setPost(Post post) {
		this.post = post;
		updateData(post);
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private void showPostDetails(boolean showComments) {
		if (post != null) {
			EventBus.getDefault().post(new OnShowPostDetailsEvent(post.getId(), post, showComments));
		}
	}

	private void updateData(Post post) {
		if (post.isIsDeleted()) {
			this.deletedGroup.setVisibility(View.VISIBLE);
			this.mainGroup.setVisibility(View.GONE);
			return;
		}
		this.deletedGroup.setVisibility(View.GONE);
		this.mainGroup.setVisibility(View.VISIBLE);
		this.authorLogo.setImage(post.getAuthor().getLogoUrl(), 50, 50);
		this.authorName.setText(post.getAuthor().getUsername());
		this.date.setText(DateTimeUtil.formatEventDateTime(post.getDate()));
		this.eventView.setVisibility(View.GONE);
		this.expandButton.setVisibility(View.INVISIBLE);
		this.repostGroup.setVisibility(View.GONE);
		this.repostFrame.removeAllViews();

		showPin(post.isIsPinned());

		if (post.getText() != null && !post.getText().isEmpty()) {
			this.textGroup.setVisibility(View.VISIBLE);

			parseText(post.getText());
			this.text.post(this::updateTextLinesCount);
		}
		else {
			this.textGroup.setVisibility(View.GONE);
		}

		if (isRepostMode) {
			getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
			{
				@Override
				public void onGlobalLayout() {
					if (getWidth() > 0) {
						getViewTreeObserver().removeOnGlobalLayoutListener(this);
						setImages(post);
					}
				}
			});
		}
		else {
			setImages(post);
		}
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

	private void showPin(boolean show) {
		this.pin.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	private void updateTextLinesCount() {
		if (isDetailsMode) {
			LayoutParams lp = (LayoutParams) this.text.getLayoutParams();
			lp.height = LayoutParams.WRAP_CONTENT;
			this.text.setLayoutParams(lp);
			this.text.setMaxLines(Integer.MAX_VALUE);
			this.expandButton.setVisibility(View.GONE);
		}
		else {
			if (this.text.getLineCount() > MAX_TEXT_LINES) {
				this.text.setHeight(MAX_TEXT_LINES * this.text.getLineHeight());
				this.expandButton.setVisibility(View.VISIBLE);
			}
			else {
				this.text.setHeight(this.text.getLineCount() * this.text.getLineHeight());
			}
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
					this.textGroup.setVisibility(View.GONE);
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
						                  blockTextClick = true;
						                  handlePostTagClick(match.getPostTag());
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
					                  if (match.getHashTag() != null) {
						                  blockTextClick = true;
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
		if (post != null && post.getImages() != null && !post.getImages().isEmpty()) {
			int pos = 0;
			int col = 1;
			int row = 1;
			int newWidth;

			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = 0;
			if (isRepostMode) {
				width = this.getWidth();
			}
			if (width == 0) {
				width = size.x;
			}

			flexbox.removeAllViews();
			ImageQuality requiredQuality = post.getImages().size() <= 3 ? ImageQuality.ORIGINAL : ImageQuality.LOW;
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
					newWidth = (width - 10 * (Math.round(maxImagesToAdd / 2) - 1)) / pos;
					setNewSize(row, newWidth);
					col = 1;
					row++;
				}

				if (pos == maxImagesToAdd - 1) {
					int count = post.getImages().size() - pos;
					if (count == 1) {
						count = 0;
					}
					addNewPostImageView(logoUrl, pos, count, col, row, post.getId());
					newWidth = (width - 10 * (maxImagesToAdd - Math.round(maxImagesToAdd / 2) - 1)) / (maxImagesToAdd - Math.round(maxImagesToAdd / 2));
					setNewSize(row, newWidth);
					break;
				}
				addNewPostImageView(logoUrl, pos, 0, col, row, post.getId());

				col++;
				pos++;
			}
			this.flexbox.setVisibility(View.VISIBLE);
		}
		else {
			this.flexbox.setVisibility(View.GONE);
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
		imageView.setListener(this);

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
				view.setOnClickListener(view1 -> handlePostTagClick(tag));
				postTagsGroup.addView(view);

				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
				lp.setMargins(index == 0 ? padding : 0, 0, TypedValueFormatter.toDp(16), 0);
				view.setLayoutParams(lp);

				index++;
			}
			if (tag.getType().equals(SocialPostTagType.POST)) {
				repostGroup.setVisibility(View.VISIBLE);
				SocialPostView view = new SocialPostView(getContext());
				view.setRepostMode(true);
				view.setPost(tag.getPost());
				repostFrame.addView(view);
			}
		}
		scrollview.setVisibility(postTagsGroup.getChildCount() > 0 ? View.VISIBLE : View.GONE);
	}

	private void handlePostTagClick(PostTag tag) {
		PostTagClickHandler.handlePostTagClick(tag);
	}

	private boolean needDisplayPostTag(PostTag tag) {
		return tag.getType().equals(SocialPostTagType.PROGRAM)
				|| tag.getType().equals(SocialPostTagType.FUND)
				|| tag.getType().equals(SocialPostTagType.FOLLOW)
				|| tag.getType().equals(SocialPostTagType.ASSET)
				|| tag.getType().equals(SocialPostTagType.USER);
	}

	@Override
	public void onPostImageClicked(ImageView imageView, String imageUrl, int position, UUID postId) {
		if (post != null && post.getId().equals(postId)) {
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

			StfalconImageViewer imageViewer = new StfalconImageViewer.Builder<>(getContext(), images,
					(loadingImageView, image) -> Glide.with(getContext()).load(image).into(loadingImageView))
					.withStartPosition(position)
					.withHiddenStatusBar(false)
					.withTransitionFrom(imageView)
					.withOverlayView(overlayView)
					.withImageChangeListener(overlayView)
					.show();

			overlayView.setImageViewer(imageViewer);
		}
	}

	@Override
	public void onEditClicked(Post post, SocialPostType type) {
		if (listener != null) {
			listener.onPostEditClicked(post);
		}
	}

	@Override
	public void onPinClicked(Post post) {
		if (socialManager != null && post != null) {
			pinSubscription = socialManager.pinPost(post.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
						pinSubscription.unsubscribe();
						this.post.isPinned(true);
						showPin(true);
					}, throwable -> pinSubscription.unsubscribe());
		}
	}

	@Override
	public void onUnpinClicked(Post post) {
		if (socialManager != null && post != null) {
			unpinSubscription = socialManager.unpinPost(post.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
						unpinSubscription.unsubscribe();
						this.post.isPinned(false);
						showPin(false);
					}, throwable -> unpinSubscription.unsubscribe());
		}
	}

	@Override
	public void onDeleteClicked(Post post) {
		if (socialManager != null && post != null) {
			deleteSubscription = socialManager.deletePost(post.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
						deleteSubscription.unsubscribe();
						this.post.isDeleted(true);
						EventBus.getDefault().post(new SetPostDeletedEvent(post, true));
						if (listener != null) {
							listener.onPostDeleted(post);
						}
					}, throwable -> deleteSubscription.unsubscribe());
		}
	}

	@Override
	public void onCopyLinkClicked(Post post) {
		ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText("link", StringFormatUtil.getPostUrl(post.getUrl()));
		if (clipboardManager != null) {
			clipboardManager.setPrimaryClip(clipData);
			Toast.makeText(getContext(), getContext().getString(R.string.copied_to_the_clipboard), Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getContext(), getContext().getString(R.string.cannot_copy), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onReportClicked(Post post) {
		EventBus.getDefault().post(new ShowReportPostEvent(post));
	}

	@Override
	public void onShareClicked(Post post) {
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
		intent.putExtra(android.content.Intent.EXTRA_TEXT, StringFormatUtil.getPostUrl(post.getUrl()));
		getContext().startActivity(Intent.createChooser(intent, getContext().getString(R.string.share)));
	}
}
