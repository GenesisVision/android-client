package vision.genesis.clientapp.feature.main.terminal.tradingview_chart

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.interfaces.ChartApi
import com.tradingview.lightweightcharts.api.interfaces.SeriesApi
import com.tradingview.lightweightcharts.api.options.models.*
import com.tradingview.lightweightcharts.api.series.common.SeriesData
import com.tradingview.lightweightcharts.api.series.models.BarData
import com.tradingview.lightweightcharts.api.series.models.PriceScaleId
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.runtime.plugins.DateTimeFormat
import com.tradingview.lightweightcharts.runtime.plugins.PriceFormatter
import com.tradingview.lightweightcharts.runtime.plugins.TimeFormatter
import com.tradingview.lightweightcharts.view.ChartsView
import com.tradingview.lightweightcharts.view.gesture.TouchDelegate
import io.swagger.client.model.BinanceKlineInterval
import io.swagger.client.model.BinanceRawKlineItemsViewModel
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import vision.genesis.clientapp.GenesisVisionApplication
import vision.genesis.clientapp.R
import vision.genesis.clientapp.managers.TerminalManager
import vision.genesis.clientapp.model.terminal.binance_socket.KlineModel
import vision.genesis.clientapp.net.ApiErrorResolver
import vision.genesis.clientapp.utils.TabLayoutUtil
import vision.genesis.clientapp.utils.ThemeUtil
import java.util.*
import javax.inject.Inject

@SuppressLint("ClickableViewAccessibility")
class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var data: MutableList<BarData>
    private var selectedTimeframeIndex: Int = 0

    @Inject
    lateinit var terminalManager: TerminalManager

    private lateinit var chart: ChartsView
    private lateinit var tabLayout: TabLayout
    private lateinit var progressBar: ProgressBar

    //    lateinit var histogramSeries: SeriesApi
    private var series: MutableList<SeriesApi> = mutableListOf()

    private var dateTimeFormat = DateTimeFormat.DATE_TIME

    private var pricePrecision = 2

    var symbol: String = ""
        set(value) {
            field = value
            pricePrecision = terminalManager.getSymbolPrecision(value)
            updateChartOptions()
            getKlineData()
        }

    private var klineDataSubscription: Subscription? = null
    private var klineUpdateSubscription: Subscription? = null

    init {
        initLayout()
        GenesisVisionApplication.getComponent().inject(this)

        initTimeframes()
        updateChartOptions()


//        chart.api.addHistogramSeries(
//            onSeriesCreated = { series ->
//                histogramSeries = series
//            }
//        )

        chart.addTouchDelegate(object : TouchDelegate {
            override fun beforeTouchEvent(view: ViewGroup) {
            }

            override fun onTouchEvent(view: ViewGroup, event: MotionEvent): Boolean {
                view.parent.requestDisallowInterceptTouchEvent(true)
                if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
                    view.parent.requestDisallowInterceptTouchEvent(false)
                }
                return false
            }
        })
