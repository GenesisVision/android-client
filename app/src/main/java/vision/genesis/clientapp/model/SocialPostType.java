package vision.genesis.clientapp.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 31/07/2020.
 */
public enum SocialPostType
{
	POST("Post"),
	COMMENT("Comment");

	public static SocialPostType fromValue(String text) {
		for (SocialPostType b : SocialPostType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	private String value;

	SocialPostType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static class Adapter extends TypeAdapter<SocialPostType>
	{
		@Override
		public void write(final JsonWriter jsonWriter, final SocialPostType enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public SocialPostType read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return SocialPostType.fromValue(String.valueOf(value));
		}
	}
}
