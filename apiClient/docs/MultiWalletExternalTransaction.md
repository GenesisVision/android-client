
# MultiWalletExternalTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**logo** | **String** |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**amount** | **Double** |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**status** | **String** |  |  [optional]
**isEnableActions** | **Boolean** |  |  [optional]
**statusUrl** | **String** |  |  [optional]


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
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
ALL | &quot;All&quot;
DEPOSIT | &quot;Deposit&quot;
WITHDRAWAL | &quot;Withdrawal&quot;



