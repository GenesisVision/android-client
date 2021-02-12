package vision.genesis.clientapp.feature.main.terminal.market_trades;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.BackpressureOverflow;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.GenesisVisionApplication;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.managers.TerminalManager;
import vision.genesis.clientapp.model.terminal.binance_api.TradeModel;
import vision.genesis.clientapp.model.terminal.binance_socket.NewTradeModel;
import vision.genesis.clientapp.net.ApiErrorResolver;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 05/02/2021.
 */

public class MarketTradesView extends RelativeLayout
{
	private static final Integer LIMIT = 50;

	@Inject
	public TerminalManager terminalManager;

	@BindView(R.id.group_trades)
	public LinearLayout tradesGroup;

	public Subscription getTradesSubscription;

	public Subscription tradesUpdateSubscription;

	private Unbinder unbinder;

	private String symbol = "";

	private ArrayList<TradeModel> trades = new ArrayList<>();

	private Double previousPrice = 0.0;

	public MarketTradesView(Context context) {
		super(context);
		initView();
	}

	public MarketTradesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MarketTradesView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		inflate(getContext(), R.layout.view_market_trades, this);

		unbinder = ButterKnife.bind(this);

		GenesisVisionApplication.getComponent().inject(this);

		onSymbolChanged();
	}

	public void onDestroy() {
		unsubscribeFromUpdates();

		if (unbinder != null) {
			unbinder.unbind();
			unbinder = null;
		}
	}

	private void unsubscribeFromUpdates() {
		if (getTradesSubscription != null) {
			getTradesSubscription.unsubscribe();
		}
		if (tradesUpdateSubscription != null) {
			tradesUpdateSubscription.unsubscribe();
		}
	}

	public void onResume() {
		if (tradesUpdateSubscription == null || tradesUpdateSubscription.isUnsubscribed()) {
			onSymbolChanged();
		}
	}

	private void onSymbolChanged() {
		unsubscribeFromUpdates();
		this.trades = new ArrayList<>();
		getTrades();
		subscribeToTradesUpdates();
	}

	private void getTrades() {
		if (symbol != null && !symbol.isEmpty() && terminalManager != null) {
			if (getTradesSubscription != null) {
				getTradesSubscription.unsubscribe();
			}
			getTradesSubscription = terminalManager.getTrades(symbol, LIMIT)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleGetTradesSuccess,
							this::handleGetTradesError);
		}
	}

	private void handleGetTradesSuccess(List<TradeModel> response) {
		getTradesSubscription.unsubscribe();

		ArrayList<TradeModel> tradesToAdd = new ArrayList<>();
		if (!trades.isEmpty()) {
			TradeModel first = trades.get(0);

			for (TradeModel trade : response) {
				if (trade.getTime().isBefore(first.getTime())) {
					tradesToAdd.add(trade);
				}
			}
		}
		else {
			tradesToAdd.addAll(response);
		}
		trades.addAll(0, tradesToAdd);
		setTrades(trades);
	}

	private void handleGetTradesError(Throwable throwable) {
		getTradesSubscription.unsubscribe();

		ApiErrorResolver.resolveErrors(throwable, this::showToast);
	}

	private void showToast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}

	private void subscribeToTradesUpdates() {
		if (terminalManager != null && symbol != null && !symbol.isEmpty()) {
			if (tradesUpdateSubscription != null) {
				tradesUpdateSubscription.unsubscribe();
			}
			tradesUpdateSubscription = terminalManager.getTradeSubject(symbol)
					.subscribeOn(Schedulers.newThread())
					.onBackpressureBuffer(10, () -> {
					}, BackpressureOverflow.ON_OVERFLOW_DROP_OLDEST)
					.onBackpressureDrop()
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::onTradesUpdate, this::onTradesUpdateError);
		}
	}

	private void onTradesUpdate(NewTradeModel update) {
		TradeModel newTrade = TradeModel.from(update);
		trades.add(newTrade);
		if (getTradesSubscription != null && getTradesSubscription.isUnsubscribed()) {
			addTrade(newTrade);
		}
		if (trades.size() > LIMIT) {
			trades.removeAll(trades.subList(0, trades.size() - LIMIT - 1));
		}
	}

	private void onTradesUpdateError(Throwable throwable) {
		throwable.printStackTrace();
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
		onSymbolChanged();
	}

	private void setTrades(ArrayList<TradeModel> trades) {
		tradesGroup.removeAllViews();

		for (TradeModel trade : trades) {
			addTrade(trade);
		}
	}

	private void addTrade(TradeModel trade) {
		MarketTradeItemView view = new MarketTradeItemView(getContext());
		view.setTrade(trade, trade.getPrice() < previousPrice);
		tradesGroup.addView(view, 0);
		if (tradesGroup.getChildCount() > LIMIT) {
			tradesGroup.removeViews(LIMIT, tradesGroup.getChildCount() - LIMIT);
		}

		previousPrice = trade.getPrice();
	}
}
