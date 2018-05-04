
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
**isInvestEnable** | **Boolean** |  |  [optional]
**isWithdrawEnable** | **Boolean** |  |  [optional]
**token** | [**Token**](Token.md) |  |  [optional]
**ownBalance** | **Double** |  |  [optional]
**minAccountBalanceUsd** | **Double** |  |  [optional]
**minAccountBalance** | **Double** |  |  [optional]
**login** | **String** |  |  [optional]
**canCloseProgram** | **Boolean** |  |  [optional]
**canClosePeriod** | **Boolean** |  |  [optional]
**isFavorite** | **Boolean** |  |  [optional]
**isTournament** | **Boolean** |  |  [optional]
**roundNumber** | **Integer** |  |  [optional]
**place** | **Integer** |  |  [optional]


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;



