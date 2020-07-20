package vision.genesis.clientapp.model;

import io.swagger.client.model.PostTag;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/07/2020.
 */
public class PostTagMatch
{
	private String tagString;

	private String replaceString;

	private int start;

	private int end;

	private String hashtag;

	private PostTag postTag;

	public PostTagMatch(String tagString, String replaceString, int start, int end, PostTag postTag, String hashtag) {
		this.tagString = tagString;
		this.replaceString = replaceString;
		this.start = start;
		this.end = end;
		this.postTag = postTag;
		this.hashtag = hashtag;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public PostTag getPostTag() {
		return postTag;
	}

	public void setPostTag(PostTag postTag) {
		this.postTag = postTag;
	}

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public String getHashTag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getReplaceString() {
		return replaceString;
	}

	public void setReplaceString(String replaceString) {
		this.replaceString = replaceString;
	}
}
