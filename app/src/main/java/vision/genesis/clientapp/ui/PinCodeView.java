package vision.genesis.clientapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 15/06/2018.
 */

public class PinCodeView extends LinearLayout
{
	private List<PinCodeDotView> dots = new ArrayList<>();

	public PinCodeView(Context context) {
		super(context);
		initView();
	}

	public PinCodeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PinCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		this.setOrientation(HORIZONTAL);
		createDots(4);
	}

	private void createDots(Integer dotsCount) {
		this.removeAllViews();
		for (int i = 0; i < dotsCount; i++) {
			PinCodeDotView newDot = new PinCodeDotView(getContext());
			dots.add(newDot);
			addDotToLayout(newDot);
		}
	}

	private void addDotToLayout(PinCodeDotView dot) {
		LayoutParams lp = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
		lp.weight = 1;
		lp.gravity = Gravity.CENTER;
		dot.setLayoutParams(lp);
		this.addView(dot);
	}

	public void selectDots(int selectedDotsCount) {
		for (int i = 0; i < dots.size(); i++) {
			dots.get(i).setSelected(i < selectedDotsCount);
		}
	}

	public void setError(boolean error) {
		for (int i = 0; i < dots.size(); i++) {
			dots.get(i).setError(error);
		}
	}
}
