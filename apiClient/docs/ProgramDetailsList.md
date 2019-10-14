# ProgramDetailsList

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**creationDate** | [**DateTime**](DateTime.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**level** | **Integer** |  |  [optional]
**levelProgress** | **Double** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**availableToInvest** | **Double** |  |  [optional]
**chart** | [**List&lt;SimpleChartPoint&gt;**](SimpleChartPoint.md) |  |  [optional]
**tags** | [**List&lt;ProgramTag&gt;**](ProgramTag.md) |  |  [optional]

<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
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
