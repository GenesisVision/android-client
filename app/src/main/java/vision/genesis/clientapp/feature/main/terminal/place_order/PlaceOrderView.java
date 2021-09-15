package vision.genesis.clientapp.feature.main.terminal.place_order;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 16/02/2021.
 */

interface PlaceOrderView extends MvpView
{
	void setSelectedSymbol(String symbol);

	void setAvailable(String availableText);

	void setBaseQuoteAssets(String baseAsset, String quoteAsset);

	void selectOperationType(String operationType);

	void setOrderTypeOptions(ArrayList<String> orderTypeOptions);

	void setOrderType(String text, Integer position);


	void showMarketPrice(boolean show);

	void showPrice(boolean show);

	void showStop(boolean show);

	void showLimit(boolean show);


	void setLotFilter(Double maxQty, int digitsCount);

	void setPriceFilter(Double maxPrice, int digitsCount);


	void setPrice(String text);

	void setStop(String text);

	void setLimit(String text);

	void setAmount(String text);

	void setPercent(int percent);

	void setTotal(String text);


	void setAmountError(boolean isError);

	void setTotalError(boolean isError);

	void setPercentSelectEnabled(boolean enabled);


	void showProgressBarButton(boolean show);

	void showSnackbarMessage(String message);

	void showProgressBar(boolean show);

	void setOpenOrdersCount(Integer count);

	void setOrderHistoryCount(Integer count);

	void setTradeHistoryCount(Integer count);
}
