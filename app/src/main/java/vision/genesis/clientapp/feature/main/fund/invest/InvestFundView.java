package vision.genesis.clientapp.feature.main.fund.invest;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import io.swagger.client.model.WalletData;
import vision.genesis.clientapp.model.CurrencyEnum;
import vision.genesis.clientapp.model.FundRequest;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 25/10/2018.
 */

interface InvestFundView extends MvpView
{
	void setMinInvestmentAmount(Double minInvestmentAmount);

	void setWalletsFrom(List<WalletData> wallets);

	void setWalletFrom(WalletData selectedWalletFrom, CurrencyEnum baseCurrency);

	void setAmount(String amountText);

	void setAmountBase(String amountBaseString);

	void setEntryFee(String entryFeeText);

	void setGvCommission(String gvCommission);

	void setInvestmentAmount(String investmentAmount);

	void setContinueButtonEnabled(boolean enabled);

	void showConfirmDialog(FundRequest fundRequest);

	void showProgress(boolean show);

	void showAmountProgress(boolean show);

	void showSnackbarMessage(String message);

	void finishActivity();
}
