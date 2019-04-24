
# WalletTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**amount** | **Double** |  |  [optional]
**amountConverted** | **Double** |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**number** | **Long** |  |  [optional]
**sourceId** | [**UUID**](UUID.md) |  |  [optional]
**sourceType** | [**SourceTypeEnum**](#SourceTypeEnum) |  |  [optional]
**sourceCurrency** | [**SourceCurrencyEnum**](#SourceCurrencyEnum) |  |  [optional]
**sourceProgramInfo** | [**ProgramInfo**](ProgramInfo.md) |  |  [optional]
**sourceBlockchainInfo** | [**BlockchainInfo**](BlockchainInfo.md) |  |  [optional]
**sourceWithdrawalInfo** | [**WithdrawalInfo**](WithdrawalInfo.md) |  |  [optional]
**action** | [**ActionEnum**](#ActionEnum) |  |  [optional]
**information** | **String** |  |  [optional]
**destinationId** | [**UUID**](UUID.md) |  |  [optional]
**destinationType** | [**DestinationTypeEnum**](#DestinationTypeEnum) |  |  [optional]
**destinationCurrency** | [**DestinationCurrencyEnum**](#DestinationCurrencyEnum) |  |  [optional]
**destinationProgramInfo** | [**ProgramInfo**](ProgramInfo.md) |  |  [optional]
**destinationBlockchainInfo** | [**BlockchainInfo**](BlockchainInfo.md) |  |  [optional]
**destinationWithdrawalInfo** | [**WithdrawalInfo**](WithdrawalInfo.md) |  |  [optional]


<a name="SourceTypeEnum"></a>
## Enum: SourceTypeEnum
Name | Value
---- | -----
WALLET | &quot;Wallet&quot;
PROGRAM | &quot;Program&quot;
FUND | &quot;Fund&quot;
PROGRAMREQUEST | &quot;ProgramRequest&quot;
FUNDREQUEST | &quot;FundRequest&quot;
WITHDRAWALREQUEST | &quot;WithdrawalRequest&quot;
PAYMENTTRANSACTION | &quot;PaymentTransaction&quot;
BLOCKCHAIN | &quot;Blockchain&quot;
GENESISVISIONPLATFORM | &quot;GenesisVisionPlatform&quot;
SIGNALMASTERSETTING | &quot;SignalMasterSetting&quot;
SIGNALTRADINGACCOUNT | &quot;SignalTradingAccount&quot;


<a name="SourceCurrencyEnum"></a>
## Enum: SourceCurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USDT | &quot;USDT&quot;
XRP | &quot;XRP&quot;
BCH | &quot;BCH&quot;
LTC | &quot;LTC&quot;
DOGE | &quot;DOGE&quot;
BNB | &quot;BNB&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;


<a name="ActionEnum"></a>
## Enum: ActionEnum
Name | Value
---- | -----
TRANSFER | &quot;Transfer&quot;
PROGRAMOPEN | &quot;ProgramOpen&quot;
PROGRAMPROFIT | &quot;ProgramProfit&quot;
PROGRAMINVEST | &quot;ProgramInvest&quot;
PROGRAMWITHDRAWAL | &quot;ProgramWithdrawal&quot;
PROGRAMREFUNDPARTIALEXECUTION | &quot;ProgramRefundPartialExecution&quot;
PROGRAMREFUNDCLOSE | &quot;ProgramRefundClose&quot;
PROGRAMREFUNDSTOPOUT | &quot;ProgramRefundStopOut&quot;
PROGRAMREQUESTINVEST | &quot;ProgramRequestInvest&quot;
PROGRAMREQUESTWITHDRAWAL | &quot;ProgramRequestWithdrawal&quot;
PROGRAMREQUESTCANCEL | &quot;ProgramRequestCancel&quot;
PAYINGFEE | &quot;PayingFee&quot;
SIGNALSLAVESUBSCRIBE | &quot;SignalSlaveSubscribe&quot;
SIGNALMASTERRECEIVESUBSCRIPTION | &quot;SignalMasterReceiveSubscription&quot;


<a name="DestinationTypeEnum"></a>
## Enum: DestinationTypeEnum
Name | Value
---- | -----
WALLET | &quot;Wallet&quot;
PROGRAM | &quot;Program&quot;
FUND | &quot;Fund&quot;
PROGRAMREQUEST | &quot;ProgramRequest&quot;
FUNDREQUEST | &quot;FundRequest&quot;
WITHDRAWALREQUEST | &quot;WithdrawalRequest&quot;
PAYMENTTRANSACTION | &quot;PaymentTransaction&quot;
BLOCKCHAIN | &quot;Blockchain&quot;
GENESISVISIONPLATFORM | &quot;GenesisVisionPlatform&quot;
SIGNALMASTERSETTING | &quot;SignalMasterSetting&quot;
SIGNALTRADINGACCOUNT | &quot;SignalTradingAccount&quot;


<a name="DestinationCurrencyEnum"></a>
## Enum: DestinationCurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USDT | &quot;USDT&quot;
XRP | &quot;XRP&quot;
BCH | &quot;BCH&quot;
LTC | &quot;LTC&quot;
DOGE | &quot;DOGE&quot;
BNB | &quot;BNB&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;



