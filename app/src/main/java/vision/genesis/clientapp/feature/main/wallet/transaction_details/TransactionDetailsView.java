package vision.genesis.clientapp.feature.main.wallet.transaction_details;

import com.arellomobile.mvp.MvpView;

import io.swagger.client.model.TransactionDetails;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/03/2019.
 */

interface TransactionDetailsView extends MvpView
{
	void setDetails(TransactionDetails transactionDetails);

	void showProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
