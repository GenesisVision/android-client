package vision.genesis.clientapp.utils;

import java.util.List;

import io.swagger.client.model.BinanceRawFuturesBracket;
import io.swagger.client.model.BinanceRawFuturesPosition;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2022.
 */
public class PositionsHelper
{
	public static double calculateUnrealisedPnl(BinanceRawFuturesPosition position) {
		float direction = position.getQuantity() < 0 ? -1 : 1;
		return Math.abs(position.getQuantity()) * direction * (position.getMarkPrice() - position.getEntryPrice());
	}

	public static double calculateRealisedPnl(Double entryPrice, Double exitPrice, Double quantity) {
		float direction = quantity < 0 ? -1 : 1;
		if (direction > 0) {
			return (exitPrice - entryPrice) * Math.abs(quantity);
		}
		return (entryPrice - exitPrice) * Math.abs(quantity);
	}

	public static double calculateRoe(Double quantity, Double pnl, Integer leverage, Double markPrice) {
		double imr = 1 / (float) leverage;
		return pnl / (Math.abs(quantity) * markPrice * imr);
	}

	public static double calculateCrossPositionMaintMargin(BinanceRawFuturesPosition position, List<BinanceRawFuturesBracket> futuresBrackets) {
		double notionalSize = Math.abs(position.getQuantity() * position.getMarkPrice());
		BinanceRawFuturesBracket currentBracket = futuresBrackets.get(0);
		for (BinanceRawFuturesBracket bracket : futuresBrackets) {
			if (bracket.getFloor() <= notionalSize) {
				currentBracket = bracket;
				break;
			}
		}

		double maintenanceMargin = notionalSize * currentBracket.getMaintenanceMarginRatio() - currentBracket.getMaintAmount();
//		double margin = notionalSize / position.getLeverage();
//		double pnl = PositionsHelper.calculateUnrealisedPnl(position);
//		double marginBalance = margin + pnl;
//		return (maintenanceMargin / marginBalance) * 100;
		return maintenanceMargin;
	}

	public static Double calculateIsolatedPositionMarginRatio(BinanceRawFuturesPosition position, List<BinanceRawFuturesBracket> futuresBrackets) {
		double notionalSize = Math.abs(position.getQuantity() * position.getMarkPrice());
		BinanceRawFuturesBracket currentBracket = futuresBrackets.get(0);
		for (BinanceRawFuturesBracket bracket : futuresBrackets) {
			if (bracket.getFloor() <= notionalSize) {
				currentBracket = bracket;
				break;
			}
		}

		double maintenanceMargin = notionalSize * currentBracket.getMaintenanceMarginRatio() - currentBracket.getMaintAmount();
		double margin = position.getIsolatedMargin() - position.getUnrealizedPnL();
		double pnl = PositionsHelper.calculateUnrealisedPnl(position);
		double marginBalance = margin + pnl;
		return (maintenanceMargin / marginBalance) * 100;
	}
}
