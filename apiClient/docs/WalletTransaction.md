
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
**action** | [**ActionEnum**](#ActionEnum) |  |  [optional]
**destinationId** | [**UUID**](UUID.md) |  |  [optional]
**destinationType** | [**DestinationTypeEnum**](#DestinationTypeEnum) |  |  [optional]
**destinationCurrency** | [**DestinationCurrencyEnum**](#DestinationCurrencyEnum) |  |  [optional]


<a name="SourceTypeEnum"></a>
## Enum: SourceTypeEnum
Name | Value
---- | -----
WALLET | &quot;Wallet&quot;
PROGRAM | &quot;Program&quot;
FUND | &quot;Fund&quot;
PROGRAMREQUEST | &quot;ProgramRequest&quot;
WITHDRAWALREQUEST | &quot;WithdrawalRequest&quot;
PAYMENTTRANSACTION | &quot;PaymentTransaction&quot;


<a name="SourceCurrencyEnum"></a>
## Enum: SourceCurrencyEnum
Name | Value
---- | -----
GVT | &quot;GVT&quot;
UNDEFINED | &quot;Undefined&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
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
PROGRAMREQUESTINVEST | &quot;ProgramRequestInvest&quot;
PROGRAMREQUESTWITHDRAWAL | &quot;ProgramRequestWithdrawal&quot;
PROGRAMREQUESTCANCEL | &quot;ProgramRequestCancel&quot;


<a name="DestinationTypeEnum"></a>
## Enum: DestinationTypeEnum
Name | Value
---- | -----
WALLET | &quot;Wallet&quot;
PROGRAM | &quot;Program&quot;
FUND | &quot;Fund&quot;
PROGRAMREQUEST | &quot;ProgramRequest&quot;
WITHDRAWALREQUEST | &quot;WithdrawalRequest&quot;
PAYMENTTRANSACTION | &quot;PaymentTransaction&quot;


<a name="DestinationCurrencyEnum"></a>
## Enum: DestinationCurrencyEnum
Name | Value
---- | -----
GVT | &quot;GVT&quot;
UNDEFINED | &quot;Undefined&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;



