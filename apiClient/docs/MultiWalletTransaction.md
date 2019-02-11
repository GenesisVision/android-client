
# MultiWalletTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**currencyFrom** | [**CurrencyFromEnum**](#CurrencyFromEnum) |  |  [optional]
**currencyTo** | [**CurrencyToEnum**](#CurrencyToEnum) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**logoFrom** | **String** |  |  [optional]
**logoTo** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**amount** | **Double** |  |  [optional]


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
ALL | &quot;All&quot;
INVESTMENT | &quot;Investment&quot;
CONVERTING | &quot;Converting&quot;
WITHDRAWAL | &quot;Withdrawal&quot;
CLOSE | &quot;Close&quot;
OPEN | &quot;Open&quot;
FEE | &quot;Fee&quot;
PROFITS | &quot;Profits&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
CONFIRM | &quot;Confirm&quot;
PENDING | &quot;Pending&quot;
CANCELED | &quot;Canceled&quot;
ERROR | &quot;Error&quot;



