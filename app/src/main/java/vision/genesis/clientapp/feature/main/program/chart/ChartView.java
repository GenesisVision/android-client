package vision.genesis.clientapp.feature.main.program.chart;

import com.arellomobile.mvp.MvpView;
import com.github.mikephil.charting.data.LineData;

import vision.genesis.clientapp.model.ChartZoomEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/04/2018.
 */

interface ChartView extends MvpView
{
	void setChartData(LineData data);

	void finishActivity();

	void showProgress(boolean show);

	void selectZoom(ChartZoomEnum currentZoom);
}