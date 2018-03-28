
# InvestmentProgramDashboardManager

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**level** | **Integer** |  |  [optional]
**logo** | **String** |  |  [optional]
**balance** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**tradesCount** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**investorsCount** | **Integer** |  |  [optional]
**isEnabled** | **Boolean** |  |  [optional]
**startOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**profitTotal** | **Double** |  |  [optional]
**profitTotalGvt** | **Double** |  |  [optional]
**profitCurrent** | **Double** |  |  [optional]


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;



