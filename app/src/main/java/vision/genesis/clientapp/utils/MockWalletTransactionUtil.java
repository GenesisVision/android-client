package vision.genesis.clientapp.utils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.swagger.client.model.TransactionsFilter;
import io.swagger.client.model.WalletTransaction;
import io.swagger.client.model.WalletTransactionsViewModel;

/**
 * GenesisVision
 * Created by Vitaly on 2/2/18.
 */

public class MockWalletTransactionUtil
{
	public static WalletTransactionsViewModel getTransactionsModel(TransactionsFilter filter) {
		WalletTransactionsViewModel model = new WalletTransactionsViewModel();
		List<WalletTransaction> transactionList = new ArrayList<>();
		for (Integer i = 0; i < filter.getTake(); i++) {
			WalletTransaction transaction = new WalletTransaction();
			transaction.setDate(DateTime.now());
			transaction.setType(getRandomType());
			transaction.setAmount(getRandomAmount());

			transactionList.add(transaction);
		}

		model.setTransactions(transactionList);
		model.setTotal(transactionList.size());
		return model;
	}

	private static WalletTransaction.TypeEnum getRandomType() {
		return WalletTransaction.TypeEnum.values()[new Random().nextInt(5)];
	}

	private static double getRandomAmount() {
		return new Random().nextDouble() * 1000 - 500;
	}
}
