
# InvestmentProgramBuyToken

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**manager** | [**ProfilePublicViewModel**](ProfilePublicViewModel.md) |  |  [optional]
**level** | **Integer** |  |  [optional]
**title** | **String** |  |  [optional]
**startOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**endOfPeriod** | [**DateTime**](DateTime.md) |  |  [optional]
**gvtRate** | **Double** |  |  [optional]
**gvtWalletAmount** | **Double** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**availableInvestments** | **Double** |  |  [optional]
**rateCacheId** | [**UUID**](UUID.md) |  |  [optional]


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



