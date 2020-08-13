package vision.genesis.clientapp.ui;

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
import android.view.WindowManager;
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

public class SocialCommentView extends RelativeLayout implements PostImageView.PostImageClickListener, SocialPostActionsBottomSheetFragment.Listener
{
	public interface Listener
	{
		void onCommentMenuButtonClicked(Post comment, SocialPostActionsBottomSheetFragment.Listener listener);

		void onReplyCommentClicked(Post comment);

		void onCommentEditClicked(Post comment);

		void onCommentDeleted(Post comment);
	}

	private final static int MAX_IMAGES = 7;

	@Inject
	public SocialManager socialManager;

	@BindView(R.id.author_logo)
	public AvatarView authorLogo;

	@BindView(R.id.author_name)
	public TextView authorName;

	@BindView(R.id.date)
	public TextView date;

	@BindView(R.id.button_menu)
	public ImageView menuButton;

	@BindView(R.id.text)
	public TextView text;

	@BindView(R.id.flex_box)
	public FlexboxLayout flexbox;

	@BindView(R.id.scrollview)
	public HorizontalScrollView scrollview;

	@BindView(R.id.group_post_tags)
	public LinearLayout postTagsGroup;

	@BindView(R.id.button_reply)
	public TextView replyButton;

	@BindView(R.id.likes_count)
	public TextView likesCount;

	@BindView(R.id.icon_like)
	public ImageView likeIcon;

	@BindDimen(R.dimen.comment_padding)
	public int commentPadding;

	private Subscription setLikeSubscription;

	private Subscription deleteSubscription;

	private Unbinder unbinder;

	private Post comment;

	private Listener listener;

	private ArrayList<PostImageView> imageViews = new ArrayList<>();

	private boolean canCommentPost;

	public SocialCommentView(Context context) {
		super(context);
		initView();
	}

