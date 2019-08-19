package vision.genesis.clientapp.feature.main.program.chart;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import rx.Subscription;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.ProgramsManager;
import vision.genesis.clientapp.model.ChartZoomEnum;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/04/2018.
 */

@InjectViewState
public class ChartPresenter extends MvpPresenter<ChartView>
{
	@Inject
	public ProgramsManager programsManager;

	private UUID programId;

	private Subscription getChartSubscription;

	private int lineColor = R.color.colorAccent;

	private ChartZoomEnum currentZoom = ChartZoomEnum.ZOOM_5MIN;

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		GenesisVisionApplication.getComponent().inject(this);

		getViewState().selectZoom(currentZoom);
		getChartData();
	}

	@Override
	public void onDestroy() {
		if (getChartSubscription != null)
			getChartSubscription.unsubscribe();

		super.onDestroy();
	}

	void setProgramId(UUID programId) {
		this.programId = programId;

		getChartData();
	}


	void onZoomClicked(ChartZoomEnum zoom) {
		if (zoom.equals(currentZoom))
			return;
		currentZoom = zoom;

		getViewState().selectZoom(currentZoom);

		getChartData();

	}

	private void getChartData() {
		if (programsManager == null || programId == null)
			return;
		getViewState().showProgress(true);
//		getChartSubscription = investManager.getProfitChart(programId)//currentZoom
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribeOn(Schedulers.io())
//				.subscribe(this::handleGetChartResponse,
//						this::handleGetChartError);
	}

//	private void handleGetChartResponse(TradesChartViewModel response) {
//		getChartSubscription.unsubscribe();
//		getViewState().showProgress(false);
//
//		List<Entry> lineEntries = new ArrayList<>();
//		for (TradeChart chart : response.getProfitChart()) {
//			lineEntries.add(new Entry(chart.getDate().getMillis(), chart.getProfit().floatValue()));
//		}
//
//		getViewState().setChartData(getLineData(lineEntries));
//	}

	private LineData getLineData(List<Entry> data) {
		Collections.sort(data, new EntryXComparator());
		LineData lineData = new LineData();

		lineData.addDataSet(createLineDataSet(data));

		return lineData;
	}

	private LineDataSet createLineDataSet(List<Entry> data) {
		LineDataSet dataSet = new LineDataSet(data, "");

		dataSet.setLabel("");
		dataSet.setDrawValues(false);
		dataSet.setDrawCircles(false);
		dataSet.setColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, lineColor));
//		dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		dataSet.setLineWidth(3f);
		dataSet.setHighlightLineWidth(1.5f);
		dataSet.setHighLightColor(ContextCompat.getColor(GenesisVisionApplication.INSTANCE, R.color.grey400));

		return dataSet;
	}

	private void handleGetChartError(Throwable throwable) {
		getChartSubscription.unsubscribe();
		getViewState().showProgress(false);
	}
}
