
# NewProgramRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**logo** | **String** |  |  [optional]
**tradingServerId** | [**UUID**](UUID.md) |  |  [optional]
**periodLength** | **Integer** |  |  [optional]
**successFee** | **Double** |  |  [optional]
**entryFee** | **Double** |  |  [optional]
**stopOutLevel** | **Double** |  |  [optional]
**depositAmount** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**leverage** | **Integer** |  |  [optional]


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



