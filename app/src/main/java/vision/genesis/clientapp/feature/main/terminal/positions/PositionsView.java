package vision.genesis.clientapp.feature.main.terminal.positions;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import io.swagger.client.model.BinanceRawFuturesPosition;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 24/02/2021.
 */

interface PositionsView extends MvpView
{
	void setPositions(List<BinanceRawFuturesPosition> positions);

	void showSnackbarMessage(String message);

	@StateStrategyType(AddToEndSingleStrategy.class)
	void showProgress(boolean show);

	void showChangePositionMargin(BinanceRawFuturesPosition position);

	void showTpSl(BinanceRawFuturesPosition position);

	void showClosePosition(BinanceRawFuturesPosition position);
}
