package vision.genesis.clientapp.utils;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;

import static android.util.TypedValue.TYPE_INT_COLOR_RGB8;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 19/07/2018.
 */
public class ThemeUtil
{
	public static final String THEME_LIGHT = "theme_light";

	public static final String THEME_DARK = "theme_dark";

	private static final int ANIM_DURATION = 300;

	private static String currentTheme = THEME_LIGHT;

	private static String previousTheme = THEME_LIGHT;

	public static void setTheme(String theme) {
		previousTheme = ThemeUtil.currentTheme;
		ThemeUtil.currentTheme = theme;
	}

	public static int background() {
		return ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.darkBackground);
	}

	public static int getCurrentThemeResource() {
		return getThemeResource(currentTheme);
	}

	public static int getSplashThemeResource() {
		return getSplashThemeResource(currentTheme);
	}

	private static int getThemeResource(String theme) {
		switch (theme) {
			case THEME_LIGHT:
				return R.style.AppTheme_Base_Light;
			case THEME_DARK:
				return R.style.AppTheme_Base_Dark;
			default:
				return R.style.AppTheme_Base_Light;
		}
	}

	private static int getSplashThemeResource(String theme) {
		switch (theme) {
			case THEME_LIGHT:
				return R.style.SplashTheme_Light;
			case THEME_DARK:
				return R.style.SplashTheme_Dark;
			default:
				return R.style.SplashTheme_Light;
		}
	}

	public static int getColorByAttrId(Context context, int themeAttributeId) {
		return getColorByAttrId(context.getTheme(), themeAttributeId);
	}

	public static int getColorByAttrId(Context context, int themeResId, int themeAttributeId) {
		TypedArray array = context.getTheme().obtainStyledAttributes(themeResId, new int[]{themeAttributeId});
		try {
			return array.getColor(0, 0);
		} finally {
			array.recycle();
		}
	}

	private static int getColorByAttrId(Resources.Theme theme, int themeAttributeId) {
		TypedValue outValue = new TypedValue();
		if (theme.resolveAttribute(themeAttributeId, outValue, true)) {
			if (outValue.type == TYPE_INT_COLOR_RGB8)
				return outValue.resourceId == 0
						? outValue.data
						: ContextCompat.getColor(GenesisVisionApplication.INSTANCE, outValue.resourceId);
		}
		return ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.colorNotFound);
	}

	public static ValueAnimator getColorAnimator(Context context, int oldColor, int newColorAttributeId) {
		ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
				oldColor,
				getColorByAttrId(context, getCurrentThemeResource(), newColorAttributeId));
		colorAnimator.setDuration(ANIM_DURATION);
		return colorAnimator;
	}
}
