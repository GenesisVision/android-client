package vision.genesis.clientapp.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/09/2018.
 */
public class TabLayoutUtil
{
	public static void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
		int externalMarginDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, externalMargin, Resources.getSystem().getDisplayMetrics());
		int internalMarginDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, internalMargin, Resources.getSystem().getDisplayMetrics());
		View tabStrip = tabLayout.getChildAt(0);
		if (tabStrip instanceof ViewGroup) {
			ViewGroup tabStripGroup = (ViewGroup) tabStrip;
			int childCount = ((ViewGroup) tabStrip).getChildCount();
			for (int i = 0; i < childCount; i++) {
				View tabView = tabStripGroup.getChildAt(i);
				//set minimum width to 0 for instead for small texts, indicator is not wrapped as expected
				tabView.setMinimumWidth(0);
				// set padding to 0 for wrapping indicator as title
				tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
				// setting custom margin between tabs
				if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
					ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
					if (i == 0) {
						// left
						settingMargin(layoutParams, externalMarginDp, internalMarginDp);
					}
					else if (i == childCount - 1) {
						// right
						settingMargin(layoutParams, internalMarginDp, externalMarginDp);
					}
					else {
						// internal
						settingMargin(layoutParams, internalMarginDp, internalMarginDp);
					}
				}
			}

			tabLayout.requestLayout();
		}
	}

	private static void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			layoutParams.setMarginStart(start);
			layoutParams.setMarginEnd(end);
			layoutParams.leftMargin = start;
			layoutParams.rightMargin = end;
		}
		else {
			layoutParams.leftMargin = start;
			layoutParams.rightMargin = end;
		}
	}
}
