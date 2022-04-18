package vision.genesis.clientapp.utils;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.BinanceFuturesMarginType;
import io.swagger.client.model.BinancePositionSide;
import io.swagger.client.model.BinanceRawFuturesBracket;
import io.swagger.client.model.BinanceRawFuturesPosition;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 21/03/2022.
 */
public class PositionsHelper
{
	public static Double calculateUnrealisedPnl(BinanceRawFuturesPosition position) {
		float direction = position.getQuantity() < 0 ? -1 : 1;
		return Math.abs(position.getQuantity()) * direction * (position.getMarkPrice() - position.getEntryPrice());
	}

	public static Double calculateRealisedPnl(Double entryPrice, Double exitPrice, Double quantity) {
		float direction = quantity < 0 ? -1 : 1;
		if (direction > 0) {
			return (exitPrice - entryPrice) * Math.abs(quantity);
		}
		return (entryPrice - exitPrice) * Math.abs(quantity);
	}

	public static Double calculateRoe(Double quantity, Double pnl, Integer leverage, Double markPrice) {
		double imr = 1 / (float) leverage;
		return pnl / (Math.abs(quantity) * markPrice * imr);
	}

	public static Double calculateCrossPositionMaintMargin(BinanceRawFuturesPosition position, List<BinanceRawFuturesBracket> futuresBrackets) {
		double maintenanceMargin = calculateMaintMargin(position, futuresBrackets);
//		double margin = notionalSize / position.getLeverage();
//		double pnl = PositionsHelper.calculateUnrealisedPnl(position);
//		double marginBalance = margin + pnl;
//		return (maintenanceMargin / marginBalance) * 100;
		return maintenanceMargin;
	}

	public static Double calculateIsolatedPositionMarginRatio(BinanceRawFuturesPosition position, List<BinanceRawFuturesBracket> futuresBrackets) {
		double maintenanceMargin = calculateMaintMargin(position, futuresBrackets);
		double margin = position.getIsolatedMargin() - position.getUnrealizedPnL();
		double pnl = PositionsHelper.calculateUnrealisedPnl(position);
		double marginBalance = margin + pnl;
		return (maintenanceMargin / marginBalance) * 100;
	}

	public static Double calculateMaintMargin(BinanceRawFuturesPosition position, List<BinanceRawFuturesBracket> futuresBrackets) {
		double notionalSize = Math.abs(position.getQuantity() * position.getMarkPrice());
		BinanceRawFuturesBracket currentBracket = futuresBrackets.get(0);
		for (BinanceRawFuturesBracket bracket : futuresBrackets) {
			if (bracket.getFloor() <= notionalSize) {
				currentBracket = bracket;
				break;
			}
		}

		return notionalSize * currentBracket.getMaintenanceMarginRatio() - currentBracket.getMaintAmount();
	}

	public static Double calculateMaxRemovable(Double entryPrice,
	                                           Double markPrice,
	                                           Double quantity,
	                                           Integer leverage,
	                                           Double isolatedWallet,
	                                           Double isolatedMaintMargin) {
		double imr = 1 / leverage;

		return Math.max(
				0,
				Math.min(
						isolatedWallet - isolatedMaintMargin,
						isolatedWallet +
								quantity * (markPrice - entryPrice) -
								markPrice * Math.abs(quantity) * imr
				)
		);
	}

	public static Double calculateIsolatedLiquidationPrice(Double entryPrice, Double quantity, Double isolatedWallet, Double maintAmount, Double maintMarginRate, BinancePositionSide positionSide) {
		double wb = isolatedWallet;
		double cumB = positionSide.equals(BinancePositionSide.BOTH) ? maintAmount : 0;
		double cumL = positionSide.equals(BinancePositionSide.LONG) ? maintAmount : 0;
		double cumS = positionSide.equals(BinancePositionSide.SHORT) ? maintAmount : 0;
		double side1Both = positionSide.equals(BinancePositionSide.BOTH) ? (quantity > 0 ? 1 : -1) : 0;
		double position1Both = positionSide.equals(BinancePositionSide.BOTH) ? Math.abs(quantity) : 0;
		double ep1Both = positionSide.equals(BinancePositionSide.BOTH) ? entryPrice : 0;
		double position1Long = positionSide.equals(BinancePositionSide.LONG) ? Math.abs(quantity) : 0;
		double ep1Long = positionSide.equals(BinancePositionSide.LONG) ? entryPrice : 0;
		double position1Short = positionSide.equals(BinancePositionSide.SHORT) ? Math.abs(quantity) : 0;
		double ep1Short = positionSide.equals(BinancePositionSide.SHORT) ? entryPrice : 0;
		double MMRb = positionSide.equals(BinancePositionSide.BOTH) ? maintMarginRate : 0;
		double MMRl = positionSide.equals(BinancePositionSide.LONG) ? maintMarginRate : 0;
		double MMRs = positionSide.equals(BinancePositionSide.SHORT) ? maintMarginRate : 0;

		return (
				(wb -
						cumB +
						cumL +
						cumS -
						side1Both * position1Both * ep1Both -
						position1Long * ep1Long +
						position1Short * ep1Short) /
						(position1Both * MMRb +
								position1Long * MMRl +
								position1Short * MMRs -
								side1Both * position1Both -
								position1Long +
								position1Short)
		);
	}

	public static Double getMaintAmount(BinanceRawFuturesPosition position, ArrayList<BinanceRawFuturesBracket> futuresBrackets) {
		double notionalSize = Math.abs(position.getQuantity() * position.getMarkPrice());
		BinanceRawFuturesBracket currentBracket = futuresBrackets.get(0);
		for (BinanceRawFuturesBracket bracket : futuresBrackets) {
			if (bracket.getFloor() <= notionalSize) {
				currentBracket = bracket;
				break;
			}
		}

		return currentBracket.getMaintAmount();
	}

	public static Double getMaintMarginRatio(BinanceRawFuturesPosition position, ArrayList<BinanceRawFuturesBracket> futuresBrackets) {
		double notionalSize = Math.abs(position.getQuantity() * position.getMarkPrice());
		BinanceRawFuturesBracket currentBracket = futuresBrackets.get(0);
		for (BinanceRawFuturesBracket bracket : futuresBrackets) {
			if (bracket.getFloor() <= notionalSize) {
				currentBracket = bracket;
				break;
			}
		}

		return currentBracket.getMaintenanceMarginRatio();
	}

	public static Double calculateMargin(BinanceRawFuturesPosition position) {
		return position.getMarginType().equals(BinanceFuturesMarginType.CROSS)
				? (position.getMarkPrice() * position.getQuantity()) / position.getLeverage()
				: position.getIsolatedMargin() - position.getUnrealizedPnL();
	}
}