	public SocialCommentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public SocialCommentView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void onDestroy() {
		if (setLikeSubscription != null) {
			setLikeSubscription.unsubscribe();
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
		inflate(getContext(), R.layout.view_social_comment, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);
	}

	@OnClick(R.id.author_logo)
	public void onAuthorLogoClicked() {
		showUserDetails();
	}

	@OnClick(R.id.author_name)
	public void onAuthorNameClicked() {
		showUserDetails();
	}

	private void showUserDetails() {
		if (comment != null) {
			UserDetailsModel model = new UserDetailsModel(
					comment.getAuthor().getId(),
					comment.getAuthor().getLogoUrl(),
					comment.getAuthor().getUsername(),
					comment.getAuthor().getRegistrationDate());
			EventBus.getDefault().post(new ShowUserDetailsEvent(model));
		}
	}

	@OnClick(R.id.button_menu)
	public void onMenuClicked() {
		if (comment != null) {
			listener.onCommentMenuButtonClicked(comment, this);
		}
	}

	@OnClick(R.id.button_like)
	public void onLikeClicked() {
		if (comment != null && comment.getPersonalDetails() != null) {
			comment.getPersonalDetails().setIsLiked(!comment.getPersonalDetails().isIsLiked());
			comment.setLikesCount(comment.getPersonalDetails().isIsLiked()
					? comment.getLikesCount() + 1
					: comment.getLikesCount() - 1);
			updateData(comment);
			setLike();
		}
	}

	@OnClick(R.id.button_reply)
	public void onReplyClicked() {
		if (comment != null) {
			if (listener != null) {
				listener.onReplyCommentClicked(comment);
			}
		}
	}

	private void setLike() {
		if (socialManager != null && comment != null) {
			setLikeSubscription = (comment.getPersonalDetails().isIsLiked()
					? socialManager.likePost(comment.getId())
					: socialManager.unlikePost(comment.getId()))
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
					}, throwable -> {
					});
		}
	}

	public void showMenuButton(boolean show) {
		this.menuButton.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void setCanCommentPost(boolean canCommentPost) {
		this.canCommentPost = canCommentPost;
		this.replyButton.setVisibility(canCommentPost ? View.VISIBLE : View.GONE);
	}

	public void setComment(Post comment) {
		this.comment = comment;
		updateData(comment);
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private void updateData(Post comment) {
		this.authorLogo.setImage(comment.getAuthor().getLogoUrl(), 50, 50);
		this.authorName.setText(comment.getAuthor().getUsername());
		this.date.setText(DateTimeUtil.formatEventDateTime(comment.getDate()));

		if (comment.getText() != null && !comment.getText().isEmpty()) {
			this.text.setVisibility(View.VISIBLE);
			parseText(comment.getText());
		}
		else {
			this.text.setVisibility(View.GONE);
		}
		setImages(comment);
		setPostTags(comment.getTags());

//		this.replyButton.setVisibility(comment.getPersonalDetails() != null
//				&& comment.getPersonalDetails().isCanComment()
//				&& canCommentPost
//				? View.VISIBLE : View.GONE);

		this.likesCount.setText(String.valueOf(comment.getLikesCount()));
		this.likesCount.setVisibility(comment.getLikesCount() > 0 ? View.VISIBLE : View.GONE);

		this.likeIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), comment.getPersonalDetails().isIsLiked() ? R.drawable.icon_like_full : R.drawable.icon_like));
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			likeIcon.setImageTintList(comment.getPersonalDetails().isIsLiked()
					? ColorStateList.valueOf(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorAccent))
					: ColorStateList.valueOf(ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextSecondary)));
			this.likesCount.setTextColor(ThemeUtil.getColorByAttrId(getContext(),
					comment.getPersonalDetails().isIsLiked() ? R.attr.colorAccent : R.attr.colorTextSecondary));
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
			for (PostTag postTag : comment.getTags()) {
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

	private void setImages(Post comment) {
		if (comment.getImages() != null && !comment.getImages().isEmpty()) {
			int pos = 0;
			int col = 1;
			int row = 1;
			int newWidth = 100;

			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);

			flexbox.removeAllViews();
			ImageQuality requiredQuality = comment.getImages().size() <= 3 ? ImageQuality.ORIGINAL : ImageQuality.LOW;

			for (PostImage image : comment.getImages()) {
				String logoUrl = image.getResizes().get(image.getResizes().size() - 1).getLogoUrl();
				for (PostImageResize resize : image.getResizes()) {
					if (resize.getQuality().equals(requiredQuality)) {
						logoUrl = resize.getLogoUrl();
						break;
					}
				}
				int maxImagesToAdd = comment.getImages().size() < MAX_IMAGES ? comment.getImages().size() : MAX_IMAGES;
				if (comment.getImages().size() > 1 && pos == Math.round(maxImagesToAdd / 2)) {

					newWidth = (int) ((size.x - 10 * (Math.round(maxImagesToAdd / 2) - 1)) / pos);
					setNewSize(row, newWidth);
					col = 1;
					row++;
				}

				if (pos == maxImagesToAdd - 1) {
					int count = comment.getImages().size() - pos;
					if (count == 1) {
						count = 0;
					}
					addNewPostImageView(logoUrl, pos, count, col, row, comment.getId());
					newWidth = (int) ((size.x - 10 * (maxImagesToAdd - Math.round(maxImagesToAdd / 2) - 1)) / (maxImagesToAdd - Math.round(maxImagesToAdd / 2)));
					setNewSize(row, newWidth);
					break;
				}
				addNewPostImageView(logoUrl, pos, 0, col, row, comment.getId());

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
				lp.setMargins(index == 0 ? commentPadding : 0, 0, TypedValueFormatter.toDp(16), 0);
				view.setLayoutParams(lp);

				index++;
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
		if (comment != null && comment.getId().equals(postId)) {
			ArrayList<String> images = new ArrayList<>();

			for (PostImage image : comment.getImages()) {
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
	public void onEditClicked(Post comment, SocialPostType type) {
		if (listener != null) {
			listener.onCommentEditClicked(comment);
		}
	}

	@Override
	public void onPinClicked(Post comment) {

	}

	@Override
	public void onUnpinClicked(Post comment) {

	}

	@Override
	public void onDeleteClicked(Post comment) {
		if (socialManager != null && comment != null) {
			deleteSubscription = socialManager.deletePost(comment.getId())
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(response -> {
						deleteSubscription.unsubscribe();
						this.comment.isDeleted(true);
						this.setVisibility(View.GONE);
						if (listener != null) {
							listener.onCommentDeleted(comment);
						}
					}, throwable -> deleteSubscription.unsubscribe());
		}
	}

	@Override
	public void onCopyLinkClicked(Post comment) {
		ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
		ClipData clipData = ClipData.newPlainText("link", StringFormatUtil.getCommentUrl(comment.getUrl()));
		if (clipboardManager != null) {
			clipboardManager.setPrimaryClip(clipData);
			Toast.makeText(getContext(), getContext().getString(R.string.copied_to_the_clipboard), Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getContext(), getContext().getString(R.string.cannot_copy), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onReportClicked(Post comment) {
		EventBus.getDefault().post(new ShowReportPostEvent(comment));
	}

	@Override
	public void onShareClicked(Post comment) {
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
		intent.putExtra(android.content.Intent.EXTRA_TEXT, StringFormatUtil.getCommentUrl(comment.getUrl()));
		getContext().startActivity(Intent.createChooser(intent, getContext().getString(R.string.share)));
	}
}
