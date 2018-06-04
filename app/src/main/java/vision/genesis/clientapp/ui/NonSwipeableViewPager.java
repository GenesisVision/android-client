package vision.genesis.clientapp.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/06/2018.
 */
public class NonSwipeableViewPager extends ViewPager
{
	private boolean allowSwipe = true;

	public NonSwipeableViewPager(Context context) {
		super(context);
	}

	public NonSwipeableViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setAllowSwipe(boolean allow) {
		this.allowSwipe = allow;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return allowSwipe && super.onInterceptTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return allowSwipe && super.onTouchEvent(event);
	}
}