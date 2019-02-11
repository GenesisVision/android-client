
# TransactionDetatils

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currencyFrom** | [**CurrencyFromEnum**](#CurrencyFromEnum) |  |  [optional]
**currencyTo** | [**CurrencyToEnum**](#CurrencyToEnum) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**programId** | [**UUID**](UUID.md) |  |  [optional]
**foundId** | [**UUID**](UUID.md) |  |  [optional]
**name** | **String** |  |  [optional]
**logo** | **String** |  |  [optional]
**entryFee** | **Double** |  |  [optional]
**gvCommission** | **Double** |  |  [optional]
**exitFee** | **Double** |  |  [optional]
**description** | **String** |  |  [optional]
**descriptionUrl** | **String** |  |  [optional]
**fromAddress** | **String** |  |  [optional]
**amountFrom** | **Double** |  |  [optional]
**amountTo** | **Double** |  |  [optional]
**rateValue** | **Double** |  |  [optional]


<a name="CurrencyFromEnum"></a>
## Enum: CurrencyFromEnum
Name | Value
---- | -----
USD | &quot;USD&quot;
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
EUR | &quot;EUR&quot;


<a name="CurrencyToEnum"></a>
## Enum: CurrencyToEnum
Name | Value
---- | -----
USD | &quot;USD&quot;
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
EUR | &quot;EUR&quot;


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
INVESTINGPROGRAM | &quot;InvestingProgram&quot;
INVESTINGFUND | &quot;InvestingFund&quot;
WITHDRAWALPROGRAM | &quot;WithdrawalProgram&quot;
WITHDRAWALFUND | &quot;WithdrawalFund&quot;
EXTERNALWITHDRAWAL | &quot;ExternalWithdrawal&quot;
EXTERNALDEPOSIT | &quot;ExternalDeposit&quot;



