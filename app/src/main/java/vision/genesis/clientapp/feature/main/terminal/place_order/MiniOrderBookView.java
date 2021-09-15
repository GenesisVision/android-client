package vision.genesis.clientapp.feature.main.terminal.place_order;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.main.terminal.order_book.DepthItemView;
import vision.genesis.clientapp.feature.main.terminal.order_book.OrderBookView;
import vision.genesis.clientapp.model.terminal.binance_socket.TickerModel;
import vision.genesis.clientapp.utils.StringFormatUtil;
import vision.genesis.clientapp.utils.ThemeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 18/02/2021.
 */

public class MiniOrderBookView extends OrderBookView implements DepthItemView.OnPriceSelectedListener
{
	public interface OnPriceSelectedListener
	{
		void onPriceSelected(Double price);
	}

	@BindView(R.id.price)
	public TextView price;

	private Subscription tickerUpdateSubscription;

	private OnPriceSelectedListener listener;

	private int colorTextPrimary;

	private int colorGreen;

	private int colorRed;

	private double previousPrice = 0.0;

	public MiniOrderBookView(Context context) {
		super(context);
	}

	public MiniOrderBookView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MiniOrderBookView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@OnClick(R.id.price)
	public void onCurrentPriceClicked() {
		OnPriceSelected(previousPrice);
	}

	@Override
	protected void inflateView() {
		inflate(getContext(), R.layout.view_mini_order_book, this);
		VIEWS_COUNT = 5;
		colorTextPrimary = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorTextPrimary);
		colorGreen = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorGreen);
		colorRed = ThemeUtil.getColorByAttrId(getContext(), R.attr.colorRed);
	}

	public void setOnPriceSelectedListener(OnPriceSelectedListener listener) {
		this.listener = listener;
	}

	@Override
	protected void addAskView(DepthItemView askView) {
		asksGroup.addView(askView, 0);
	}

	@Override
	protected DepthItemView createDepthItemView(String type) {
		DepthItemView view = new DepthItemView(getContext());
		view.initView(type, true);
		view.setListener(this);
		return view;
	}

	@Override
	public void OnPriceSelected(Double price) {
		if (listener != null) {
			listener.onPriceSelected(price);
		}
	}

	@Override
	public void onDestroy() {
		if (tickerUpdateSubscription != null) {
			tickerUpdateSubscription.unsubscribe();
		}

		super.onDestroy();
	}

	@Override
	public void onResume() {
		if (tickerUpdateSubscription == null || tickerUpdateSubscription.isUnsubscribed()) {
			subscribeToTickerUpdates();
		}

		super.onResume();
	}

	@Override
	protected void onSymbolChanged() {
		subscribeToTickerUpdates();

		super.onSymbolChanged();
	}

	private void subscribeToTickerUpdates() {
		if (terminalManager != null && symbol != null) {
			if (tickerUpdateSubscription != null) {
				tickerUpdateSubscription.unsubscribe();
			}
			tickerUpdateSubscription = terminalManager.getTickerSubject(symbol)
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(this::handleTickerUpdate, error -> {
					});
		}
	}

	private void handleTickerUpdate(TickerModel ticker) {
		if (ticker.getLastPrice() != null) {
			price.setText(StringFormatUtil.formatAmount(ticker.getLastPrice()));

			int color = colorTextPrimary;
			if (previousPrice != 0.0) {
				if (ticker.getLastPrice() > previousPrice) {
					color = colorGreen;
				}
				else if (ticker.getLastPrice() < previousPrice) {
					color = colorRed;
				}
			}
			this.price.setTextColor(color);

			previousPrice = ticker.getLastPrice();
		}
	}
}
