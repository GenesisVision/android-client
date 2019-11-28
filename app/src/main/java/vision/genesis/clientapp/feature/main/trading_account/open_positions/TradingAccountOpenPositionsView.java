package vision.genesis.clientapp.feature.main.trading_account.open_positions;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.OrderModel;
import vision.genesis.clientapp.ui.common.SimpleSectionedRecyclerViewAdapter;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 28/11/2019.
 */

interface TradingAccountOpenPositionsView extends MvpView
{
	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void setOpenPositions(List<OrderModel> trades, List<SimpleSectionedRecyclerViewAdapter.Section> sections);

	void showOpenPositionDetails(OrderModel openPosition);

	void showSnackbarMessage(String message);
}
