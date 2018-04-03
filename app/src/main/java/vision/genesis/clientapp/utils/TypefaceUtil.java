package vision.genesis.clientapp.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;
import java.util.Hashtable;

import timber.log.Timber;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/03/2018.
 */

public class TypefaceUtil
{
	private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

	/**
	 * Using reflection to override default typeface
	 * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT TYPEFACE WHICH WILL BE OVERRIDDEN
	 *
	 * @param context                    to work with assets
	 * @param defaultFontNameToOverride  for example "monospace"
	 * @param customFontFileNameInAssets file name of the font from assets
	 */
	public static void overrideFont(Context context, String defaultFontNameToOverride, String customFontFileNameInAssets) {
		try {
			final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), customFontFileNameInAssets);

			final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(defaultFontNameToOverride);
			defaultFontTypefaceField.setAccessible(true);
			defaultFontTypefaceField.set(null, customFontTypeface);
		} catch (Exception e) {
			Timber.e("Can not set custom font %s instead of %s", customFontFileNameInAssets, defaultFontNameToOverride);
		}
	}

	public static Typeface get(String name) {
		Typeface tf = fontCache.get(name);
		if (tf == null) {
			try {
				tf = Typeface.createFromAsset(GenesisVisionApplication.INSTANCE.getAssets(), name);
			} catch (Exception e) {
				return null;
			}
			fontCache.put(name, tf);
		}
		return tf;
	}

	public static Typeface light() {
		return get(GenesisVisionApplication.INSTANCE.getString(R.string.font_neuzeitgro_light));
	}

	public static Typeface regular() {
		return get(GenesisVisionApplication.INSTANCE.getString(R.string.font_neuzeitgro_regular));
	}

	public static Typeface bold() {
		return get(GenesisVisionApplication.INSTANCE.getString(R.string.font_neuzeitgro_bold));
	}

	public static Typeface black() {
		return get(GenesisVisionApplication.INSTANCE.getString(R.string.font_neuzeitgro_black));
	}
}