//        { v: View, me: MotionEvent ->
//            v.parent.requestDisallowInterceptTouchEvent(true)
//            if (me.action == MotionEvent.ACTION_UP || me.action == MotionEvent.ACTION_CANCEL) {
//                v.parent.requestDisallowInterceptTouchEvent(false)
//            }
//            true
//        }
    }

    private fun initLayout() {
        val view = View.inflate(context, R.layout.view_tradingview_chart, this)

        chart = view.findViewById(R.id.chart) as ChartsView
        tabLayout = view.findViewById(R.id.tablayout_timeframes) as TabLayout
        progressBar = view.findViewById(R.id.chart_progress_bar) as ProgressBar
    }

    private fun initTimeframes() {
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                selectedTimeframeIndex = tab.position
                updateDateTimeFormat()
                getKlineData()
                if (tab.customView != null && tab.customView!!.javaClass == TimeframeTabView::class.java) {
                    (tab.customView as TimeframeTabView?)!!.setSelectedState(true)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                if (tab.customView != null && tab.customView!!.javaClass == TimeframeTabView::class.java) {
                    (tab.customView as TimeframeTabView?)!!.setSelectedState(false)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                if (tab.customView != null && tab.customView!!.javaClass == TimeframeTabView::class.java) {
                    (tab.customView as TimeframeTabView?)!!.setSelectedState(true)
                }
            }
        })

        val timeframes = context.resources.getStringArray(R.array.chart_timeframes)
        timeframes.forEachIndexed { index, timeframe ->
            addTab(tabLayout.newTab().setCustomView(getTabView(index, timeframe)), index == 0)
        }
    }

    private fun updateChartOptions() {
        chart.api.applyOptions {
            layout = layoutOptions {
                textColor =
                    ThemeUtil.getColorByAttrId(context, R.attr.colorTextSecondary).toIntColor()
            }
            grid = gridOptions {
                vertLines = gridLineOptions {
                    color = ThemeUtil.getColorByAttrId(context, R.attr.colorCard).toIntColor()
                }
                horzLines = gridLineOptions {
                    color = ThemeUtil.getColorByAttrId(context, R.attr.colorCard).toIntColor()
                }
            }
            handleScroll = handleScrollOptions {
                vertTouchDrag = false
            }
            localization = localizationOptions {
                locale = Locale.getDefault().toLanguageTag()
                priceFormatter = PriceFormatter(template = "{price:#0:#$pricePrecision}")
                timeFormatter = TimeFormatter(
                    locale = Locale.getDefault().toLanguageTag(),
                    dateTimeFormat = dateTimeFormat
                )
            }
        }
    }

    private fun updateDateTimeFormat() {
        dateTimeFormat = when (BinanceKlineInterval.values()[selectedTimeframeIndex]) {
            BinanceKlineInterval.ONEDAY -> DateTimeFormat.DATE
            BinanceKlineInterval.THREEDAY -> DateTimeFormat.DATE
            BinanceKlineInterval.ONEWEEK -> DateTimeFormat.DATE
            BinanceKlineInterval.ONEMONTH -> DateTimeFormat.DATE
            else -> DateTimeFormat.DATE_TIME
        }
        updateChartOptions()
    }

    private fun getTabView(index: Int, text: String): View {
        val view = TimeframeTabView(context)
        view.setData(index, text)
        return view
    }

    private fun addTab(tab: TabLayout.Tab, selected: Boolean) {
        if (tab.position != TabLayout.Tab.INVALID_POSITION) {
            return
        }
        tabLayout.addTab(tab, selected)
        TabLayoutUtil.wrapTabIndicatorToTitle(tabLayout, 0, 0)
    }

    private fun getKlineData() {
        if (symbol.isNotEmpty()) {
            klineDataSubscription?.unsubscribe()
            klineUpdateSubscription?.unsubscribe()
            showProgress(true)
            for (series in this.series) {
                chart.api.removeSeries(series)
            }
            this.series.clear()
            klineDataSubscription = terminalManager.getKlineData(
                symbol,
                BinanceKlineInterval.values()[selectedTimeframeIndex],
                null,
                null,
                null
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showProgress(false)
                    updateData(it)
                    subscribeToKlineUpdates()
                }, {
                    showProgress(false)
                    ApiErrorResolver.resolveErrors(it) { message: String -> this.showToast(message) }
                })
        }
    }

    private fun subscribeToKlineUpdates() {
        if (symbol.isNotEmpty()) {
            klineUpdateSubscription?.unsubscribe()
            klineUpdateSubscription = terminalManager.getKlineSubject(
                symbol,
                BinanceKlineInterval.values()[selectedTimeframeIndex]
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    handleKlinesUpdate(it)
                }, {
                    ApiErrorResolver.resolveErrors(it) { message: String -> this.showToast(message) }
                })
        }
    }

    private fun handleKlinesUpdate(kline: KlineModel) {
        val newData = BarData(
            Time.Utc(kline.openTime.millis / 1000),
            kline.open.toFloat(),
            kline.high.toFloat(),
            kline.low.toFloat(),
            kline.close.toFloat()
        )

        series[series.size - 1].update(newData)
    }

    private fun updateData(model: BinanceRawKlineItemsViewModel) {
        data = mutableListOf()
        for (item in model.items) {
            data.add(
                BarData(
                    Time.Utc(item.openTime.millis / 1000),
                    item.open.toFloat(),
                    item.high.toFloat(),
                    item.low.toFloat(),
                    item.close.toFloat()
                )
            )
        }

        createSeriesWithData(data, PriceScaleId.RIGHT, chart.api) { series ->
            this.series.clear()
            this.series.add(series)

//            realtimeDataJob = lifecycleScope.launchWhenResumed {
//                viewModel.seriesFlow.collect {
//                    this@RealTimeEmulationFragment.series.lastOrNull()?.update(it)
//                }
//            }
        }
    }

    private fun createSeriesWithData(
        data: List<SeriesData>,
        priceScale: PriceScaleId,
        chartApi: ChartApi,
        onSeriesCreated: (SeriesApi) -> Unit
    ) {
        chartApi.addCandlestickSeries(
            options = candlestickSeriesOptions {
                wickUpColor = ContextCompat.getColor(context, R.color.green).toIntColor()
                upColor = ContextCompat.getColor(context, R.color.green).toIntColor()
                wickDownColor = ContextCompat.getColor(context, R.color.red).toIntColor()
                downColor = ContextCompat.getColor(context, R.color.red).toIntColor()
            },
            onSeriesCreated = { api ->
                api.setData(data)
                onSeriesCreated(api)
            }
        )
    }

    private fun showProgress(show: Boolean) {
        this.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        this.chart.visibility = if (!show) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}

